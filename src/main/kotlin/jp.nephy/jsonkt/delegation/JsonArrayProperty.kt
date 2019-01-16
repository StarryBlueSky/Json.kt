@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonArray
import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.jsonArrayOrNull

/*
    JsonArray
 */

inline fun JsonObject.byJsonArray(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonArray>
) = byLambda(key, default) { it.jsonArray }

inline fun JsonObject.byJsonArray(key: String? = null) = byLambda(key) { it.jsonArray }

inline fun JsonModel.jsonArray(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonArray>
) = lambda(key, default) { it.jsonArray }

inline fun JsonModel.jsonArray(key: String? = null) = lambda(key) { it.jsonArray }

inline val JsonObject.byJsonArray
    get() = byJsonArray()
inline val JsonModel.jsonArray
    get() = jsonArray()

/*
    JsonArray?
 */

inline fun JsonObject?.byNullableJsonArray(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonArray?>
) = byNullableLambda(key, default) { it.jsonArrayOrNull }

inline fun JsonObject?.byNullableJsonArray(key: String? = null) = byNullableLambda(key) { it.jsonArrayOrNull }

inline fun JsonModel.nullableJsonArray(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonArray?>
) = nullableLambda(key, default) { it.jsonArrayOrNull }

inline fun JsonModel.nullableJsonArray(key: String? = null) = nullableLambda(key) { it.jsonArrayOrNull }

inline val JsonObject.byNullableJsonArray
    get() = byNullableJsonArray()
inline val JsonModel.nullableJsonArray
    get() = nullableJsonArray()
inline val JsonModel.jsonArrayOrNull
    get() = nullableJsonArray

/*
    List<JsonArray>
 */

inline fun JsonObject.byJsonArrayList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonArray>
) = byLambdaList(key, default) { it.jsonArray }

inline fun JsonObject.byJsonArrayList(key: String? = null) = byLambdaList(key) { it.jsonArray }

inline fun JsonModel.jsonArrayList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonArray>
) = lambdaList(key, default) { it.jsonArray }

inline fun JsonModel.jsonArrayList(key: String? = null) = lambdaList(key) { it.jsonArray }

inline val JsonObject.byJsonArrayList
    get() = byJsonArrayList()
inline val JsonModel.jsonArrayList
    get() = jsonArrayList()

/*
    List<JsonArray?>
 */

inline fun JsonObject.byNullableJsonArrayList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonArray?>
) = byNullableLambdaList(key, default) { it.jsonArrayOrNull }

inline fun JsonObject.byNullableJsonArrayList(key: String? = null) = byNullableLambdaList(key) { it.jsonArrayOrNull }

inline fun JsonModel.nullableJsonArrayList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonArray?>
) = nullableLambdaList(key, default) { it.jsonArrayOrNull }

inline fun JsonModel.nullableJsonArrayList(key: String? = null) = nullableLambdaList(key) { it.jsonArrayOrNull }

inline val JsonObject.byNullableJsonArrayList
    get() = byNullableJsonArrayList()
inline val JsonModel.nullableJsonArrayList
    get() = nullableJsonArrayList()
