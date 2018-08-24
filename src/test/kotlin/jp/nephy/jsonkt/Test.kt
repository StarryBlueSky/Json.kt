package jp.nephy.jsonkt

import com.google.gson.JsonObject

fun main(args: Array<String>) {
    val json = "{\"enum\":1,\"enums\":[2,3]}".parse<Model>()
    println(json.enum)
    println(json.enums)
}

enum class Test(override val value: Int): JsonEnum<Int> {
    A(1), B(2), C(3), D(0)
}

class Model(override val json: JsonObject): JsonModel {
    val enum by json.byEnum(Test::class) { Test.D }
    val enums by json.byEnumList(Test::class, unknown = Test.D)
}
