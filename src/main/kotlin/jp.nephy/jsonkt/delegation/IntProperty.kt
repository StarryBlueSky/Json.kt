@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

/*
    Int
 */

inline fun JsonObject.byInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int>
) = byLambda(key, default) { it.int }

inline fun JsonObject.byInt(key: String? = null) = byLambda(key) { it.int }

inline fun JsonModel.int(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int>
) = lambda(key, default) { it.int }

inline fun JsonModel.int(key: String? = null) = lambda(key) { it.int }

inline val JsonObject.byInt
    get() = byInt()
inline val JsonModel.int
    get() = int()

/*
    Int?
 */

inline fun JsonObject?.byNullableInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int?>
) = byNullableLambda(key, default) { it.intOrNull }

inline fun JsonObject?.byNullableInt(key: String? = null) = byNullableLambda(key) { it.intOrNull }

inline fun JsonModel.nullableInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int?>
) = nullableLambda(key, default) { it.intOrNull }

inline fun JsonModel.nullableInt(key: String? = null) = nullableLambda(key) { it.intOrNull }

inline val JsonObject.byNullableInt
    get() = byNullableInt()
inline val JsonModel.nullableInt
    get() = nullableInt()
inline val JsonModel.intOrNull
    get() = nullableInt

/*
    List<Int>
 */

inline fun JsonObject.byIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int>
) = byLambdaList(key, default) { it.int }

inline fun JsonObject.byIntList(key: String? = null) = byLambdaList(key) { it.int }

inline fun JsonModel.intList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int>
) = lambdaList(key, default) { it.int }

inline fun JsonModel.intList(key: String? = null) = lambdaList(key) { it.int }

inline val JsonObject.byIntList
    get() = byIntList()
inline val JsonModel.intList
    get() = intList()

/*
    List<Int?>
 */

inline fun JsonObject.byNullableIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int?>
) = byNullableLambdaList(key, default) { it.intOrNull }

inline fun JsonObject.byNullableIntList(key: String? = null) = byNullableLambdaList(key) { it.intOrNull }

inline fun JsonModel.nullableIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int?>
) = nullableLambdaList(key, default) { it.intOrNull }

inline fun JsonModel.nullableIntList(key: String? = null) = nullableLambdaList(key) { it.intOrNull }

inline val JsonObject.byNullableIntList
    get() = byNullableIntList()
inline val JsonModel.nullableIntList
    get() = nullableIntList()
