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

/*
    T
 */

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = jsonObjectProperty(key,  default, converter)

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = jsonObjectProperty(key,  converter = converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = json.jsonObjectProperty(key, default, converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = json.jsonObjectProperty(key, converter = converter)

/*
    T?
 */

inline fun <T: Any?> JsonObject?.byNullableLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = jsonObjectProperty(key, default, converter)

inline fun <T: Any?> JsonObject?.byNullableLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = jsonObjectProperty(key, converter = converter)

inline fun <T: Any?> JsonModel.nullableLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonObjectProperty(key, default, converter)

inline fun <T: Any?> JsonModel.nullableLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonObjectProperty(key, converter = converter)

/*
    List<T>
 */

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = jsonArrayProperty(key, default, converter)

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = jsonArrayProperty(key, converter = converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = json.jsonArrayProperty(key, default, converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = json.jsonArrayProperty(key, converter = converter)

/*
    List<T?>
 */

inline fun <T: Any?> JsonObject.byNullableLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = jsonArrayProperty(key, default, converter)

inline fun <T: Any?> JsonObject.byNullableLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = jsonArrayProperty(key, converter =  converter)

inline fun <T: Any?> JsonModel.nullableLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonArrayProperty(key, default, converter)

inline fun <T: Any?> JsonModel.nullableLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonArrayProperty(key, converter = converter)
