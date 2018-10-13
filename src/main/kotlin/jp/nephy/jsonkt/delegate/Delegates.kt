@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt.delegate

import jp.nephy.jsonkt.*
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass

inline fun <T> JsonObject.byLambda(key: String? = null, noinline default: JsonObjectSelector<T>? = null, noinline lambda: JsonElementConverter<T>) = JsonDelegate(this, key, null, default, lambda)
inline fun <T> JsonObject.byNullableLambda(key: String? = null, noinline default: JsonObjectSelector<T?>? = null, noinline lambda: JsonElementConverter<T?>) = JsonDelegate(this, key, null, default, lambda)

inline fun <T> JsonObject.byLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline lambda: JsonElementConverter<T>) = JsonArrayDelegate(this, key, null, default, lambda)
inline fun <T> JsonObject.byNullableLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline lambda: JsonElementConverter<T?>) = JsonArrayDelegate(this, key, null, default, lambda)

inline fun <reified T, R: JsonEnum<T>> JsonObject.byEnum(enum: KClass<R>, key: String? = null, noinline default: () -> R) = JsonDelegate(this, key, null, { default() }, {
    val casted = it.jsonPrimitive.jsonValue as? T
    enum.java.enumConstants.find { const -> const.value == casted } ?: default()
})
inline fun <reified T, R: JsonEnum<T>> JsonObject.byEnumList(enum: KClass<R>, key: String? = null, unknown: R, noinline default: () -> List<R> = { emptyList() }) = JsonArrayDelegate(this, key, null, { default() }, {
    val casted = it.jsonPrimitive.jsonValue as? T
    enum.java.enumConstants.find { const -> const.value == casted } ?: unknown
})

inline fun <reified T: JsonModel?> JsonObject.byModel(key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonDelegate(this, key, T::class.java, default, null)
inline fun <reified T: JsonModel?> JsonObject.byModel(vararg args: Any, key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonDelegate(this, key, T::class.java, default, null, *args)
inline fun <reified T: JsonModel?> JsonObject.byModelList(key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayDelegate(this, key, T::class.java, default, null)
inline fun <reified T: JsonModel?> JsonObject.byModelList(vararg args: Any, key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayDelegate(this, key, T::class.java, default, null, *args)

inline fun JsonObject.byJsonObject(key: String? = null, noinline default: JsonObjectSelector<JsonObject>? = null) = byLambda(key, default) { it.immutableJsonObject }
inline val JsonObject.byJsonObject: JsonDelegate<JsonObject>
    get() = byJsonObject()
inline fun JsonObject.byNullableJsonObject(key: String? = null, noinline default: JsonObjectSelector<JsonObject?>? = null) = byNullableLambda(key, default) { it.immutableJsonObject }
inline val JsonObject.byNullableJsonObject: JsonDelegate<JsonObject?>
    get() = byNullableJsonObject()
inline fun JsonObject.byJsonObjectList(key: String? = null, noinline default: JsonArraySelector<JsonObject>? = null) = byLambdaList(key, default) { it.immutableJsonObject }
inline val JsonObject.byJsonObjectList: JsonArrayDelegate<JsonObject>
    get() = byJsonObjectList()
inline fun JsonObject.byNullableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<JsonObject?>? = null) = byNullableLambdaList(key, default) { it.immutableJsonObject }
inline val JsonObject.byNullableJsonObjectList: JsonArrayDelegate<JsonObject?>
    get() = byNullableJsonObjectList()

inline fun JsonObject.byJsonArray(key: String? = null, noinline default: JsonObjectSelector<JsonArray>? = null) = byLambda(key, default) { it.immutableJsonArray }
inline val JsonObject.byJsonArray: JsonDelegate<JsonArray>
    get() = byJsonArray()
inline fun JsonObject.byNullableJsonArray(key: String? = null, noinline default: JsonObjectSelector<JsonArray?>? = null) = byNullableLambda(key, default) { it.immutableJsonArray }
inline val JsonObject.byNullableJsonArray: JsonDelegate<JsonArray?>
    get() = byNullableJsonArray()
inline fun JsonObject.byJsonArrayList(key: String? = null, noinline default: JsonArraySelector<JsonArray>? = null) = byLambdaList(key, default) { it.immutableJsonArray }
inline val JsonObject.byJsonArrayList: JsonArrayDelegate<JsonArray>
    get() = byJsonArrayList()
inline fun JsonObject.byNullableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<JsonArray?>? = null) = byNullableLambdaList(key, default) { it.immutableJsonArray }
inline val JsonObject.byNullableJsonArrayList: JsonArrayDelegate<JsonArray?>
    get() = byNullableJsonArrayList()

inline fun JsonObject.byJsonPrimitive(key: String? = null, noinline default: JsonObjectSelector<JsonPrimitive>? = null) = byLambda(key, default) { it.jsonPrimitive }
inline val JsonObject.byJsonPrimitive: JsonDelegate<JsonPrimitive>
    get() = byJsonPrimitive()
inline fun JsonObject.byNullableJsonPrimitive(key: String? = null, noinline default: JsonObjectSelector<JsonPrimitive?>? = null) = byNullableLambda(key, default) { it.jsonPrimitive }
inline val JsonObject.byNullableJsonPrimitive: JsonDelegate<JsonPrimitive?>
    get() = byNullableJsonPrimitive()
inline fun JsonObject.byJsonPrimitiveList(key: String? = null, noinline default: JsonArraySelector<JsonPrimitive>? = null) = byLambdaList(key, default) { it.jsonPrimitive }
inline val JsonObject.byJsonPrimitiveList: JsonArrayDelegate<JsonPrimitive>
    get() = byJsonPrimitiveList()
inline fun JsonObject.byNullableJsonPrimitiveList(key: String? = null, noinline default: JsonArraySelector<JsonPrimitive?>? = null) = byNullableLambdaList(key, default) { it.jsonPrimitive }
inline val JsonObject.byNullableJsonPrimitiveList: JsonArrayDelegate<JsonPrimitive?>
    get() = byNullableJsonPrimitiveList()

inline fun JsonObject.byBoolean(key: String? = null, noinline default: JsonObjectSelector<Boolean>? = null) = byLambda(key, default) { it.boolean }
inline val JsonObject.byBoolean: JsonDelegate<Boolean>
    get() = byBoolean()
inline fun JsonObject.byNullableBoolean(key: String? = null, noinline default: JsonObjectSelector<Boolean?>? = null) = byNullableLambda(key, default) { it.boolean }
inline val JsonObject.byNullableBoolean: JsonDelegate<Boolean?>
    get() = byNullableBoolean()
inline fun JsonObject.byBooleanList(key: String? = null, noinline default: JsonArraySelector<Boolean>? = null) = byLambdaList(key, default) { it.boolean }
inline val JsonObject.byBooleanList: JsonArrayDelegate<Boolean>
    get() = byBooleanList()
inline fun JsonObject.byNullableBooleanList(key: String? = null, noinline default: JsonArraySelector<Boolean?>? = null) = byNullableLambdaList(key, default) { it.boolean }
inline val JsonObject.byNullableBooleanList: JsonArrayDelegate<Boolean?>
    get() = byNullableBooleanList()

inline fun JsonObject.byByte(key: String? = null, noinline default: JsonObjectSelector<Byte>? = null) = byLambda(key, default) { it.byte }
inline val JsonObject.byByte: JsonDelegate<Byte>
    get() = byByte()
inline fun JsonObject.byNullableByte(key: String? = null, noinline default: JsonObjectSelector<Byte?>? = null) = byNullableLambda(key, default) { it.byte }
inline val JsonObject.byNullableByte: JsonDelegate<Byte?>
    get() = byNullableByte()
inline fun JsonObject.byByteList(key: String? = null, noinline default: JsonArraySelector<Byte>? = null) = byLambdaList(key, default) { it.byte }
inline val JsonObject.byByteList: JsonArrayDelegate<Byte>
    get() = byByteList()
inline fun JsonObject.byNullableByteList(key: String? = null, noinline default: JsonArraySelector<Byte?>? = null) = byNullableLambdaList(key, default) { it.byte }
inline val JsonObject.byNullableByteList: JsonArrayDelegate<Byte?>
    get() = byNullableByteList()

inline fun JsonObject.byChar(key: String? = null, noinline default: JsonObjectSelector<Char>? = null) = byLambda(key, default) { it.char }
inline val JsonObject.byChar: JsonDelegate<Char>
    get() = byChar()
inline fun JsonObject.byNullableChar(key: String? = null, noinline default: JsonObjectSelector<Char?>? = null) = byNullableLambda(key, default) { it.char }
inline val JsonObject.byNullableChar: JsonDelegate<Char?>
    get() = byNullableChar()
inline fun JsonObject.byCharList(key: String? = null, noinline default: JsonArraySelector<Char>? = null) = byLambdaList(key, default) { it.char }
inline val JsonObject.byCharList: JsonArrayDelegate<Char>
    get() = byCharList()
inline fun JsonObject.byNullableCharList(key: String? = null, noinline default: JsonArraySelector<Char?>? = null) = byNullableLambdaList(key, default) { it.char }
inline val JsonObject.byNullableCharList: JsonArrayDelegate<Char?>
    get() = byNullableCharList()

inline fun JsonObject.byShort(key: String? = null, noinline default: JsonObjectSelector<Short>? = null) = byLambda(key, default) { it.short }
inline val JsonObject.byShort: JsonDelegate<Short>
    get() = byShort()
inline fun JsonObject.byNullableShort(key: String? = null, noinline default: JsonObjectSelector<Short?>? = null) = byNullableLambda(key, default) { it.short }
inline val JsonObject.byNullableShort: JsonDelegate<Short?>
    get() = byNullableShort()
inline fun JsonObject.byShortList(key: String? = null, noinline default: JsonArraySelector<Short>? = null) = byLambdaList(key, default) { it.short }
inline val JsonObject.byShortList: JsonArrayDelegate<Short>
    get() = byShortList()
inline fun JsonObject.byNullableShortList(key: String? = null, noinline default: JsonArraySelector<Short?>? = null) = byNullableLambdaList(key, default) { it.short }
inline val JsonObject.byNullableShortList: JsonArrayDelegate<Short?>
    get() = byNullableShortList()

inline fun JsonObject.byInt(key: String? = null, noinline default: JsonObjectSelector<Int>? = null) = byLambda(key, default) { it.int }
inline val JsonObject.byInt: JsonDelegate<Int>
    get() = byInt()
inline fun JsonObject.byNullableInt(key: String? = null, noinline default: JsonObjectSelector<Int?>? = null) = byNullableLambda(key, default) { it.int }
inline val JsonObject.byNullableInt: JsonDelegate<Int?>
    get() = byNullableInt()
inline fun JsonObject.byIntList(key: String? = null, noinline default: JsonArraySelector<Int>? = null) = byLambdaList(key, default) { it.int }
inline val JsonObject.byIntList: JsonArrayDelegate<Int>
    get() = byIntList()
inline fun JsonObject.byNullableIntList(key: String? = null, noinline default: JsonArraySelector<Int?>? = null) = byNullableLambdaList(key, default) { it.int }
inline val JsonObject.byNullableIntList: JsonArrayDelegate<Int?>
    get() = byNullableIntList()

inline fun JsonObject.byLong(key: String? = null, noinline default: JsonObjectSelector<Long>? = null) = byLambda(key, default) { it.long }
inline val JsonObject.byLong: JsonDelegate<Long>
    get() = byLong()
inline fun JsonObject.byNullableLong(key: String? = null, noinline default: JsonObjectSelector<Long?>? = null) = byNullableLambda(key, default) { it.long }
inline val JsonObject.byNullableLong: JsonDelegate<Long?>
    get() = byNullableLong()
inline fun JsonObject.byLongList(key: String? = null, noinline default: JsonArraySelector<Long>? = null) = byLambdaList(key, default) { it.long }
inline val JsonObject.byLongList: JsonArrayDelegate<Long>
    get() = byLongList()
inline fun JsonObject.byNullableLongList(key: String? = null, noinline default: JsonArraySelector<Long?>? = null) = byNullableLambdaList(key, default) { it.long }
inline val JsonObject.byNullableLongList: JsonArrayDelegate<Long?>
    get() = byNullableLongList()

inline fun JsonObject.byBigInteger(key: String? = null, noinline default: JsonObjectSelector<BigInteger>? = null) = byLambda(key, default) { it.bigInteger }
inline val JsonObject.byBigInteger: JsonDelegate<BigInteger>
    get() = byBigInteger()
inline fun JsonObject.byNullableBigInteger(key: String? = null, noinline default: JsonObjectSelector<BigInteger?>? = null) = byNullableLambda(key, default) { it.bigInteger }
inline val JsonObject.byNullableBigInteger: JsonDelegate<BigInteger?>
    get() = byNullableBigInteger()
inline fun JsonObject.byBigIntegerList(key: String? = null, noinline default: JsonArraySelector<BigInteger>? = null) = byLambdaList(key, default) { it.bigInteger }
inline val JsonObject.byBigIntegerList: JsonArrayDelegate<BigInteger>
    get() = byBigIntegerList()
inline fun JsonObject.byNullableBigIntegerList(key: String? = null, noinline default: JsonArraySelector<BigInteger?>? = null) = byNullableLambdaList(key, default) { it.bigInteger }
inline val JsonObject.byNullableBigIntegerList: JsonArrayDelegate<BigInteger?>
    get() = byNullableBigIntegerList()

inline fun JsonObject.byFloat(key: String? = null, noinline default: JsonObjectSelector<Float>? = null) = byLambda(key, default) { it.float }
inline val JsonObject.byFloat: JsonDelegate<Float>
    get() = byFloat()
inline fun JsonObject.byNullableFloat(key: String? = null, noinline default: JsonObjectSelector<Float?>? = null) = byNullableLambda(key, default) { it.float }
inline val JsonObject.byNullableFloat: JsonDelegate<Float?>
    get() = byNullableFloat()
inline fun JsonObject.byFloatList(key: String? = null, noinline default: JsonArraySelector<Float>? = null) = byLambdaList(key, default) { it.float }
inline val JsonObject.byFloatList: JsonArrayDelegate<Float>
    get() = byFloatList()
inline fun JsonObject.byNullableFloatList(key: String? = null, noinline default: JsonArraySelector<Float?>? = null) = byNullableLambdaList(key, default) { it.float }
inline val JsonObject.byNullableFloatList: JsonArrayDelegate<Float?>
    get() = byNullableFloatList()

inline fun JsonObject.byDouble(key: String? = null, noinline default: JsonObjectSelector<Double>? = null) = byLambda(key, default) { it.double }
inline val JsonObject.byDouble: JsonDelegate<Double>
    get() = byDouble()
inline fun JsonObject.byNullableDouble(key: String? = null, noinline default: JsonObjectSelector<Double?>? = null) = byNullableLambda(key, default) { it.double }
inline val JsonObject.byNullableDouble: JsonDelegate<Double?>
    get() = byNullableDouble()
inline fun JsonObject.byDoubleList(key: String? = null, noinline default: JsonArraySelector<Double>? = null) = byLambdaList(key, default) { it.double }
inline val JsonObject.byDoubleList: JsonArrayDelegate<Double>
    get() = byDoubleList()
inline fun JsonObject.byNullableDoubleList(key: String? = null, noinline default: JsonArraySelector<Double?>? = null) = byNullableLambdaList(key, default) { it.double }
inline val JsonObject.byNullableDoubleList: JsonArrayDelegate<Double?>
    get() = byNullableDoubleList()

inline fun JsonObject.byBigDecimal(key: String? = null, noinline default: JsonObjectSelector<BigDecimal>? = null) = byLambda(key, default) { it.bigDecimal }
inline val JsonObject.byBigDecimal: JsonDelegate<BigDecimal>
    get() = byBigDecimal()
inline fun JsonObject.byNullableBigDecimal(key: String? = null, noinline default: JsonObjectSelector<BigDecimal?>? = null) = byNullableLambda(key, default) { it.bigDecimal }
inline val JsonObject.byNullableBigDecimal: JsonDelegate<BigDecimal?>
    get() = byNullableBigDecimal()
inline fun JsonObject.byBigDecimalList(key: String? = null, noinline default: JsonArraySelector<BigDecimal>? = null) = byLambdaList(key, default) { it.bigDecimal }
inline val JsonObject.byBigDecimalList: JsonArrayDelegate<BigDecimal>
    get() = byBigDecimalList()
inline fun JsonObject.byNullableBigDecimalList(key: String? = null, noinline default: JsonArraySelector<BigDecimal?>? = null) = byNullableLambdaList(key, default) { it.bigDecimal }
inline val JsonObject.byNullableBigDecimalList: JsonArrayDelegate<BigDecimal?>
    get() = byNullableBigDecimalList()

inline fun JsonObject.byString(key: String? = null, noinline default: JsonObjectSelector<String>? = null) = byLambda(key, default) { it.string }
inline val JsonObject.byString: JsonDelegate<String>
    get() = byString()
inline fun JsonObject.byNullableString(key: String? = null, noinline default: JsonObjectSelector<String?>? = null) = byNullableLambda(key, default) { it.string }
inline val JsonObject.byNullableString: JsonDelegate<String?>
    get() = byNullableString()
inline fun JsonObject.byStringList(key: String? = null, noinline default: JsonArraySelector<String>? = null) = byLambdaList(key, default) { it.string }
inline val JsonObject.byStringList: JsonArrayDelegate<String>
    get() = byStringList()
inline fun JsonObject.byNullableStringList(key: String? = null, noinline default: JsonArraySelector<String?>? = null) = byNullableLambdaList(key, default) { it.string }
inline val JsonObject.byNullableStringList: JsonArrayDelegate<String?>
    get() = byNullableStringList()
