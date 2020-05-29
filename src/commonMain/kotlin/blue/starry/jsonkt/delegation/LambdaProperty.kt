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

/*
 *  T
 */

fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    default: JsonObjectDefaultSelector<T>,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, default, converter)

fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    default: T,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, { default }, converter)

fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, converter = converter)

fun <T: Any> JsonModel.lambda(
    key: String? = null,
    default: JsonObjectDefaultSelector<T>,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, default, converter)

fun <T: Any> JsonModel.lambda(
    key: String? = null,
    default: T,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, { default }, converter)

fun <T: Any> JsonModel.lambda(
    key: String? = null,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, converter = converter)

/*
 *  T?
 */

fun <T> JsonObject?.byNullableLambda(
    key: String? = null,
    default: JsonObjectDefaultSelector<T?>,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, default, converter)

fun <T> JsonObject?.byNullableLambda(
    key: String? = null,
    default: T?,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, { default }, converter)

fun <T> JsonObject?.byNullableLambda(
    key: String? = null,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, converter = converter)

fun <T> JsonModel?.nullableLambda(
    key: String? = null,
    default: JsonObjectDefaultSelector<T?>,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, default, converter)

fun <T> JsonModel?.nullableLambda(
    key: String? = null,
    default: T?,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, { default }, converter)

fun <T> JsonModel?.nullableLambda(
    key: String? = null,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, converter = converter)

/*
 *  List<T>
 */

fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    default: JsonArrayDefaultSelector<T>,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, default, converter)

fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    default: List<T>,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, { default }, converter)

fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, converter = converter)

fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    default: JsonArrayDefaultSelector<T>,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, default, converter)

fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    default: List<T>,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, { default }, converter)

fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, converter = converter)

/*
 *  List<T?>
 */

fun <T> JsonObject?.byNullableLambdaList(
    key: String? = null,
    default: JsonArrayDefaultSelector<T?>,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, default, converter)

fun <T> JsonObject?.byNullableLambdaList(
    key: String? = null,
    default: List<T?>,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, { default }, converter)

fun <T> JsonObject?.byNullableLambdaList(
    key: String? = null,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, converter =  converter)

fun <T> JsonModel?.nullableLambdaList(
    key: String? = null,
    default: JsonArrayDefaultSelector<T?>,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, default, converter)

fun <T> JsonModel?.nullableLambdaList(
    key: String? = null,
    default: List<T?>,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, { default }, converter)

fun <T> JsonModel?.nullableLambdaList(
    key: String? = null,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, converter = converter)
