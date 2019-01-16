@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.jsonObjectOrNull

/*
    JsonObject
 */

inline fun JsonObject.byJsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject>
) = byLambda(key, default) { it.jsonObject }

inline fun JsonObject.byJsonObject(key: String? = null) = byLambda(key) { it.jsonObject }

inline fun JsonModel.jsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject>
) = lambda(key, default) { it.jsonObject }

inline fun JsonModel.jsonObject(key: String? = null) = lambda(key) { it.jsonObject }

inline val JsonObject.byJsonObject
    get() = byJsonObject()
inline val JsonModel.jsonObject
    get() = jsonObject()

/*
    JsonObject?
 */

inline fun JsonObject?.byNullableJsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject?>
) = byNullableLambda(key, default) { it.jsonObjectOrNull }

inline fun JsonObject?.byNullableJsonObject(key: String? = null) = byNullableLambda(key) { it.jsonObjectOrNull }

inline fun JsonModel.nullableJsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject?>
) = nullableLambda(key, default) { it.jsonObjectOrNull }

inline fun JsonModel.nullableJsonObject(key: String? = null) = nullableLambda(key) { it.jsonObjectOrNull }

inline val JsonObject.byNullableJsonObject
    get() = byNullableJsonObject()
inline val JsonModel.nullableJsonObject
    get() = nullableJsonObject()
inline val JsonModel.jsonObjectOrNull
    get() = nullableJsonObject

/*
    List<JsonObject>
 */

inline fun JsonObject.byJsonObjectList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject>
) = byLambdaList(key, default) { it.jsonObject }

inline fun JsonObject.byJsonObjectList(key: String? = null) = byLambdaList(key) { it.jsonObject }

inline fun JsonModel.jsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject>
) = lambdaList(key, default) { it.jsonObject }

inline fun JsonModel.jsonObjectList(key: String? = null) = lambdaList(key) { it.jsonObject }

inline val JsonObject.byJsonObjectList
    get() = byJsonObjectList()
inline val JsonModel.jsonObjectList
    get() = jsonObjectList()

/*
    List<JsonObject?>
 */

inline fun JsonObject.byNullableJsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject?>
) = byNullableLambdaList(key, default) { it.jsonObjectOrNull }

inline fun JsonObject.byNullableJsonObjectList(key: String? = null) = byNullableLambdaList(key) { it.jsonObjectOrNull }

inline fun JsonModel.nullableJsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject?>
) = nullableLambdaList(key, default) { it.jsonObjectOrNull }

inline fun JsonModel.nullableJsonObjectList(key: String? = null) = nullableLambdaList(key) { it.jsonObjectOrNull }

inline val JsonObject.byNullableJsonObjectList
    get() = byNullableJsonObjectList()
inline val JsonModel.nullableJsonObjectList
    get() = nullableJsonObjectList()
