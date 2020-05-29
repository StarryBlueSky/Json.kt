/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
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

@file:Suppress("UNUSED")

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.JsonObject
import kotlinx.serialization.json.float
import kotlinx.serialization.json.floatOrNull

/*
 *  Float
 */

fun JsonObject.byFloat(
    key: String? = null,
    default: JsonObjectDefaultSelector<Float>
) = byLambda(key, default) { it.float }

fun JsonObject.byFloat(
    key: String? = null,
    default: Float
) = byLambda(key, default) { it.float }

fun JsonObject.byFloat(key: String) = byLambda(key) { it.float }

fun JsonModel.float(
    key: String? = null,
    default: JsonObjectDefaultSelector<Float>
) = lambda(key, default) { it.float }

fun JsonModel.float(
    key: String? = null,
    default: Float
) = lambda(key, default) { it.float }

fun JsonModel.float(key: String) = lambda(key) { it.float }

inline val JsonObject.byFloat
    get() = byLambda { it.float }

inline val JsonModel.float
    get() = lambda { it.float }

/*
 *  Float?
 */

fun JsonObject?.byNullableFloat(
    key: String? = null,
    default: JsonObjectDefaultSelector<Float?>
) = byNullableLambda(key, default) { it.floatOrNull }

fun JsonObject?.byNullableFloat(
    key: String? = null,
    default: Float?
) = byNullableLambda(key, default) { it.floatOrNull }

fun JsonObject?.byNullableFloat(key: String) = byNullableLambda(key) { it.floatOrNull }

fun JsonModel?.nullableFloat(
    key: String? = null,
    default: JsonObjectDefaultSelector<Float?>
) = nullableLambda(key, default) { it.floatOrNull }

fun JsonModel?.nullableFloat(
    key: String? = null,
    default: Float?
) = nullableLambda(key, default) { it.floatOrNull }

fun JsonModel?.nullableFloat(key: String) = nullableLambda(key) { it.floatOrNull }

inline val JsonObject?.byNullableFloat
    get() = byNullableLambda { it.floatOrNull }

inline val JsonModel?.nullableFloat
    get() = nullableLambda { it.floatOrNull }

/*
 *  List<Float>
 */

fun JsonObject.byFloatList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Float>
) = byLambdaList(key, default) { it.float }

fun JsonObject.byFloatList(
    key: String? = null,
    default: List<Float>
) = byLambdaList(key, default) { it.float }

fun JsonObject.byFloatList(key: String) = byLambdaList(key) { it.float }

fun JsonModel.floatList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Float>
) = lambdaList(key, default) { it.float }

fun JsonModel.floatList(
    key: String? = null,
    default: List<Float>
) = lambdaList(key, default) { it.float }

fun JsonModel.floatList(key: String) = lambdaList(key) { it.float }

inline val JsonObject.byFloatList
    get() = byLambdaList { it.float }

inline val JsonModel.floatList
    get() = lambdaList { it.float }

/*
 *  List<Float?>
 */

fun JsonObject?.byNullableFloatList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Float?>
) = byNullableLambdaList(key, default) { it.floatOrNull }

fun JsonObject?.byNullableFloatList(
    key: String? = null,
    default: List<Float?>
) = byNullableLambdaList(key, default) { it.floatOrNull }

fun JsonObject?.byNullableFloatList(key: String) = byNullableLambdaList(key) { it.floatOrNull }

fun JsonModel?.nullableFloatList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Float?>
) = nullableLambdaList(key, default) { it.floatOrNull }

fun JsonModel?.nullableFloatList(
    key: String? = null,
    default: List<Float?>
) = nullableLambdaList(key, default) { it.floatOrNull }

fun JsonModel?.nullableFloatList(key: String) = nullableLambdaList(key) { it.floatOrNull }

inline val JsonObject?.byNullableFloatList
    get() = byNullableLambdaList { it.floatOrNull }

inline val JsonModel?.nullableFloatList
    get() = nullableLambdaList { it.floatOrNull }
