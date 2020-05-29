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
import blue.starry.jsonkt.JsonPrimitive
import blue.starry.jsonkt.primitiveOrNull

/*
 *  JsonPrimitive
 */

fun JsonObject.byJsonPrimitive(
    key: String? = null,
    default: JsonObjectDefaultSelector<JsonPrimitive>
) = byLambda(key, default) { it.primitive }

fun JsonObject.byJsonPrimitive(
    key: String? = null,
    default: JsonPrimitive
) = byLambda(key, default) { it.primitive }

fun JsonObject.byJsonPrimitive(key: String) = byLambda(key) { it.primitive }

fun JsonModel.jsonPrimitive(
    key: String? = null,
    default: JsonObjectDefaultSelector<JsonPrimitive>
) = lambda(key, default) { it.primitive }

fun JsonModel.jsonPrimitive(
    key: String? = null,
    default: JsonPrimitive
) = lambda(key, default) { it.primitive }

fun JsonModel.jsonPrimitive(key: String) = lambda(key) { it.primitive }

inline val JsonObject.byJsonPrimitive
    get() = byLambda { it.primitive }

inline val JsonModel.jsonPrimitive
    get() = lambda { it.primitive }

/*
 *  JsonPrimitive?
 */

fun JsonObject?.byNullableJsonPrimitive(
    key: String? = null,
    default: JsonObjectDefaultSelector<JsonPrimitive?>
) = byNullableLambda(key, default) { it.primitiveOrNull }

fun JsonObject?.byNullableJsonPrimitive(
    key: String? = null,
    default: JsonPrimitive?
) = byNullableLambda(key, default) { it.primitiveOrNull }

fun JsonObject?.byNullableJsonPrimitive(key: String) = byNullableLambda(key) { it.primitiveOrNull }

fun JsonModel?.nullableJsonPrimitive(
    key: String? = null,
    default: JsonObjectDefaultSelector<JsonPrimitive?>
) = nullableLambda(key, default) { it.primitiveOrNull }

fun JsonModel?.nullableJsonPrimitive(
    key: String? = null,
    default: JsonPrimitive?
) = nullableLambda(key, default) { it.primitiveOrNull }

fun JsonModel?.nullableJsonPrimitive(key: String) = nullableLambda(key) { it.primitiveOrNull }

inline val JsonObject?.byNullableJsonPrimitive
    get() = byNullableLambda { it.primitiveOrNull }

inline val JsonModel?.nullableJsonPrimitive
    get() = nullableLambda { it.primitiveOrNull }

/*
 *  List<JsonPrimitive>
 */

fun JsonObject.byJsonPrimitiveList(
    key: String? = null,
    default: JsonArrayDefaultSelector<JsonPrimitive>
) = byLambdaList(key, default) { it.primitive }

fun JsonObject.byJsonPrimitiveList(
    key: String? = null,
    default: List<JsonPrimitive>
) = byLambdaList(key, default) { it.primitive }

fun JsonObject.byJsonPrimitiveList(key: String) = byLambdaList(key) { it.primitive }

fun JsonModel.jsonPrimitiveList(
    key: String? = null,
    default: JsonArrayDefaultSelector<JsonPrimitive>
) = lambdaList(key, default) { it.primitive }

fun JsonModel.jsonPrimitiveList(
    key: String? = null,
    default: List<JsonPrimitive>
) = lambdaList(key, default) { it.primitive }

fun JsonModel.jsonPrimitiveList(key: String) = lambdaList(key) { it.primitive }

inline val JsonObject.byJsonPrimitiveList
    get() = byLambdaList { it.primitive }

inline val JsonModel.jsonPrimitiveList
    get() = lambdaList { it.primitive }

/*
 *  List<JsonPrimitive?>
 */

fun JsonObject?.byNullableJsonPrimitiveList(
    key: String? = null,
    default: JsonArrayDefaultSelector<JsonPrimitive?>
) = byNullableLambdaList(key, default) { it.primitiveOrNull }

fun JsonObject?.byNullableJsonPrimitiveList(
    key: String? = null,
    default: List<JsonPrimitive?>
) = byNullableLambdaList(key, default) { it.primitiveOrNull }

fun JsonObject?.byNullableJsonPrimitiveList(key: String) = byNullableLambdaList(key) { it.primitiveOrNull }

fun JsonModel?.nullableJsonPrimitiveList(
    key: String? = null,
    default: JsonArrayDefaultSelector<JsonPrimitive?>
) = nullableLambdaList(key, default) { it.primitiveOrNull }

fun JsonModel?.nullableJsonPrimitiveList(
    key: String? = null,
    default: List<JsonPrimitive?>
) = nullableLambdaList(key, default) { it.primitiveOrNull }

fun JsonModel?.nullableJsonPrimitiveList(key: String) = nullableLambdaList(key) { it.primitiveOrNull }

inline val JsonObject?.byNullableJsonPrimitiveList
    get() = byNullableLambdaList { it.primitiveOrNull }

inline val JsonModel?.nullableJsonPrimitiveList
    get() = nullableLambdaList { it.primitiveOrNull }
