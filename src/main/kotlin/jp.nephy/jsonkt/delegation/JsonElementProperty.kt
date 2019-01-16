@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonElement
import jp.nephy.jsonkt.JsonObject

/*
    JsonElement
 */

inline fun JsonObject.byJsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement>
) = byLambda(key, default) { it }

inline fun JsonObject.byJsonElement(key: String? = null) = byLambda(key) { it }

inline fun JsonModel.jsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement>
) = lambda(key, default) { it }

inline fun JsonModel.jsonElement(key: String? = null) = lambda(key) { it }

inline val JsonObject.byJsonElement
    get() = byJsonElement()
inline val JsonModel.jsonElement
    get() = jsonElement()

/*
    JsonElement?
 */

inline fun JsonObject?.byNullableJsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement?>
) = byNullableLambda(key, default) { it }

inline fun JsonObject?.byNullableJsonElement(key: String? = null) = byNullableLambda(key) { it }

inline fun JsonModel.nullableJsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement?>
) = nullableLambda(key, default) { it }

inline fun JsonModel.nullableJsonElement(key: String? = null) = nullableLambda(key) { it }

inline val JsonObject.byNullableJsonElement
    get() = byNullableJsonElement()
inline val JsonModel.nullableJsonElement
    get() = nullableJsonElement()
inline val JsonModel.jsonElementOrNull
    get() = nullableJsonElement

/*
    List<JsonElement>
 */

inline fun JsonObject.byJsonElementList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement>
) = byLambdaList(key, default) { it }

inline fun JsonObject.byJsonElementList(key: String? = null) = byLambdaList(key) { it }

inline fun JsonModel.jsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement>
) = lambdaList(key, default) { it }

inline fun JsonModel.jsonElementList(key: String? = null) = lambdaList(key) { it }

inline val JsonObject.byJsonElementList
    get() = byJsonElementList()
inline val JsonModel.jsonElementList
    get() = jsonElementList()

/*
    List<JsonElement?>
 */

inline fun JsonObject.byNullableJsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement?>
) = byNullableLambdaList(key, default) { it }

inline fun JsonObject.byNullableJsonElementList(key: String? = null) = byNullableLambdaList(key) { it }

inline fun JsonModel.nullableJsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement?>
) = nullableLambdaList(key, default) { it }

inline fun JsonModel.nullableJsonElementList(key: String? = null) = nullableLambdaList(key) { it }

inline val JsonObject.byNullableJsonElementList
    get() = byNullableJsonElementList()
inline val JsonModel.nullableJsonElementList
    get() = nullableJsonElementList()
