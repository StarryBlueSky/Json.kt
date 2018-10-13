@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import java.math.BigDecimal
import java.math.BigInteger

private typealias GsonJsonElement = com.google.gson.JsonElement

class JsonElement(private val value: GsonJsonElement): GsonCompatible<GsonJsonElement> {
    override fun toGsonObject(): GsonJsonElement {
        return value
    }

    fun isJsonObject(): Boolean {
        return value.isJsonObject
    }
    fun toImmutableJsonObject(): ImmutableJsonObject {
        return ImmutableJsonObject(value.asJsonObject)
    }
    fun toMutableJsonObject(): MutableJsonObject {
        return MutableJsonObject(value.asJsonObject)
    }

    fun isJsonArray(): Boolean {
        return value.isJsonArray
    }
    fun toImmutableJsonArray(): ImmutableJsonArray {
        return ImmutableJsonArray(value.asJsonArray)
    }
    fun toMutableJsonArray(): MutableJsonArray {
        return MutableJsonArray(value.asJsonArray)
    }

    fun isJsonPrimitive(): Boolean {
        return value.isJsonPrimitive
    }
    fun toJsonPrimitive(): JsonPrimitive {
        return JsonPrimitive(value.asJsonPrimitive)
    }

    fun isJsonNull(): Boolean {
        return value.isJsonNull
    }

    override fun toString(): String {
        return value.toString()
    }
}

val jsonNull = JsonElement(com.google.gson.JsonNull.INSTANCE)

inline fun Any?.toJsonElement(): JsonElement {
    return JsonElement(when (this) {
        null -> return jsonNull
        is JsonElement -> return this
        is GsonJsonElement -> this
        is Boolean -> GsonJsonPrimitive(this)
        is Number -> GsonJsonPrimitive(this)
        is Char -> GsonJsonPrimitive(this)
        is String -> GsonJsonPrimitive(this)
        is GsonCompatible<*> -> toGsonObject()
        else -> throw JsonConversionException(this::class.java)
    })
}

/*
  Operator functions
 */

@Throws(JsonKtException::class)
inline operator fun JsonElement.get(key: String): JsonElement? {
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


inline val JsonElement.immutableJsonObject: ImmutableJsonObject
    get() = toImmutableJsonObject()
inline val JsonElement.nullableImmutableJsonObject: ImmutableJsonObject?
    get() = toImmutableJsonObjectOrNull()
inline fun JsonElement.toImmutableJsonObjectOrNull(): ImmutableJsonObject? {
    return try {
        toImmutableJsonObject()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toImmutableJsonObjectOrDefault(defaultValue: ImmutableJsonObject) = toImmutableJsonObjectOrNull() ?: defaultValue
inline fun JsonElement.toImmutableJsonObjectOrElse(defaultValue: () -> ImmutableJsonObject) = toImmutableJsonObjectOrNull() ?: defaultValue()
//inline fun JsonElement.toJsonObjectList() = dynamicCastList<JsonObject>()
//inline val JsonElement.jsonObjectList: List<JsonObject>
//    get() = toJsonObjectList()

inline val JsonElement.mutableJsonObject: MutableJsonObject
    get() = toMutableJsonObject()
inline val JsonElement.nullableMutableJsonObject: MutableJsonObject?
    get() = toMutableJsonObjectOrNull()
inline fun JsonElement.toMutableJsonObjectOrNull(): MutableJsonObject? {
    return try {
        toMutableJsonObject()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toMutableJsonObjectOrDefault(defaultValue: MutableJsonObject) = toMutableJsonObjectOrNull() ?: defaultValue
inline fun JsonElement.toMutableJsonObjectOrElse(defaultValue: () -> MutableJsonObject) = toMutableJsonObjectOrNull() ?: defaultValue()

inline val JsonElement.immutableJsonArray: ImmutableJsonArray
    get() = toImmutableJsonArray()
inline val JsonElement.nullableImmutableJsonArray: ImmutableJsonArray?
    get() = toImmutableJsonArrayOrNull()
inline fun JsonElement.toImmutableJsonArrayOrNull(): ImmutableJsonArray? {
    return try {
        toImmutableJsonArray()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toImmutableJsonArrayOrDefault(defaultValue: ImmutableJsonArray) = toImmutableJsonArrayOrNull() ?: defaultValue
inline fun JsonElement.toImmutableJsonArrayOrElse(defaultValue: () -> ImmutableJsonArray) = toImmutableJsonArrayOrNull() ?: defaultValue()
//inline fun JsonElement.toJsonArrayList() = dynamicCastList<JsonArray>()
//inline val JsonElement.jsonArrayList: List<JsonArray>
//    get() = toJsonArrayList()

inline val JsonElement.mutableJsonArray: MutableJsonArray
    get() = toMutableJsonArray()
inline val JsonElement.nullableMutableJsonArray: MutableJsonArray?
    get() = toMutableJsonArrayOrNull()
inline fun JsonElement.toMutableJsonArrayOrNull(): MutableJsonArray? {
    return try {
        toMutableJsonArray()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toMutableJsonArrayOrDefault(defaultValue: MutableJsonArray) = toMutableJsonArrayOrNull() ?: defaultValue
inline fun JsonElement.toMutableJsonArrayOrElse(defaultValue: () -> MutableJsonArray) = toMutableJsonArrayOrNull() ?: defaultValue()

inline val JsonElement.jsonPrimitive: JsonPrimitive
    get() = toJsonPrimitive()
inline val JsonElement.nullableJsonPrimitive: JsonPrimitive?
    get() = toJsonPrimitiveOrNull()
inline fun JsonElement.toJsonPrimitiveOrNull(): JsonPrimitive? {
    return try {
        toJsonPrimitive()
    } catch (e: Exception) {
        null
    }
}
inline fun JsonElement.toJsonPrimitiveOrDefault(defaultValue: JsonPrimitive) = toJsonPrimitiveOrNull() ?: defaultValue
inline fun JsonElement.toJsonPrimitiveOrElse(defaultValue: () -> JsonPrimitive) = toJsonPrimitiveOrNull() ?: defaultValue()
//inline fun JsonElement.toJsonPrimitiveList() = dynamicCastList<JsonPrimitive>()
//inline val JsonElement.jsonPrimitiveList: List<JsonPrimitive>
//    get() = toJsonPrimitiveList()

inline val JsonElement.boolean: Boolean
    get() = toBoolean()
inline val JsonElement.nullableBool: Boolean?
    get() = toBooleanOrNull()
inline fun JsonElement.toBoolean() = jsonPrimitive.boolean
inline fun JsonElement.toBooleanOrNull() = jsonPrimitive.nullableBoolean
inline fun JsonElement.toBooleanOrDefault(defaultValue: Boolean) = toBooleanOrNull() ?: defaultValue
inline fun JsonElement.toBooleanOrElse(defaultValue: () -> Boolean) = toBooleanOrNull() ?: defaultValue()
//inline fun JsonElement.toBoolList() = dynamicCastList<Boolean>()
//inline val JsonElement.boolList: List<Boolean>
//    get() = toBoolList()

inline val JsonElement.byte: Byte
    get() = toByte()
inline val JsonElement.nullableByte: Byte?
    get() = toByteOrNull()
inline fun JsonElement.toByte() = jsonPrimitive.byte
inline fun JsonElement.toByteOrNull() = jsonPrimitive.nullableByte
inline fun JsonElement.toByteOrDefault(defaultValue: Byte) = toByteOrNull() ?: defaultValue
inline fun JsonElement.toByteOrElse(defaultValue: () -> Byte) = toByteOrNull() ?: defaultValue()
//inline fun JsonElement.toByteList() = dynamicCastList<Byte>()
//inline val JsonElement.byteList: List<Byte>
//    get() = toByteList()

inline val JsonElement.char: Char
    get() = toChar()
inline val JsonElement.nullableChar: Char?
    get() = toCharOrNull()
inline fun JsonElement.toChar() = jsonPrimitive.char
inline fun JsonElement.toCharOrNull() = jsonPrimitive.nullableChar
inline fun JsonElement.toCharOrDefault(defaultValue: Char) = toCharOrNull() ?: defaultValue
inline fun JsonElement.toCharOrElse(defaultValue: () -> Char) = toCharOrNull() ?: defaultValue()
//inline fun JsonElement.toCharList() = dynamicCastList<Char>()
//inline val JsonElement.charList: List<Char>
//    get() = toCharList()

inline val JsonElement.short: Short
    get() = toShort()
inline val JsonElement.nullableShort: Short?
    get() = toShortOrNull()
inline fun JsonElement.toShort() = jsonPrimitive.short
inline fun JsonElement.toShortOrNull() = jsonPrimitive.nullableShort
inline fun JsonElement.toShortOrDefault(defaultValue: Short) = toShortOrNull() ?: defaultValue
inline fun JsonElement.toShortOrElse(defaultValue: () -> Short) = toShortOrNull() ?: defaultValue()
//inline fun JsonElement.toShortList() = dynamicCastList<Short>()
//inline val JsonElement.shortList: List<Short>
//    get() = toShortList()

inline val JsonElement.int: Int
    get() = toInt()
inline val JsonElement.nullableInt: Int?
    get() = toIntOrNull()
inline fun JsonElement.toInt() = jsonPrimitive.int
inline fun JsonElement.toIntOrNull() = jsonPrimitive.nullableInt
inline fun JsonElement.toIntOrDefault(defaultValue: Int) = toIntOrNull() ?: defaultValue
inline fun JsonElement.toIntOrElse(defaultValue: () -> Int) = toIntOrNull() ?: defaultValue()
//inline fun JsonElement.toIntList() = dynamicCastList<Int>()
//inline val JsonElement.intList: List<Int>
//    get() = toIntList()

inline val JsonElement.long: Long
    get() = toLong()
inline val JsonElement.nullableLong: Long?
    get() = toLongOrNull()
inline fun JsonElement.toLong() = jsonPrimitive.long
inline fun JsonElement.toLongOrNull() = jsonPrimitive.nullableLong
inline fun JsonElement.toLongOrDefault(defaultValue: Long) = toLongOrNull() ?: defaultValue
inline fun JsonElement.toLongOrElse(defaultValue: () -> Long) = toLongOrNull() ?: defaultValue()
//inline fun JsonElement.toLongList() = dynamicCastList<Long>()
//inline val JsonElement.longList: List<Long>
//    get() = toLongList()

inline val JsonElement.bigInteger: BigInteger
    get() = toBigInteger()
inline val JsonElement.nullableBigInteger: BigInteger?
    get() = toBigIntegerOrNull()
inline fun JsonElement.toBigInteger() = jsonPrimitive.bigInteger
inline fun JsonElement.toBigIntegerOrNull() = jsonPrimitive.nullableBigInteger
inline fun JsonElement.toBigIntegerOrDefault(defaultValue: BigInteger) = toBigIntegerOrNull() ?: defaultValue
inline fun JsonElement.toBigIntegerOrElse(defaultValue: () -> BigInteger) = toBigIntegerOrNull() ?: defaultValue()
//inline fun JsonElement.toBigIntegerList() = dynamicCastList<BigInteger>()
//inline val JsonElement.bigIntegerList: List<BigInteger>
//    get() = toBigIntegerList()

inline val JsonElement.float: Float
    get() = toFloat()
inline val JsonElement.nullableFloat: Float?
    get() = toFloatOrNull()
inline fun JsonElement.toFloat() = jsonPrimitive.float
inline fun JsonElement.toFloatOrNull() = jsonPrimitive.nullableFloat
inline fun JsonElement.toFloatOrDefault(defaultValue: Float) = toFloatOrNull() ?: defaultValue
inline fun JsonElement.toFloatOrElse(defaultValue: () -> Float) = toFloatOrNull() ?: defaultValue()
//inline fun JsonElement.toFloatList() = dynamicCastList<Float>()
//inline val JsonElement.floatList: List<Float>
//    get() = toFloatList()

inline val JsonElement.double: Double
    get() = toDouble()
inline val JsonElement.nullableDouble: Double?
    get() = toDoubleOrNull()
inline fun JsonElement.toDouble() = jsonPrimitive.double
inline fun JsonElement.toDoubleOrNull() = jsonPrimitive.nullableDouble
inline fun JsonElement.toDoubleOrDefault(defaultValue: Double) = toDoubleOrNull() ?: defaultValue
inline fun JsonElement.toDoubleOrElse(defaultValue: () -> Double) = toDoubleOrNull() ?: defaultValue()
//inline fun JsonElement.toDoubleList() = dynamicCastList<Double>()
//inline val JsonElement.doubleList: List<Double>
//    get() = toDoubleList()

inline val JsonElement.bigDecimal: BigDecimal
    get() = toBigDecimal()
inline val JsonElement.nullableBigDecimal: BigDecimal?
    get() = toBigDecimalOrNull()
inline fun JsonElement.toBigDecimal() = jsonPrimitive.bigDecimal
inline fun JsonElement.toBigDecimalOrNull() = jsonPrimitive.nullableBigDecimal
inline fun JsonElement.toBigDecimalOrDefault(defaultValue: BigDecimal) = toBigDecimalOrNull() ?: defaultValue
inline fun JsonElement.toBigDecimalOrElse(defaultValue: () -> BigDecimal) = toBigDecimalOrNull() ?: defaultValue()
//inline fun JsonElement.toBigDecimalList() = dynamicCastList<BigDecimal>()
//inline val JsonElement.bigDecimalList: List<BigDecimal>
//    get() = toBigDecimalList()

inline val JsonElement.string: String
    get() = toStringValue()
inline val JsonElement.nullableString: String?
    get() = toStringValueOrNull()
inline fun JsonElement.toStringValue() = jsonPrimitive.string
inline fun JsonElement.toStringValueOrNull() = jsonPrimitive.nullableString
inline fun JsonElement.toStringOrDefault(defaultValue: String) = toStringValueOrNull() ?: defaultValue
inline fun JsonElement.toStringOrElse(defaultValue: () -> String) = toStringValueOrNull() ?: defaultValue()
//inline fun JsonElement.toStringList() = dynamicCastList<String>()
//inline val JsonElement.stringList: List<String>
//    get() = toStringList()
