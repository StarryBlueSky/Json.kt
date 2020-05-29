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
import blue.starry.jsonkt.char
import blue.starry.jsonkt.charOrNull

/*
 *  Char
 */

fun JsonObject.byChar(
    key: String? = null,
    default: JsonObjectDefaultSelector<Char>
) = byLambda(key, default) { it.char }

fun JsonObject.byChar(
    key: String? = null,
    default: Char
) = byLambda(key, default) { it.char }

fun JsonObject.byChar(key: String) = byLambda(key) { it.char }

fun JsonModel.char(
    key: String? = null,
    default: JsonObjectDefaultSelector<Char>
) = lambda(key, default) { it.char }

fun JsonModel.char(
    key: String? = null,
    default: Char
) = lambda(key, default) { it.char }

fun JsonModel.char(key: String) = lambda(key) { it.char }

inline val JsonObject.byChar
    get() = byLambda { it.char }

inline val JsonModel.char
    get() = lambda { it.char }

/*
 *  Char?
 */

fun JsonObject?.byNullableChar(
    key: String? = null,
    default: JsonObjectDefaultSelector<Char?>
) = byNullableLambda(key, default) { it.charOrNull }

fun JsonObject?.byNullableChar(
    key: String? = null,
    default: Char?
) = byNullableLambda(key, default) { it.charOrNull }

fun JsonObject?.byNullableChar(key: String) = byNullableLambda(key) { it.charOrNull }

fun JsonModel?.nullableChar(
    key: String? = null,
    default: JsonObjectDefaultSelector<Char?>
) = nullableLambda(key, default) { it.charOrNull }

fun JsonModel?.nullableChar(
    key: String? = null,
    default: Char?
) = nullableLambda(key, default) { it.charOrNull }

fun JsonModel?.nullableChar(key: String) = nullableLambda(key) { it.charOrNull }

inline val JsonObject?.byNullableChar
    get() = byNullableLambda { it.charOrNull }

inline val JsonModel?.nullableChar
    get() = nullableLambda { it.charOrNull }

/*
 *  List<Char>
 */

fun JsonObject.byCharList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Char>
) = byLambdaList(key, default) { it.char }

fun JsonObject.byCharList(
    key: String? = null,
    default: List<Char>
) = byLambdaList(key, default) { it.char }

fun JsonObject.byCharList(key: String) = byLambdaList(key) { it.char }

fun JsonModel.charList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Char>
) = lambdaList(key, default) { it.char }

fun JsonModel.charList(
    key: String? = null,
    default: List<Char>
) = lambdaList(key, default) { it.char }

fun JsonModel.charList(key: String) = lambdaList(key) { it.char }

inline val JsonObject.byCharList
    get() = byLambdaList { it.char }

inline val JsonModel.charList
    get() = lambdaList { it.char }

/*
 *  List<Char?>
 */

fun JsonObject?.byNullableCharList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Char?>
) = byNullableLambdaList(key, default) { it.charOrNull }

fun JsonObject?.byNullableCharList(
    key: String? = null,
    default: List<Char?>
) = byNullableLambdaList(key, default) { it.charOrNull }

fun JsonObject?.byNullableCharList(key: String) = byNullableLambdaList(key) { it.charOrNull }

fun JsonModel?.nullableCharList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Char?>
) = nullableLambdaList(key, default) { it.charOrNull }

fun JsonModel?.nullableCharList(
    key: String? = null,
    default: List<Char?>
) = nullableLambdaList(key, default) { it.charOrNull }

fun JsonModel?.nullableCharList(key: String) = nullableLambdaList(key) { it.charOrNull }

inline val JsonObject?.byNullableCharList
    get() = byNullableLambdaList { it.charOrNull }

inline val JsonModel?.nullableCharList
    get() = nullableLambdaList { it.charOrNull }
