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
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

/*
 *  Int
 */

inline fun JsonObject.byInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int>
) = byLambda(key, default) { it.int }

inline fun JsonObject.byInt(
    key: String? = null,
    default: Int
) = byLambda(key, default) { it.int }

inline fun JsonObject.byInt(key: String) = byLambda(key) { it.int }

inline fun JsonModel.int(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int>
) = lambda(key, default) { it.int }

inline fun JsonModel.int(
    key: String? = null,
    default: Int
) = lambda(key, default) { it.int }

inline fun JsonModel.int(key: String) = lambda(key) { it.int }

inline val JsonObject.byInt
    get() = byLambda { it.int }

inline val JsonModel.int
    get() = lambda { it.int }

/*
 *  Int?
 */

inline fun JsonObject?.byNullableInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int?>
) = byNullableLambda(key, default) { it.intOrNull }

inline fun JsonObject?.byNullableInt(
    key: String? = null,
    default: Int?
) = byNullableLambda(key, default) { it.intOrNull }

inline fun JsonObject?.byNullableInt(key: String) = byNullableLambda(key) { it.intOrNull }

inline fun JsonModel?.nullableInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int?>
) = nullableLambda(key, default) { it.intOrNull }

inline fun JsonModel?.nullableInt(
    key: String? = null,
    default: Int?
) = nullableLambda(key, default) { it.intOrNull }

inline fun JsonModel?.nullableInt(key: String) = nullableLambda(key) { it.intOrNull }

inline val JsonObject?.byNullableInt
    get() = byNullableLambda { it.intOrNull }

inline val JsonModel?.nullableInt
    get() = nullableLambda { it.intOrNull }

/*
 *  List<Int>
 */

inline fun JsonObject.byIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int>
) = byLambdaList(key, default) { it.int }

inline fun JsonObject.byIntList(
    key: String? = null,
    default: List<Int>
) = byLambdaList(key, default) { it.int }

inline fun JsonObject.byIntList(key: String) = byLambdaList(key) { it.int }

inline fun JsonModel.intList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int>
) = lambdaList(key, default) { it.int }

inline fun JsonModel.intList(
    key: String? = null,
    default: List<Int>
) = lambdaList(key, default) { it.int }

inline fun JsonModel.intList(key: String) = lambdaList(key) { it.int }

inline val JsonObject.byIntList
    get() = byLambdaList { it.int }

inline val JsonModel.intList
    get() = lambdaList { it.int }

/*
 *  List<Int?>
 */

inline fun JsonObject?.byNullableIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int?>
) = byNullableLambdaList(key, default) { it.intOrNull }

inline fun JsonObject?.byNullableIntList(
    key: String? = null,
    default: List<Int?>
) = byNullableLambdaList(key, default) { it.intOrNull }

inline fun JsonObject?.byNullableIntList(key: String) = byNullableLambdaList(key) { it.intOrNull }

inline fun JsonModel?.nullableIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int?>
) = nullableLambdaList(key, default) { it.intOrNull }

inline fun JsonModel?.nullableIntList(
    key: String? = null,
    default: List<Int?>
) = nullableLambdaList(key, default) { it.intOrNull }

inline fun JsonModel?.nullableIntList(key: String) = nullableLambdaList(key) { it.intOrNull }

inline val JsonObject?.byNullableIntList
    get() = byNullableLambdaList { it.intOrNull }

inline val JsonModel?.nullableIntList
    get() = nullableLambdaList { it.intOrNull }
