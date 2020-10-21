# Json.kt: Json bindings for Kotlin Multiplatform
[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.10-blue.svg)](https://kotlinlang.org)
[![stable](https://img.shields.io/bintray/v/nephyproject/stable/JsonKt.svg?label=stable)](https://bintray.com/nephyproject/stable/JsonKt/_latestVersion)
[![dev](https://img.shields.io/bintray/v/nephyproject/dev/JsonKt.svg?label=dev)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion)
[![license](https://img.shields.io/github/license/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/blob/master/LICENSE)
[![issues](https://img.shields.io/github/issues/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/issues)
[![pull requests](https://img.shields.io/github/issues-pr/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/pulls)  

委譲プロパティを使い, 直感的に Json を Kotlin のクラスに変換できます。  
Using delegation properties, you can convert Json to Kotlin classes intuitively.  

現時点では JVM (Android), JS target に対応しています。 (各種 Native target に対応予定)  
JVM (Android) and JS targets are supported for now. (We'll support Native targets later.)  

```kotlin
data class Model(override val json: JsonObject): JsonModel {
    val a by int
    val b by float
    val c by string
    val d by intList
    val e by model { E(it) }
    val f by modelList { E(it) }

    data class E(override val json: JsonObject): JsonModel {
        val x by nullableString
        val y by double
        val z by int
    }
}

private val json = """{
    "a": 1,
    "b": 2.3,
    "c": "hoge",
    "d": [2, 3, 5, 7],
    "e": {
        "x": "1",
        "y": 2.0,
        "z": 3
    },
    "f": [
        {
            "x": "1",
            "y": 2.0001,
            "z": 3
        },
        {
            "x": null,
            "y": 20.00001,
            "z": 30
        }
    ]
}"""

fun main() {
    val model = json.parseObject { Model(it) }

    println(model.a == 1) // true
}
```

Get Started
-----------

Latest Json.kt version is [![Stable](https://img.shields.io/bintray/v/nephyproject/stable/JsonKt.svg?label=stable)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion) or [![Dev](https://img.shields.io/bintray/v/nephyproject/dev/JsonKt.svg?label=dev)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion).  

Stable releases are available at [Bintray](https://bintray.com/nephyproject/stable/JsonKt). EAP builds are also available ([Dev Repository](https://bintray.com/nephyproject/dev/JsonKt)). Every commit is published as EAP build.  

build.gradle.kts:
```kotlin
repositories {
    mavenCentral()
    jcenter()

    maven(url = "https://dl.bintray.com/nephyproject/stable")
    // or dev repository if EAP builds preferred
    // maven(url = "https://dl.bintray.com/nephyproject/dev")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                // for common; In many cases, this is not necessary.
                implementation("blue.starry:jsonkt-common:$jsonkt_version")
            }
        }

        named("jvmMain") {
            dependencies {
                // for JVM (Android)
                implementation("blue.starry:jsonkt:$jsonkt_version")
            }
        }

        named("jsMain") {
            dependencies {
                // for JS
                implementation("blue.starry:jsonkt-js:$jsonkt_version")
            }
        }
    }
}
```

License
---------

Json.kt is provided under MIT License.  

Copyright (c) 2017-2020 StarryBlueSky.
