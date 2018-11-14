@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import kotlinx.serialization.json.*
import kotlin.reflect.KClass

inline fun <T> JsonObject.byLambda(key: String? = null, noinline default: JsonObjectSelector<T>? = null, noinline converter: JsonElementConverter<T>) = JsonObjectProperty(key, null, default, converter)
inline fun <T> JsonModel.lambda(key: String? = null, noinline default: JsonObjectSelector<T>? = null, noinline converter: JsonElementConverter<T>) = json.JsonObjectProperty(key, null, default, converter)

inline fun <T> JsonObject.byNullableLambda(key: String? = null, noinline default: JsonObjectSelector<T?>? = null, noinline converter: JsonElementConverter<T?>) = JsonObjectProperty(key, null, default, converter)
inline fun <T> JsonModel.nullableLambda(key: String? = null, noinline default: JsonObjectSelector<T?>? = null, noinline converter: JsonElementConverter<T?>) = json.JsonObjectProperty(key, null, default, converter)

inline fun <T> JsonObject.byLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T>) = JsonArrayProperty(key, null, default, converter, null)
inline fun <T> JsonModel.lambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T>) = json.JsonArrayProperty(key, null, default, converter, null)

inline fun <T> JsonObject.byNullableLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T?>) = JsonArrayProperty(key, null, default, converter, null)
inline fun <T> JsonModel.nullableLambdaList(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline converter: JsonElementConverter<T?>) = json.JsonArrayProperty(key, null, default, converter, null)

inline fun <T> JsonElement.byMap(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline operation: JsonArrayOperation<T>? = null) = jsonObject.JsonArrayProperty(key, null, default, null, operation)
inline fun <T> JsonModel.map(key: String? = null, noinline default: JsonArraySelector<T>? = null, noinline operation: JsonArrayOperation<T>? = null) = json.JsonArrayProperty(key, null, default, null, operation)

inline fun <reified T, R: JsonEnum<T>> JsonObject.byEnum(enum: KClass<R>, key: String? = null, default: R) = JsonObjectProperty(key, null, { default }, {
    val casted = it.primitive.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: default
})
inline fun <reified T, R: JsonEnum<T>> JsonModel.enum(enum: KClass<R>, key: String? = null, default: R) = json.JsonObjectProperty(key, null, { default }, {
    val casted = it.primitive.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: default
})

inline fun <reified T, R: JsonEnum<T>> JsonObject.byEnumList(enum: KClass<R>, key: String? = null, unknown: R, noinline default: () -> List<R> = { emptyList() }) = JsonArrayProperty(key, null, { default() }, {
    val casted = it.primitive.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: unknown
}, null)
inline fun <reified T, R: JsonEnum<T>> JsonModel.enumList(enum: KClass<R>, key: String? = null, unknown: R, noinline default: () -> List<R> = { emptyList() }) = json.JsonArrayProperty(key, null, { default() }, {
    val casted = it.primitive.toString()
    enum.java.enumConstants.find { const -> const.value.toString() == casted } ?: unknown
}, null)

inline fun <reified T: JsonModel?> JsonObject.byModel(key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonObjectProperty(key, T::class.java, default, null)
inline fun <reified T: JsonModel?> JsonObject.byModel(vararg args: Any, key: String? = null, noinline default: JsonObjectSelector<T>? = null) = JsonObjectProperty(key, T::class.java, default, null, *args)
inline fun <reified T: JsonModel?> JsonModel.model(key: String? = null, noinline default: JsonObjectSelector<T>? = null) = json.JsonObjectProperty(key, T::class.java, default, null)
inline fun <reified T: JsonModel?> JsonModel.model(vararg args: Any, key: String? = null, noinline default: JsonObjectSelector<T>? = null) = json.JsonObjectProperty(key, T::class.java, default, null, *args)

inline fun <reified T: JsonModel?> JsonObject.byModelList(key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayProperty(key, T::class.java, default, null, null)
inline fun <reified T: JsonModel?> JsonObject.byModelList(vararg args: Any, key: String? = null, noinline default: JsonArraySelector<T>? = null) = JsonArrayProperty(key, T::class.java, default, null, null, *args)
inline fun <reified T: JsonModel?> JsonModel.modelList(key: String? = null, noinline default: JsonArraySelector<T>? = null) = json.JsonArrayProperty(key, T::class.java, default, null, null)
inline fun <reified T: JsonModel?> JsonModel.modelList(vararg args: Any, key: String? = null, noinline default: JsonArraySelector<T>? = null) = json.JsonArrayProperty(key, T::class.java, default, null, null, *args)

inline fun JsonObject.byJsonElement(key: String? = null, noinline default: JsonObjectSelector<JsonElement>? = null) = byLambda(key, default) { it }
inline val JsonObject.byJsonElement: JsonObjectProperty<JsonElement>
    get() = byJsonElement()
inline fun JsonObject.byNullableJsonElement(key: String? = null, noinline default: JsonObjectSelector<JsonElement?>? = null) = byNullableLambda(key, default) { it }
inline val JsonObject.byNullableJsonElement: JsonObjectProperty<JsonElement?>
    get() = byNullableJsonElement()
inline fun JsonObject.byJsonElementList(key: String? = null, noinline default: JsonArraySelector<JsonElement>? = null) = byLambdaList(key, default) { it }
inline val JsonObject.byJsonElementList: JsonArrayProperty<JsonElement>
    get() = byJsonElementList()
inline fun JsonObject.byNullableJsonElementList(key: String? = null, noinline default: JsonArraySelector<JsonElement?>? = null) = byNullableLambdaList(key, default) { it }
inline val JsonObject.byNullableJsonElementList: JsonArrayProperty<JsonElement?>
    get() = byNullableJsonElementList()
inline fun JsonModel.jsonElement(key: String? = null, noinline default: JsonObjectSelector<JsonElement>? = null) = lambda(key, default) { it }
inline val JsonModel.jsonElement: JsonObjectProperty<JsonElement>
    get() = jsonElement()
inline fun JsonModel.nullableJsonElement(key: String? = null, noinline default: JsonObjectSelector<JsonElement?>? = null) = nullableLambda(key, default) { it }
inline val JsonModel.nullableJsonElement: JsonObjectProperty<JsonElement?>
    get() = nullableJsonElement()
inline fun JsonModel.jsonElementList(key: String? = null, noinline default: JsonArraySelector<JsonElement>? = null) = lambdaList(key, default) { it }
inline val JsonModel.jsonElementList: JsonArrayProperty<JsonElement>
    get() = jsonElementList()
inline fun JsonModel.nullableJsonElementList(key: String? = null, noinline default: JsonArraySelector<JsonElement?>? = null) = nullableLambdaList(key, default) { it }
inline val JsonModel.nullableJsonElementList: JsonArrayProperty<JsonElement?>
    get() = nullableJsonElementList()

inline fun JsonObject.byJsonObject(key: String? = null, noinline default: JsonObjectSelector<JsonObject>? = null) = byLambda(key, default) { it.jsonObject }
inline val JsonObject.byJsonObject: JsonObjectProperty<JsonObject>
    get() = byJsonObject()
inline fun JsonObject.byNullableJsonObject(key: String? = null, noinline default: JsonObjectSelector<JsonObject?>? = null) = byNullableLambda(key, default) { it.jsonObject }
inline val JsonObject.byNullableJsonObject: JsonObjectProperty<JsonObject?>
    get() = byNullableJsonObject()
inline fun JsonObject.byJsonObjectList(key: String? = null, noinline default: JsonArraySelector<JsonObject>? = null) = byLambdaList(key, default) { it.jsonObject }
inline val JsonObject.byJsonObjectList: JsonArrayProperty<JsonObject>
    get() = byJsonObjectList()
inline fun JsonObject.byNullableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<JsonObject?>? = null) = byNullableLambdaList(key, default) { it.jsonObject }
inline val JsonObject.byNullableJsonObjectList: JsonArrayProperty<JsonObject?>
    get() = byNullableJsonObjectList()
inline fun JsonModel.jsonObject(key: String? = null, noinline default: JsonObjectSelector<JsonObject>? = null) = lambda(key, default) { it.jsonObject }
inline val JsonModel.jsonObject: JsonObjectProperty<JsonObject>
    get() = jsonObject()
inline fun JsonModel.nullableJsonObject(key: String? = null, noinline default: JsonObjectSelector<JsonObject?>? = null) = nullableLambda(key, default) { it.jsonObject }
inline val JsonModel.nullableJsonObject: JsonObjectProperty<JsonObject?>
    get() = nullableJsonObject()
inline fun JsonModel.jsonObjectList(key: String? = null, noinline default: JsonArraySelector<JsonObject>? = null) = lambdaList(key, default) { it.jsonObject }
inline val JsonModel.jsonObjectList: JsonArrayProperty<JsonObject>
    get() = jsonObjectList()
inline fun JsonModel.nullableJsonObjectList(key: String? = null, noinline default: JsonArraySelector<JsonObject?>? = null) = nullableLambdaList(key, default) { it.jsonObject }
inline val JsonModel.nullableJsonObjectList: JsonArrayProperty<JsonObject?>
    get() = nullableJsonObjectList()

inline fun JsonObject.byJsonArray(key: String? = null, noinline default: JsonObjectSelector<JsonArray>? = null) = byLambda(key, default) { it.jsonArray }
inline val JsonObject.byJsonArray: JsonObjectProperty<JsonArray>
    get() = byJsonArray()
inline fun JsonObject.byNullableJsonArray(key: String? = null, noinline default: JsonObjectSelector<JsonArray?>? = null) = byNullableLambda(key, default) { it.jsonArray }
inline val JsonObject.byNullableJsonArray: JsonObjectProperty<JsonArray?>
    get() = byNullableJsonArray()
inline fun JsonObject.byJsonArrayList(key: String? = null, noinline default: JsonArraySelector<JsonArray>? = null) = byLambdaList(key, default) { it.jsonArray }
inline val JsonObject.byJsonArrayList: JsonArrayProperty<JsonArray>
    get() = byJsonArrayList()
inline fun JsonObject.byNullableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<JsonArray?>? = null) = byNullableLambdaList(key, default) { it.jsonArray }
inline val JsonObject.byNullableJsonArrayList: JsonArrayProperty<JsonArray?>
    get() = byNullableJsonArrayList()
inline fun JsonModel.jsonArray(key: String? = null, noinline default: JsonObjectSelector<JsonArray>? = null) = lambda(key, default) { it.jsonArray }
inline val JsonModel.jsonArray: JsonObjectProperty<JsonArray>
    get() = jsonArray()
inline fun JsonModel.nullableJsonArray(key: String? = null, noinline default: JsonObjectSelector<JsonArray?>? = null) = nullableLambda(key, default) { it.jsonArray }
inline val JsonModel.nullableJsonArray: JsonObjectProperty<JsonArray?>
    get() = nullableJsonArray()
inline fun JsonModel.jsonArrayList(key: String? = null, noinline default: JsonArraySelector<JsonArray>? = null) = lambdaList(key, default) { it.jsonArray }
inline val JsonModel.jsonArrayList: JsonArrayProperty<JsonArray>
    get() = jsonArrayList()
inline fun JsonModel.nullableJsonArrayList(key: String? = null, noinline default: JsonArraySelector<JsonArray?>? = null) = nullableLambdaList(key, default) { it.jsonArray }
inline val JsonModel.nullableJsonArrayList: JsonArrayProperty<JsonArray?>
    get() = nullableJsonArrayList()

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

inline fun JsonObject.byString(key: String? = null, noinline default: JsonObjectSelector<String>? = null) = byLambda(key, default) { it.content }
inline val JsonObject.byString: JsonObjectProperty<String>
    get() = byString()
inline fun JsonObject.byNullableString(key: String? = null, noinline default: JsonObjectSelector<String?>? = null) = byNullableLambda(key, default) { it.content }
inline val JsonObject.byNullableString: JsonObjectProperty<String?>
    get() = byNullableString()
inline fun JsonObject.byStringList(key: String? = null, noinline default: JsonArraySelector<String>? = null) = byLambdaList(key, default) { it.content }
inline val JsonObject.byStringList: JsonArrayProperty<String>
    get() = byStringList()
inline fun JsonObject.byNullableStringList(key: String? = null, noinline default: JsonArraySelector<String?>? = null) = byNullableLambdaList(key, default) { it.content }
inline val JsonObject.byNullableStringList: JsonArrayProperty<String?>
    get() = byNullableStringList()
inline fun JsonModel.string(key: String? = null, noinline default: JsonObjectSelector<String>? = null) = lambda(key, default) { it.content }
inline val JsonModel.string: JsonObjectProperty<String>
    get() = string()
inline fun JsonModel.nullableString(key: String? = null, noinline default: JsonObjectSelector<String?>? = null) = nullableLambda(key, default) { it.content }
inline val JsonModel.nullableString: JsonObjectProperty<String?>
    get() = nullableString()
inline fun JsonModel.stringList(key: String? = null, noinline default: JsonArraySelector<String>? = null) = lambdaList(key, default) { it.content }
inline val JsonModel.stringList: JsonArrayProperty<String>
    get() = stringList()
inline fun JsonModel.nullableStringList(key: String? = null, noinline default: JsonArraySelector<String?>? = null) = nullableLambdaList(key, default) { it.content }
inline val JsonModel.nullableStringList: JsonArrayProperty<String?>
    get() = nullableStringList()
