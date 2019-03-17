/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2019 Nephy Project Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
