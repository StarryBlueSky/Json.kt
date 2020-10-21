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
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonPrimitive

/*
 *  Int
 */

fun JsonObject.byInt(
    key: String? = null,
    default: JsonObjectDefaultSelector<Int>
) = byLambda(key, default) { it.jsonPrimitive.int }

fun JsonObject.byInt(
    key: String? = null,
    default: Int
) = byLambda(key, default) { it.jsonPrimitive.int }

fun JsonObject.byInt(key: String) = byLambda(key) { it.jsonPrimitive.int }

fun JsonModel.int(
    key: String? = null,
    default: JsonObjectDefaultSelector<Int>
) = lambda(key, default) { it.jsonPrimitive.int }

fun JsonModel.int(
    key: String? = null,
    default: Int
) = lambda(key, default) { it.jsonPrimitive.int }

fun JsonModel.int(key: String) = lambda(key) { it.jsonPrimitive.int }

inline val JsonObject.byInt
    get() = byLambda { it.jsonPrimitive.int }

inline val JsonModel.int
    get() = lambda { it.jsonPrimitive.int }

/*
 *  Int?
 */

fun JsonObject?.byNullableInt(
    key: String? = null,
    default: JsonObjectDefaultSelector<Int?>
) = byNullableLambda(key, default) { it.jsonPrimitive.intOrNull }

fun JsonObject?.byNullableInt(
    key: String? = null,
    default: Int?
) = byNullableLambda(key, default) { it.jsonPrimitive.intOrNull }

fun JsonObject?.byNullableInt(key: String) = byNullableLambda(key) { it.jsonPrimitive.intOrNull }

fun JsonModel?.nullableInt(
    key: String? = null,
    default: JsonObjectDefaultSelector<Int?>
) = nullableLambda(key, default) { it.jsonPrimitive.intOrNull }

fun JsonModel?.nullableInt(
    key: String? = null,
    default: Int?
) = nullableLambda(key, default) { it.jsonPrimitive.intOrNull }

fun JsonModel?.nullableInt(key: String) = nullableLambda(key) { it.jsonPrimitive.intOrNull }

inline val JsonObject?.byNullableInt
    get() = byNullableLambda { it.jsonPrimitive.intOrNull }

inline val JsonModel?.nullableInt
    get() = nullableLambda { it.jsonPrimitive.intOrNull }

/*
 *  List<Int>
 */

fun JsonObject.byIntList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Int>
) = byLambdaList(key, default) { it.jsonPrimitive.int }

fun JsonObject.byIntList(
    key: String? = null,
    default: List<Int>
) = byLambdaList(key, default) { it.jsonPrimitive.int }

fun JsonObject.byIntList(key: String) = byLambdaList(key) { it.jsonPrimitive.int }

fun JsonModel.intList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Int>
) = lambdaList(key, default) { it.jsonPrimitive.int }

fun JsonModel.intList(
    key: String? = null,
    default: List<Int>
) = lambdaList(key, default) { it.jsonPrimitive.int }

fun JsonModel.intList(key: String) = lambdaList(key) { it.jsonPrimitive.int }

inline val JsonObject.byIntList
    get() = byLambdaList { it.jsonPrimitive.int }

inline val JsonModel.intList
    get() = lambdaList { it.jsonPrimitive.int }

/*
 *  List<Int?>
 */

fun JsonObject?.byNullableIntList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Int?>
) = byNullableLambdaList(key, default) { it.jsonPrimitive.intOrNull }

fun JsonObject?.byNullableIntList(
    key: String? = null,
    default: List<Int?>
) = byNullableLambdaList(key, default) { it.jsonPrimitive.intOrNull }

fun JsonObject?.byNullableIntList(key: String) = byNullableLambdaList(key) { it.jsonPrimitive.intOrNull }

fun JsonModel?.nullableIntList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Int?>
) = nullableLambdaList(key, default) { it.jsonPrimitive.intOrNull }

fun JsonModel?.nullableIntList(
    key: String? = null,
    default: List<Int?>
) = nullableLambdaList(key, default) { it.jsonPrimitive.intOrNull }

fun JsonModel?.nullableIntList(key: String) = nullableLambdaList(key) { it.jsonPrimitive.intOrNull }

inline val JsonObject?.byNullableIntList
    get() = byNullableLambdaList { it.jsonPrimitive.intOrNull }

inline val JsonModel?.nullableIntList
    get() = nullableLambdaList { it.jsonPrimitive.intOrNull }
