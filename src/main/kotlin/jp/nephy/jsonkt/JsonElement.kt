@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import java.math.BigDecimal
import java.math.BigInteger

typealias GsonJsonElement = com.google.gson.JsonElement
typealias GsonJsonNull = com.google.gson.JsonNull

class JsonElement private constructor(private val value: Any?): GsonCompatible<GsonJsonElement> {
    constructor(json: ImmutableJsonObject): this(value = json)
    constructor(json: ImmutableJsonArray): this(value = json)
    constructor(json: JsonPrimitive): this(value = json)

    companion object {
        val jsonNull = JsonElement(null)
    }

    override fun toGsonObject(): GsonJsonElement {
        return when (value) {
            is ImmutableJsonObject -> value.toGsonObject()
            is ImmutableJsonArray -> value.toGsonObject()
            is JsonPrimitive -> value.toGsonObject()
            null -> GsonJsonNull.INSTANCE
            else -> throw IllegalArgumentException()
        }
    }

    fun isJsonObject(): Boolean {
        return value is ImmutableJsonObject
    }
    fun asImmutableJsonObject(): ImmutableJsonObject {
        return value as? ImmutableJsonObject ?: throw JsonCastException(value.toString(), ImmutableJsonObject::class.java)
    }
    fun asMutableJsonObject(): MutableJsonObject {
        return value as? MutableJsonObject ?: throw JsonCastException(value.toString(), MutableJsonObject::class.java)
    }

    fun isJsonArray(): Boolean {
        return value is ImmutableJsonArray
    }
    fun asImmutableJsonArray(): ImmutableJsonArray {
        return value as? ImmutableJsonArray ?: throw JsonCastException(value.toString(), ImmutableJsonArray::class.java)
    }
    fun asMutableJsonArray(): MutableJsonArray {
        return value as? MutableJsonArray ?: throw JsonCastException(value.toString(), MutableJsonArray::class.java)
    }

    fun isJsonPrimitive(): Boolean {
        return value is JsonPrimitive
    }
    fun asJsonPrimitive(): JsonPrimitive {
        return value as? JsonPrimitive ?: throw JsonCastException(value.toString(), JsonPrimitive::class.java)
    }

    fun isJsonNull(): Boolean {
        return value == null
    }

    override fun equals(other: Any?): Boolean {
        return value == (other as? JsonElement)?.value
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }

    override fun toString(): String {
        return value.toString()
    }
}

inline fun Any?.toJsonElement(): JsonElement {
    return when (this) {
        null, is GsonJsonNull -> JsonElement.jsonNull
        is ImmutableJsonObject -> JsonElement(this)
        is ImmutableJsonArray -> JsonElement(this)
        is JsonPrimitive -> JsonElement(this)
        is JsonElement -> this
        is GsonJsonObject -> {
            JsonElement(toJsonKt())
        }
        is GsonJsonArray -> {
            JsonElement(toJsonKt())
        }
        is GsonJsonPrimitive -> {
            JsonElement(toJsonKt())
        }
        is Map<*, *> -> JsonElement(immutableJsonObjectOf(*this.toList().map { it.first.toString() to it.second }.toTypedArray()))
        is List<*> -> JsonElement(immutableJsonArrayOf(*this.toList().toTypedArray()))
        is Boolean -> JsonElement(GsonJsonPrimitive(this).toJsonKt())
        is Number -> JsonElement(GsonJsonPrimitive(this).toJsonKt())
        is Char -> JsonElement(GsonJsonPrimitive(this).toJsonKt())
        is String -> JsonElement(GsonJsonPrimitive(this).toJsonKt())
        else -> throw JsonConversionException(this::class.java)
    }
}

/*
  Operator functions
 */

@Throws(JsonKtException::class)
inline operator fun JsonElement.get(key: String): JsonElement {
    return immutableJsonObject[key]
}

@Throws(JsonKtException::class, IndexOutOfBoundsException::class)
inline operator fun JsonElement.get(index: Int): JsonElement {
    return immutableJsonArray[index]
}

@Throws(JsonKtException::class)
inline operator fun JsonElement.set(key: String, value: Any?) {
    mutableJsonObject[key] = value.toJsonElement()
}

@Throws(JsonKtException::class)
inline operator fun JsonElement.set(index: Int, value: Any?) {
    mutableJsonArray[index] = value.toJsonElement()
}


inline val JsonElement.jsonString: String
    get() = toString()


inline val JsonElement.isJsonObject: Boolean
    get() = isJsonObject()
inline val JsonElement.immutableJsonObject: ImmutableJsonObject
    get() = asImmutableJsonObject()
inline val JsonElement.nullableImmutableJsonObject: ImmutableJsonObject?
    get() = toImmutableJsonObjectOrNull()
inline val JsonElement.immutableJsonObjectList: List<ImmutableJsonObject>
    get() = toImmutableJsonObjectList()
inline fun JsonElement.toImmutableJsonObjectOrNull(): ImmutableJsonObject? {
    return try {
        asImmutableJsonObject()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toImmutableJsonObjectOrDefault(defaultValue: ImmutableJsonObject) = toImmutableJsonObjectOrNull() ?: defaultValue
inline fun JsonElement.toImmutableJsonObjectOrElse(defaultValue: () -> ImmutableJsonObject) = toImmutableJsonObjectOrNull() ?: defaultValue()
inline fun JsonElement.toImmutableJsonObjectList() = immutableJsonArray.immutableJsonObjectList

inline val JsonElement.mutableJsonObject: MutableJsonObject
    get() = asMutableJsonObject()
inline val JsonElement.nullableMutableJsonObject: MutableJsonObject?
    get() = toMutableJsonObjectOrNull()
inline val JsonElement.mutableJsonObjectList: List<MutableJsonObject>
    get() = toMutableJsonObjectList()
inline fun JsonElement.toMutableJsonObjectOrNull(): MutableJsonObject? {
    return try {
        asMutableJsonObject()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toMutableJsonObjectOrDefault(defaultValue: MutableJsonObject) = toMutableJsonObjectOrNull() ?: defaultValue
inline fun JsonElement.toMutableJsonObjectOrElse(defaultValue: () -> MutableJsonObject) = toMutableJsonObjectOrNull() ?: defaultValue()
inline fun JsonElement.toMutableJsonObjectList() = immutableJsonArray.mutableJsonObjectList

inline val JsonElement.isJsonArray: Boolean
    get() = isJsonArray()
inline val JsonElement.immutableJsonArray: ImmutableJsonArray
    get() = asImmutableJsonArray()
inline val JsonElement.nullableImmutableJsonArray: ImmutableJsonArray?
    get() = toImmutableJsonArrayOrNull()
inline val JsonElement.immutableJsonArrayList: List<ImmutableJsonArray>
    get() = toImmutableJsonArrayList()
inline fun JsonElement.toImmutableJsonArrayOrNull(): ImmutableJsonArray? {
    return try {
        asImmutableJsonArray()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toImmutableJsonArrayOrDefault(defaultValue: ImmutableJsonArray) = toImmutableJsonArrayOrNull() ?: defaultValue
inline fun JsonElement.toImmutableJsonArrayOrElse(defaultValue: () -> ImmutableJsonArray) = toImmutableJsonArrayOrNull() ?: defaultValue()
inline fun JsonElement.toImmutableJsonArrayList() = immutableJsonArray.immutableJsonArrayList

inline val JsonElement.mutableJsonArray: MutableJsonArray
    get() = asMutableJsonArray()
inline val JsonElement.nullableMutableJsonArray: MutableJsonArray?
    get() = toMutableJsonArrayOrNull()
inline val JsonElement.mutableJsonArrayList: List<MutableJsonArray>
    get() = toMutableJsonArrayList()
inline fun JsonElement.toMutableJsonArrayOrNull(): MutableJsonArray? {
    return try {
        asMutableJsonArray()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toMutableJsonArrayOrDefault(defaultValue: MutableJsonArray) = toMutableJsonArrayOrNull() ?: defaultValue
inline fun JsonElement.toMutableJsonArrayOrElse(defaultValue: () -> MutableJsonArray) = toMutableJsonArrayOrNull() ?: defaultValue()
inline fun JsonElement.toMutableJsonArrayList() = immutableJsonArray.mutableJsonArrayList

inline val JsonElement.isJsonPrimitive: Boolean
    get() = isJsonPrimitive()
inline val JsonElement.jsonPrimitive: JsonPrimitive
    get() = asJsonPrimitive()
inline val JsonElement.nullableJsonPrimitive: JsonPrimitive?
    get() = toJsonPrimitiveOrNull()
inline val JsonElement.jsonPrimitiveList: List<JsonPrimitive>
    get() = toJsonPrimitiveList()
inline fun JsonElement.toJsonPrimitiveOrNull(): JsonPrimitive? {
    return try {
        asJsonPrimitive()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toJsonPrimitiveOrDefault(defaultValue: JsonPrimitive) = toJsonPrimitiveOrNull() ?: defaultValue
inline fun JsonElement.toJsonPrimitiveOrElse(defaultValue: () -> JsonPrimitive) = toJsonPrimitiveOrNull() ?: defaultValue()
inline fun JsonElement.toJsonPrimitiveList() = immutableJsonArray.jsonPrimitiveList

inline val JsonElement.isJsonNull: Boolean
    get() = isJsonNull()

inline val JsonElement.boolean: Boolean
    get() = toBoolean()
inline val JsonElement.nullableBool: Boolean?
    get() = toBooleanOrNull()
inline val JsonElement.booleanList: List<Boolean>
    get() = toBooleanList()
inline fun JsonElement.toBoolean() = jsonPrimitive.boolean
inline fun JsonElement.toBooleanOrNull() = jsonPrimitive.nullableBoolean
inline fun JsonElement.toBooleanOrDefault(defaultValue: Boolean) = toBooleanOrNull() ?: defaultValue
inline fun JsonElement.toBooleanOrElse(defaultValue: () -> Boolean) = toBooleanOrNull() ?: defaultValue()
inline fun JsonElement.toBooleanList() = immutableJsonArray.booleanList

inline val JsonElement.byte: Byte
    get() = toByte()
inline val JsonElement.nullableByte: Byte?
    get() = toByteOrNull()
inline val JsonElement.byteList: List<Byte>
    get() = toByteList()
inline fun JsonElement.toByte() = jsonPrimitive.byte
inline fun JsonElement.toByteOrNull() = jsonPrimitive.nullableByte
inline fun JsonElement.toByteOrDefault(defaultValue: Byte) = toByteOrNull() ?: defaultValue
inline fun JsonElement.toByteOrElse(defaultValue: () -> Byte) = toByteOrNull() ?: defaultValue()
inline fun JsonElement.toByteList() = immutableJsonArray.byteList

inline val JsonElement.char: Char
    get() = toChar()
inline val JsonElement.nullableChar: Char?
    get() = toCharOrNull()
inline val JsonElement.charList: List<Char>
    get() = toCharList()
inline fun JsonElement.toChar() = jsonPrimitive.char
inline fun JsonElement.toCharOrNull() = jsonPrimitive.nullableChar
inline fun JsonElement.toCharOrDefault(defaultValue: Char) = toCharOrNull() ?: defaultValue
inline fun JsonElement.toCharOrElse(defaultValue: () -> Char) = toCharOrNull() ?: defaultValue()
inline fun JsonElement.toCharList() = immutableJsonArray.charList

inline val JsonElement.short: Short
    get() = toShort()
inline val JsonElement.nullableShort: Short?
    get() = toShortOrNull()
inline val JsonElement.shortList: List<Short>
    get() = toShortList()
inline fun JsonElement.toShort() = jsonPrimitive.short
inline fun JsonElement.toShortOrNull() = jsonPrimitive.nullableShort
inline fun JsonElement.toShortOrDefault(defaultValue: Short) = toShortOrNull() ?: defaultValue
inline fun JsonElement.toShortOrElse(defaultValue: () -> Short) = toShortOrNull() ?: defaultValue()
inline fun JsonElement.toShortList() = immutableJsonArray.shortList

inline val JsonElement.int: Int
    get() = toInt()
inline val JsonElement.nullableInt: Int?
    get() = toIntOrNull()
inline val JsonElement.intList: List<Int>
    get() = toIntList()
inline fun JsonElement.toInt() = jsonPrimitive.int
inline fun JsonElement.toIntOrNull() = jsonPrimitive.nullableInt
inline fun JsonElement.toIntOrDefault(defaultValue: Int) = toIntOrNull() ?: defaultValue
inline fun JsonElement.toIntOrElse(defaultValue: () -> Int) = toIntOrNull() ?: defaultValue()
inline fun JsonElement.toIntList() = immutableJsonArray.intList

inline val JsonElement.long: Long
    get() = toLong()
inline val JsonElement.nullableLong: Long?
    get() = toLongOrNull()
inline val JsonElement.longList: List<Long>
    get() = toLongList()
inline fun JsonElement.toLong() = jsonPrimitive.long
inline fun JsonElement.toLongOrNull() = jsonPrimitive.nullableLong
inline fun JsonElement.toLongOrDefault(defaultValue: Long) = toLongOrNull() ?: defaultValue
inline fun JsonElement.toLongOrElse(defaultValue: () -> Long) = toLongOrNull() ?: defaultValue()
inline fun JsonElement.toLongList() = immutableJsonArray.longList

inline val JsonElement.bigInteger: BigInteger
    get() = toBigInteger()
inline val JsonElement.nullableBigInteger: BigInteger?
    get() = toBigIntegerOrNull()
inline val JsonElement.bigIntegerList: List<BigInteger>
    get() = toBigIntegerList()
inline fun JsonElement.toBigInteger() = jsonPrimitive.bigInteger
inline fun JsonElement.toBigIntegerOrNull() = jsonPrimitive.nullableBigInteger
inline fun JsonElement.toBigIntegerOrDefault(defaultValue: BigInteger) = toBigIntegerOrNull() ?: defaultValue
inline fun JsonElement.toBigIntegerOrElse(defaultValue: () -> BigInteger) = toBigIntegerOrNull() ?: defaultValue()
inline fun JsonElement.toBigIntegerList() = immutableJsonArray.bigIntegerList

inline val JsonElement.float: Float
    get() = toFloat()
inline val JsonElement.nullableFloat: Float?
    get() = toFloatOrNull()
inline val JsonElement.floatList: List<Float>
    get() = toFloatList()
inline fun JsonElement.toFloat() = jsonPrimitive.float
inline fun JsonElement.toFloatOrNull() = jsonPrimitive.nullableFloat
inline fun JsonElement.toFloatOrDefault(defaultValue: Float) = toFloatOrNull() ?: defaultValue
inline fun JsonElement.toFloatOrElse(defaultValue: () -> Float) = toFloatOrNull() ?: defaultValue()
inline fun JsonElement.toFloatList() = immutableJsonArray.floatList

inline val JsonElement.double: Double
    get() = toDouble()
inline val JsonElement.nullableDouble: Double?
    get() = toDoubleOrNull()
inline val JsonElement.doubleList: List<Double>
    get() = toDoubleList()
inline fun JsonElement.toDouble() = jsonPrimitive.double
inline fun JsonElement.toDoubleOrNull() = jsonPrimitive.nullableDouble
inline fun JsonElement.toDoubleOrDefault(defaultValue: Double) = toDoubleOrNull() ?: defaultValue
inline fun JsonElement.toDoubleOrElse(defaultValue: () -> Double) = toDoubleOrNull() ?: defaultValue()
inline fun JsonElement.toDoubleList() = immutableJsonArray.doubleList

inline val JsonElement.bigDecimal: BigDecimal
    get() = toBigDecimal()
inline val JsonElement.nullableBigDecimal: BigDecimal?
    get() = toBigDecimalOrNull()
inline val JsonElement.bigDecimalList: List<BigDecimal>
    get() = toBigDecimalList()
inline fun JsonElement.toBigDecimal() = jsonPrimitive.bigDecimal
inline fun JsonElement.toBigDecimalOrNull() = jsonPrimitive.nullableBigDecimal
inline fun JsonElement.toBigDecimalOrDefault(defaultValue: BigDecimal) = toBigDecimalOrNull() ?: defaultValue
inline fun JsonElement.toBigDecimalOrElse(defaultValue: () -> BigDecimal) = toBigDecimalOrNull() ?: defaultValue()
inline fun JsonElement.toBigDecimalList() = immutableJsonArray.bigDecimalList

inline val JsonElement.string: String
    get() = toStringValue()
inline val JsonElement.nullableString: String?
    get() = toStringValueOrNull()
inline val JsonElement.stringList: List<String>
    get() = toStringValueList()
inline fun JsonElement.toStringValue() = jsonPrimitive.string
inline fun JsonElement.toStringValueOrNull() = jsonPrimitive.nullableString
inline fun JsonElement.toStringValueOrDefault(defaultValue: String) = toStringValueOrNull() ?: defaultValue
inline fun JsonElement.toStringValueOrElse(defaultValue: () -> String) = toStringValueOrNull() ?: defaultValue()
inline fun JsonElement.toStringValueList() = immutableJsonArray.stringList
