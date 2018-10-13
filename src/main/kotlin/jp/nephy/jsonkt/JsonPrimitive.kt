@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import java.math.BigDecimal
import java.math.BigInteger

typealias GsonJsonPrimitive = com.google.gson.JsonPrimitive

class JsonPrimitive(private val value: GsonJsonPrimitive): GsonCompatible<GsonJsonPrimitive> {
    override fun toGsonObject(): GsonJsonPrimitive {
        return value
    }

    val jsonValue = value.javaClass.getDeclaredField("value").also { it.isAccessible = true }.get(value)!!

    val javaPrimitiveClass: Class<*>
        get() = jsonValue.javaClass

    private inline fun <reified T> safeCast(): T? {
        return jsonValue as? T
    }
    private inline fun <reified T> cast(): T {
        return safeCast() ?: throw JsonCastException(value.toJsonElement(), T::class.java)
    }

    fun isBoolean(): Boolean {
        return value.isBoolean
    }
    fun toBoolean(): Boolean {
        return cast()
    }
    fun toBooleanOrNull(): Boolean? {
        return safeCast()
    }

    fun isByte(): Boolean {
        return jsonValue is Byte
    }
    fun toByte(): Byte {
        return cast()
    }
    fun toByteOrNull(): Byte? {
        return safeCast()
    }

    fun isShort(): Boolean {
        return jsonValue is Short
    }
    fun toShort(): Short {
        return cast()
    }
    fun toShortOrNull(): Short? {
        return safeCast()
    }

    fun isInt(): Boolean {
        return jsonValue is Int
    }
    fun toInt(): Int {
        return cast()
    }
    fun toIntOrNull(): Int? {
        return safeCast()
    }

    fun isLong(): Boolean {
        return jsonValue is Long
    }
    fun toLong(): Long {
        return cast()
    }
    fun toLongOrNull(): Long? {
        return safeCast()
    }

    fun isBigInteger(): Boolean {
        return jsonValue is BigInteger
    }
    fun toBigInteger(): BigInteger {
        return cast()
    }
    fun toBigIntegerOrNull(): BigInteger? {
        return safeCast()
    }

    fun isFloat(): Boolean {
        return jsonValue is Float
    }
    fun toFloat(): Float {
        return cast()
    }
    fun toFloatOrNull(): Float? {
        return safeCast()
    }

    fun isDouble(): Boolean {
        return jsonValue is Double
    }
    fun toDouble(): Double {
        return cast()
    }
    fun toDoubleOrNull(): Double? {
        return safeCast()
    }

    fun isBigDecimal(): Boolean {
        return jsonValue is BigDecimal
    }
    fun toBigDecimal(): BigDecimal {
        return cast()
    }
    fun toBigDecimalOrNull(): BigDecimal? {
        return safeCast()
    }

    fun isNumber(): Boolean {
        return value.isNumber
    }

    fun isChar(): Boolean {
        return jsonValue is Char
    }
    fun toChar(): Char {
        return cast()
    }
    fun toCharOrNull(): Char? {
        return safeCast()
    }

    fun isString(): Boolean {
        return value.isString
    }
    fun toStringValue(): String {
        return cast()
    }
    fun toStringValueOrNull(): String? {
        return safeCast()
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

inline val JsonPrimitive.isBigInteger: Boolean
    get() = isBigInteger()
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

inline val JsonPrimitive.isBigDecimal: Boolean
    get() = isBigDecimal()
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
