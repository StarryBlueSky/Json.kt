@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull

/*
    Double
 */

inline fun JsonObject.byDouble(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double>
) = byLambda(key, default) { it.double }

inline fun JsonObject.byDouble(key: String? = null) = byLambda(key) { it.double }

inline fun JsonModel.double(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double>
) = lambda(key, default) { it.double }

inline fun JsonModel.double(key: String? = null) = lambda(key) { it.double }

inline val JsonObject.byDouble
    get() = byDouble()
inline val JsonModel.double
    get() = double()

/*
    Double?
 */

inline fun JsonObject?.byNullableDouble(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double?>
) = byNullableLambda(key, default) { it.doubleOrNull }

inline fun JsonObject?.byNullableDouble(key: String? = null) = byNullableLambda(key) { it.doubleOrNull }

inline fun JsonModel.nullableDouble(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double?>
) = nullableLambda(key, default) { it.doubleOrNull }

inline fun JsonModel.nullableDouble(key: String? = null) = nullableLambda(key) { it.doubleOrNull }

inline val JsonObject.byNullableDouble
    get() = byNullableDouble()
inline val JsonModel.nullableDouble
    get() = nullableDouble()
inline val JsonModel.doubleOrNull
    get() = nullableDouble

/*
    List<Double>
 */

inline fun JsonObject.byDoubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double>
) = byLambdaList(key, default) { it.double }

inline fun JsonObject.byDoubleList(key: String? = null) = byLambdaList(key) { it.double }

inline fun JsonModel.doubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double>
) = lambdaList(key, default) { it.double }

inline fun JsonModel.doubleList(key: String? = null) = lambdaList(key) { it.double }

inline val JsonObject.byDoubleList
    get() = byDoubleList()
inline val JsonModel.doubleList
    get() = doubleList()

/*
    List<Double?>
 */

inline fun JsonObject.byNullableDoubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double?>
) = byNullableLambdaList(key, default) { it.doubleOrNull }

inline fun JsonObject.byNullableDoubleList(key: String? = null) = byNullableLambdaList(key) { it.doubleOrNull }

inline fun JsonModel.nullableDoubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double?>
) = nullableLambdaList(key, default) { it.doubleOrNull }

inline fun JsonModel.nullableDoubleList(key: String? = null) = nullableLambdaList(key) { it.doubleOrNull }

inline val JsonObject.byNullableDoubleList
    get() = byNullableDoubleList()
inline val JsonModel.nullableDoubleList
    get() = nullableDoubleList()
