package jp.nephy.jsonkt

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import jp.nephy.jsonkt.exception.AnyTypeCastException
import jp.nephy.jsonkt.exception.JsonTypeCastException
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.net.URL
import kotlin.reflect.full.starProjectedType

fun Boolean.toJson() = JsonPrimitive(this)
fun Number.toJson() = JsonPrimitive(this)
fun Char.toJson() = JsonPrimitive(this)
fun String.toJson() = JsonPrimitive(this)

fun Any?.toJsonElement(): JsonElement {
    if (this == null) {
        return JsonKt.jsonNull
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

private inline fun <reified T: Any?> JsonElement?.dynamicCast(): T {
    return try {
        if (this == null) {
            throw UnsupportedOperationException()
        }

        @Suppress("IMPLICIT_CAST_TO_ANY")
        when (T::class.java) {
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

fun JsonElement.toBool() = dynamicCast<Boolean>()
fun JsonElement?.toBoolOrNull() = dynamicCast<Boolean?>()
fun JsonElement?.toBoolOrDefault(default: Boolean) = toBoolOrNull() ?: default
fun JsonElement?.toBoolOrElse(default: () -> Boolean) = toBoolOrNull() ?: default()
val JsonElement.bool: Boolean
    get() = toBool()
val JsonElement?.nullableBool: Boolean?
    get() = toBoolOrNull()

fun JsonElement.toByte() = dynamicCast<Byte>()
fun JsonElement?.toByteOrNull() = dynamicCast<Byte?>()
fun JsonElement?.toByteOrDefault(default: Byte) = toByteOrNull() ?: default
fun JsonElement?.toByteOrElse(default: () -> Byte) = toByteOrNull() ?: default()
val JsonElement.byte: Byte
    get() = toByte()
val JsonElement?.nullableByte: Byte?
    get() = toByteOrNull()

fun JsonElement.toChar() = dynamicCast<Char>()
fun JsonElement?.toCharOrNull() = dynamicCast<Char?>()
fun JsonElement?.toCharOrDefault(default: Char) = toCharOrNull() ?: default
fun JsonElement?.toCharOrElse(default: () -> Char) = toCharOrNull() ?: default()
val JsonElement.char: Char
    get() = toChar()
val JsonElement?.nullableChar: Char?
    get() = toCharOrNull()

fun JsonElement.toShort() = dynamicCast<Short>()
fun JsonElement?.toShortOrNull() = dynamicCast<Short?>()
fun JsonElement?.toShortOrDefault(default: Short) = toShortOrNull() ?: default
fun JsonElement?.toShortOrElse(default: () -> Short) = toShortOrNull() ?: default()
val JsonElement.short: Short
    get() = toShort()
val JsonElement?.nullableShort: Short?
    get() = toShortOrNull()

fun JsonElement.toInt() = dynamicCast<Int>()
fun JsonElement?.toIntOrNull() = dynamicCast<Int?>()
fun JsonElement?.toIntOrDefault(default: Int) = toIntOrNull() ?: default
fun JsonElement?.toIntOrElse(default: () -> Int) = toIntOrNull() ?: default()
val JsonElement.int: Int
    get() = toInt()
val JsonElement?.nullableInt: Int?
    get() = toIntOrNull()

fun JsonElement.toLong() = dynamicCast<Long>()
fun JsonElement?.toLongOrNull() = dynamicCast<Long?>()
fun JsonElement?.toLongOrDefault(default: Long) = toLongOrNull() ?: default
fun JsonElement?.toLongOrElse(default: () -> Long) = toLongOrNull() ?: default()
val JsonElement.long: Long
    get() = toLong()
val JsonElement?.nullableLong: Long?
    get() = toLongOrNull()

fun JsonElement.toBigInteger() = dynamicCast<BigInteger>()
fun JsonElement?.toBigIntegerOrNull() = dynamicCast<BigInteger?>()
fun JsonElement?.toBigIntegerOrDefault(default: BigInteger) = toBigIntegerOrNull() ?: default
fun JsonElement?.toBigIntegerOrElse(default: () -> BigInteger) = toBigIntegerOrNull() ?: default()
val JsonElement.bigInteger: BigInteger
    get() = toBigInteger()
val JsonElement?.nullableBigInteger: BigInteger?
    get() = toBigIntegerOrNull()

fun JsonElement.toFloat() = dynamicCast<Float>()
fun JsonElement?.toFloatOrNull() = dynamicCast<Float?>()
fun JsonElement?.toFloatOrDefault(default: Float) = toFloatOrNull() ?: default
fun JsonElement?.toFloatOrElse(default: () -> Float) = toFloatOrNull() ?: default()
val JsonElement.float: Float
    get() = toFloat()
val JsonElement?.nullableFloat: Float?
    get() = toFloatOrNull()

fun JsonElement.toDouble() = dynamicCast<Double>()
fun JsonElement?.toDoubleOrNull() = dynamicCast<Double?>()
fun JsonElement?.toDoubleOrDefault(default: Double) = toDoubleOrNull() ?: default
fun JsonElement?.toDoubleOrElse(default: () -> Double) = toDoubleOrNull() ?: default()
val JsonElement.double: Double
    get() = toDouble()
val JsonElement?.nullableDouble: Double?
    get() = toDoubleOrNull()

fun JsonElement.toBigDecimal() = dynamicCast<BigDecimal>()
fun JsonElement?.toBigDecimalOrNull() = dynamicCast<BigDecimal?>()
fun JsonElement?.toBigDecimalOrDefault(default: BigDecimal) = toBigDecimalOrNull() ?: default
fun JsonElement?.toBigDecimalOrElse(default: () -> BigDecimal) = toBigDecimalOrNull() ?: default()
val JsonElement.bigDecimal: BigDecimal
    get() = toBigDecimal()
val JsonElement?.nullableBigDecimal: BigDecimal?
    get() = toBigDecimalOrNull()

fun JsonElement?.toStringOrNull() = dynamicCast<String?>()
fun JsonElement?.toStringOrDefault(default: String) = toStringOrNull() ?: default
fun JsonElement?.toStringOrElse(default: () -> String) = toStringOrNull() ?: default()
val JsonElement.string: String
    get() = dynamicCast<String>()
val JsonElement?.nullableString: String?
    get() = toStringOrNull()

fun JsonElement.toURI() = dynamicCast<URI>()
fun JsonElement?.toURIOrNull() = dynamicCast<URI?>()
fun JsonElement?.toURIOrDefault(default: URI) = toURIOrNull() ?: default
fun JsonElement?.toURIOrElse(default: () -> URI) = toURIOrNull() ?: default()
val JsonElement.uri: URI
    get() = toURI()
val JsonElement?.nullableUri: URI?
    get() = toURIOrNull()

fun JsonElement.toURL() = dynamicCast<URL>()
fun JsonElement?.toURLOrNull() = dynamicCast<URL?>()
fun JsonElement?.toURLOrDefault(default: URL) = toURLOrNull() ?: default
fun JsonElement?.toURLOrElse(default: () -> URL) = toURLOrNull() ?: default()
val JsonElement.url: URL
    get() = toURL()
val JsonElement?.nullableUrl: URL?
    get() = toURLOrNull()
