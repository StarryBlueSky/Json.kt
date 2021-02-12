# Json.kt: Json bindings for Kotlin Multiplatform
[![Kotlin](https://img.shields.io/badge/Kotlin-1.4.30-blue.svg)](https://kotlinlang.org)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/StarryBlueSky/Json.kt)](https://github.com/StarryBlueSky/Json.kt/releases)
[![GitHub Workflow Status](https://img.shields.io/github/workflow/status/StarryBlueSky/Json.kt/Check)](https://github.com/StarryBlueSky/Json.kt)
[![license](https://img.shields.io/github/license/StarryBlueSky/Json.kt)](https://github.com/StarryBlueSky/Json.kt/blob/master/LICENSE)
[![issues](https://img.shields.io/github/issues/StarryBlueSky/Json.kt)](https://github.com/StarryBlueSky/Json.kt/issues)
[![pull requests](https://img.shields.io/github/issues-pr/StarryBlueSky/Json.kt)](https://github.com/StarryBlueSky/Json.kt/pulls)

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

# Get Started

[![GitHub release (latest by date)](https://img.shields.io/github/v/release/StarryBlueSky/Json.kt)](https://github.com/StarryBlueSky/Json.kt/releases)

```kotlin
dependencies {
    implementation("blue.starry:jsonkt:$JsonKtVersion")
}
```

# License

Json.kt is provided under MIT License.  

Copyright (c) 2017-2021 StarryBlueSky.
