# Json.kt: Json Bindings for Kotlin
[![Kotlin](https://img.shields.io/badge/Kotlin-1.3.41-blue.svg)](https://kotlinlang.org)
[![Stable](https://img.shields.io/bintray/v/nephyproject/stable/JsonKt.svg?label=stable)](https://bintray.com/nephyproject/stable/JsonKt/_latestVersion)
[![Dev](https://img.shields.io/bintray/v/nephyproject/dev/JsonKt.svg?label=dev)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion)
[![License](https://img.shields.io/github/license/NephyProject/Json.kt.svg)](https://github.com/NephyProject/Json.kt/blob/master/LICENSE)
[![Issues](https://img.shields.io/github/issues/NephyProject/Json.kt.svg)](https://github.com/NephyProject/Json.kt/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/NephyProject/Json.kt.svg)](https://github.com/NephyProject/Json.kt/pulls)

===========================

シンプルな記法でJsonをKotlinのクラスに変換できます。委譲プロパティを使用しているためパフォーマンスの心配はいりません。
```kotlin
import jp.nephy.jsonkt.*
import jp.nephy.jsonkt.delegation.*

class Model(override val json: JsonObject): JsonModel {
    // Json内のxというキーにInt型のデータが格納されている場合 次のようにフィールドに変換できます.
    val x by int("x")  // -> 4
    // Jsonキーがプロパティ名と一致している場合 関数呼び出しにする必要はありません.
    val y by int  // -> 6
    // byLambda は任意のラムダ式を遅延評価できます. ラムダ式内のthisはJsonElementなのでdoubleで値を参照できます.
    // また doubleのほかに bool, byte, char, short, int, long, bigInteger, float, bigDecimal, stringなどのようにプロパティでJsonの値を取得できます.
    // もし型が不一致でキャストに失敗した際は JsonKtException が送出されます.
    val z by lambda { it.double * (x + y) }  // (x + y) * z = 1.0

    // キャストに失敗するのは, Jsonにキーが存在しなかったり その値がキャストする型と不一致であったりするときです.
    // 安全にキャストをする場合は次のようにします. Int型でキャストできればIntを そうではない場合はnullが返ります. 上同様にプロパティではなく toIntOrNull() メソッドも用意されています.
    val a by lambda { it.nullableInt }  // -> null
    // キャスト失敗時にnullではなくデフォルト値で代用したいときは次のようにします.
    val b by lambda { it.toIntOrDefault(0) }  // -> 0
    // あるいはデフォルト値をラムダ式で代用できます.
    val c by lambda { it.toIntOrElse { 10 } }  // -> 10
    // さらに byNullableLambda も使えます.
    val d by nullableLambda { it.string }  // -> null

    // 存在しないキーの値はnullとして扱われます. ここで仮にstringとするとアクセス時に型が不一致により JsonKtException が送出されます.
    val notFoundKey by nullableString  // -> null
    // 存在しないキーの値をデフォルト値で代用することもできます.
    val defaultValueInsteadOfValue by string { "Awesome Kotlin!" }  // -> "Awesome Kotlin!"

    // Jsonで配列になっているデータはJson.ktではリストとして扱われます.
    val array by intList  // -> listOf(1, 2, 3)
    // なお同様にデフォルト値を使うこともできます.
    val array2 by stringList { listOf("Kotlin", "1.2.21") }  // -> listOf("Kotlin", "1.2.21")
    // 上のbyLambdaと似ていますが, 配列の各要素に対して処理をするには lambdaList を使います.
    val array3 by lambdaList { it.int + 1 }  // -> listOf(10, 20, 30)

    // さらに深いJsonのオブジェクト構造をパースする場合は次のようにします.
    class InnerModel(override val json: JsonObject): JsonModel {
        val deep by string  // -> "You found me!"
        val hoge by nullableString  // -> null
    }
    val innerModel by model<InnerModel>()
    // 安全にアクセスするためにNullableにすることもできます.
    val innerModel2 by model<InnerModel?>()
    // Jsonのオブジェクトに対してのみ map が使用可能です.
    val innerModelFields by map("innerModel") {
        it.key
    }  // -> listOf("deep", "hoge")

    // オブジェクトが配列になっている場合 modelList を使うこともできます.
    class ObjectArray(override val json: JsonObject): JsonModel {
        val key by string  // -> "a" then "b"
    }
    val objectsArray by modelList<ObjectArray>()

    // モデルクラスに実行時の値を渡すことも可能です.
    class ValueRequiredModel(x: Int, y: Int, z: Int, override val json: JsonObject): JsonModel {
        val a by lambda { it.int + x + y + z }  // -> a + x + y + z = 10
    }
    val valueRequiredModel by model<ValueRequiredModel>(1, 2, 3)
}

fun main(args: Array<String>) {
    val content = "{\"x\": 4, \"y\": 6, \"z\": 0.1, \"url\": \"https://www.google.co.jp\", \"array\": [1, 2, 3], \"array3\": [9, 19, 29], \"innermodel\": {\"deep\": \"You found me!\"}, \"objectsArray\": [{\"key\": \"a\"}, {\"key\": \"b\"}], \"valueRequiredModel\": {\"a\": 4}}"

    // String.parse<T>()でJsonをパースします.
    val model = content.parse<Model>()

    // jsonObjectOf, jsonArrayOfはJsonObject, JsonArrayを生成するユーティリティ関数です.
    val json = jsonObjectOf(
        "x" to 4,
        "y" to 6
    )

    // モデルクラスを作るのが面倒な方は 次で1発生成できます.
    // 生成される文字列をクラスとして保存すれば すぐにモデルクラスとして使うことができます.
    // 配列内のオブジェクトやnullが混在している値も型推論するので安全にアクセスできます.
    println(content.toModelString())
    // import jp.nephy.jsonkt.*
    // import jp.nephy.jsonkt.delegation.*
    //
    // class Model(override val json: JsonObject): JsonModel {
    //     val array by intList  // [1, ...]
    //     val array3 by intList  // [9, ...]
    //     val innermodel by model<Innermodel>()  // {...}
    //     val objectsArray by modelList<ObjectsArray>()  // [{"key":"a"}, ...]
    //     val url by string  // "https://www.google.co.jp"
    //     val valueRequiredModel by model<ValueRequiredModel>()  // {...}
    //     val x by int  // 4
    //     val y by int  // 6
    //     val z by float  // 0.1
    // }
    //
    // class Innermodel(override val json: JsonObject): JsonModel {
    //     val deep by string  // "You found me!"
    // }
    //
    // class ObjectsArray(override val json: JsonObject): JsonModel {
    //     val key by string  // "a"
    // }
    //
    // class ValueRequiredModel(override val json: JsonObject): JsonModel {
    //     val a by int  // 4
    // }
}
```

もっとほかのサンプルを参照したい方は, [Penicillin](https://github.com/NephyProject/Penicillin) や [GLaDOS-bot](https://github.com/NephyProject/GLaDOS-bot) の内部でも使用していますのでご覧ください。

Get Started
-----------

Latest Json.kt version is [![Stable](https://img.shields.io/bintray/v/nephyproject/stable/JsonKt.svg?label=stable)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion) or [![Dev](https://img.shields.io/bintray/v/nephyproject/dev/JsonKt.svg?label=dev)](https://bintray.com/nephyproject/dev/JsonKt/_latestVersion).  

Stable releases are available at [Bintray](https://bintray.com/nephyproject/stable/JsonKt). EAP builds are also available ([Dev Repository](https://bintray.com/nephyproject/dev/JsonKt)). Every commit is published as EAP build.  

Gradle:
```groovy
repositories {
    mavenCentral()
    jcenter()
    maven { url "https://kotlin.bintray.com/kotlinx" }
    maven { url "https://dl.bintray.com/nephyproject/stable" } 
    // Or dev repository
    // maven { url "https://dl.bintray.com/nephyproject/dev" }
}

dependencies {
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.41"
    implementation "jp.nephy:jsonkt:5.0.0-eap-1"
}
```

License
---------

Json.kt is provided under MIT License.


Copyright (c) 2018-2019 Nephy Project.
