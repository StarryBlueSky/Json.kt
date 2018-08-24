package jp.nephy.jsonkt

import com.google.gson.JsonObject
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.full.createType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaType

class DelegateTest {
    init {
        val json = makeJsonObject()
        val model = json.parse<ExampleModel>()

        model::class.memberProperties.forEach {
            if (it.visibility?.name == "PRIVATE") {
                return@forEach
            }

            if (it.returnType.isSubtypeOf(JsonModel::class.createType())) {
                println("val ${it.name}: ${it.returnType.javaType.typeName} = {")
                val model2 = it.call(model)!!
                model2::class.memberProperties.forEach {
                    if (it.visibility?.name != "PRIVATE") {
                        println("   val ${it.name}: ${it.returnType.javaType.typeName} = ${it.call(model2)}")
                    }
                }
                println("}")
            } else {
                println("val ${it.name}: ${it.returnType.javaType.typeName} = ${it.call(model)}")
            }
        }
    }

    private fun makeJsonObject(): JsonObject {
        return jsonObject(
                "model" to mapOf(
                        "x" to 5,
                        "y" to 2,
                        "z" to arrayOf(1, 10, 100)
                ),
                "model2" to mapOf(
                        "x" to 5,
                        "y" to 2,
                        "z" to arrayOf(1, 10, 100)
                ),

                "bool" to true,
                "boolNullable" to null,
                "boolList" to arrayOf(false, true),

                "byte" to 1,
                "byteNullable" to null,
                "byteList" to arrayOf(2),

                "char" to 96,
                "charNullable" to null,
                "charList" to arrayOf(75),

                "short" to 100,
                "shortNullable" to null,
                "shortList" to arrayOf(200),

                "int" to 10000,
                "intNullable" to 20,
                "intList" to arrayOf(20000),

                "long" to 293994785239L,
                "longNullable" to null,
                "longList" to arrayOf(471470140L),

                "bigInteger" to BigInteger.valueOf(10000000000L),
                "bigIntegerNullable" to null,
                "bigIntegerList" to arrayOf<BigInteger>(),

                "float" to 0.002F,
                "floatNullable" to null,
                "floatList" to arrayOf(1.234F),

                "double" to 9.5242,
                "doubleNullable" to null,
                "doubleList" to arrayOf(1.44235),

                "bigDecimal" to BigDecimal.valueOf(1000L),
                "bigDecimalNullable" to null,
                "bigDecimalList" to arrayOf(BigDecimal.valueOf(10000L)),

                "string" to "aaa",
                "stringNullable" to null,
                "stringList" to arrayOf("aaa", "nnn"),

                "uri" to "https://www.google.co.jp/",
                "uriNullable" to "https://www.google.co.jp/search",
                "uriList" to arrayOf("https://www.google.com", "https://www.google.co.jp/"),

                "url" to "https://www.google.co.jp/",
                "urlNullable" to null,
                "urlList" to arrayOf("https://www.google.co.jp/")
        )
    }
}
