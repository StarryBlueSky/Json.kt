@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.JsonPrimitive
import jp.nephy.jsonkt.primitiveOrNull

/*
    JsonPrimitive
 */

inline fun JsonObject.byJsonPrimitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive>
) = byLambda(key, default) { it.primitive }

inline fun JsonObject.byJsonPrimitive(key: String? = null) = byLambda(key) { it.primitive }

inline fun JsonModel.primitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive>
) = lambda(key, default) { it.primitive }

inline fun JsonModel.primitive(key: String? = null) = lambda(key) { it.primitive }

inline val JsonObject.byJsonPrimitive
    get() = byJsonPrimitive()
inline val JsonModel.primitive
    get() = primitive()

/*
    JsonPrimitive?
 */

inline fun JsonObject?.byNullableJsonPrimitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive?>
) = byNullableLambda(key, default) { it.primitiveOrNull }

inline fun JsonObject?.byNullableJsonPrimitive(key: String? = null) = byNullableLambda(key) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive?>
) = nullableLambda(key, default) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitive(key: String? = null) = nullableLambda(key) { it.primitiveOrNull }

inline val JsonObject.byNullableJsonPrimitive
    get() = byNullableJsonPrimitive()
inline val JsonModel.nullableJsonPrimitive
    get() = nullableJsonPrimitive()
inline val JsonModel.primitiveOrNull
    get() = nullableJsonPrimitive

/*
    List<JsonPrimitive>
 */

inline fun JsonObject.byJsonPrimitiveList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive>
) = byLambdaList(key, default) { it.primitive }

inline fun JsonObject.byJsonPrimitiveList(key: String? = null) = byLambdaList(key) { it.primitive }

inline fun JsonModel.primitiveList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive>
) = lambdaList(key, default) { it.primitive }

inline fun JsonModel.primitiveList(key: String? = null) = lambdaList(key) { it.primitive }

inline val JsonObject.byJsonPrimitiveList
    get() = byJsonPrimitiveList()
inline val JsonModel.primitiveList
    get() = primitiveList()

/*
    List<JsonPrimitive?>
 */

inline fun JsonObject.byNullableJsonPrimitiveList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive?>
) = byNullableLambdaList(key, default) { it.primitiveOrNull }

inline fun JsonObject.byNullableJsonPrimitiveList(key: String? = null) = byNullableLambdaList(key) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitiveList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive?>
) = nullableLambdaList(key, default) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitiveList(key: String? = null) = nullableLambdaList(key) { it.primitiveOrNull }

inline val JsonObject.byNullableJsonPrimitiveList
    get() = byNullableJsonPrimitiveList()
inline val JsonModel.nullableJsonPrimitiveList
    get() = nullableJsonPrimitiveList()
