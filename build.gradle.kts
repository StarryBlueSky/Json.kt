@file:Suppress("KDocMissingDocumentation", "PublicApiImplicitType")

import com.adarshr.gradle.testlogger.theme.ThemeType
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.file.Files
import java.nio.file.Paths
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

val githubOrganizationName = "NephyProject"
val githubRepositoryName = "Json.kt"
val packageGroupId = "jp.nephy"
val packageName = "JsonKt"
val packageVersion = Version(5, 0, 0)
val packageDescription = "Json bindings for Kotlin"

plugins {
    kotlin("jvm") version "1.3.41"

    // For testing
    id("com.adarshr.test-logger") version "1.7.0"
    id("build-time-tracker") version "0.11.1"

    // For publishing
    id("maven-publish")
    id("com.jfrog.bintray") version "1.8.4"

    // For documentation
    id("org.jetbrains.dokka") version "0.9.18"
}

fun Project.property(key: String? = null) = object: ReadOnlyProperty<Project, String?> {
    override fun getValue(thisRef: Project, property: KProperty<*>): String? {
        val name = key ?: property.name
        return (properties[name] ?: System.getProperty(name) ?: System.getenv(name))?.toString()
    }
}

/*
 * Dependencies
 */

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://kotlin.bintray.com/kotlin-eap")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.1")
}

/*
 * Compilations
 */

tasks.named<KotlinCompile>("compileKotlin") {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-Xuse-experimental=kotlin.Experimental", "-Xuse-experimental=kotlin.contracts.ExperimentalContracts")
    }
}

tasks.named<KotlinCompile>("compileTestKotlin") {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf("-Xuse-experimental=kotlin.Experimental", "-Xuse-experimental=kotlin.contracts.ExperimentalContracts")
    }
}

/*
 * Versioning
 */

data class Version(val major: Int, val minor: Int, val patch: Int) {
    val label: String
        get() = "$major.$minor.$patch"
}

val isEAPBuild: Boolean
    get() = hasProperty("snapshot")

fun incrementBuildNumber(): Int {
    val buildNumberPath = Paths.get(buildDir.absolutePath, "build-number-${packageVersion.label}.txt")
    val buildNumber = if (Files.exists(buildNumberPath)) {
        buildNumberPath.toFile().readText().toIntOrNull()
    } else {
        null
    }?.coerceAtLeast(0)?.plus(1) ?: 1

    buildNumberPath.toFile().writeText(buildNumber.toString())

    return buildNumber
}

project.group = packageGroupId
project.version = if (isEAPBuild) {
    "${packageVersion.label}-eap-${incrementBuildNumber()}"
} else {
    packageVersion.label
}

/*
 * Tests
 */

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["shortenTaskNames"] = "false"
        }
    }
}

testlogger {
    theme = ThemeType.MOCHA
}

tasks.named<Test>("test") {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

/*
 * Documentation
 */

val dokka = tasks.named<DokkaTask>("dokka") {
    outputFormat = "html"
    outputDirectory = "$buildDir/kdoc"

    jdkVersion = 8
    includeNonPublic = false
    reportUndocumented = true
    skipEmptyPackages = true
    skipDeprecated = false
}

val dokkaJavadoc = task<DokkaTask>("dokkaJavadoc") {
    // Maybe prefer "javadoc"?
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"

    jdkVersion = 8
    includeNonPublic = false
    reportUndocumented = false
    skipEmptyPackages = true
    skipDeprecated = false
}

/*
 * Publishing
 */

val jar = tasks.named<Jar>("jar").get()

if (isEAPBuild) {
    jar.destinationDir.listFiles()?.forEach {
        it.delete()
    }
}

val sourcesJar = task<Jar>("sourcesJar") {
    classifier = "sources"
    from(sourceSets["main"].allSource)
}

val javadocJar = task<Jar>("javadocJar") {
    dependsOn(dokkaJavadoc)

    classifier = "javadoc"
    from("$buildDir/javadoc")
}

val kdocJar = task<Jar>("kdocJar") {
    dependsOn(dokka)

    classifier = "kdoc"
    from("$buildDir/kdoc")
}

publishing {
    publications {
        create<MavenPublication>("kotlin") {
            from(components.getByName("java"))

            artifact(sourcesJar)
            artifact(javadocJar)
        }
    }
}

val bintrayUsername by property()
val bintrayApiKey by property()

bintray {
    setPublications("kotlin")

    user = bintrayUsername
    key = bintrayApiKey
    publish = true
    override = true

    pkg.apply {
        repo = if (isEAPBuild) "dev" else "stable"
        userOrg = githubOrganizationName.toLowerCase()

        name = packageName
        desc = packageDescription

        setLicenses("MIT")
        publicDownloadNumbers = true

        githubRepo = "$githubOrganizationName/$githubRepositoryName"
        websiteUrl = "https://github.com/$githubOrganizationName/$githubRepositoryName"
        issueTrackerUrl = "https://github.com/$githubOrganizationName/$githubRepositoryName/issues"
        vcsUrl = "https://github.com/$githubOrganizationName/$githubRepositoryName.git"

        version.apply {
            name = project.version.toString()
            desc = "$packageName ${project.version}"
            released = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").format(ZonedDateTime.now())
            vcsTag = project.version.toString()
        }
    }
}

tasks.named<BintrayUploadTask>("bintrayUpload") {
    dependsOn("publishToMavenLocal")
}
