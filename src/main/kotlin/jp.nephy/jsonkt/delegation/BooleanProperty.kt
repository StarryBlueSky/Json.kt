@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull

/*
    Boolean
 */

inline fun JsonObject.byBoolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean>
) = byLambda(key, default) { it.boolean }

inline fun JsonObject.byBoolean(key: String? = null) = byLambda(key) { it.boolean }

inline fun JsonModel.boolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean>
) = lambda(key, default) { it.boolean }

inline fun JsonModel.boolean(key: String? = null) = lambda(key) { it.boolean }

inline val JsonObject.byBoolean
    get() = byBoolean()
inline val JsonModel.boolean
    get() = boolean()

/*
    Boolean?
 */

inline fun JsonObject?.byNullableBoolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean?>
) = byNullableLambda(key, default) { it.booleanOrNull }

inline fun JsonObject?.byNullableBoolean(key: String? = null) = byNullableLambda(key) { it.booleanOrNull }

inline fun JsonModel.nullableBoolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean?>
) = nullableLambda(key, default) { it.booleanOrNull }

inline fun JsonModel.nullableBoolean(key: String? = null) = nullableLambda(key) { it.booleanOrNull }

inline val JsonObject.byNullableBoolean
    get() = byNullableBoolean()
inline val JsonModel.nullableBoolean
    get() = nullableBoolean()
inline val JsonModel.booleanOrNull
    get() = nullableBoolean

/*
    List<Boolean>
 */

inline fun JsonObject.byBooleanList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean>
) = byLambdaList(key, default) { it.boolean }

inline fun JsonObject.byBooleanList(key: String? = null) = byLambdaList(key) { it.boolean }

inline fun JsonModel.booleanList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean>
) = lambdaList(key, default) { it.boolean }

inline fun JsonModel.booleanList(key: String? = null) = lambdaList(key) { it.boolean }

inline val JsonObject.byBooleanList
    get() = byBooleanList()
inline val JsonModel.booleanList
    get() = booleanList()

/*
    List<Boolean?>
 */

inline fun JsonObject.byNullableBooleanList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean?>
) = byNullableLambdaList(key, default) { it.booleanOrNull }

inline fun JsonObject.byNullableBooleanList(key: String? = null) = byNullableLambdaList(key) { it.booleanOrNull }

inline fun JsonModel.nullableBooleanList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean?>
) = nullableLambdaList(key, default) { it.booleanOrNull }

inline fun JsonModel.nullableBooleanList(key: String? = null) = nullableLambdaList(key) { it.booleanOrNull }

inline val JsonObject.byNullableBooleanList
    get() = byNullableBooleanList()
inline val JsonModel.nullableBooleanList
    get() = nullableBooleanList()
