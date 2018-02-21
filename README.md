# Json.kt
[![Kotlin 1.2.21](https://img.shields.io/badge/Kotlin-1.2.21-blue.svg)](http://kotlinlang.org)
[![Maven Central](https://img.shields.io/maven-central/v/jp.nephy/jsonkt.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22jp.nephy%22)
[![Travis](https://img.shields.io/travis/NephyProject/Json.kt.svg)](https://travis-ci.org/NephyProject/Json.kt/builds)
[![MIT License](https://img.shields.io/github/license/NephyProject/Json.kt.svg)](https://github.com/NephyProject/Json.kt/blob/master/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/NephyProject/Json.kt.svg)](https://github.com/NephyProject/Json.kt/issues)

Json.kt: Json Bindings for Kotlin.  
===========================

シンプルな記法でJsonをKotlinのクラスに変換できます. 委譲プロパティを使用しているためJsonのパースに時間が掛かる心配はありません.    
```kotlin
class Model(override val json: JsonObject): JsonModel {
    // Json内のxというキーにInt型のデータが格納されている場合 次のようにフィールドに変換できます.
    val x by json.byInt("x")  // -> 4
    // Jsonキーがプロパティ名と一致している場合 関数呼び出しにする必要はありません.
    val y by json.byInt  // -> 6
    // byLambda は任意のラムダ式を遅延評価できます. ラムダ式内のthisはJsonElementなのでdoubleで値を参照できます.
    // また doubleのほかに bool, byte, char, short, int, long, bigInteger, float, bigDecimal, string, uri, url などのようにプロパティでJsonの値を取得できます.
    // もし型が不一致でキャストに失敗した際は JsonTypeCastException が送出されます.
    val z by json.byLambda { double * (x + y) }  // (x + y) * z = 1.0

    // キャストに失敗するのは, Jsonにキーが存在しなかったり その値がキャストする型と不一致であったりするときです.
    // 安全にキャストをする場合は次のようにします. Int型でキャストできればIntを そうではない場合はnullが返ります. 上同様にプロパティではなく toIntOrNull() メソッドも用意されています.
    val a by json.byLambda { nullableInt }  // -> null
    // キャスト失敗時にnullではなくデフォルト値で代用したいときは次のようにします.
    val b by json.byLambda { toIntOrDefault(0) }  // -> 0
    // あるいはデフォルト値をラムダ式で代用できます.
    val c by json.byLambda { toIntOrElse { 10 } }  // -> 10
    // さらに byNullableLambda も使えます.
    val d by json.byNullableLambda { string }  // -> null

    // 存在しないキーの値はnullとして扱われます. ここで仮にbyStringとするとアクセス時に型が不一致により JsonNullCastException が送出されます.
    val notFoundKey by json.byNullableString  // -> null
    // 存在しないキーの値をデフォルト値で代用することもできます.
    val defaultValueInsteadOfValue by json.byString { "Awesome Kotlin!" }  // -> "Awesome Kotlin!"

    // REST API等でJsonを扱う際に頻出するURLもインスタンスに1発変換できます.
    val url by json.byUrl  // -> URL("https://www.google.co.jp")

    // Jsonで配列になっているデータはJson.ktではリストとして扱われます.
    val array by json.byIntList  // -> listOf(1, 2, 3)
    // なお同様にデフォルト値を使うこともできます.
    val array2 by json.byStringList { listOf("Kotlin", "1.2.21") }  // -> listOf("Kotlin", "1.2.21")
    // 上のbyLambdaと似ていますが, 配列の各要素に対して処理をするには byLambdaList を使います.
    val array3 by json.byLambdaList { int + 1 }  // -> listOf(10, 20, 30)

    // さらに深いJsonのオブジェクト構造をパースする場合は次のようにします.
    class InnerModel(override val json: JsonObject): JsonModel {
        val deep by json.byString  // -> "You found me!"
        val hoge by json.byNullableString  // -> null
    }
    val innerModel by json.byModel<InnerModel>()
    // 安全にアクセスするためにNullableにすることもできます.
    val innerModel2 by json.byModel<InnerModel?>()
    // Jsonのオブジェクトに対してのみ map が使用可能です.
    val innerModelFields by json.byMap("innerModel") {
        it.key
    }  // -> listOf("deep", "hoge")

    // オブジェクトが配列になっている場合 byModelList を使うこともできます.
    class ObjectArray(override val json: JsonObject): JsonModel {
        val key by json.byString  // -> "a" then "b"
    }
    val objectsArray by json.byModelList<ObjectArray>()

    // モデルクラスに実行時の値を渡すことも可能です.
    class ValueRequiredModel(x: Int, y: Int, z: Int, override val json: JsonObject): JsonModel {
        val a by json.byLambda { int + x + y + z }  // -> a + x + y + z = 10
    }
    val valueRequiredModel by json.byModel<ValueRequiredModel>(1, 2, 3)
}

fun main(args: Array<String>) {
    val content = "{\"x\": 4, \"y\": 6, \"z\": 0.1, \"url\": \"https://www.google.co.jp\", \"array\": [1, 2, 3], \"array3\": [9, 19, 29], \"innermodel\": {\"deep\": \"You found me!\"}, \"objectsArray\": [{\"key\": \"a\"}, \"key\": \"b\"], \"valueRequiredModel\": {\"a\": 4}}"

    // JsonKt.parse(String)はGsonのデフォルトインスタンスでJsonをパースします.
    val model = JsonKt.parse<Model>(content)
    // 独自のGsonインスタンスを生成している場合は次のようにしてもパースできます.
    val model2 = Gson().parse<Model>(content)

    // jsonObject, jsonArrayはJsonObject, JsonArrayを生成するユーティリティ関数です.
    val json = jsonObject(
        "x" to 4,
        "y" to 6
    )
    // JsonKt.toPrettyString(JsonObject) は整形してJsonを出力する関数です.
    val jsonString = JsonKt.toPrettyString(json)
    println(jsonString)
    // {
    //    "x": 4,
    //    "y": 6
    // }
    // JsonKt.toJsonObject(String) はJson文字列をJsonObjectに変換する関数です. Gsonインスタンスからも利用できます.
    val json2 = JsonKt.toJsonObject(jsonString)
    val json3 = Gson().toJsonObject(jsonString)
}
```

もっとほかのサンプルを参照したい方は, [Penicillin](https://github.com/NephyProject/Penicillin) や [KChroner](https://github.com/NephyProject/KChroner) の内部でも使用していますのでご覧ください.

Prepare
-------
最新バージョンは `v1.2` です. すべての変更は [Change Logs](https://github.com/NephyProject/Json.kt/blob/master/CHANGELOG.md) から確認できます.

Gradle:
```groovy
compile "jp.nephy:json.kt:1.2"
```

Maven:
```xml
<dependency>
    <groupId>jp.nephy</groupId>
    <artifactId>json.kt</artifactId>
    <version>1.2</version>
</dependency>
```

Release: [GitHub](https://github.com/NephyProject/Json.kt/releases).  
Snapshot: [Sonatype Snapshots Repository](https://oss.sonatype.org/content/repositories/snapshots/jp/nephy/json.kt/).


Credits
---------
Json.ktは次のサードパーティソフトウェアを利用しています.
- Gson (https://github.com/google/gson) by Google, Inc.

License
---------
Json.ktはMITライセンスのもとで公開されています.  
Json.kt is provided under MIT License.  


Copyright (c) 2018 Nephy Project
