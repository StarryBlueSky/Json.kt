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
import kotlinx.serialization.json.long
import kotlinx.serialization.json.longOrNull

/*
 *  Long
 */

inline fun JsonObject.byLong(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long>
) = byLambda(key, default) { it.long }

inline fun JsonObject.byLong(
    key: String? = null,
    default: Long
) = byLambda(key, default) { it.long }

inline fun JsonObject.byLong(key: String) = byLambda(key) { it.long }

inline fun JsonModel.long(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long>
) = lambda(key, default) { it.long }

inline fun JsonModel.long(
    key: String? = null,
    default: Long
) = lambda(key, default) { it.long }

inline fun JsonModel.long(key: String) = lambda(key) { it.long }

inline val JsonObject.byLong
    get() = byLambda { it.long }

inline val JsonModel.long
    get() = lambda { it.long }

/*
 *  Long?
 */

inline fun JsonObject?.byNullableLong(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long?>
) = byNullableLambda(key, default) { it.longOrNull }

inline fun JsonObject?.byNullableLong(
    key: String? = null,
    default: Long?
) = byNullableLambda(key, default) { it.longOrNull }

inline fun JsonObject?.byNullableLong(key: String) = byNullableLambda(key) { it.longOrNull }

inline fun JsonModel?.nullableLong(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Long?>
) = nullableLambda(key, default) { it.longOrNull }

inline fun JsonModel?.nullableLong(
    key: String? = null,
    default: Long?
) = nullableLambda(key, default) { it.longOrNull }

inline fun JsonModel?.nullableLong(key: String) = nullableLambda(key) { it.longOrNull }

inline val JsonObject?.byNullableLong
    get() = byNullableLambda { it.longOrNull }

inline val JsonModel?.nullableLong
    get() = nullableLambda { it.longOrNull }

/*
 *  List<Long>
 */

inline fun JsonObject.byLongList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long>
) = byLambdaList(key, default) { it.long }

inline fun JsonObject.byLongList(
    key: String? = null,
    default: List<Long>
) = byLambdaList(key, default) { it.long }

inline fun JsonObject.byLongList(key: String) = byLambdaList(key) { it.long }

inline fun JsonModel.longList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long>
) = lambdaList(key, default) { it.long }

inline fun JsonModel.longList(
    key: String? = null,
    default: List<Long>
) = lambdaList(key, default) { it.long }

inline fun JsonModel.longList(key: String) = lambdaList(key) { it.long }

inline val JsonObject.byLongList
    get() = byLambdaList { it.long }

inline val JsonModel.longList
    get() = lambdaList { it.long }

/*
 *  List<Long?>
 */

inline fun JsonObject?.byNullableLongList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long?>
) = byNullableLambdaList(key, default) { it.longOrNull }

inline fun JsonObject?.byNullableLongList(
    key: String? = null,
    default: List<Long?>
) = byNullableLambdaList(key, default) { it.longOrNull }

inline fun JsonObject?.byNullableLongList(key: String) = byNullableLambdaList(key) { it.longOrNull }

inline fun JsonModel?.nullableLongList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Long?>
) = nullableLambdaList(key, default) { it.longOrNull }

inline fun JsonModel?.nullableLongList(
    key: String? = null,
    default: List<Long?>
) = nullableLambdaList(key, default) { it.longOrNull }

inline fun JsonModel?.nullableLongList(key: String) = nullableLambdaList(key) { it.longOrNull }

inline val JsonObject?.byNullableLongList
    get() = byNullableLambdaList { it.longOrNull }

inline val JsonModel?.nullableLongList
    get() = nullableLambdaList { it.longOrNull }
