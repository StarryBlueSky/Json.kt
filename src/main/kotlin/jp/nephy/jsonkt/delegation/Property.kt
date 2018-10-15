@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass

inline fun <T> JsonObject.byLambda(key: String? = null, noinline default: JsonObjectSelector<T>? = null, noinline converter: JsonElementConverter<T>) = JsonObjectProperty(this, key, null, default, converter)
inline fun <T> JsonModel.lambda(key: String? = null, noinline default: JsonObjectSelector<T>? = null, noinline converter: JsonElementConverter<T>) = JsonObjectProperty(json, key, null, default, converter)

inline fun <T> JsonObject.byNullableLambda(key: String? = null, noinline default: JsonObjectSelector<T?>? = null, noinline converter: JsonElementConverter<T?>) = JsonObjectProperty(this, key, null, default, converter)
inline fun <T> JsonModel.nullableLambda(key: String? = null, noinline default: JsonObjectSelector<T?>? = null, noinline converter: JsonElementConverter<T?>) = JsonObjectProperty(json, key, null, default, converter)

inline fun <T> JsonObject.byLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T>) = JsonArrayProperty(this, key, null, default, converter, null)
inline fun <T> JsonModel.lambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T>) = JsonArrayProperty(json, key, null, default, converter, null)

inline fun <T> JsonObject.byNullableLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T?>) = JsonArrayProperty(this, key, null, default, converter, null)
inline fun <T> JsonModel.nullableLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T?>) = JsonArrayProperty(json, key, null, default, converter, null)

inline fun <T> JsonElement.byMap(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline operation: JsonArrayOperation<T>? = null) = JsonArrayProperty(immutableJsonObject, key, null, default, null, operation)
inline fun <T> JsonModel.map(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline operation: JsonArrayOperation<T>? = null) = JsonArrayProperty(json, key, null, default, null, operation)

inline fun <reified T, R: JsonEnum<T>> JsonObject.byEnum(enum: KClass<R>, key: String? = null, default: R) = JsonObjectProperty(this, key, null, { default }, {
    val casted = it.jsonPrimitive.jsonValue.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: default
})
inline fun <reified T, R: JsonEnum<T>> JsonModel.enum(enum: KClass<R>, key: String? = null, default: R) = JsonObjectProperty(json, key, null, { default }, {
    val casted = it.jsonPrimitive.jsonValue.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: default
})

inline fun <reified T, R: JsonEnum<T>> JsonObject.byEnumList(enum: KClass<R>, key: String? = null, unknown: R, noinline default: () -> List<R> = { emptyList() }) = JsonArrayProperty(this, key, null, { default() }, {
    val casted = it.jsonPrimitive.jsonValue.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: unknown
}, null)
inline fun <reified T, R: JsonEnum<T>> JsonModel.enumList(enum: KClass<R>, key: String? = null, unknown: R, noinline default: () -> List<R> = { emptyList() }) = JsonArrayProperty(json, key, null, { default() }, {
    val casted = it.jsonPrimitive.jsonValue.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: unknown
}, null)

inline fun <reified T: JsonModel?> JsonObject.byModel(key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonObjectProperty(this, key, T::class.java, default, null)
inline fun <reified T: JsonModel?> JsonObject.byModel(vararg args: Any, key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonObjectProperty(this, key, T::class.java, default, null, *args)
inline fun <reified T: JsonModel?> JsonModel.model(key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonObjectProperty(json, key, T::class.java, default, null)
inline fun <reified T: JsonModel?> JsonModel.model(vararg args: Any, key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonObjectProperty(json, key, T::class.java, default, null, *args)

inline fun <reified T: JsonModel?> JsonObject.byModelList(key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayProperty(this, key, T::class.java, default, null, null)
inline fun <reified T: JsonModel?> JsonObject.byModelList(vararg args: Any, key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayProperty(this, key, T::class.java, default, null, null, *args)
inline fun <reified T: JsonModel?> JsonModel.modelList(key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayProperty(json, key, T::class.java, default, null, null)
inline fun <reified T: JsonModel?> JsonModel.modelList(vararg args: Any, key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayProperty(json, key, T::class.java, default, null, null, *args)

inline fun JsonObject.byImmutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonObject>? = null) = byLambda(key, default) { it.immutableJsonObject }
inline val JsonObject.byImmutableJsonObject: JsonObjectProperty<ImmutableJsonObject>
    get() = byImmutableJsonObject()
inline fun JsonObject.byNullableImmutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonObject?>? = null) = byNullableLambda(key, default) { it.immutableJsonObject }
inline val JsonObject.byNullableImmutableJsonObject: JsonObjectProperty<ImmutableJsonObject?>
    get() = byNullableImmutableJsonObject()
inline fun JsonObject.byImmutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonObject>? = null) = byLambdaList(key, default) { it.immutableJsonObject }
inline val JsonObject.byImmutableJsonObjectList: JsonArrayProperty<ImmutableJsonObject>
    get() = byImmutableJsonObjectList()
inline fun JsonObject.byNullableImmutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonObject?>? = null) = byNullableLambdaList(key, default) { it.immutableJsonObject }
inline val JsonObject.byNullableImmutableJsonObjectList: JsonArrayProperty<ImmutableJsonObject?>
    get() = byNullableImmutableJsonObjectList()
inline fun JsonModel.immutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonObject>? = null) = lambda(key, default) { it.immutableJsonObject }
inline val JsonModel.immutableJsonObject: JsonObjectProperty<ImmutableJsonObject>
    get() = immutableJsonObject()
inline fun JsonModel.nullableImmutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonObject?>? = null) = nullableLambda(key, default) { it.immutableJsonObject }
inline val JsonModel.nullableImmutableJsonObject: JsonObjectProperty<ImmutableJsonObject?>
    get() = nullableImmutableJsonObject()
inline fun JsonModel.immutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonObject>? = null) = lambdaList(key, default) { it.immutableJsonObject }
inline val JsonModel.immutableJsonObjectList: JsonArrayProperty<ImmutableJsonObject>
    get() = immutableJsonObjectList()
inline fun JsonModel.nullableImmutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonObject?>? = null) = nullableLambdaList(key, default) { it.immutableJsonObject }
inline val JsonModel.nullableImmutableJsonObjectList: JsonArrayProperty<ImmutableJsonObject?>
    get() = nullableImmutableJsonObjectList()

inline fun JsonObject.byMutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<MutableJsonObject>? = null) = byLambda(key, default) { it.mutableJsonObject }
inline val JsonObject.byMutableJsonObject: JsonObjectProperty<MutableJsonObject>
    get() = byMutableJsonObject()
inline fun JsonObject.byNullableMutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<MutableJsonObject?>? = null) = byNullableLambda(key, default) { it.mutableJsonObject }
inline val JsonObject.byNullableMutableJsonObject: JsonObjectProperty<MutableJsonObject?>
    get() = byNullableMutableJsonObject()
inline fun JsonObject.byMutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<MutableJsonObject>? = null) = byLambdaList(key, default) { it.mutableJsonObject }
inline val JsonObject.byMutableJsonObjectList: JsonArrayProperty<MutableJsonObject>
    get() = byMutableJsonObjectList()
inline fun JsonObject.byNullableMutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<MutableJsonObject?>? = null) = byNullableLambdaList(key, default) { it.mutableJsonObject }
inline val JsonObject.byNullableMutableJsonObjectList: JsonArrayProperty<MutableJsonObject?>
    get() = byNullableMutableJsonObjectList()
inline fun JsonModel.mutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<MutableJsonObject>? = null) = lambda(key, default) { it.mutableJsonObject }
inline val JsonModel.mutableJsonObject: JsonObjectProperty<MutableJsonObject>
    get() = mutableJsonObject()
inline fun JsonModel.nullableMutableJsonObject(key: String? = null, noinline default: JsonObjectSelector<MutableJsonObject?>? = null) = nullableLambda(key, default) { it.mutableJsonObject }
inline val JsonModel.nullableMutableJsonObject: JsonObjectProperty<MutableJsonObject?>
    get() = nullableMutableJsonObject()
inline fun JsonModel.mutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<MutableJsonObject>? = null) = lambdaList(key, default) { it.mutableJsonObject }
inline val JsonModel.mutableJsonObjectList: JsonArrayProperty<MutableJsonObject>
    get() = mutableJsonObjectList()
inline fun JsonModel.nullableMutableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<MutableJsonObject?>? = null) = nullableLambdaList(key, default) { it.mutableJsonObject }
inline val JsonModel.nullableMutableJsonObjectList: JsonArrayProperty<MutableJsonObject?>
    get() = nullableMutableJsonObjectList()

inline fun JsonObject.byImmutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonArray>? = null) = byLambda(key, default) { it.immutableJsonArray }
inline val JsonObject.byImmutableJsonArray: JsonObjectProperty<ImmutableJsonArray>
    get() = byImmutableJsonArray()
inline fun JsonObject.byNullableImmutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonArray?>? = null) = byNullableLambda(key, default) { it.immutableJsonArray }
inline val JsonObject.byNullableImmutableJsonArray: JsonObjectProperty<ImmutableJsonArray?>
    get() = byNullableImmutableJsonArray()
inline fun JsonObject.byImmutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonArray>? = null) = byLambdaList(key, default) { it.immutableJsonArray }
inline val JsonObject.byImmutableJsonArrayList: JsonArrayProperty<ImmutableJsonArray>
    get() = byImmutableJsonArrayList()
inline fun JsonObject.byNullableImmutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonArray?>? = null) = byNullableLambdaList(key, default) { it.immutableJsonArray }
inline val JsonObject.byNullableImmutableJsonArrayList: JsonArrayProperty<ImmutableJsonArray?>
    get() = byNullableImmutableJsonArrayList()
inline fun JsonModel.immutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonArray>? = null) = lambda(key, default) { it.immutableJsonArray }
inline val JsonModel.immutableJsonArray: JsonObjectProperty<ImmutableJsonArray>
    get() = immutableJsonArray()
inline fun JsonModel.nullableImmutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<ImmutableJsonArray?>? = null) = nullableLambda(key, default) { it.immutableJsonArray }
inline val JsonModel.nullableImmutableJsonArray: JsonObjectProperty<ImmutableJsonArray?>
    get() = nullableImmutableJsonArray()
inline fun JsonModel.immutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonArray>? = null) = lambdaList(key, default) { it.immutableJsonArray }
inline val JsonModel.immutableJsonArrayList: JsonArrayProperty<ImmutableJsonArray>
    get() = immutableJsonArrayList()
inline fun JsonModel.nullableImmutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<ImmutableJsonArray?>? = null) = nullableLambdaList(key, default) { it.immutableJsonArray }
inline val JsonModel.nullableImmutableJsonArrayList: JsonArrayProperty<ImmutableJsonArray?>
    get() = nullableImmutableJsonArrayList()

inline fun JsonObject.byMutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<MutableJsonArray>? = null) = byLambda(key, default) { it.mutableJsonArray }
inline val JsonObject.byMutableJsonArray: JsonObjectProperty<MutableJsonArray>
    get() = byMutableJsonArray()
inline fun JsonObject.byNullableMutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<MutableJsonArray?>? = null) = byNullableLambda(key, default) { it.mutableJsonArray }
inline val JsonObject.byNullableMutableJsonArray: JsonObjectProperty<MutableJsonArray?>
    get() = byNullableMutableJsonArray()
inline fun JsonObject.byMutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<MutableJsonArray>? = null) = byLambdaList(key, default) { it.mutableJsonArray }
inline val JsonObject.byMutableJsonArrayList: JsonArrayProperty<MutableJsonArray>
    get() = byMutableJsonArrayList()
inline fun JsonObject.byNullableMutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<MutableJsonArray?>? = null) = byNullableLambdaList(key, default) { it.mutableJsonArray }
inline val JsonObject.byNullableMutableJsonArrayList: JsonArrayProperty<MutableJsonArray?>
    get() = byNullableMutableJsonArrayList()
inline fun JsonModel.mutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<MutableJsonArray>? = null) = lambda(key, default) { it.mutableJsonArray }
inline val JsonModel.mutableJsonArray: JsonObjectProperty<MutableJsonArray>
    get() = mutableJsonArray()
inline fun JsonModel.nullableMutableJsonArray(key: String? = null, noinline default: JsonObjectSelector<MutableJsonArray?>? = null) = nullableLambda(key, default) { it.mutableJsonArray }
inline val JsonModel.nullableMutableJsonArray: JsonObjectProperty<MutableJsonArray?>
    get() = nullableMutableJsonArray()
inline fun JsonModel.mutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<MutableJsonArray>? = null) = lambdaList(key, default) { it.mutableJsonArray }
inline val JsonModel.mutableJsonArrayList: JsonArrayProperty<MutableJsonArray>
    get() = mutableJsonArrayList()
inline fun JsonModel.nullableMutableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<MutableJsonArray?>? = null) = nullableLambdaList(key, default) { it.mutableJsonArray }
inline val JsonModel.nullableMutableJsonArrayList: JsonArrayProperty<MutableJsonArray?>
    get() = nullableMutableJsonArrayList()

inline fun JsonObject.byJsonPrimitive(key: String? = null, noinline default: JsonObjectSelector<JsonPrimitive>? = null) = byLambda(key, default) { it.jsonPrimitive }
inline val JsonObject.byJsonPrimitive: JsonObjectProperty<JsonPrimitive>
    get() = byJsonPrimitive()
inline fun JsonObject.byNullableJsonPrimitive(key: String? = null, noinline default: JsonObjectSelector<JsonPrimitive?>? = null) = byNullableLambda(key, default) { it.jsonPrimitive }
inline val JsonObject.byNullableJsonPrimitive: JsonObjectProperty<JsonPrimitive?>
    get() = byNullableJsonPrimitive()
inline fun JsonObject.byJsonPrimitiveList(key: String? = null, noinline default: JsonArraySelector<JsonPrimitive>? = null) = byLambdaList(key, default) { it.jsonPrimitive }
inline val JsonObject.byJsonPrimitiveList: JsonArrayProperty<JsonPrimitive>
    get() = byJsonPrimitiveList()
inline fun JsonObject.byNullableJsonPrimitiveList(key: String? = null, noinline default: JsonArraySelector<JsonPrimitive?>? = null) = byNullableLambdaList(key, default) { it.jsonPrimitive }
inline val JsonObject.byNullableJsonPrimitiveList: JsonArrayProperty<JsonPrimitive?>
    get() = byNullableJsonPrimitiveList()
inline fun JsonModel.jsonPrimitive(key: String? = null, noinline default: JsonObjectSelector<JsonPrimitive>? = null) = lambda(key, default) { it.jsonPrimitive }
inline val JsonModel.jsonPrimitive: JsonObjectProperty<JsonPrimitive>
    get() = jsonPrimitive()
inline fun JsonModel.nullableJsonPrimitive(key: String? = null, noinline default: JsonObjectSelector<JsonPrimitive?>? = null) = nullableLambda(key, default) { it.jsonPrimitive }
inline val JsonModel.nullableJsonPrimitive: JsonObjectProperty<JsonPrimitive?>
    get() = nullableJsonPrimitive()
inline fun JsonModel.jsonPrimitiveList(key: String? = null, noinline default: JsonArraySelector<JsonPrimitive>? = null) = lambdaList(key, default) { it.jsonPrimitive }
inline val JsonModel.jsonPrimitiveList: JsonArrayProperty<JsonPrimitive>
    get() = jsonPrimitiveList()
inline fun JsonModel.nullableJsonPrimitiveList(key: String? = null, noinline default: JsonArraySelector<JsonPrimitive?>? = null) = nullableLambdaList(key, default) { it.jsonPrimitive }
inline val JsonModel.nullableJsonPrimitiveList: JsonArrayProperty<JsonPrimitive?>
    get() = nullableJsonPrimitiveList()

inline fun JsonObject.byBoolean(key: String? = null, noinline default: JsonObjectSelector<Boolean>? = null) = byLambda(key, default) { it.boolean }
inline val JsonObject.byBoolean: JsonObjectProperty<Boolean>
    get() = byBoolean()
inline fun JsonObject.byNullableBoolean(key: String? = null, noinline default: JsonObjectSelector<Boolean?>? = null) = byNullableLambda(key, default) { it.boolean }
inline val JsonObject.byNullableBoolean: JsonObjectProperty<Boolean?>
    get() = byNullableBoolean()
inline fun JsonObject.byBooleanList(key: String? = null, noinline default: JsonArraySelector<Boolean>? = null) = byLambdaList(key, default) { it.boolean }
inline val JsonObject.byBooleanList: JsonArrayProperty<Boolean>
    get() = byBooleanList()
inline fun JsonObject.byNullableBooleanList(key: String? = null, noinline default: JsonArraySelector<Boolean?>? = null) = byNullableLambdaList(key, default) { it.boolean }
inline val JsonObject.byNullableBooleanList: JsonArrayProperty<Boolean?>
    get() = byNullableBooleanList()
inline fun JsonModel.boolean(key: String? = null, noinline default: JsonObjectSelector<Boolean>? = null) = lambda(key, default) { it.boolean }
inline val JsonModel.boolean: JsonObjectProperty<Boolean>
    get() = boolean()
inline fun JsonModel.nullableBoolean(key: String? = null, noinline default: JsonObjectSelector<Boolean?>? = null) = nullableLambda(key, default) { it.boolean }
inline val JsonModel.nullableBoolean: JsonObjectProperty<Boolean?>
    get() = nullableBoolean()
inline fun JsonModel.booleanList(key: String? = null, noinline default: JsonArraySelector<Boolean>? = null) = lambdaList(key, default) { it.boolean }
inline val JsonModel.booleanList: JsonArrayProperty<Boolean>
    get() = booleanList()
inline fun JsonModel.nullableBooleanList(key: String? = null, noinline default: JsonArraySelector<Boolean?>? = null) = nullableLambdaList(key, default) { it.boolean }
inline val JsonModel.nullableBooleanList: JsonArrayProperty<Boolean?>
    get() = nullableBooleanList()

inline fun JsonObject.byByte(key: String? = null, noinline default: JsonObjectSelector<Byte>? = null) = byLambda(key, default) { it.byte }
inline val JsonObject.byByte: JsonObjectProperty<Byte>
    get() = byByte()
inline fun JsonObject.byNullableByte(key: String? = null, noinline default: JsonObjectSelector<Byte?>? = null) = byNullableLambda(key, default) { it.byte }
inline val JsonObject.byNullableByte: JsonObjectProperty<Byte?>
    get() = byNullableByte()
inline fun JsonObject.byByteList(key: String? = null, noinline default: JsonArraySelector<Byte>? = null) = byLambdaList(key, default) { it.byte }
inline val JsonObject.byByteList: JsonArrayProperty<Byte>
    get() = byByteList()
inline fun JsonObject.byNullableByteList(key: String? = null, noinline default: JsonArraySelector<Byte?>? = null) = byNullableLambdaList(key, default) { it.byte }
inline val JsonObject.byNullableByteList: JsonArrayProperty<Byte?>
    get() = byNullableByteList()
inline fun JsonModel.byte(key: String? = null, noinline default: JsonObjectSelector<Byte>? = null) = lambda(key, default) { it.byte }
inline val JsonModel.byte: JsonObjectProperty<Byte>
    get() = byte()
inline fun JsonModel.nullableByte(key: String? = null, noinline default: JsonObjectSelector<Byte?>? = null) = nullableLambda(key, default) { it.byte }
inline val JsonModel.nullableByte: JsonObjectProperty<Byte?>
    get() = nullableByte()
inline fun JsonModel.byteList(key: String? = null, noinline default: JsonArraySelector<Byte>? = null) = lambdaList(key, default) { it.byte }
inline val JsonModel.byteList: JsonArrayProperty<Byte>
    get() = byteList()
inline fun JsonModel.nullableByteList(key: String? = null, noinline default: JsonArraySelector<Byte?>? = null) = nullableLambdaList(key, default) { it.byte }
inline val JsonModel.nullableByteList: JsonArrayProperty<Byte?>
    get() = nullableByteList()

inline fun JsonObject.byChar(key: String? = null, noinline default: JsonObjectSelector<Char>? = null) = byLambda(key, default) { it.char }
inline val JsonObject.byChar: JsonObjectProperty<Char>
    get() = byChar()
inline fun JsonObject.byNullableChar(key: String? = null, noinline default: JsonObjectSelector<Char?>? = null) = byNullableLambda(key, default) { it.char }
inline val JsonObject.byNullableChar: JsonObjectProperty<Char?>
    get() = byNullableChar()
inline fun JsonObject.byCharList(key: String? = null, noinline default: JsonArraySelector<Char>? = null) = byLambdaList(key, default) { it.char }
inline val JsonObject.byCharList: JsonArrayProperty<Char>
    get() = byCharList()
inline fun JsonObject.byNullableCharList(key: String? = null, noinline default: JsonArraySelector<Char?>? = null) = byNullableLambdaList(key, default) { it.char }
inline val JsonObject.byNullableCharList: JsonArrayProperty<Char?>
    get() = byNullableCharList()
inline fun JsonModel.char(key: String? = null, noinline default: JsonObjectSelector<Char>? = null) = lambda(key, default) { it.char }
inline val JsonModel.char: JsonObjectProperty<Char>
    get() = char()
inline fun JsonModel.nullableChar(key: String? = null, noinline default: JsonObjectSelector<Char?>? = null) = nullableLambda(key, default) { it.char }
inline val JsonModel.nullableChar: JsonObjectProperty<Char?>
    get() = nullableChar()
inline fun JsonModel.charList(key: String? = null, noinline default: JsonArraySelector<Char>? = null) = lambdaList(key, default) { it.char }
inline val JsonModel.charList: JsonArrayProperty<Char>
    get() = charList()
inline fun JsonModel.nullableCharList(key: String? = null, noinline default: JsonArraySelector<Char?>? = null) = nullableLambdaList(key, default) { it.char }
inline val JsonModel.nullableCharList: JsonArrayProperty<Char?>
    get() = nullableCharList()

inline fun JsonObject.byShort(key: String? = null, noinline default: JsonObjectSelector<Short>? = null) = byLambda(key, default) { it.short }
inline val JsonObject.byShort: JsonObjectProperty<Short>
    get() = byShort()
inline fun JsonObject.byNullableShort(key: String? = null, noinline default: JsonObjectSelector<Short?>? = null) = byNullableLambda(key, default) { it.short }
inline val JsonObject.byNullableShort: JsonObjectProperty<Short?>
    get() = byNullableShort()
inline fun JsonObject.byShortList(key: String? = null, noinline default: JsonArraySelector<Short>? = null) = byLambdaList(key, default) { it.short }
inline val JsonObject.byShortList: JsonArrayProperty<Short>
    get() = byShortList()
inline fun JsonObject.byNullableShortList(key: String? = null, noinline default: JsonArraySelector<Short?>? = null) = byNullableLambdaList(key, default) { it.short }
inline val JsonObject.byNullableShortList: JsonArrayProperty<Short?>
    get() = byNullableShortList()
inline fun JsonModel.short(key: String? = null, noinline default: JsonObjectSelector<Short>? = null) = lambda(key, default) { it.short }
inline val JsonModel.short: JsonObjectProperty<Short>
    get() = short()
inline fun JsonModel.nullableShort(key: String? = null, noinline default: JsonObjectSelector<Short?>? = null) = nullableLambda(key, default) { it.short }
inline val JsonModel.nullableShort: JsonObjectProperty<Short?>
    get() = nullableShort()
inline fun JsonModel.shortList(key: String? = null, noinline default: JsonArraySelector<Short>? = null) = lambdaList(key, default) { it.short }
inline val JsonModel.shortList: JsonArrayProperty<Short>
    get() = shortList()
inline fun JsonModel.nullableShortList(key: String? = null, noinline default: JsonArraySelector<Short?>? = null) = nullableLambdaList(key, default) { it.short }
inline val JsonModel.nullableShortList: JsonArrayProperty<Short?>
    get() = nullableShortList()

inline fun JsonObject.byInt(key: String? = null, noinline default: JsonObjectSelector<Int>? = null) = byLambda(key, default) { it.int }
inline val JsonObject.byInt: JsonObjectProperty<Int>
    get() = byInt()
inline fun JsonObject.byNullableInt(key: String? = null, noinline default: JsonObjectSelector<Int?>? = null) = byNullableLambda(key, default) { it.int }
inline val JsonObject.byNullableInt: JsonObjectProperty<Int?>
    get() = byNullableInt()
inline fun JsonObject.byIntList(key: String? = null, noinline default: JsonArraySelector<Int>? = null) = byLambdaList(key, default) { it.int }
inline val JsonObject.byIntList: JsonArrayProperty<Int>
    get() = byIntList()
inline fun JsonObject.byNullableIntList(key: String? = null, noinline default: JsonArraySelector<Int?>? = null) = byNullableLambdaList(key, default) { it.int }
inline val JsonObject.byNullableIntList: JsonArrayProperty<Int?>
    get() = byNullableIntList()
inline fun JsonModel.int(key: String? = null, noinline default: JsonObjectSelector<Int>? = null) = lambda(key, default) { it.int }
inline val JsonModel.int: JsonObjectProperty<Int>
    get() = int()
inline fun JsonModel.nullableInt(key: String? = null, noinline default: JsonObjectSelector<Int?>? = null) = nullableLambda(key, default) { it.int }
inline val JsonModel.nullableInt: JsonObjectProperty<Int?>
    get() = nullableInt()
inline fun JsonModel.intList(key: String? = null, noinline default: JsonArraySelector<Int>? = null) = lambdaList(key, default) { it.int }
inline val JsonModel.intList: JsonArrayProperty<Int>
    get() = intList()
inline fun JsonModel.nullableIntList(key: String? = null, noinline default: JsonArraySelector<Int?>? = null) = nullableLambdaList(key, default) { it.int }
inline val JsonModel.nullableIntList: JsonArrayProperty<Int?>
    get() = nullableIntList()

inline fun JsonObject.byLong(key: String? = null, noinline default: JsonObjectSelector<Long>? = null) = byLambda(key, default) { it.long }
inline val JsonObject.byLong: JsonObjectProperty<Long>
    get() = byLong()
inline fun JsonObject.byNullableLong(key: String? = null, noinline default: JsonObjectSelector<Long?>? = null) = byNullableLambda(key, default) { it.long }
inline val JsonObject.byNullableLong: JsonObjectProperty<Long?>
    get() = byNullableLong()
inline fun JsonObject.byLongList(key: String? = null, noinline default: JsonArraySelector<Long>? = null) = byLambdaList(key, default) { it.long }
inline val JsonObject.byLongList: JsonArrayProperty<Long>
    get() = byLongList()
inline fun JsonObject.byNullableLongList(key: String? = null, noinline default: JsonArraySelector<Long?>? = null) = byNullableLambdaList(key, default) { it.long }
inline val JsonObject.byNullableLongList: JsonArrayProperty<Long?>
    get() = byNullableLongList()
inline fun JsonModel.long(key: String? = null, noinline default: JsonObjectSelector<Long>? = null) = lambda(key, default) { it.long }
inline val JsonModel.long: JsonObjectProperty<Long>
    get() = long()
inline fun JsonModel.nullableLong(key: String? = null, noinline default: JsonObjectSelector<Long?>? = null) = nullableLambda(key, default) { it.long }
inline val JsonModel.nullableLong: JsonObjectProperty<Long?>
    get() = nullableLong()
inline fun JsonModel.longList(key: String? = null, noinline default: JsonArraySelector<Long>? = null) = lambdaList(key, default) { it.long }
inline val JsonModel.longList: JsonArrayProperty<Long>
    get() = longList()
inline fun JsonModel.nullableLongList(key: String? = null, noinline default: JsonArraySelector<Long?>? = null) = nullableLambdaList(key, default) { it.long }
inline val JsonModel.nullableLongList: JsonArrayProperty<Long?>
    get() = nullableLongList()

inline fun JsonObject.byBigInteger(key: String? = null, noinline default: JsonObjectSelector<BigInteger>? = null) = byLambda(key, default) { it.bigInteger }
inline val JsonObject.byBigInteger: JsonObjectProperty<BigInteger>
    get() = byBigInteger()
inline fun JsonObject.byNullableBigInteger(key: String? = null, noinline default: JsonObjectSelector<BigInteger?>? = null) = byNullableLambda(key, default) { it.bigInteger }
inline val JsonObject.byNullableBigInteger: JsonObjectProperty<BigInteger?>
    get() = byNullableBigInteger()
inline fun JsonObject.byBigIntegerList(key: String? = null, noinline default: JsonArraySelector<BigInteger>? = null) = byLambdaList(key, default) { it.bigInteger }
inline val JsonObject.byBigIntegerList: JsonArrayProperty<BigInteger>
    get() = byBigIntegerList()
inline fun JsonObject.byNullableBigIntegerList(key: String? = null, noinline default: JsonArraySelector<BigInteger?>? = null) = byNullableLambdaList(key, default) { it.bigInteger }
inline val JsonObject.byNullableBigIntegerList: JsonArrayProperty<BigInteger?>
    get() = byNullableBigIntegerList()
inline fun JsonModel.bigInteger(key: String? = null, noinline default: JsonObjectSelector<BigInteger>? = null) = lambda(key, default) { it.bigInteger }
inline val JsonModel.bigInteger: JsonObjectProperty<BigInteger>
    get() = bigInteger()
inline fun JsonModel.nullableBigInteger(key: String? = null, noinline default: JsonObjectSelector<BigInteger?>? = null) = nullableLambda(key, default) { it.bigInteger }
inline val JsonModel.nullableBigInteger: JsonObjectProperty<BigInteger?>
    get() = nullableBigInteger()
inline fun JsonModel.bigIntegerList(key: String? = null, noinline default: JsonArraySelector<BigInteger>? = null) = lambdaList(key, default) { it.bigInteger }
inline val JsonModel.bigIntegerList: JsonArrayProperty<BigInteger>
    get() = bigIntegerList()
inline fun JsonModel.nullableBigIntegerList(key: String? = null, noinline default: JsonArraySelector<BigInteger?>? = null) = nullableLambdaList(key, default) { it.bigInteger }
inline val JsonModel.nullableBigIntegerList: JsonArrayProperty<BigInteger?>
    get() = nullableBigIntegerList()

inline fun JsonObject.byFloat(key: String? = null, noinline default: JsonObjectSelector<Float>? = null) = byLambda(key, default) { it.float }
inline val JsonObject.byFloat: JsonObjectProperty<Float>
    get() = byFloat()
inline fun JsonObject.byNullableFloat(key: String? = null, noinline default: JsonObjectSelector<Float?>? = null) = byNullableLambda(key, default) { it.float }
inline val JsonObject.byNullableFloat: JsonObjectProperty<Float?>
    get() = byNullableFloat()
inline fun JsonObject.byFloatList(key: String? = null, noinline default: JsonArraySelector<Float>? = null) = byLambdaList(key, default) { it.float }
inline val JsonObject.byFloatList: JsonArrayProperty<Float>
    get() = byFloatList()
inline fun JsonObject.byNullableFloatList(key: String? = null, noinline default: JsonArraySelector<Float?>? = null) = byNullableLambdaList(key, default) { it.float }
inline val JsonObject.byNullableFloatList: JsonArrayProperty<Float?>
    get() = byNullableFloatList()
inline fun JsonModel.float(key: String? = null, noinline default: JsonObjectSelector<Float>? = null) = lambda(key, default) { it.float }
inline val JsonModel.float: JsonObjectProperty<Float>
    get() = float()
inline fun JsonModel.nullableFloat(key: String? = null, noinline default: JsonObjectSelector<Float?>? = null) = nullableLambda(key, default) { it.float }
inline val JsonModel.nullableFloat: JsonObjectProperty<Float?>
    get() = nullableFloat()
inline fun JsonModel.floatList(key: String? = null, noinline default: JsonArraySelector<Float>? = null) = lambdaList(key, default) { it.float }
inline val JsonModel.floatList: JsonArrayProperty<Float>
    get() = floatList()
inline fun JsonModel.nullableFloatList(key: String? = null, noinline default: JsonArraySelector<Float?>? = null) = nullableLambdaList(key, default) { it.float }
inline val JsonModel.nullableFloatList: JsonArrayProperty<Float?>
    get() = nullableFloatList()

inline fun JsonObject.byDouble(key: String? = null, noinline default: JsonObjectSelector<Double>? = null) = byLambda(key, default) { it.double }
inline val JsonObject.byDouble: JsonObjectProperty<Double>
    get() = byDouble()
inline fun JsonObject.byNullableDouble(key: String? = null, noinline default: JsonObjectSelector<Double?>? = null) = byNullableLambda(key, default) { it.double }
inline val JsonObject.byNullableDouble: JsonObjectProperty<Double?>
    get() = byNullableDouble()
inline fun JsonObject.byDoubleList(key: String? = null, noinline default: JsonArraySelector<Double>? = null) = byLambdaList(key, default) { it.double }
inline val JsonObject.byDoubleList: JsonArrayProperty<Double>
    get() = byDoubleList()
inline fun JsonObject.byNullableDoubleList(key: String? = null, noinline default: JsonArraySelector<Double?>? = null) = byNullableLambdaList(key, default) { it.double }
inline val JsonObject.byNullableDoubleList: JsonArrayProperty<Double?>
    get() = byNullableDoubleList()
inline fun JsonModel.double(key: String? = null, noinline default: JsonObjectSelector<Double>? = null) = lambda(key, default) { it.double }
inline val JsonModel.double: JsonObjectProperty<Double>
    get() = double()
inline fun JsonModel.nullableDouble(key: String? = null, noinline default: JsonObjectSelector<Double?>? = null) = nullableLambda(key, default) { it.double }
inline val JsonModel.nullableDouble: JsonObjectProperty<Double?>
    get() = nullableDouble()
inline fun JsonModel.doubleList(key: String? = null, noinline default: JsonArraySelector<Double>? = null) = lambdaList(key, default) { it.double }
inline val JsonModel.doubleList: JsonArrayProperty<Double>
    get() = doubleList()
inline fun JsonModel.nullableDoubleList(key: String? = null, noinline default: JsonArraySelector<Double?>? = null) = nullableLambdaList(key, default) { it.double }
inline val JsonModel.nullableDoubleList: JsonArrayProperty<Double?>
    get() = nullableDoubleList()

inline fun JsonObject.byBigDecimal(key: String? = null, noinline default: JsonObjectSelector<BigDecimal>? = null) = byLambda(key, default) { it.bigDecimal }
inline val JsonObject.byBigDecimal: JsonObjectProperty<BigDecimal>
    get() = byBigDecimal()
inline fun JsonObject.byNullableBigDecimal(key: String? = null, noinline default: JsonObjectSelector<BigDecimal?>? = null) = byNullableLambda(key, default) { it.bigDecimal }
inline val JsonObject.byNullableBigDecimal: JsonObjectProperty<BigDecimal?>
    get() = byNullableBigDecimal()
inline fun JsonObject.byBigDecimalList(key: String? = null, noinline default: JsonArraySelector<BigDecimal>? = null) = byLambdaList(key, default) { it.bigDecimal }
inline val JsonObject.byBigDecimalList: JsonArrayProperty<BigDecimal>
    get() = byBigDecimalList()
inline fun JsonObject.byNullableBigDecimalList(key: String? = null, noinline default: JsonArraySelector<BigDecimal?>? = null) = byNullableLambdaList(key, default) { it.bigDecimal }
inline val JsonObject.byNullableBigDecimalList: JsonArrayProperty<BigDecimal?>
    get() = byNullableBigDecimalList()
inline fun JsonModel.bigDecimal(key: String? = null, noinline default: JsonObjectSelector<BigDecimal>? = null) = lambda(key, default) { it.bigDecimal }
inline val JsonModel.bigDecimal: JsonObjectProperty<BigDecimal>
    get() = bigDecimal()
inline fun JsonModel.nullableBigDecimal(key: String? = null, noinline default: JsonObjectSelector<BigDecimal?>? = null) = nullableLambda(key, default) { it.bigDecimal }
inline val JsonModel.nullableBigDecimal: JsonObjectProperty<BigDecimal?>
    get() = nullableBigDecimal()
inline fun JsonModel.bigDecimalList(key: String? = null, noinline default: JsonArraySelector<BigDecimal>? = null) = lambdaList(key, default) { it.bigDecimal }
inline val JsonModel.bigDecimalList: JsonArrayProperty<BigDecimal>
    get() = bigDecimalList()
inline fun JsonModel.nullableBigDecimalList(key: String? = null, noinline default: JsonArraySelector<BigDecimal?>? = null) = nullableLambdaList(key, default) { it.bigDecimal }
inline val JsonModel.nullableBigDecimalList: JsonArrayProperty<BigDecimal?>
    get() = nullableBigDecimalList()

inline fun JsonObject.byString(key: String? = null, noinline default: JsonObjectSelector<String>? = null) = byLambda(key, default) { it.string }
inline val JsonObject.byString: JsonObjectProperty<String>
    get() = byString()
inline fun JsonObject.byNullableString(key: String? = null, noinline default: JsonObjectSelector<String?>? = null) = byNullableLambda(key, default) { it.string }
inline val JsonObject.byNullableString: JsonObjectProperty<String?>
    get() = byNullableString()
inline fun JsonObject.byStringList(key: String? = null, noinline default: JsonArraySelector<String>? = null) = byLambdaList(key, default) { it.string }
inline val JsonObject.byStringList: JsonArrayProperty<String>
    get() = byStringList()
inline fun JsonObject.byNullableStringList(key: String? = null, noinline default: JsonArraySelector<String?>? = null) = byNullableLambdaList(key, default) { it.string }
inline val JsonObject.byNullableStringList: JsonArrayProperty<String?>
    get() = byNullableStringList()
inline fun JsonModel.string(key: String? = null, noinline default: JsonObjectSelector<String>? = null) = lambda(key, default) { it.string }
inline val JsonModel.string: JsonObjectProperty<String>
    get() = string()
inline fun JsonModel.nullableString(key: String? = null, noinline default: JsonObjectSelector<String?>? = null) = nullableLambda(key, default) { it.string }
inline val JsonModel.nullableString: JsonObjectProperty<String?>
    get() = nullableString()
inline fun JsonModel.stringList(key: String? = null, noinline default: JsonArraySelector<String>? = null) = lambdaList(key, default) { it.string }
inline val JsonModel.stringList: JsonArrayProperty<String>
    get() = stringList()
inline fun JsonModel.nullableStringList(key: String? = null, noinline default: JsonArraySelector<String?>? = null) = nullableLambdaList(key, default) { it.string }
inline val JsonModel.nullableStringList: JsonArrayProperty<String?>
    get() = nullableStringList()
