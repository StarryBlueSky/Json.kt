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
import blue.starry.jsonkt.string
import blue.starry.jsonkt.stringOrNull

/*
 *  String
 */

fun JsonObject.byString(
    key: String? = null,
    default: JsonObjectDefaultSelector<String>
) = byLambda(key, default) { it.string }

fun JsonObject.byString(
    key: String? = null,
    default: String
) = byLambda(key, default) { it.string }

fun JsonObject.byString(key: String) = byLambda(key) { it.string }

fun JsonModel.string(
    key: String? = null,
    default: JsonObjectDefaultSelector<String>
) = lambda(key, default) { it.string }

fun JsonModel.string(
    key: String? = null,
    default: String
) = lambda(key, default) { it.string }

fun JsonModel.string(key: String) = lambda(key) { it.string }

inline val JsonObject.byString
    get() = byLambda { it.string }

inline val JsonModel.string
    get() = lambda { it.string }

/*
 *  String?
 */

fun JsonObject?.byNullableString(
    key: String? = null,
    default: JsonObjectDefaultSelector<String?>
) = byNullableLambda(key, default) { it.stringOrNull }

fun JsonObject?.byNullableString(
    key: String? = null,
    default: String?
) = byNullableLambda(key, default) { it.stringOrNull }

fun JsonObject?.byNullableString(key: String) = byNullableLambda(key) { it.stringOrNull }

fun JsonModel?.nullableString(
    key: String? = null,
    default: JsonObjectDefaultSelector<String?>
) = nullableLambda(key, default) { it.stringOrNull }

fun JsonModel?.nullableString(
    key: String? = null,
    default: String?
) = nullableLambda(key, default) { it.stringOrNull }

fun JsonModel?.nullableString(key: String) = nullableLambda(key) { it.stringOrNull }

inline val JsonObject?.byNullableString
    get() = byNullableLambda { it.stringOrNull }

inline val JsonModel?.nullableString
    get() = nullableLambda { it.stringOrNull }

/*
 *  List<String>
 */

fun JsonObject.byStringList(
    key: String? = null,
    default: JsonArrayDefaultSelector<String>
) = byLambdaList(key, default) { it.string }

fun JsonObject.byStringList(
    key: String? = null,
    default: List<String>
) = byLambdaList(key, default) { it.string }

fun JsonObject.byStringList(key: String) = byLambdaList(key) { it.string }

fun JsonModel.stringList(
    key: String? = null,
    default: JsonArrayDefaultSelector<String>
) = lambdaList(key, default) { it.string }

fun JsonModel.stringList(
    key: String? = null,
    default: List<String>
) = lambdaList(key, default) { it.string }

fun JsonModel.stringList(key: String) = lambdaList(key) { it.string }

inline val JsonObject.byStringList
    get() = byLambdaList { it.string }

inline val JsonModel.stringList
    get() = lambdaList { it.string }

/*
 *  List<String?>
 */

fun JsonObject?.byNullableStringList(
    key: String? = null,
    default: JsonArrayDefaultSelector<String?>
) = byNullableLambdaList(key, default) { it.stringOrNull }

fun JsonObject?.byNullableStringList(
    key: String? = null,
    default: List<String?>
) = byNullableLambdaList(key, default) { it.stringOrNull }

fun JsonObject?.byNullableStringList(key: String) = byNullableLambdaList(key) { it.stringOrNull }

fun JsonModel?.nullableStringList(
    key: String? = null,
    default: JsonArrayDefaultSelector<String?>
) = nullableLambdaList(key, default) { it.stringOrNull }

fun JsonModel?.nullableStringList(
    key: String? = null,
    default: List<String?>
) = nullableLambdaList(key, default) { it.stringOrNull }

fun JsonModel?.nullableStringList(key: String) = nullableLambdaList(key) { it.stringOrNull }

inline val JsonObject?.byNullableStringList
    get() = byNullableLambdaList { it.stringOrNull }

inline val JsonModel?.nullableStringList
    get() = nullableLambdaList { it.stringOrNull }
