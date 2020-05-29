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

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.string
import jp.nephy.jsonkt.stringOrNull

/*
 *  String
 */

inline fun JsonObject.byString(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String>
) = byLambda(key, default) { it.string }

inline fun JsonObject.byString(
    key: String? = null,
    default: String
) = byLambda(key, default) { it.string }

inline fun JsonObject.byString(key: String) = byLambda(key) { it.string }

inline fun JsonModel.string(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String>
) = lambda(key, default) { it.string }

inline fun JsonModel.string(
    key: String? = null,
    default: String
) = lambda(key, default) { it.string }

inline fun JsonModel.string(key: String) = lambda(key) { it.string }

inline val JsonObject.byString
    get() = byLambda { it.string }

inline val JsonModel.string
    get() = lambda { it.string }

/*
 *  String?
 */

inline fun JsonObject?.byNullableString(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String?>
) = byNullableLambda(key, default) { it.stringOrNull }

inline fun JsonObject?.byNullableString(
    key: String? = null,
    default: String?
) = byNullableLambda(key, default) { it.stringOrNull }

inline fun JsonObject?.byNullableString(key: String) = byNullableLambda(key) { it.stringOrNull }

inline fun JsonModel?.nullableString(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<String?>
) = nullableLambda(key, default) { it.stringOrNull }

inline fun JsonModel?.nullableString(
    key: String? = null,
    default: String?
) = nullableLambda(key, default) { it.stringOrNull }

inline fun JsonModel?.nullableString(key: String) = nullableLambda(key) { it.stringOrNull }

inline val JsonObject?.byNullableString
    get() = byNullableLambda { it.stringOrNull }

inline val JsonModel?.nullableString
    get() = nullableLambda { it.stringOrNull }

/*
 *  List<String>
 */

inline fun JsonObject.byStringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String>
) = byLambdaList(key, default) { it.string }

inline fun JsonObject.byStringList(
    key: String? = null,
    default: List<String>
) = byLambdaList(key, default) { it.string }

inline fun JsonObject.byStringList(key: String) = byLambdaList(key) { it.string }

inline fun JsonModel.stringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String>
) = lambdaList(key, default) { it.string }

inline fun JsonModel.stringList(
    key: String? = null,
    default: List<String>
) = lambdaList(key, default) { it.string }

inline fun JsonModel.stringList(key: String) = lambdaList(key) { it.string }

inline val JsonObject.byStringList
    get() = byLambdaList { it.string }

inline val JsonModel.stringList
    get() = lambdaList { it.string }

/*
 *  List<String?>
 */

inline fun JsonObject?.byNullableStringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String?>
) = byNullableLambdaList(key, default) { it.stringOrNull }

inline fun JsonObject?.byNullableStringList(
    key: String? = null,
    default: List<String?>
) = byNullableLambdaList(key, default) { it.stringOrNull }

inline fun JsonObject?.byNullableStringList(key: String) = byNullableLambdaList(key) { it.stringOrNull }

inline fun JsonModel?.nullableStringList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<String?>
) = nullableLambdaList(key, default) { it.stringOrNull }

inline fun JsonModel?.nullableStringList(
    key: String? = null,
    default: List<String?>
) = nullableLambdaList(key, default) { it.stringOrNull }

inline fun JsonModel?.nullableStringList(key: String) = nullableLambdaList(key) { it.stringOrNull }

inline val JsonObject?.byNullableStringList
    get() = byNullableLambdaList { it.stringOrNull }

inline val JsonModel?.nullableStringList
    get() = nullableLambdaList { it.stringOrNull }
