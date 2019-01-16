@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import kotlinx.serialization.json.long
import kotlinx.serialization.json.longOrNull

/*
    Long
 */

inline fun JsonObject.byLong(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long>
) = byLambda(key, default) { it.long }

inline fun JsonObject.byLong(key: String? = null) = byLambda(key) { it.long }

inline fun JsonModel.long(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long>
) = lambda(key, default) { it.long }

inline fun JsonModel.long(key: String? = null) = lambda(key) { it.long }

inline val JsonObject.byLong
    get() = byLong()
inline val JsonModel.long
    get() = long()

/*
    Long?
 */

inline fun JsonObject?.byNullableLong(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long?>
) = byNullableLambda(key, default) { it.longOrNull }

inline fun JsonObject?.byNullableLong(key: String? = null) = byNullableLambda(key) { it.longOrNull }

inline fun JsonModel.nullableLong(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long?>
) = nullableLambda(key, default) { it.longOrNull }

inline fun JsonModel.nullableLong(key: String? = null) = nullableLambda(key) { it.longOrNull }

inline val JsonObject.byNullableLong
    get() = byNullableLong()
inline val JsonModel.nullableLong
    get() = nullableLong()
inline val JsonModel.longOrNull
    get() = nullableLong

/*
    List<Long>
 */

inline fun JsonObject.byLongList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long>
) = byLambdaList(key, default) { it.long }

inline fun JsonObject.byLongList(key: String? = null) = byLambdaList(key) { it.long }

inline fun JsonModel.longList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long>
) = lambdaList(key, default) { it.long }

inline fun JsonModel.longList(key: String? = null) = lambdaList(key) { it.long }

inline val JsonObject.byLongList
    get() = byLongList()
inline val JsonModel.longList
    get() = longList()

/*
    List<Long?>
 */

inline fun JsonObject.byNullableLongList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long?>
) = byNullableLambdaList(key, default) { it.longOrNull }

inline fun JsonObject.byNullableLongList(key: String? = null) = byNullableLambdaList(key) { it.longOrNull }

inline fun JsonModel.nullableLongList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long?>
) = nullableLambdaList(key, default) { it.longOrNull }

inline fun JsonModel.nullableLongList(key: String? = null) = nullableLambdaList(key) { it.longOrNull }

inline val JsonObject.byNullableLongList
    get() = byNullableLongList()
inline val JsonModel.nullableLongList
    get() = nullableLongList()
