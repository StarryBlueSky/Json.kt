@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import kotlinx.serialization.json.float
import kotlinx.serialization.json.floatOrNull

/*
    Float
 */

inline fun JsonObject.byFloat(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Float>
) = byLambda(key, default) { it.float }

inline fun JsonObject.byFloat(key: String? = null) = byLambda(key) { it.float }

inline fun JsonModel.float(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Float>
) = lambda(key, default) { it.float }

inline fun JsonModel.float(key: String? = null) = lambda(key) { it.float }

inline val JsonObject.byFloat
    get() = byFloat()
inline val JsonModel.float
    get() = float()

/*
    Float?
 */

inline fun JsonObject?.byNullableFloat(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Float?>
) = byNullableLambda(key, default) { it.floatOrNull }

inline fun JsonObject?.byNullableFloat(key: String? = null) = byNullableLambda(key) { it.floatOrNull }

inline fun JsonModel.nullableFloat(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Float?>
) = nullableLambda(key, default) { it.floatOrNull }

inline fun JsonModel.nullableFloat(key: String? = null) = nullableLambda(key) { it.floatOrNull }

inline val JsonObject.byNullableFloat
    get() = byNullableFloat()
inline val JsonModel.nullableFloat
    get() = nullableFloat()
inline val JsonModel.floatOrNull
    get() = nullableFloat

/*
    List<Float>
 */

inline fun JsonObject.byFloatList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Float>
) = byLambdaList(key, default) { it.float }

inline fun JsonObject.byFloatList(key: String? = null) = byLambdaList(key) { it.float }

inline fun JsonModel.floatList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Float>
) = lambdaList(key, default) { it.float }

inline fun JsonModel.floatList(key: String? = null) = lambdaList(key) { it.float }

inline val JsonObject.byFloatList
    get() = byFloatList()
inline val JsonModel.floatList
    get() = floatList()

/*
    List<Float?>
 */

inline fun JsonObject.byNullableFloatList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Float?>
) = byNullableLambdaList(key, default) { it.floatOrNull }

inline fun JsonObject.byNullableFloatList(key: String? = null) = byNullableLambdaList(key) { it.floatOrNull }

inline fun JsonModel.nullableFloatList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Float?>
) = nullableLambdaList(key, default) { it.floatOrNull }

inline fun JsonModel.nullableFloatList(key: String? = null) = nullableLambdaList(key) { it.floatOrNull }

inline val JsonObject.byNullableFloatList
    get() = byNullableFloatList()
inline val JsonModel.nullableFloatList
    get() = nullableFloatList()
