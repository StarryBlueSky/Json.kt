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

import jp.nephy.jsonkt.JsonElement
import jp.nephy.jsonkt.JsonObject

/*
 *  JsonElement
 */

inline fun JsonObject.byJsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement>
) = byLambda(key, default) { it }

inline fun JsonObject.byJsonElement(
    key: String? = null,
    default: JsonElement
) = byLambda(key, default) { it }

inline fun JsonObject.byJsonElement(key: String) = byLambda(key) { it }

inline fun JsonModel.jsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement>
) = lambda(key, default) { it }

inline fun JsonModel.jsonElement(
    key: String? = null,
    default: JsonElement
) = lambda(key, default) { it }

inline fun JsonModel.jsonElement(key: String) = lambda(key) { it }

inline val JsonObject.byJsonElement
    get() = byLambda { it }

inline val JsonModel.jsonElement
    get() = lambda { it }

/*
 *  JsonElement?
 */

inline fun JsonObject?.byNullableJsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement?>
) = byNullableLambda(key, default) { it }

inline fun JsonObject?.byNullableJsonElement(
    key: String? = null,
    default: JsonElement?
) = byNullableLambda(key, default) { it }

inline fun JsonObject?.byNullableJsonElement(key: String) = byNullableLambda(key) { it }

inline fun JsonModel?.nullableJsonElement(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonElement?>
) = nullableLambda(key, default) { it }

inline fun JsonModel?.nullableJsonElement(
    key: String? = null,
    default: JsonElement?
) = nullableLambda(key, default) { it }

inline fun JsonModel?.nullableJsonElement(key: String) = nullableLambda(key) { it }

inline val JsonObject?.byNullableJsonElement
    get() = byNullableLambda { it }

inline val JsonModel?.nullableJsonElement
    get() = nullableLambda { it }

/*
 *  List<JsonElement>
 */

inline fun JsonObject.byJsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement>
) = byLambdaList(key, default) { it }

inline fun JsonObject.byJsonElementList(
    key: String? = null,
    default: List<JsonElement>
) = byLambdaList(key, default) { it }

inline fun JsonObject.byJsonElementList(key: String) = byLambdaList(key) { it }

inline fun JsonModel.jsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement>
) = lambdaList(key, default) { it }

inline fun JsonModel.jsonElementList(
    key: String? = null,
    default: List<JsonElement>
) = lambdaList(key, default) { it }

inline fun JsonModel.jsonElementList(key: String) = lambdaList(key) { it }

inline val JsonObject.byJsonElementList
    get() = byLambdaList { it }

inline val JsonModel.jsonElementList
    get() = lambdaList { it }

/*
 *  List<JsonElement?>
 */

inline fun JsonObject?.byNullableJsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement?>
) = byNullableLambdaList(key, default) { it }

inline fun JsonObject?.byNullableJsonElementList(
    key: String? = null,
    default: List<JsonElement?>
) = byNullableLambdaList(key, default) { it }

inline fun JsonObject?.byNullableJsonElementList(key: String) = byNullableLambdaList(key) { it }

inline fun JsonModel?.nullableJsonElementList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonElement?>
) = nullableLambdaList(key, default) { it }

inline fun JsonModel?.nullableJsonElementList(
    key: String? = null,
    default: List<JsonElement?>
) = nullableLambdaList(key, default) { it }

inline fun JsonModel?.nullableJsonElementList(key: String) = nullableLambdaList(key) { it }

inline val JsonObject?.byNullableJsonElementList
    get() = byNullableLambdaList { it }

inline val JsonModel?.nullableJsonElementList
    get() = nullableLambdaList { it }
