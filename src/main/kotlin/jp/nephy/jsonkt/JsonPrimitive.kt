@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import java.math.BigDecimal
import java.math.BigInteger

typealias GsonJsonPrimitive = com.google.gson.JsonPrimitive

class JsonPrimitive(private val value: GsonJsonPrimitive): GsonCompatible<GsonJsonPrimitive> {
    override fun toGsonObject(): GsonJsonPrimitive {
        return value
    }

    val jsonValue: Any
        get() = value.javaClass.getDeclaredField("value").also { it.isAccessible = true }.get(value)!!

    private inline fun <reified T> safeCast(operation: (GsonJsonPrimitive) -> T?): T? {
        return try {
            operation(value)
        } catch (e: Exception) {
            null
        }
    }
    private inline fun <reified T> cast(operation: (GsonJsonPrimitive) -> T?): T {
        return safeCast(operation) ?: throw JsonCastException(value.toJsonElement(), T::class.java)
    }

    fun isBoolean(): Boolean {
        return value.isBoolean
    }
    fun toBoolean(): Boolean {
        return cast { it.asBoolean }
    }
    fun toBooleanOrNull(): Boolean? {
        return safeCast { it.asBoolean }
    }

    fun isByte(): Boolean {
        return if (isNumber() && '.' !in toString()) {
            val value = toNumber().toLong()
            Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE
        } else {
            false
        }
    }
    fun toByte(): Byte {
        return cast { it.asByte }
    }
    fun toByteOrNull(): Byte? {
        return safeCast { it.asByte }
    }

    fun isShort(): Boolean {
        return if (isNumber() && '.' !in toString()) {
            val value = toNumber().toLong()
            Short.MIN_VALUE <= value && value <= Short.MAX_VALUE
        } else {
            false
        }
    }
    fun toShort(): Short {
        return cast { it.asShort }
    }
    fun toShortOrNull(): Short? {
        return safeCast { it.asShort }
    }

    fun isInt(): Boolean {
        return if (isNumber() && '.' !in toString()) {
            val value = toNumber().toLong()
            Int.MIN_VALUE <= value && value <= Int.MAX_VALUE
        } else {
            false
        }
    }
    fun toInt(): Int {
        return cast { it.asInt }
    }
    fun toIntOrNull(): Int? {
        return safeCast { it.asInt }
    }

    fun isLong(): Boolean {
        return isNumber() && '.' !in toString() && toString() == toNumber().toLong().toString()
    }
    fun toLong(): Long {
        return cast { it.asLong }
    }
    fun toLongOrNull(): Long? {
        return safeCast { it.asLong }
    }

    fun toBigInteger(): BigInteger {
        return cast { it.asBigInteger }
    }
    fun toBigIntegerOrNull(): BigInteger? {
        return safeCast { it.asBigInteger }
    }

    fun isFloat(): Boolean {
        return if (isNumber() && '.' in toString()) {
            val number = toNumber()
            val value = number.toDouble()
            value.toString() == number.toFloat().toString()
        } else {
            false
        }
    }
    fun toFloat(): Float {
        return cast { it.asFloat }
    }
    fun toFloatOrNull(): Float? {
        return safeCast { it.asFloat }
    }

    fun isDouble(): Boolean {
        return isNumber() && '.' in toString() && !isFloat
    }
    fun toDouble(): Double {
        return cast { it.asDouble }
    }
    fun toDoubleOrNull(): Double? {
        return safeCast { it.asDouble }
    }

    fun toBigDecimal(): BigDecimal {
        return cast { it.asBigDecimal }
    }
    fun toBigDecimalOrNull(): BigDecimal? {
        return safeCast { it.asBigDecimal }
    }

    fun isNumber(): Boolean {
        return value.isNumber
    }
    fun toNumber(): Number {
        return cast { it.asNumber }
    }
    fun toNumberOrNull(): Number? {
        return safeCast { it.asNumber }
    }

    fun isChar(): Boolean {
        return isString && string.length == 1
    }
    fun toChar(): Char {
        return cast { it.asCharacter }
    }
    fun toCharOrNull(): Char? {
        return safeCast { it.asCharacter }
    }

    fun isString(): Boolean {
        return value.isString
    }
    fun toStringValue(): String {
        return cast { it.asString }
    }
    fun toStringValueOrNull(): String? {
        return safeCast { it.asString }
    }

    override fun equals(other: Any?): Boolean {
        return value == (other as? JsonPrimitive)?.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value.toString()
    }
}

inline val JsonPrimitive.isBoolean: Boolean
    get() = isBoolean()
inline val JsonPrimitive.boolean: Boolean
    get() = toBoolean()
inline val JsonPrimitive.nullableBoolean: Boolean?
    get() = toBooleanOrNull()
inline fun JsonPrimitive.toBooleanOrDefault(defaultValue: Boolean) = toBooleanOrNull() ?: defaultValue
inline fun JsonPrimitive.toBooleanOrElse(defaultValue: () -> Boolean) = toBooleanOrNull() ?: defaultValue()

inline val JsonPrimitive.isByte: Boolean
    get() = isByte()
inline val JsonPrimitive.byte: Byte
    get() = toByte()
inline val JsonPrimitive.nullableByte: Byte?
    get() = toByteOrNull()
inline fun JsonPrimitive.toByteOrDefault(defaultValue: Byte) = toByteOrNull() ?: defaultValue
inline fun JsonPrimitive.toByteOrElse(defaultValue: () -> Byte) = toByteOrNull() ?: defaultValue()

inline val JsonPrimitive.isShort: Boolean
    get() = isShort()
inline val JsonPrimitive.short: Short
    get() = toShort()
inline val JsonPrimitive.nullableShort: Short?
    get() = toShortOrNull()
inline fun JsonPrimitive.toShortOrDefault(defaultValue: Short) = toShortOrNull() ?: defaultValue
inline fun JsonPrimitive.toShortOrElse(defaultValue: () -> Short) = toShortOrNull() ?: defaultValue()

inline val JsonPrimitive.isInt: Boolean
    get() = isInt()
inline val JsonPrimitive.int: Int
    get() = toInt()
inline val JsonPrimitive.nullableInt: Int?
    get() = toIntOrNull()
inline fun JsonPrimitive.toIntOrDefault(defaultValue: Int) = toIntOrNull() ?: defaultValue
inline fun JsonPrimitive.toIntOrElse(defaultValue: () -> Int) = toIntOrNull() ?: defaultValue()

inline val JsonPrimitive.isLong: Boolean
    get() = isLong()
inline val JsonPrimitive.long: Long
    get() = toLong()
inline val JsonPrimitive.nullableLong: Long?
    get() = toLongOrNull()
inline fun JsonPrimitive.toLongOrDefault(defaultValue: Long) = toLongOrNull() ?: defaultValue
inline fun JsonPrimitive.toLongOrElse(defaultValue: () -> Long) = toLongOrNull() ?: defaultValue()

inline val JsonPrimitive.bigInteger: BigInteger
    get() = toBigInteger()
inline val JsonPrimitive.nullableBigInteger: BigInteger?
    get() = toBigIntegerOrNull()
inline fun JsonPrimitive.toBigIntegerOrDefault(defaultValue: BigInteger) = toBigIntegerOrNull() ?: defaultValue
inline fun JsonPrimitive.toBigIntegerOrElse(defaultValue: () -> BigInteger) = toBigIntegerOrNull() ?: defaultValue()

inline val JsonPrimitive.isFloat: Boolean
    get() = isFloat()
inline val JsonPrimitive.float: Float
    get() = toFloat()
inline val JsonPrimitive.nullableFloat: Float?
    get() = toFloatOrNull()
inline fun JsonPrimitive.toFloatOrDefault(defaultValue: Float) = toFloatOrNull() ?: defaultValue
inline fun JsonPrimitive.toFloatOrElse(defaultValue: () -> Float) = toFloatOrNull() ?: defaultValue()

inline val JsonPrimitive.isDouble: Boolean
    get() = isDouble()
inline val JsonPrimitive.double: Double
    get() = toDouble()
inline val JsonPrimitive.nullableDouble: Double?
    get() = toDoubleOrNull()
inline fun JsonPrimitive.toDoubleOrDefault(defaultValue: Double) = toDoubleOrNull() ?: defaultValue
inline fun JsonPrimitive.toDoubleOrElse(defaultValue: () -> Double) = toDoubleOrNull() ?: defaultValue()

inline val JsonPrimitive.bigDecimal: BigDecimal
    get() = toBigDecimal()
inline val JsonPrimitive.nullableBigDecimal: BigDecimal?
    get() = toBigDecimalOrNull()
inline fun JsonPrimitive.toBigDecimalOrDefault(defaultValue: BigDecimal) = toBigDecimalOrNull() ?: defaultValue
inline fun JsonPrimitive.toBigDecimalOrElse(defaultValue: () -> BigDecimal) = toBigDecimalOrNull() ?: defaultValue()

inline val JsonPrimitive.isChar: Boolean
    get() = isChar()
inline val JsonPrimitive.char: Char
    get() = toChar()
inline val JsonPrimitive.nullableChar: Char?
    get() = toCharOrNull()
inline fun JsonPrimitive.toCharOrDefault(defaultValue: Char) = toCharOrNull() ?: defaultValue
inline fun JsonPrimitive.toCharOrElse(defaultValue: () -> Char) = toCharOrNull() ?: defaultValue()

inline val JsonPrimitive.isString: Boolean
    get() = isString()
inline val JsonPrimitive.string: String
    get() = toStringValue()
inline val JsonPrimitive.nullableString: String?
    get() = toStringValueOrNull()
inline fun JsonPrimitive.toStringOrDefault(defaultValue: String) = toStringValueOrNull() ?: defaultValue
inline fun JsonPrimitive.toStringOrElse(defaultValue: () -> String) = toStringValueOrNull() ?: defaultValue()
