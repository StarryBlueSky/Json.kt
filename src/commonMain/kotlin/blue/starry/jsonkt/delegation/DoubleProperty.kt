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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.doubleOrNull

/*
 *  Double
 */

inline fun JsonObject.byDouble(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double>
) = byLambda(key, default) { it.double }

inline fun JsonObject.byDouble(
    key: String? = null,
    default: Double
) = byLambda(key, default) { it.double }

inline fun JsonObject.byDouble(key: String) = byLambda(key) { it.double }

inline fun JsonModel.double(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double>
) = lambda(key, default) { it.double }

inline fun JsonModel.double(
    key: String? = null,
    default: Double
) = lambda(key, default) { it.double }

inline fun JsonModel.double(key: String) = lambda(key) { it.double }

inline val JsonObject.byDouble
    get() = byLambda { it.double }

inline val JsonModel.double
    get() = lambda { it.double }

/*
 *  Double?
 */

inline fun JsonObject?.byNullableDouble(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double?>
) = byNullableLambda(key, default) { it.doubleOrNull }

inline fun JsonObject?.byNullableDouble(
    key: String? = null,
    default: Double?
) = byNullableLambda(key, default) { it.doubleOrNull }

inline fun JsonObject?.byNullableDouble(key: String) = byNullableLambda(key) { it.doubleOrNull }

inline fun JsonModel?.nullableDouble(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Double?>
) = nullableLambda(key, default) { it.doubleOrNull }

inline fun JsonModel?.nullableDouble(
    key: String? = null,
    default: Double?
) = nullableLambda(key, default) { it.doubleOrNull }

inline fun JsonModel?.nullableDouble(key: String) = nullableLambda(key) { it.doubleOrNull }

inline val JsonObject?.byNullableDouble
    get() = byNullableLambda { it.doubleOrNull }

inline val JsonModel?.nullableDouble
    get() = nullableLambda { it.doubleOrNull }

/*
 *  List<Double>
 */

inline fun JsonObject.byDoubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double>
) = byLambdaList(key, default) { it.double }

inline fun JsonObject.byDoubleList(
    key: String? = null,
    default: List<Double>
) = byLambdaList(key, default) { it.double }

inline fun JsonObject.byDoubleList(key: String) = byLambdaList(key) { it.double }

inline fun JsonModel.doubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double>
) = lambdaList(key, default) { it.double }

inline fun JsonModel.doubleList(
    key: String? = null,
    default: List<Double>
) = lambdaList(key, default) { it.double }

inline fun JsonModel.doubleList(key: String) = lambdaList(key) { it.double }

inline val JsonObject.byDoubleList
    get() = byLambdaList { it.double }

inline val JsonModel.doubleList
    get() = lambdaList { it.double }

/*
 *  List<Double?>
 */

inline fun JsonObject?.byNullableDoubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double?>
) = byNullableLambdaList(key, default) { it.doubleOrNull }

inline fun JsonObject?.byNullableDoubleList(
    key: String? = null,
    default: List<Double?>
) = byNullableLambdaList(key, default) { it.doubleOrNull }

inline fun JsonObject?.byNullableDoubleList(key: String) = byNullableLambdaList(key) { it.doubleOrNull }

inline fun JsonModel?.nullableDoubleList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Double?>
) = nullableLambdaList(key, default) { it.doubleOrNull }

inline fun JsonModel?.nullableDoubleList(
    key: String? = null,
    default: List<Double?>
) = nullableLambdaList(key, default) { it.doubleOrNull }

inline fun JsonModel?.nullableDoubleList(key: String) = nullableLambdaList(key) { it.doubleOrNull }

inline val JsonObject?.byNullableDoubleList
    get() = byNullableLambdaList { it.doubleOrNull }

inline val JsonModel?.nullableDoubleList
    get() = nullableLambdaList { it.doubleOrNull }
