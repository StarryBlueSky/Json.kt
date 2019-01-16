@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.string
import jp.nephy.jsonkt.stringOrNull

/*
    String
 */

inline fun JsonObject.byString(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String>
) = byLambda(key, default) { it.string }

inline fun JsonObject.byString(key: String? = null) = byLambda(key) { it.string }

inline fun JsonModel.string(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String>
) = lambda(key, default) { it.string }

inline fun JsonModel.string(key: String? = null) = lambda(key) { it.string }

inline val JsonObject.byString
    get() = byString()
inline val JsonModel.string
    get() = string()

/*
    String?
 */

inline fun JsonObject?.byNullableString(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String?>
) = byNullableLambda(key, default) { it.stringOrNull }

inline fun JsonObject?.byNullableString(key: String? = null) = byNullableLambda(key) { it.stringOrNull }

inline fun JsonModel.nullableString(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String?>
) = nullableLambda(key, default) { it.stringOrNull }

inline fun JsonModel.nullableString(key: String? = null) = nullableLambda(key) { it.stringOrNull }

inline val JsonObject.byNullableString
    get() = byNullableString()
inline val JsonModel.nullableString
    get() = nullableString()
inline val JsonModel.stringOrNull
    get() = nullableString

/*
    List<String>
 */

inline fun JsonObject.byStringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String>
) = byLambdaList(key, default) { it.string }

inline fun JsonObject.byStringList(key: String? = null) = byLambdaList(key) { it.string }

inline fun JsonModel.stringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String>
) = lambdaList(key, default) { it.string }

inline fun JsonModel.stringList(key: String? = null) = lambdaList(key) { it.string }

inline val JsonObject.byStringList
    get() = byStringList()
inline val JsonModel.stringList
    get() = stringList()

/*
    List<String?>
 */

inline fun JsonObject.byNullableStringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String?>
) = byNullableLambdaList(key, default) { it.stringOrNull }

inline fun JsonObject.byNullableStringList(key: String? = null) = byNullableLambdaList(key) { it.stringOrNull }

inline fun JsonModel.nullableStringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String?>
) = nullableLambdaList(key, default) { it.stringOrNull }

inline fun JsonModel.nullableStringList(key: String? = null) = nullableLambdaList(key) { it.stringOrNull }

inline val JsonObject.byNullableStringList
    get() = byNullableStringList()
inline val JsonModel.nullableStringList
    get() = nullableStringList()
