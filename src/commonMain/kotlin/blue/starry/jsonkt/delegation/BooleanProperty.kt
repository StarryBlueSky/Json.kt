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
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull

/*
 *  Boolean
 */

fun JsonObject.byBoolean(
    key: String? = null,
    default: JsonObjectDefaultSelector<Boolean>
) = byLambda(key, default) { it.boolean }

fun JsonObject.byBoolean(
    key: String? = null,
    default: Boolean
) = byLambda(key, default) { it.boolean }

fun JsonObject.byBoolean(key: String) = byLambda(key) { it.boolean }

fun JsonModel.boolean(
    key: String? = null,
    default: JsonObjectDefaultSelector<Boolean>
) = lambda(key, default) { it.boolean }

fun JsonModel.boolean(
    key: String? = null,
    default: Boolean
) = lambda(key, default) { it.boolean }

fun JsonModel.boolean(key: String) = lambda(key) { it.boolean }

inline val JsonObject.byBoolean
    get() = byLambda { it.boolean }

inline val JsonModel.boolean
    get() = lambda { it.boolean }

/*
 *  Boolean?
 */

fun JsonObject?.byNullableBoolean(
    key: String? = null,
    default: JsonObjectDefaultSelector<Boolean?>
) = byNullableLambda(key, default) { it.booleanOrNull }

fun JsonObject?.byNullableBoolean(
    key: String? = null,
    default: Boolean?
) = byNullableLambda(key, default) { it.booleanOrNull }

fun JsonObject?.byNullableBoolean(key: String) = byNullableLambda(key) { it.booleanOrNull }

fun JsonModel?.nullableBoolean(
    key: String? = null,
    default: JsonObjectDefaultSelector<Boolean?>
) = nullableLambda(key, default) { it.booleanOrNull }

fun JsonModel?.nullableBoolean(
    key: String? = null,
    default: Boolean?
) = nullableLambda(key, default) { it.booleanOrNull }

fun JsonModel?.nullableBoolean(key: String) = nullableLambda(key) { it.booleanOrNull }

inline val JsonObject?.byNullableBoolean
    get() = byNullableLambda { it.booleanOrNull }

inline val JsonModel?.nullableBoolean
    get() = nullableLambda { it.booleanOrNull }

/*
 *  List<Boolean>
 */

fun JsonObject.byBooleanList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Boolean>
) = byLambdaList(key, default) { it.boolean }

fun JsonObject.byBooleanList(
    key: String? = null,
    default: List<Boolean>
) = byLambdaList(key, default) { it.boolean }

fun JsonObject.byBooleanList(key: String) = byLambdaList(key) { it.boolean }

fun JsonModel.booleanList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Boolean>
) = lambdaList(key, default) { it.boolean }

fun JsonModel.booleanList(
    key: String? = null,
    default: List<Boolean>
) = lambdaList(key, default) { it.boolean }

fun JsonModel.booleanList(key: String) = lambdaList(key) { it.boolean }

inline val JsonObject.byBooleanList
    get() = byLambdaList { it.boolean }

inline val JsonModel.booleanList
    get() = lambdaList { it.boolean }

/*
 *  List<Boolean?>
 */

fun JsonObject?.byNullableBooleanList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Boolean?>
) = byNullableLambdaList(key, default) { it.booleanOrNull }

fun JsonObject?.byNullableBooleanList(
    key: String? = null,
    default: List<Boolean?>
) = byNullableLambdaList(key, default) { it.booleanOrNull }

fun JsonObject?.byNullableBooleanList(key: String) = byNullableLambdaList(key) { it.booleanOrNull }

fun JsonModel?.nullableBooleanList(
    key: String? = null,
    default: JsonArrayDefaultSelector<Boolean?>
) = nullableLambdaList(key, default) { it.booleanOrNull }

fun JsonModel?.nullableBooleanList(
    key: String? = null,
    default: List<Boolean?>
) = nullableLambdaList(key, default) { it.booleanOrNull }

fun JsonModel?.nullableBooleanList(key: String) = nullableLambdaList(key) { it.booleanOrNull }

inline val JsonObject?.byNullableBooleanList
    get() = byNullableLambdaList { it.booleanOrNull }

inline val JsonModel?.nullableBooleanList
    get() = nullableLambdaList { it.booleanOrNull }
