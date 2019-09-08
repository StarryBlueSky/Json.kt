@file:Suppress("KDocMissingDocumentation", "PublicApiImplicitType")

import com.adarshr.gradle.testlogger.theme.ThemeType
import com.jfrog.bintray.gradle.tasks.BintrayUploadTask
import org.jetbrains.dokka.gradle.DokkaTask
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

object ThirdpartyVersion {
    const val KotlinxSerializationRuntime = "0.12.0"

    // for testing
    const val Spek = "2.0.6"

    // for logging
    const val KotlinLogging = "1.7.6"
    const val Logback = "1.2.3"
    const val Jansi = "1.18"
}

plugins {
    kotlin("multiplatform") version "1.3.50"

    // For testing
    id("com.adarshr.test-logger") version "1.7.0"
    id("build-time-tracker") version "0.11.1"

    // For publishing
    `maven-publish`
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
    maven(url = "https://dl.bintray.com/spekframework/spek-dev")
}

kotlin {
    metadata {
        mavenPublication {
            artifactId = "${rootProject.name}-common"
        }
    }
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        mavenPublication {
            artifactId = rootProject.name
        }
    }
    js {
        compilations {
            named("main").configure {
                kotlinOptions {
                    metaInfo = true
                    sourceMap = true
                    verbose = true
                    moduleKind = "umd"
                }
            }
        }
    }

    sourceSets {
        named("commonMain").configure {
            dependencies {
                implementation(kotlin("stdlib-common"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${ThirdpartyVersion.KotlinxSerializationRuntime}")
            }
        }
        named("commonTest").configure {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                implementation("io.github.microutils:kotlin-logging-common:${ThirdpartyVersion.KotlinLogging}")
            }
        }

        named("jvmMain").configure {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${ThirdpartyVersion.KotlinxSerializationRuntime}")
            }
        }
        named("jvmTest").configure {
            dependencies {
                implementation(kotlin("test"))

                implementation("org.spekframework.spek2:spek-dsl-jvm:${ThirdpartyVersion.Spek}") {
                    exclude(group = "org.jetbrains.kotlin")
                }
                runtimeOnly("org.spekframework.spek2:spek-runner-junit5:${ThirdpartyVersion.Spek}") {
                    exclude(group = "org.junit.platform")
                    exclude(group = "org.jetbrains.kotlin")
                }

                implementation("io.github.microutils:kotlin-logging:${ThirdpartyVersion.KotlinLogging}")
                implementation("ch.qos.logback:logback-core:${ThirdpartyVersion.Logback}")
                implementation("ch.qos.logback:logback-classic:${ThirdpartyVersion.Logback}")
                implementation("org.fusesource.jansi:jansi:${ThirdpartyVersion.Jansi}")
            }
        }

        named("jsMain").configure {
            dependencies {
                implementation(kotlin("stdlib-js"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${ThirdpartyVersion.KotlinxSerializationRuntime}")
            }
        }
        named("jsTest").configure {
            dependencies {
                implementation(kotlin("test-js"))

                implementation("io.github.microutils:kotlin-logging-js:${ThirdpartyVersion.KotlinLogging}")
            }
        }

//        named("nativeMain").configure {
//            dependencies {
//                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$kotlinxSerializationVersion")
//            }
//        }
//        named("nativeTest").configure {
//        }
    }

    sourceSets.all {
        languageSettings.apiVersion = "1.3"
        languageSettings.languageVersion = "1.3"
        languageSettings.useExperimentalAnnotation("kotlin.Experimental")
        languageSettings.useExperimentalAnnotation("kotlin.contracts.ExperimentalContracts")
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

tasks.named<Test>("jvmTest") {
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
    skipDeprecated = true
    
    impliedPlatforms = mutableListOf("Common")
    kotlinTasks {
        emptyList()
    }
    sourceRoot {
        path = kotlin.sourceSets.getByName("commonMain").kotlin.srcDirs.first().toString()
        platforms = listOf("Common")
    }
    sourceRoot {
        path = kotlin.sourceSets.getByName("jvmMain").kotlin.srcDirs.first().toString()
        platforms = listOf("JVM")
    }
    sourceRoot {
        path = kotlin.sourceSets.getByName("jsMain").kotlin.srcDirs.first().toString()
        platforms = listOf("JS")
    }
}

val dokkaJavadoc = task<DokkaTask>("dokkaJavadoc") {
    // Maybe prefer "javadoc"?
    outputFormat = "html"
    outputDirectory = "$buildDir/javadoc"

    jdkVersion = 8
    includeNonPublic = false
    reportUndocumented = false
    skipEmptyPackages = true
    skipDeprecated = true
    
    impliedPlatforms = mutableListOf("Common")
    kotlinTasks {
        emptyList()
    }
    sourceRoot {
        path = kotlin.sourceSets.getByName("commonMain").kotlin.srcDirs.first().toString()
        platforms = listOf("Common")
    }
    sourceRoot {
        path = kotlin.sourceSets.getByName("jvmMain").kotlin.srcDirs.first().toString()
        platforms = listOf("JVM")
    }
    sourceRoot {
        path = kotlin.sourceSets.getByName("jsMain").kotlin.srcDirs.first().toString()
        platforms = listOf("JS")
    }
}

/*
 * Publishing
 */

val javadocJar = task<Jar>("javadocJar") {
    dependsOn(dokkaJavadoc)

    archiveClassifier.set("javadoc")
    from("$buildDir/javadoc")
}

val kdocJar = task<Jar>("kdocJar") {
    dependsOn(dokka)

    archiveClassifier.set("kdoc")
    from("$buildDir/kdoc")
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set(rootProject.name)
            description.set(packageDescription)
            url.set("https://github.com/$githubOrganizationName/$githubRepositoryName")
            licenses {
                license {
                    name.set("MIT Licence")
                    url.set("https://nephy.jp/license/mit")
                }
            }
            developers {
                developer {
                    name.set("Slash Nephy")
                    email.set("slash@nephy.jp")
                    organization.set("github")
                    organizationUrl.set("https://github.com/SlashNephy")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/$githubOrganizationName/$githubRepositoryName.git")
                developerConnection.set("scm:git:ssh://github.com:$githubOrganizationName/$githubRepositoryName.git")
                url.set("https://github.com/$githubOrganizationName/$githubRepositoryName/tree/master")
            }
        }

        artifact(javadocJar)
    }
}

val bintrayUsername by property()
val bintrayApiKey by property()

bintray {
    user = bintrayUsername
    key = bintrayApiKey
    publish = true
    override = true

    setPublications("metadata", "jvm", "js")

    pkg.apply {
        repo = if (isEAPBuild) "dev" else "stable"
        userOrg = githubOrganizationName.toLowerCase()

        name = packageName
        desc = packageDescription

        setLicenses("MIT")
        publicDownloadNumbers = true

        githubRepo = "$githubOrganizationName/$githubRepositoryName"
        websiteUrl = "https://github.com/$githubRepo"
        issueTrackerUrl = "$websiteUrl/issues"
        vcsUrl = "$websiteUrl.git"

        version.apply {
            name = project.version.toString()
            desc = "$packageName $name"
            released = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").format(ZonedDateTime.now())
            vcsTag = name
        }
    }
}

tasks.named<BintrayUploadTask>("bintrayUpload") {
    dependsOn("publishToMavenLocal")
}
