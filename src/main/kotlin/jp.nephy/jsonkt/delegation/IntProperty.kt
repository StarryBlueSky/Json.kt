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
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

/*
    Int
 */

inline fun JsonObject.byInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int>
) = byLambda(key, default) { it.int }

inline fun JsonObject.byInt(key: String? = null) = byLambda(key) { it.int }

inline fun JsonModel.int(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int>
) = lambda(key, default) { it.int }

inline fun JsonModel.int(key: String? = null) = lambda(key) { it.int }

inline val JsonObject.byInt
    get() = byInt()
inline val JsonModel.int
    get() = int()

/*
    Int?
 */

inline fun JsonObject?.byNullableInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int?>
) = byNullableLambda(key, default) { it.intOrNull }

inline fun JsonObject?.byNullableInt(key: String? = null) = byNullableLambda(key) { it.intOrNull }

inline fun JsonModel.nullableInt(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Int?>
) = nullableLambda(key, default) { it.intOrNull }

inline fun JsonModel.nullableInt(key: String? = null) = nullableLambda(key) { it.intOrNull }

inline val JsonObject.byNullableInt
    get() = byNullableInt()
inline val JsonModel.nullableInt
    get() = nullableInt()
inline val JsonModel.intOrNull
    get() = nullableInt

/*
    List<Int>
 */

inline fun JsonObject.byIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int>
) = byLambdaList(key, default) { it.int }

inline fun JsonObject.byIntList(key: String? = null) = byLambdaList(key) { it.int }

inline fun JsonModel.intList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int>
) = lambdaList(key, default) { it.int }

inline fun JsonModel.intList(key: String? = null) = lambdaList(key) { it.int }

inline val JsonObject.byIntList
    get() = byIntList()
inline val JsonModel.intList
    get() = intList()

/*
    List<Int?>
 */

inline fun JsonObject.byNullableIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int?>
) = byNullableLambdaList(key, default) { it.intOrNull }

inline fun JsonObject.byNullableIntList(key: String? = null) = byNullableLambdaList(key) { it.intOrNull }

inline fun JsonModel.nullableIntList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Int?>
) = nullableLambdaList(key, default) { it.intOrNull }

inline fun JsonModel.nullableIntList(key: String? = null) = nullableLambdaList(key) { it.intOrNull }

inline val JsonObject.byNullableIntList
    get() = byNullableIntList()
inline val JsonModel.nullableIntList
    get() = nullableIntList()
