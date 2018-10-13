package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegate.*
import java.math.BigDecimal
import java.math.BigInteger

@Suppress("UNUSED")
class ExampleModel(override val json: JsonObject): JsonModel {
    class InnerModel(override val json: JsonObject): JsonModel {
        private val x by json.byInt
        val y by json.byLambda { x * it.int }
        val z by json.byLambdaList { it.int + 10 }
    }

    class ValueRequiredModel(val a: String, val b: String, val c: String, override val json: JsonObject): JsonModel {
        private val x by json.byInt
        val y by json.byLambda { x * it.int }
        val z by json.byLambdaList { it.int + 10 }
    }

    val model2 by json.byModel<ValueRequiredModel>("aaa", "bbb", "ccc")

    val bool by json.byBoolean
    val boolKey by json.byBoolean("bool")
    val boolNullable by json.byNullableBoolean
    val boolDefault by json.byBoolean { false }
    val boolListKey by json.byBooleanList("boolList")
    val boolListNullable by json.byBooleanList
    val boolListDefault by json.byBooleanList { listOf(true, false) }

    val byte by json.byByte
    val byteKey by json.byByte("byte")
    val byteNullable by json.byNullableByte
    val byteDefault by json.byByte { 10 }
    val byteListKey by json.byByteList("byteList")
    val byteListNullable by json.byByteList
    val byteListDefault by json.byByteList { listOf(1, 2, 3) }

    val char by json.byChar
    val charKey by json.byChar("char")
    val charNullable by json.byNullableChar
    val charDefault by json.byChar { 'c' }
    val charListKey by json.byCharList("charList")
    val charListNullable by json.byCharList
    val charListDefault by json.byCharList { listOf('a', 'b') }

    val short by json.byShort
    val shortKey by json.byShort("short")
    val shortNullable by json.byNullableShort
    val shortDefault by json.byShort { 2 }
    val shortListKey by json.byShortList("shortList")
    val shortListNullable by json.byShortList
    val shortListDefault by json.byShortList { listOf(1, 2) }

    val int by json.byInt
    val intKey by json.byInt("int")
    val intNullable by json.byNullableInt
    val intDefault by json.byInt { 10 }
    val intListKey by json.byIntList("intList")
    val intListNullable by json.byIntList
    val intListDefault by json.byIntList { listOf(10) }

    val long by json.byLong
    val longKey by json.byLong("long")
    val longNullable by json.byNullableLong
    val longDefault by json.byLong { 100L }
    val longListKey by json.byLongList("longList")
    val longListNullable by json.byLongList
    val longListDefault by json.byLongList { listOf() }

    val bigInteger by json.byBigInteger
    val bigIntegerKey by json.byBigInteger("bigInteger")
    val bigIntegerNullable by json.byNullableBigInteger
    val bigIntegerDefault by json.byBigInteger { BigInteger.valueOf(1000L) }
    val bigIntegerListKey by json.byBigIntegerList("bigIntegerList")
    val bigIntegerListNullable by json.byBigIntegerList
    val bigIntegerListDefault by json.byBigIntegerList { listOf(BigInteger.valueOf(10000L)) }

    val float by json.byFloat
    val floatKey by json.byFloat("float")
    val floatNullable by json.byNullableFloat
    val floatDefault by json.byFloat { 2.0F }
    val floatListKey by json.byFloatList("floatList")
    val floatListNullable by json.byFloatList
    val floatListDefault by json.byFloatList { listOf(20F) }

    val double by json.byDouble
    val doubleKey by json.byDouble("double")
    val doubleNullable by json.byNullableDouble
    val doubleDefault by json.byDouble { 40.0 }
    val doubleListKey by json.byDoubleList("doubleList")
    val doubleListNullable by json.byDoubleList
    val doubleListDefault by json.byDoubleList { listOf(1000.1) }

    val bigDecimal by json.byBigDecimal
    val bigDecimalKey by json.byBigDecimal("bigDecimal")
    val bigDecimalNullable by json.byNullableBigDecimal
    val bigDecimalDefault by json.byBigDecimal { BigDecimal.valueOf(1000) }
    val bigDecimalListKey by json.byBigDecimalList("bigDecimalList")
    val bigDecimalListNullable by json.byBigDecimalList
    val bigDecimalListDefault by json.byBigDecimalList { arrayListOf(BigDecimal.valueOf(100)) }

    val string by json.byString
    val stringKey by json.byString("string")
    val stringNullable by json.byNullableString
    val stringDefault by json.byString { "aaa" }
    val stringListKey by json.byStringList("stringList")
    val stringListNullable by json.byStringList
    val stringListDefault by json.byStringList { listOf("aaa") }
}
