/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("KDocMissingDocumentation", "PublicApiImplicitType")

import com.adarshr.gradle.testlogger.theme.ThemeType
import org.gradle.kotlin.dsl.*
import org.jetbrains.dokka.gradle.DokkaTask
import java.nio.file.Files
import java.nio.file.Paths
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

val githubOrganizationName = "StarryBlueSky"
val githubRepositoryName = "Json.kt"
val packageGroupId = "blue.starry"
val packageName = "JsonKt"
val packageVersion = Version(5, 0, 0)
val packageDescription = "Json bindings for Kotlin"

object ThirdpartyVersion {
    const val KotlinxSerializationRuntime = "0.20.0"

    // for testing
    const val Spek = "2.0.10"

    // for logging
    const val KotlinLogging = "1.7.9"
    const val Logback = "1.2.3"
    const val Jansi = "1.18"
}

plugins {
    kotlin("multiplatform") version "1.3.72"

    // For testing
    id("com.adarshr.test-logger") version "2.0.0"
    id("build-time-tracker") version "0.11.1"

    // For publishing
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"

    // For documentation
    id("org.jetbrains.dokka") version "0.10.1"
}

fun Project.property(key: String? = null) = object: ReadOnlyProperty<Project, String?> {
    override fun getValue(thisRef: Project, property: KProperty<*>): String? {
        val name = key ?: property.name
        return (properties[name] ?: System.getProperty(name) ?: System.getenv(name))?.toString()
    }
}

data class Version(val major: Int, val minor: Int, val patch: Int) {
    val label: String
        get() = "$major.$minor.$patch"
}

// cannot be companion object
val isEAPBuild: Boolean
    get() = hasProperty("snapshot")

val buildNumber: Int
    get() {
        val path = Paths.get(buildDir.absolutePath, "build-number-${packageVersion.label}.txt")
        val number = if (Files.exists(path)) {
            path.toFile().readText().toIntOrNull()
        } else {
            null
        }?.coerceAtLeast(0)?.plus(1) ?: 1

        path.toFile().writeText(number.toString())

        return number
    }

/*
 * Dependencies
 */

repositories {
    mavenCentral()
    jcenter()
}

kotlin {
    metadata {
        // make a name of an artifact backward-compatible, default "-metadata"
        mavenPublication {
            artifactId = "${rootProject.name}-common"
        }
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        mavenPublication {
            // make a name of jvm artifact backward-compatible, default "-jvm"
            artifactId = rootProject.name
        }
    }
    js {
        nodejs()
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }

        compilations.all {
            kotlinOptions {
                metaInfo = true
                sourceMap = true
                verbose = true
                moduleKind = "umd"
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("stdlib-common"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${ThirdpartyVersion.KotlinxSerializationRuntime}")
                api("io.github.microutils:kotlin-logging-common:${ThirdpartyVersion.KotlinLogging}")
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        named("jvmMain") {
            dependencies {
                implementation(kotlin("stdlib"))
                implementation(kotlin("reflect"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:${ThirdpartyVersion.KotlinxSerializationRuntime}")
                implementation("io.github.microutils:kotlin-logging:${ThirdpartyVersion.KotlinLogging}")
            }
        }
        named("jvmTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit5"))

                implementation("org.spekframework.spek2:spek-dsl-jvm:${ThirdpartyVersion.Spek}")
                runtimeOnly("org.spekframework.spek2:spek-runner-junit5:${ThirdpartyVersion.Spek}")

                implementation("ch.qos.logback:logback-core:${ThirdpartyVersion.Logback}")
                implementation("ch.qos.logback:logback-classic:${ThirdpartyVersion.Logback}")
                implementation("org.fusesource.jansi:jansi:${ThirdpartyVersion.Jansi}")
            }
        }

        named("jsMain") {
            dependencies {
                implementation(kotlin("stdlib-js"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${ThirdpartyVersion.KotlinxSerializationRuntime}")
                implementation("io.github.microutils:kotlin-logging-js:${ThirdpartyVersion.KotlinLogging}")
            }
        }
        named("jsTest") {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }

//        named("nativeMain") {
//            dependencies {
//                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${ThirdpartyVersion.KotlinxSerializationRuntime}")
//            }
//        }
//        named("nativeTest") {
//        }
    }

    sourceSets.all {
        languageSettings.progressiveMode = true
        languageSettings.apiVersion = "1.3"
        languageSettings.languageVersion = "1.3"
        languageSettings.useExperimentalAnnotation("kotlin.Experimental")
        languageSettings.useExperimentalAnnotation("kotlin.contracts.ExperimentalContracts")
    }
}

/*
 * Tests
 */

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["barstyle"] = "ascii"
            options["shortenTaskNames"] = "false"
        }
    }
}

testlogger {
    theme = ThemeType.MOCHA
}

tasks {
    named<Test>("jvmTest") {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}

/*
 * Publishing
 */

project.group = packageGroupId
project.version = if (isEAPBuild) {
    "${packageVersion.label}-eap-${buildNumber}"
} else {
    packageVersion.label
}

tasks {
    dokka {
        outputFormat = "html"
        outputDirectory = "$buildDir/kdoc"

        configuration {
            jdkVersion = 8
            includeNonPublic = false
            reportUndocumented = true
            skipEmptyPackages = true
            skipDeprecated = true

//            sourceRoot {
//                path = kotlin.sourceSets.getByName("commonMain").kotlin.srcDirs.first().toString()
//                platforms = listOf("Common")
//            }
//            sourceRoot {
//                path = kotlin.sourceSets.getByName("jvmMain").kotlin.srcDirs.first().toString()
//                platforms = listOf("JVM")
//            }
//            sourceRoot {
//                path = kotlin.sourceSets.getByName("jsMain").kotlin.srcDirs.first().toString()
//                platforms = listOf("JS")
//            }
        }
    }

    register<Jar>("kdocJar") {
        val dokkaTask = getByName<DokkaTask>("dokka")
        from(dokkaTask.outputDirectory)
        dependsOn(dokkaTask)
        archiveClassifier.set("kdoc")
    }

    register<DokkaTask>("dokkaJavadoc") {
        outputFormat = "javadoc"
        outputDirectory = "$buildDir/javadoc"

        configuration {
            jdkVersion = 8
            includeNonPublic = false
            reportUndocumented = false
            skipEmptyPackages = true
            skipDeprecated = true

//            sourceRoot {
//                path = kotlin.sourceSets.getByName("commonMain").kotlin.srcDirs.first().toString()
//                platforms = listOf("Common")
//            }
//            sourceRoot {
//                path = kotlin.sourceSets.getByName("jvmMain").kotlin.srcDirs.first().toString()
//                platforms = listOf("JVM")
//            }
//            sourceRoot {
//                path = kotlin.sourceSets.getByName("jsMain").kotlin.srcDirs.first().toString()
//                platforms = listOf("JS")
//            }
        }
    }

    register<Jar>("javadocJar") {
        val dokkaTask = getByName<DokkaTask>("dokkaJavadoc")
        from(dokkaTask.outputDirectory)
        dependsOn(dokkaTask)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set(rootProject.name)
            description.set(packageDescription)
            url.set("https://github.com/$githubOrganizationName/$githubRepositoryName")
            licenses {
                license {
                    name.set("The MIT Licence")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    name.set("Nep")
                    email.set("spica@starry.blue")
                    organization.set("github")
                    organizationUrl.set("https://github.com/$githubOrganizationName")
                }
            }
            scm {
                connection.set("scm:git:git://github.com/$githubOrganizationName/$githubRepositoryName.git")
                developerConnection.set("scm:git:ssh://github.com:$githubOrganizationName/$githubRepositoryName.git")
                url.set("https://github.com/$githubOrganizationName/$githubRepositoryName/tree/master")
            }
        }

        artifact(tasks["javadocJar"])
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
        userOrg = "nephyproject"

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
