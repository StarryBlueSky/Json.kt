# Json.kt: Json bindings for Kotlin Multiplatform
[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.20-blue.svg)](https://kotlinlang.org)
[![stable](https://img.shields.io/bintray/v/starry-blue-sky/stable/JsonKt.svg?label=stable)](https://bintray.com/starry-blue-sky/stable/JsonKt/_latestVersion)
[![dev](https://img.shields.io/bintray/v/starry-blue-sky/dev/JsonKt.svg?label=dev)](https://bintray.com/starry-blue-sky/dev/JsonKt/_latestVersion)
[![license](https://img.shields.io/github/license/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/blob/master/LICENSE)
[![issues](https://img.shields.io/github/issues/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/issues)
[![pull requests](https://img.shields.io/github/issues-pr/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/pulls)  

委譲プロパティを使い, 直感的に Json を Kotlin のクラスに変換できます。  
Using delegation properties, you can convert Json to Kotlin classes intuitively.  

現時点では JVM (Android), JS target に対応しています。 (各種 Native target に対応予定です)  
JVM (Android) and JS targets are supported for now. (We'll support Native targets later.)  

ドキュメントは [こちら](https://docs.starry.blue/jsonkt) で公開しています。  
Documentations are published at [here](https://docs.starry.blue/jsonkt).  


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

Latest Json.kt version is [![Stable](https://img.shields.io/bintray/v/starry-blue-sky/stable/JsonKt.svg?label=stable)](https://bintray.com/starry-blue-sky/dev/JsonKt/_latestVersion) or [![Dev](https://img.shields.io/bintray/v/starry-blue-sky/dev/JsonKt.svg?label=dev)](https://dl.bintray.com/starry-blue-sky/dev/JsonKt/_latestVersion).  

Stable releases are available at [Bintray](hhttps://dl.bintray.com/starry-blue-sky/stable/JsonKt). EAP builds are also available ([Dev Repository](https://dl.bintray.com/starry-blue-sky/dev/JsonKt)). Every commit is published as EAP build.  

build.gradle.kts:
```kotlin
repositories {
    mavenCentral()
    jcenter()

    maven(url = "https://dl.bintray.com/starry-blue-sky/stable")
    // or dev repository if EAP builds preferred
    // maven(url = "https://dl.bintray.com/starry-blue-sky/dev")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                implementation("blue.starry:jsonkt:$jsonkt_version")
            }
        }

        // Json.kt v6.0+: Not needed anymore
        named("jvmMain") {
        }
        named("jsMain") {
        }
    }
}
```

License
---------

Json.kt is provided under MIT License.  

Copyright (c) 2017-2020 StarryBlueSky.
