plugins {
    kotlin("multiplatform") version "1.5.0"

    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
    id("com.adarshr.test-logger") version "3.0.0"
    id("net.rdrei.android.buildtimetracker") version "0.11.0"

    `maven-publish`
    signing
    id("io.codearte.nexus-staging") version "0.30.0"

    id("org.jetbrains.dokka") version "1.4.32"
}

object Versions {
    const val KotlinxSerializationJson = "1.2.1"

    const val JUnit = "5.7.1"
    const val KotlinLogging = "2.0.6"
    const val Logback = "1.2.3"
    const val Jansi = "2.3.2"
}

object Libraries {
    const val KotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinxSerializationJson}"
    const val KotlinLogging = "io.github.microutils:kotlin-logging:${Versions.KotlinLogging}"

    const val JUnitJupiter = "org.junit.jupiter:junit-jupiter:${Versions.JUnit}"
    const val LogbackCore = "ch.qos.logback:logback-core:${Versions.Logback}"
    const val LogbackClassic = "ch.qos.logback:logback-classic:${Versions.Logback}"
    const val Jansi = "org.fusesource.jansi:jansi:${Versions.Jansi}"

    val ExperimentalAnnotations = setOf(
        "kotlin.Experimental",
        "kotlin.contracts.ExperimentalContracts"
    )
}

object Publications {
    const val GroupId = "blue.starry"
    const val OSSRHProfileGroupId = "blue.starry.jsonkt"
    const val Description = "Json bindings for Kotlin"
    const val GitHubUsername = "StarryBlueSky"
    const val GitHubRepository = "Json.kt"

    const val LicenseName = "The MIT Licence"
    const val LicenseUrl = "https://opensource.org/licenses/MIT"

    const val DeveloperId = "StarryBlueSky"
    const val DeveloperName = "The Starry Blue Sky"
    const val DeveloperEmail = "letter@starry.blue"
    const val DeveloperOrganization = "The Starry Blue Sky"
    const val DeveloperOrganizationUrl = "https://github.com/StarryBlueSky"

    const val MavenCentralStagingRepositoryUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
    const val MavenCentralSnapshotRepositoryUrl = "https://oss.sonatype.org/content/repositories/snapshots"
    const val GitHubPackagesRepositoryUrl = "https://maven.pkg.github.com/$GitHubUsername/$GitHubRepository"
}

object Env {
    const val Version = "VERSION"

    const val OSSRHProfileId = "OSSRH_PROFILE_ID"
    const val OSSRHUsername = "OSSRH_USERNAME"
    const val OSSRHPassword = "OSSRH_PASSWORD"

    const val GitHubUsername = "GITHUB_USERNAME"
    const val GitHubPassword = "GITHUB_PASSWORD"

    const val SigningKeyId = "SIGNING_KEYID"
    const val SigningKey = "SIGNING_KEY"
    const val SigningPassword = "SIGNING_PASSWORD"
}

/*
 * Dependencies
 */

repositories {
    mavenCentral()
}

kotlin {
    // explicitApi()

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("reflect"))

                api(Libraries.KotlinxSerializationJson)
                api(Libraries.KotlinLogging)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

        named("jvmMain") {
        }
        named("jvmTest") {
            dependencies {
                implementation(kotlin("test"))

                implementation(kotlin("test-junit5"))
                implementation(Libraries.JUnitJupiter)

                implementation(Libraries.LogbackCore)
                implementation(Libraries.LogbackClassic)
                implementation(Libraries.Jansi)
            }
        }

        named("jsMain") {
        }
        named("jsTest") {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }

    targets.all {
        compilations.all {
            kotlinOptions {
                apiVersion = "1.5"
                languageVersion = "1.5"
                allWarningsAsErrors = true
                verbose = true
            }
        }
    }

    sourceSets.all {
        languageSettings.progressiveMode = true

        Libraries.ExperimentalAnnotations.forEach {
            languageSettings.useExperimentalAnnotation(it)
        }
    }
}

/*
 * Tests
 */

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    ignoreFailures.set(true)
}

buildtimetracker {
    reporters {
        register("summary") {
            options["ordered"] = "true"
            options["barstyle"] = "ascii"
            options["shortenTaskNames"] = "false"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        showStandardStreams = true
        events("passed", "failed")
    }

    testlogger {
        theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA_PARALLEL
    }
}

/*
 * Publishing
 */

tasks {
    register<Jar>("kdocJar") {
        from(dokkaHtml)
        dependsOn(dokkaHtml)
        archiveClassifier.set("javadoc")
    }
}

publishing {
    repositories {
        maven {
            name = "Sonatype"
            url = uri(
                if (System.getenv(Env.Version).orEmpty().endsWith("-SNAPSHOT")) {
                    Publications.MavenCentralSnapshotRepositoryUrl
                } else {
                    Publications.MavenCentralStagingRepositoryUrl
                }
            )

            credentials {
                username = System.getenv(Env.OSSRHUsername)
                password = System.getenv(Env.OSSRHPassword)
            }
        }

        maven {
            name = "GitHubPackages"
            url = uri(Publications.GitHubPackagesRepositoryUrl)

            credentials {
                username = System.getenv(Env.GitHubUsername)
                password = System.getenv(Env.GitHubPassword)
            }
        }
    }

    publications.withType<MavenPublication> {
        groupId = Publications.GroupId
        artifactId = when (name) {
            "kotlinMultiplatform" -> {
                rootProject.name
            }
            else -> {
                "${rootProject.name}-$name"
            }
        }
        version = System.getenv(Env.Version)

        pom {
            name.set(artifactId)
            description.set(Publications.Description)
            url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")

            licenses {
                license {
                    name.set(Publications.LicenseName)
                    url.set(Publications.LicenseUrl)
                }
            }

            developers {
                developer {
                    id.set(Publications.DeveloperId)
                    name.set(Publications.DeveloperName)
                    email.set(Publications.DeveloperEmail)
                    organization.set(Publications.DeveloperOrganization)
                    organizationUrl.set(Publications.DeveloperOrganizationUrl)
                }
            }

            scm {
                url.set("https://github.com/${Publications.GitHubUsername}/${Publications.GitHubRepository}")
            }
        }

        artifact(tasks["kdocJar"])
    }
}

signing {
    setRequired { gradle.taskGraph.hasTask("publish") }
    sign(publishing.publications)

    if (System.getenv(Env.SigningKey) != null) {
        @Suppress("UnstableApiUsage")
        useInMemoryPgpKeys(
            System.getenv(Env.SigningKeyId),
            System.getenv(Env.SigningKey),
            System.getenv(Env.SigningPassword)
        )
    }
}

nexusStaging {
    packageGroup = Publications.OSSRHProfileGroupId
    stagingProfileId = System.getenv(Env.OSSRHProfileId)
    username = System.getenv(Env.OSSRHUsername)
    password = System.getenv(Env.OSSRHPassword)
}
