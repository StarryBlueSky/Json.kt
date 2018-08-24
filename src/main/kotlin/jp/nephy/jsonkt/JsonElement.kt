package jp.nephy.jsonkt

import com.google.gson.*
import jp.nephy.jsonkt.exception.AnyTypeCastException
import jp.nephy.jsonkt.exception.JsonTypeCastException
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.net.URL

val jsonNull = JsonNull.INSTANCE!!

fun Boolean.toJson() = JsonPrimitive(this)
fun Number.toJson() = JsonPrimitive(this)
fun Char.toJson() = JsonPrimitive(this)
fun String.toJson() = JsonPrimitive(this)

fun Any?.toJsonElement(): JsonElement {
    if (this == null) {
        return jsonNull
    }

    return when (this) {
        is JsonElement -> this
        is Boolean -> toJson()
        is Number -> toJson()
        is Char -> toJson()
        is String -> toJson()
        else -> throw AnyTypeCastException(this::class.java.canonicalName)
    }
}

inline fun <reified T: Any?> JsonElement?.dynamicCast(): T {
    return try {
        if (this == null) {
            throw UnsupportedOperationException()
        }

        @Suppress("IMPLICIT_CAST_TO_ANY")
        when (T::class.java) {
            com.google.gson.JsonObject::class.java -> asJsonObject
            com.google.gson.JsonArray::class.java -> asJsonArray
            com.google.gson.JsonPrimitive::class.java -> asJsonPrimitive
            com.google.gson.JsonNull::class.java -> asJsonNull
            java.lang.Boolean::class.java -> asBoolean
            java.lang.Byte::class.java -> asByte
            java.lang.Character::class.java -> asCharacter
            java.lang.Short::class.java -> asShort
            java.lang.Integer::class.java -> asInt
            java.lang.Long::class.java -> asLong
            java.math.BigInteger::class.java -> asBigInteger
            java.lang.Float::class.java -> asFloat
            java.lang.Double::class.java -> asDouble
            java.math.BigDecimal::class.java -> asBigDecimal
            java.lang.Number::class.java -> asNumber
            java.lang.String::class.java -> asString
            java.net.URI::class.java -> URI(asString)
            java.net.URL::class.java -> URL(asString)
            else -> throw UnsupportedOperationException()
        } as T
    } catch (e: UnsupportedOperationException) {
        try {
            null as T
        } catch (e: TypeCastException) {
            throw JsonTypeCastException(this, T::class.simpleName!!)
        }
    }
}

inline fun <reified T: Any?> JsonElement?.dynamicCastList(): List<T> {
    return try {
        if (this == null || !isJsonArray) {
            return emptyList()
        }

        jsonArray.map {
            it.dynamicCast<T>()
        }
    } catch (e: UnsupportedOperationException) {
        emptyList()
    }
}

operator fun JsonElement.get(key: String): JsonElement {
    return jsonObject.get(key) ?: throw NoSuchElementException(key)
}
operator fun JsonElement.get(index: Int): JsonElement {
    return jsonArray.get(index)
}

operator fun JsonElement.set(key: String, value: Any?) {
    jsonObject.add(key, value.toJsonElement())
}
operator fun JsonElement.set(index: Int, value: Any?) {
    jsonArray.set(index, value.toJsonElement())
}

fun JsonElement.toJsonElement() = dynamicCast<JsonElement>()
fun JsonElement?.toJsonElementOrNull() = dynamicCast<JsonElement?>()
fun JsonElement?.toJsonElementOrDefault(default: JsonElement) = toJsonElementOrNull() ?: default
fun JsonElement?.toJsonElementOrElse(default: () -> JsonElement) = toJsonElementOrNull() ?: default()
val JsonElement.jsonElement: JsonElement
    get() = toJsonElement()
val JsonElement?.nullableJsonElement: JsonElement?
    get() = toJsonElementOrNull()
fun JsonElement.toJsonElementList() = dynamicCastList<JsonElement>()
val JsonElement.jsonElementList: List<JsonElement>
    get() = toJsonElementList()

fun JsonElement.toJsonObject() = dynamicCast<JsonObject>()
fun JsonElement?.toJsonObjectOrNull() = dynamicCast<JsonObject?>()
fun JsonElement?.toJsonObjectOrDefault(default: JsonObject) = toJsonObjectOrNull() ?: default
fun JsonElement?.toJsonObjectOrElse(default: () -> JsonObject) = toJsonObjectOrNull() ?: default()
val JsonElement.jsonObject: JsonObject
    get() = toJsonObject()
val JsonElement?.nullableJsonObject: JsonObject?
    get() = toJsonObjectOrNull()
fun JsonElement.toJsonObjectList() = dynamicCastList<JsonObject>()
val JsonElement.jsonObjectList: List<JsonObject>
    get() = toJsonObjectList()

fun JsonElement.toJsonArray() = dynamicCast<JsonArray>()
fun JsonElement?.toJsonArrayOrNull() = dynamicCast<JsonArray?>()
fun JsonElement?.toJsonArrayOrDefault(default: JsonArray) = toJsonArrayOrNull() ?: default
fun JsonElement?.toJsonArrayOrElse(default: () -> JsonArray) = toJsonArrayOrNull() ?: default()
val JsonElement.jsonArray: JsonArray
    get() = toJsonArray()
val JsonElement?.nullableJsonArray: JsonArray?
    get() = toJsonArrayOrNull()
fun JsonElement.toJsonArrayList() = dynamicCastList<JsonArray>()
val JsonElement.jsonArrayList: List<JsonArray>
    get() = toJsonArrayList()

fun JsonElement.toJsonPrimitive() = dynamicCast<JsonPrimitive>()
fun JsonElement?.toJsonPrimitiveOrNull() = dynamicCast<JsonPrimitive?>()
fun JsonElement?.toJsonPrimitiveOrDefault(default: JsonPrimitive) = toJsonPrimitiveOrNull() ?: default
fun JsonElement?.toJsonPrimitiveOrElse(default: () -> JsonPrimitive) = toJsonPrimitiveOrNull() ?: default()
val JsonElement.jsonPrimitive: JsonPrimitive
    get() = toJsonPrimitive()
val JsonElement?.nullableJsonPrimitive: JsonPrimitive?
    get() = toJsonPrimitiveOrNull()
fun JsonElement.toJsonPrimitiveList() = dynamicCastList<JsonPrimitive>()
val JsonElement.jsonPrimitiveList: List<JsonPrimitive>
    get() = toJsonPrimitiveList()

fun JsonElement.toJsonNull() = dynamicCast<JsonNull>()
fun JsonElement?.toJsonNullOrNull() = dynamicCast<JsonNull?>()
fun JsonElement?.toJsonNullOrDefault(default: JsonNull) = toJsonNullOrNull() ?: default
fun JsonElement?.toJsonNullOrElse(default: () -> JsonNull) = toJsonNullOrNull() ?: default()
val JsonElement.jsonNull: JsonNull
    get() = toJsonNull()
val JsonElement?.nullableJsonNull: JsonNull?
    get() = toJsonNullOrNull()
fun JsonElement.toJsonNullList() = dynamicCastList<JsonNull>()
val JsonElement.jsonNullList: List<JsonNull>
    get() = toJsonNullList()

fun JsonElement.toBool() = dynamicCast<Boolean>()
fun JsonElement?.toBoolOrNull() = dynamicCast<Boolean?>()
fun JsonElement?.toBoolOrDefault(default: Boolean) = toBoolOrNull() ?: default
fun JsonElement?.toBoolOrElse(default: () -> Boolean) = toBoolOrNull() ?: default()
val JsonElement.bool: Boolean
    get() = toBool()
val JsonElement?.nullableBool: Boolean?
    get() = toBoolOrNull()
fun JsonElement.toBoolList() = dynamicCastList<Boolean>()
val JsonElement.boolList: List<Boolean>
    get() = toBoolList()

fun JsonElement.toByte() = dynamicCast<Byte>()
fun JsonElement?.toByteOrNull() = dynamicCast<Byte?>()
fun JsonElement?.toByteOrDefault(default: Byte) = toByteOrNull() ?: default
fun JsonElement?.toByteOrElse(default: () -> Byte) = toByteOrNull() ?: default()
val JsonElement.byte: Byte
    get() = toByte()
val JsonElement?.nullableByte: Byte?
    get() = toByteOrNull()
fun JsonElement.toByteList() = dynamicCastList<Byte>()
val JsonElement.byteList: List<Byte>
    get() = toByteList()

fun JsonElement.toChar() = dynamicCast<Char>()
fun JsonElement?.toCharOrNull() = dynamicCast<Char?>()
fun JsonElement?.toCharOrDefault(default: Char) = toCharOrNull() ?: default
fun JsonElement?.toCharOrElse(default: () -> Char) = toCharOrNull() ?: default()
val JsonElement.char: Char
    get() = toChar()
val JsonElement?.nullableChar: Char?
    get() = toCharOrNull()
fun JsonElement.toCharList() = dynamicCastList<Char>()
val JsonElement.charList: List<Char>
    get() = toCharList()

fun JsonElement.toShort() = dynamicCast<Short>()
fun JsonElement?.toShortOrNull() = dynamicCast<Short?>()
fun JsonElement?.toShortOrDefault(default: Short) = toShortOrNull() ?: default
fun JsonElement?.toShortOrElse(default: () -> Short) = toShortOrNull() ?: default()
val JsonElement.short: Short
    get() = toShort()
val JsonElement?.nullableShort: Short?
    get() = toShortOrNull()
fun JsonElement.toShortList() = dynamicCastList<Short>()
val JsonElement.shortList: List<Short>
    get() = toShortList()

fun JsonElement.toInt() = dynamicCast<Int>()
fun JsonElement?.toIntOrNull() = dynamicCast<Int?>()
fun JsonElement?.toIntOrDefault(default: Int) = toIntOrNull() ?: default
fun JsonElement?.toIntOrElse(default: () -> Int) = toIntOrNull() ?: default()
val JsonElement.int: Int
    get() = toInt()
val JsonElement?.nullableInt: Int?
    get() = toIntOrNull()
fun JsonElement.toIntList() = dynamicCastList<Int>()
val JsonElement.intList: List<Int>
    get() = toIntList()

fun JsonElement.toLong() = dynamicCast<Long>()
fun JsonElement?.toLongOrNull() = dynamicCast<Long?>()
fun JsonElement?.toLongOrDefault(default: Long) = toLongOrNull() ?: default
fun JsonElement?.toLongOrElse(default: () -> Long) = toLongOrNull() ?: default()
val JsonElement.long: Long
    get() = toLong()
val JsonElement?.nullableLong: Long?
    get() = toLongOrNull()
fun JsonElement.toLongList() = dynamicCastList<Long>()
val JsonElement.longList: List<Long>
    get() = toLongList()

fun JsonElement.toBigInteger() = dynamicCast<BigInteger>()
fun JsonElement?.toBigIntegerOrNull() = dynamicCast<BigInteger?>()
fun JsonElement?.toBigIntegerOrDefault(default: BigInteger) = toBigIntegerOrNull() ?: default
fun JsonElement?.toBigIntegerOrElse(default: () -> BigInteger) = toBigIntegerOrNull() ?: default()
val JsonElement.bigInteger: BigInteger
    get() = toBigInteger()
val JsonElement?.nullableBigInteger: BigInteger?
    get() = toBigIntegerOrNull()
fun JsonElement.toBigIntegerList() = dynamicCastList<BigInteger>()
val JsonElement.bigIntegerList: List<BigInteger>
    get() = toBigIntegerList()

fun JsonElement.toFloat() = dynamicCast<Float>()
fun JsonElement?.toFloatOrNull() = dynamicCast<Float?>()
fun JsonElement?.toFloatOrDefault(default: Float) = toFloatOrNull() ?: default
fun JsonElement?.toFloatOrElse(default: () -> Float) = toFloatOrNull() ?: default()
val JsonElement.float: Float
    get() = toFloat()
val JsonElement?.nullableFloat: Float?
    get() = toFloatOrNull()
fun JsonElement.toFloatList() = dynamicCastList<Float>()
val JsonElement.floatList: List<Float>
    get() = toFloatList()

fun JsonElement.toDouble() = dynamicCast<Double>()
fun JsonElement?.toDoubleOrNull() = dynamicCast<Double?>()
fun JsonElement?.toDoubleOrDefault(default: Double) = toDoubleOrNull() ?: default
fun JsonElement?.toDoubleOrElse(default: () -> Double) = toDoubleOrNull() ?: default()
val JsonElement.double: Double
    get() = toDouble()
val JsonElement?.nullableDouble: Double?
    get() = toDoubleOrNull()
fun JsonElement.toDoubleList() = dynamicCastList<Double>()
val JsonElement.doubleList: List<Double>
    get() = toDoubleList()

fun JsonElement.toBigDecimal() = dynamicCast<BigDecimal>()
fun JsonElement?.toBigDecimalOrNull() = dynamicCast<BigDecimal?>()
fun JsonElement?.toBigDecimalOrDefault(default: BigDecimal) = toBigDecimalOrNull() ?: default
fun JsonElement?.toBigDecimalOrElse(default: () -> BigDecimal) = toBigDecimalOrNull() ?: default()
val JsonElement.bigDecimal: BigDecimal
    get() = toBigDecimal()
val JsonElement?.nullableBigDecimal: BigDecimal?
    get() = toBigDecimalOrNull()
fun JsonElement.toBigDecimalList() = dynamicCastList<BigDecimal>()
val JsonElement.bigDecimalList: List<BigDecimal>
    get() = toBigDecimalList()

fun JsonElement.toNumber() = dynamicCast<Number>()
fun JsonElement?.toNumberOrNull() = dynamicCast<Number?>()
fun JsonElement?.toNumberOrDefault(default: Number) = toNumberOrNull() ?: default
fun JsonElement?.toNumberOrElse(default: () -> Number) = toNumberOrNull() ?: default()
val JsonElement.number: Number
    get() = toNumber()
val JsonElement?.nullableNumber: Number?
    get() = toNumberOrNull()
fun JsonElement.toNumberList() = dynamicCastList<Number>()
val JsonElement.numberList: List<Number>
    get() = toNumberList()

fun JsonElement?.toStringOrNull() = dynamicCast<String?>()
fun JsonElement?.toStringOrDefault(default: String) = toStringOrNull() ?: default
fun JsonElement?.toStringOrElse(default: () -> String) = toStringOrNull() ?: default()
val JsonElement.string: String
    get() = dynamicCast()
val JsonElement?.nullableString: String?
    get() = toStringOrNull()
fun JsonElement.toStringList() = dynamicCastList<String>()
val JsonElement.stringList: List<String>
    get() = toStringList()

fun JsonElement.toURI() = dynamicCast<URI>()
fun JsonElement?.toURIOrNull() = dynamicCast<URI?>()
fun JsonElement?.toURIOrDefault(default: URI) = toURIOrNull() ?: default
fun JsonElement?.toURIOrElse(default: () -> URI) = toURIOrNull() ?: default()
val JsonElement.uri: URI
    get() = toURI()
val JsonElement?.nullableUri: URI?
    get() = toURIOrNull()
fun JsonElement.toURIList() = dynamicCastList<URI>()
val JsonElement.uriList: List<URI>
    get() = toURIList()

fun JsonElement.toURL() = dynamicCast<URL>()
fun JsonElement?.toURLOrNull() = dynamicCast<URL?>()
fun JsonElement?.toURLOrDefault(default: URL) = toURLOrNull() ?: default
fun JsonElement?.toURLOrElse(default: () -> URL) = toURLOrNull() ?: default()
val JsonElement.url: URL
    get() = toURL()
val JsonElement?.nullableUrl: URL?
    get() = toURLOrNull()
fun JsonElement.toURLList() = dynamicCastList<URL>()
val JsonElement.urlList: List<URL>
    get() = toURLList()
