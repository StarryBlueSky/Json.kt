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
