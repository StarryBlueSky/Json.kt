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
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull

/*
    Boolean
 */

inline fun JsonObject.byBoolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean>
) = byLambda(key, default) { it.boolean }

inline fun JsonObject.byBoolean(key: String? = null) = byLambda(key) { it.boolean }

inline fun JsonModel.boolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean>
) = lambda(key, default) { it.boolean }

inline fun JsonModel.boolean(key: String? = null) = lambda(key) { it.boolean }

inline val JsonObject.byBoolean
    get() = byBoolean()
inline val JsonModel.boolean
    get() = boolean()

/*
    Boolean?
 */

inline fun JsonObject?.byNullableBoolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean?>
) = byNullableLambda(key, default) { it.booleanOrNull }

inline fun JsonObject?.byNullableBoolean(key: String? = null) = byNullableLambda(key) { it.booleanOrNull }

inline fun JsonModel.nullableBoolean(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<Boolean?>
) = nullableLambda(key, default) { it.booleanOrNull }

inline fun JsonModel.nullableBoolean(key: String? = null) = nullableLambda(key) { it.booleanOrNull }

inline val JsonObject.byNullableBoolean
    get() = byNullableBoolean()
inline val JsonModel.nullableBoolean
    get() = nullableBoolean()
inline val JsonModel.booleanOrNull
    get() = nullableBoolean

/*
    List<Boolean>
 */

inline fun JsonObject.byBooleanList (
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean>
) = byLambdaList(key, default) { it.boolean }

inline fun JsonObject.byBooleanList(key: String? = null) = byLambdaList(key) { it.boolean }

inline fun JsonModel.booleanList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean>
) = lambdaList(key, default) { it.boolean }

inline fun JsonModel.booleanList(key: String? = null) = lambdaList(key) { it.boolean }

inline val JsonObject.byBooleanList
    get() = byBooleanList()
inline val JsonModel.booleanList
    get() = booleanList()

/*
    List<Boolean?>
 */

inline fun JsonObject.byNullableBooleanList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean?>
) = byNullableLambdaList(key, default) { it.booleanOrNull }

inline fun JsonObject.byNullableBooleanList(key: String? = null) = byNullableLambdaList(key) { it.booleanOrNull }

inline fun JsonModel.nullableBooleanList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<Boolean?>
) = nullableLambdaList(key, default) { it.booleanOrNull }

inline fun JsonModel.nullableBooleanList(key: String? = null) = nullableLambdaList(key) { it.booleanOrNull }

inline val JsonObject.byNullableBooleanList
    get() = byNullableBooleanList()
inline val JsonModel.nullableBooleanList
    get() = nullableBooleanList()
