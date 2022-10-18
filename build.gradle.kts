import blue.starry.gradle.EnvReference

plugins {
    kotlin("multiplatform") version "1.7.10"
    `maven-publish`
    signing
    id("io.codearte.nexus-staging") version "0.30.0"
    id("org.jetbrains.dokka") version "1.7.20"
    id("blue.starry.gradle") version "0.0.1"
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
    val Version = EnvReference("VERSION")

    val OSSRHProfileId = EnvReference("OSSRH_PROFILE_ID")
    val OSSRHUsername = EnvReference("OSSRH_USERNAME")
    val OSSRHPassword = EnvReference("OSSRH_PASSWORD")

    val GitHubUsername = EnvReference("GITHUB_USERNAME")
    val GitHubPassword = EnvReference("GITHUB_PASSWORD")

    val SigningKeyId = EnvReference("SIGNING_KEYID")
    val SigningKey = EnvReference("SIGNING_KEY")
    val SigningPassword = EnvReference("SIGNING_PASSWORD")
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
                enabled = false
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("reflect"))

                api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
                api("io.github.microutils:kotlin-logging:2.1.23")
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

                implementation("ch.qos.logback:logback-classic:1.4.4")
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
                apiVersion = "1.7"
                languageVersion = "1.7"
            }
        }
    }

    sourceSets.all {
        languageSettings {
            progressiveMode = true

            optIn("kotlin.RequiresOptIn")
        }
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
                if (Env.Version.valueOrNull.orEmpty().endsWith("-SNAPSHOT")) {
                    Publications.MavenCentralSnapshotRepositoryUrl
                } else {
                    Publications.MavenCentralStagingRepositoryUrl
                }
            )

            credentials {
                username = Env.OSSRHUsername.valueOrNull
                password = Env.OSSRHPassword.valueOrNull
            }
        }

        maven {
            name = "GitHubPackages"
            url = uri(Publications.GitHubPackagesRepositoryUrl)

            credentials {
                username = Env.GitHubUsername.valueOrNull
                password = Env.GitHubPassword.valueOrNull
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
        version = Env.Version.valueOrNull

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

    if (Env.SigningKey.isPresent) {
        useInMemoryPgpKeys(
            Env.SigningKeyId.value,
            Env.SigningKey.value,
            Env.SigningPassword.value
        )
    }
}

nexusStaging {
    packageGroup = Publications.OSSRHProfileGroupId
    stagingProfileId = Env.OSSRHProfileId.valueOrNull
    username = Env.OSSRHUsername.valueOrNull
    password = Env.OSSRHPassword.valueOrNull
}
