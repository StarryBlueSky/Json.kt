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
