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
import jp.nephy.jsonkt.JsonPrimitive
import jp.nephy.jsonkt.primitiveOrNull

/*
    JsonPrimitive
 */

inline fun JsonObject.byJsonPrimitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive>
) = byLambda(key, default) { it.primitive }

inline fun JsonObject.byJsonPrimitive(key: String? = null) = byLambda(key) { it.primitive }

inline fun JsonModel.primitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive>
) = lambda(key, default) { it.primitive }

inline fun JsonModel.primitive(key: String? = null) = lambda(key) { it.primitive }

inline val JsonObject.byJsonPrimitive
    get() = byJsonPrimitive()
inline val JsonModel.primitive
    get() = primitive()

/*
    JsonPrimitive?
 */

inline fun JsonObject?.byNullableJsonPrimitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive?>
) = byNullableLambda(key, default) { it.primitiveOrNull }

inline fun JsonObject?.byNullableJsonPrimitive(key: String? = null) = byNullableLambda(key) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitive(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonPrimitive?>
) = nullableLambda(key, default) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitive(key: String? = null) = nullableLambda(key) { it.primitiveOrNull }

inline val JsonObject.byNullableJsonPrimitive
    get() = byNullableJsonPrimitive()
inline val JsonModel.nullableJsonPrimitive
    get() = nullableJsonPrimitive()
inline val JsonModel.primitiveOrNull
    get() = nullableJsonPrimitive

/*
    List<JsonPrimitive>
 */

inline fun JsonObject.byJsonPrimitiveList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive>
) = byLambdaList(key, default) { it.primitive }

inline fun JsonObject.byJsonPrimitiveList(key: String? = null) = byLambdaList(key) { it.primitive }

inline fun JsonModel.primitiveList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive>
) = lambdaList(key, default) { it.primitive }

inline fun JsonModel.primitiveList(key: String? = null) = lambdaList(key) { it.primitive }

inline val JsonObject.byJsonPrimitiveList
    get() = byJsonPrimitiveList()
inline val JsonModel.primitiveList
    get() = primitiveList()

/*
    List<JsonPrimitive?>
 */

inline fun JsonObject.byNullableJsonPrimitiveList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive?>
) = byNullableLambdaList(key, default) { it.primitiveOrNull }

inline fun JsonObject.byNullableJsonPrimitiveList(key: String? = null) = byNullableLambdaList(key) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitiveList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonPrimitive?>
) = nullableLambdaList(key, default) { it.primitiveOrNull }

inline fun JsonModel.nullableJsonPrimitiveList(key: String? = null) = nullableLambdaList(key) { it.primitiveOrNull }

inline val JsonObject.byNullableJsonPrimitiveList
    get() = byNullableJsonPrimitiveList()
inline val JsonModel.nullableJsonPrimitiveList
    get() = nullableJsonPrimitiveList()
