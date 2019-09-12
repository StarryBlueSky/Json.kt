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

@file:Suppress("UNUSED")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonObject?.jsonModelOrDefaultProperty(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonObject?.jsonModelOrNullProperty(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T?>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonModel?.jsonModelProperty(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonModel?.jsonModelOrDefaultProperty(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonModel?.jsonModelOrNullProperty(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T?>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String? = null): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonObject?.jsonModelOrDefaultProperty(key: String? = null): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonObject?.jsonModelOrNullProperty(key: String? = null): JsonObjectProperty<T?>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonModel?.jsonModelProperty(key: String? = null): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonModel?.jsonModelOrDefaultProperty(key: String? = null): JsonObjectProperty<T>

@PublishedApi
internal expect inline fun <reified T: JsonModel> JsonModel?.jsonModelOrNullProperty(key: String? = null): JsonObjectProperty<T?>

/*
    JsonModel
 */

inline fun <reified T: JsonModel> JsonObject.byModel(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T> = jsonModelProperty<T>(key, *parameters)

inline fun <reified T: JsonModel> JsonModel.model(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T> = jsonModelProperty<T>(key, *parameters)

inline fun <reified T: JsonModel> JsonObject.byModelOrDefault(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T> = jsonModelOrDefaultProperty<T>(key, *parameters)

inline fun <reified T: JsonModel> JsonModel.modelOrDefault(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T> = jsonModelOrDefaultProperty<T>(key, *parameters)

inline fun <reified T: JsonModel> JsonObject.byModel(key: String? = null): JsonObjectProperty<T> = jsonModelProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.model(key: String? = null): JsonObjectProperty<T> = jsonModelProperty<T>(key)

inline fun <reified T: JsonModel> JsonObject.byModelOrDefault(key: String? = null): JsonObjectProperty<T> = jsonModelOrDefaultProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.modelOrDefault(key: String? = null): JsonObjectProperty<T> = jsonModelOrDefaultProperty<T>(key)

/*
    JsonModel?
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModel(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T?> = jsonModelOrNullProperty<T>(key, *parameters)

inline fun <reified T: JsonModel> JsonModel?.nullableModel(key: String? = null, vararg parameters: Any?): JsonObjectProperty<T?> = jsonModelOrNullProperty<T>(key, *parameters)

inline fun <reified T: JsonModel> JsonObject?.byNullableModel(key: String? = null): JsonObjectProperty<T?> = jsonModelOrNullProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel?.nullableModel(key: String? = null): JsonObjectProperty<T?> = jsonModelOrNullProperty<T>(key)
