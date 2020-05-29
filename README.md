# Json.kt: Json Bindings for Kotlin
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.72-blue.svg)](https://kotlinlang.org)
[![Stable](https://img.shields.io/bintray/v/nephyproject/stable/JsonKt.svg?label=stable)](https://bintray.com/nephyproject/stable/JsonKt/_latestVersion)
[![Dev](https://img.shields.io/bintray/v/nephyproject/dev/JsonKt.svg?label=dev)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion)
[![License](https://img.shields.io/github/license/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/blob/master/LICENSE)
[![Issues](https://img.shields.io/github/issues/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/StarryBlueSky/Json.kt.svg)](https://github.com/StarryBlueSky/Json.kt/pulls)

委譲プロパティを使い, わかりやすく Json を Kotlin のクラスに変換できます。
Kotlin/JVM, Kotlin/JS, Kotlin/Common に対応しています。

```kotlin
class Model(override val json: JsonObject): JsonModel {
    val a by int
    val b by float
    val c by string
    val d by intList
    val e by model { E(it) }
    val f by modelList { E(it) }

    class E(override val json: JsonObject): JsonModel {
        val x by nullableString
        val y by double
        val z by int
    }
}

const val json = """{
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
    val model = json.parseObject { Model(it} }

    println(model.a == 1) // true
}
```

Get Started
-----------

Latest Json.kt version is [![Stable](https://img.shields.io/bintray/v/nephyproject/stable/JsonKt.svg?label=stable)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion) or [![Dev](https://img.shields.io/bintray/v/nephyproject/dev/JsonKt.svg?label=dev)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion).  

Stable releases are available at [Bintray](https://bintray.com/nephyproject/stable/JsonKt). EAP builds are also available ([Dev Repository](https://bintray.com/nephyproject/dev/JsonKt)). Every commit is published as EAP build.  

Gradle:
```kotlin
repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://kotlin.bintray.com/kotlinx")
    maven(url = "https://dl.bintray.com/nephyproject/stable")
    // Or dev repository
    // maven(url = "https://dl.bintray.com/nephyproject/dev")
}

dependencies {
    // Kotlin/JVM
    implementation("jp.nephy:jsonkt:$jsonkt_version")

    // Kotlin/JS
    implementation("jp.nephy:jsonkt-js:$jsonkt_version")

    // Kotlin/Common
    implementation("jp.nephy:jsonkt-common:$jsonkt_version")
}
```

License
---------

Json.kt is provided under MIT License.


Copyright (c) 2017-2020 Nephy Project.
