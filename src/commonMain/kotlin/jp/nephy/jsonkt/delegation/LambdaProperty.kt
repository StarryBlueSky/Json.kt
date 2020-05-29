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

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject

/*
 *  T
 */

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, default, converter)

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    default: T,
    crossinline converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, { default }, converter)

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, converter = converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, default, converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    default: T,
    crossinline converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, { default }, converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
): JsonObjectProperty<T> = jsonObjectProperty(key, converter = converter)

/*
 *  T?
 */

inline fun <T> JsonObject?.byNullableLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, default, converter)

inline fun <T> JsonObject?.byNullableLambda(
    key: String? = null,
    default: T?,
    crossinline converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, { default }, converter)

inline fun <T> JsonObject?.byNullableLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, converter = converter)

inline fun <T> JsonModel?.nullableLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, default, converter)

inline fun <T> JsonModel?.nullableLambda(
    key: String? = null,
    default: T?,
    crossinline converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, { default }, converter)

inline fun <T> JsonModel?.nullableLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = nullableJsonObjectProperty(key, converter = converter)

/*
 *  List<T>
 */

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, default, converter)

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    default: List<T>,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, { default }, converter)

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, converter = converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, default, converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    default: List<T>,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, { default }, converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = jsonArrayProperty(key, converter = converter)

/*
 *  List<T?>
 */

inline fun <T> JsonObject?.byNullableLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, default, converter)

inline fun <T> JsonObject?.byNullableLambdaList(
    key: String? = null,
    default: List<T?>,
    crossinline converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, { default }, converter)

inline fun <T> JsonObject?.byNullableLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, converter =  converter)

inline fun <T> JsonModel?.nullableLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, default, converter)

inline fun <T> JsonModel?.nullableLambdaList(
    key: String? = null,
    default: List<T?>,
    crossinline converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, { default }, converter)

inline fun <T> JsonModel?.nullableLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = nullableJsonArrayProperty(key, converter = converter)
