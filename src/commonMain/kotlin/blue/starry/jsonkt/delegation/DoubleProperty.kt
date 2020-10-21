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
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull
import kotlinx.serialization.json.jsonPrimitive

/*
 *  Double
 */

fun JsonObject.byDouble(
    key: String? = null,
    default: JsonObjectDefaultSelector<Double>
) = byLambda(key, default) { it.jsonPrimitive.double }

fun JsonObject.byDouble(
    key: String? = null,
    default: Double
) = byLambda(key, default) { it.jsonPrimitive.double }

fun JsonObject.byDouble(key: String) = byLambda(key) { it.jsonPrimitive.double }

fun JsonModel.double(
    key: String? = null,
    default: JsonObjectDefaultSelector<Double>
) = lambda(key, default) { it.jsonPrimitive.double }

fun JsonModel.double(
    key: String? = null,
    default: Double
) = lambda(key, default) { it.jsonPrimitive.double }

fun JsonModel.double(key: String) = lambda(key) { it.jsonPrimitive.double }

inline val JsonObject.byDouble
    get() = byLambda { it.jsonPrimitive.double }

inline val JsonModel.double
    get() = lambda { it.jsonPrimitive.double }

/*
 *  Double?
 */

fun JsonObject?.byNullableDouble(
    key: String? = null,
    default: JsonObjectDefaultSelector<Double?>
) = byNullableLambda(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonObject?.byNullableDouble(
    key: String? = null,
    default: Double?
) = byNullableLambda(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonObject?.byNullableDouble(key: String) = byNullableLambda(key) { it.jsonPrimitive.doubleOrNull }

fun JsonModel?.nullableDouble(
    key: String? = null,
    default: JsonObjectDefaultSelector<Double?>
) = nullableLambda(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonModel?.nullableDouble(
    key: String? = null,
    default: Double?
) = nullableLambda(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonModel?.nullableDouble(key: String) = nullableLambda(key) { it.jsonPrimitive.doubleOrNull }

inline val JsonObject?.byNullableDouble
    get() = byNullableLambda { it.jsonPrimitive.doubleOrNull }

inline val JsonModel?.nullableDouble
    get() = nullableLambda { it.jsonPrimitive.doubleOrNull }

/*
 *  List<Double>
 */

fun JsonObject.byDoubleList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Double>
) = byLambdaList(key, default) { it.jsonPrimitive.double }

fun JsonObject.byDoubleList(
    key: String? = null,
    default: List<Double>
) = byLambdaList(key, default) { it.jsonPrimitive.double }

fun JsonObject.byDoubleList(key: String) = byLambdaList(key) { it.jsonPrimitive.double }

fun JsonModel.doubleList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Double>
) = lambdaList(key, default) { it.jsonPrimitive.double }

fun JsonModel.doubleList(
    key: String? = null,
    default: List<Double>
) = lambdaList(key, default) { it.jsonPrimitive.double }

fun JsonModel.doubleList(key: String) = lambdaList(key) { it.jsonPrimitive.double }

inline val JsonObject.byDoubleList
    get() = byLambdaList { it.jsonPrimitive.double }

inline val JsonModel.doubleList
    get() = lambdaList { it.jsonPrimitive.double }

/*
 *  List<Double?>
 */

fun JsonObject?.byNullableDoubleList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Double?>
) = byNullableLambdaList(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonObject?.byNullableDoubleList(
    key: String? = null,
    default: List<Double?>
) = byNullableLambdaList(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonObject?.byNullableDoubleList(key: String) = byNullableLambdaList(key) { it.jsonPrimitive.doubleOrNull }

fun JsonModel?.nullableDoubleList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Double?>
) = nullableLambdaList(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonModel?.nullableDoubleList(
    key: String? = null,
    default: List<Double?>
) = nullableLambdaList(key, default) { it.jsonPrimitive.doubleOrNull }

fun JsonModel?.nullableDoubleList(key: String) = nullableLambdaList(key) { it.jsonPrimitive.doubleOrNull }

inline val JsonObject?.byNullableDoubleList
    get() = byNullableLambdaList { it.jsonPrimitive.doubleOrNull }

inline val JsonModel?.nullableDoubleList
    get() = nullableLambdaList { it.jsonPrimitive.doubleOrNull }
