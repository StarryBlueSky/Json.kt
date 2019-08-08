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

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.jsonObjectOrNull

/*
 *  JsonObject
 */

inline fun JsonObject.byJsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject>
) = byLambda(key, default) { it.jsonObject }

inline fun JsonObject.byJsonObject(
    key: String? = null,
    default: JsonObject
) = byLambda(key, default) { it.jsonObject }

inline fun JsonObject.byJsonObject(key: String) = byLambda(key) { it.jsonObject }

inline fun JsonModel.jsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject>
) = lambda(key, default) { it.jsonObject }

inline fun JsonModel.jsonObject(
    key: String? = null,
    default: JsonObject
) = lambda(key, default) { it.jsonObject }

inline fun JsonModel.jsonObject(key: String) = lambda(key) { it.jsonObject }

inline val JsonObject.byJsonObject
    get() = byLambda { it.jsonObject }

inline val JsonModel.jsonObject
    get() = lambda { it.jsonObject }

/*
 *  JsonObject?
 */

inline fun JsonObject?.byNullableJsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject?>
) = byNullableLambda(key, default) { it.jsonObjectOrNull }

inline fun JsonObject?.byNullableJsonObject(
    key: String? = null,
    default: JsonObject?
) = byNullableLambda(key, default) { it.jsonObjectOrNull }

inline fun JsonObject?.byNullableJsonObject(key: String) = byNullableLambda(key) { it.jsonObjectOrNull }

inline fun JsonModel?.nullableJsonObject(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonObject?>
) = nullableLambda(key, default) { it.jsonObjectOrNull }

inline fun JsonModel?.nullableJsonObject(
    key: String? = null,
    default: JsonObject?
) = nullableLambda(key, default) { it.jsonObjectOrNull }

inline fun JsonModel?.nullableJsonObject(key: String) = nullableLambda(key) { it.jsonObjectOrNull }

inline val JsonObject?.byNullableJsonObject
    get() = byNullableLambda { it.jsonObjectOrNull }

inline val JsonModel?.nullableJsonObject
    get() = nullableLambda { it.jsonObjectOrNull }

/*
 *  List<JsonObject>
 */

inline fun JsonObject.byJsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject>
) = byLambdaList(key, default) { it.jsonObject }

inline fun JsonObject.byJsonObjectList(
    key: String? = null,
    default: List<JsonObject>
) = byLambdaList(key, default) { it.jsonObject }

inline fun JsonObject.byJsonObjectList(key: String) = byLambdaList(key) { it.jsonObject }

inline fun JsonModel.jsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject>
) = lambdaList(key, default) { it.jsonObject }

inline fun JsonModel.jsonObjectList(
    key: String? = null,
    default: List<JsonObject>
) = lambdaList(key, default) { it.jsonObject }

inline fun JsonModel.jsonObjectList(key: String) = lambdaList(key) { it.jsonObject }

inline val JsonObject.byJsonObjectList
    get() = byLambdaList { it.jsonObject }

inline val JsonModel.jsonObjectList
    get() = lambdaList { it.jsonObject }

/*
 *  List<JsonObject?>
 */

inline fun JsonObject?.byNullableJsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject?>
) = byNullableLambdaList(key, default) { it.jsonObjectOrNull }

inline fun JsonObject?.byNullableJsonObjectList(
    key: String? = null,
    default: List<JsonObject?>
) = byNullableLambdaList(key, default) { it.jsonObjectOrNull }

inline fun JsonObject?.byNullableJsonObjectList(key: String) = byNullableLambdaList(key) { it.jsonObjectOrNull }

inline fun JsonModel?.nullableJsonObjectList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonObject?>
) = nullableLambdaList(key, default) { it.jsonObjectOrNull }

inline fun JsonModel?.nullableJsonObjectList(
    key: String? = null,
    default: List<JsonObject?>
) = nullableLambdaList(key, default) { it.jsonObjectOrNull }

inline fun JsonModel?.nullableJsonObjectList(key: String) = nullableLambdaList(key) { it.jsonObjectOrNull }

inline val JsonObject?.byNullableJsonObjectList
    get() = byNullableLambdaList { it.jsonObjectOrNull }

inline val JsonModel?.nullableJsonObjectList
    get() = nullableLambdaList { it.jsonObjectOrNull }
