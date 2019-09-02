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

import jp.nephy.jsonkt.*
import kotlin.reflect.KClass

interface JsonModel {
    val json: JsonObject
}

class InvalidJsonModelException(
    @Suppress("UNUSED_PARAMETER") val modelClass: KClass<*>
): JsonKtException("${modelClass.simpleName} does not have valid constructor.")

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
@PublishedApi
internal inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String? = null, vararg args: Any?) = jsonObjectProperty(key) { it.parse<T>(*args) }

@PublishedApi
internal inline fun <reified T: JsonModel> JsonObject?.jsonModelOrDefaultProperty(key: String? = null, vararg args: Any?) = jsonObjectProperty(key) { it.parseOrNull<T>(*args) ?: jsonObjectOf().parse(*args) }

@PublishedApi
internal inline fun <reified T: JsonModel> JsonObject?.jsonModelOrNullProperty(key: String? = null, vararg args: Any?) = jsonObjectProperty(key) { it.parseOrNull<T>(*args) }

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
@PublishedApi
internal inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String? = null) = jsonObjectProperty(key) { it.parse<T>() }

@PublishedApi
internal inline fun <reified T: JsonModel> JsonObject?.jsonModelOrDefaultProperty(key: String? = null) = jsonObjectProperty(key) { it.parseOrNull<T>() ?: jsonObjectOf().parse() }

@PublishedApi
internal inline fun <reified T: JsonModel> JsonObject?.jsonModelOrNullProperty(key: String? = null) = jsonObjectProperty(key) { it.parseOrNull<T>() }

/*
    JsonModel
 */

inline fun <reified T: JsonModel> JsonObject.byModel(key: String? = null, vararg args: Any?) = jsonModelProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.model(key: String? = null, vararg args: Any?) = json.jsonModelProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonObject.byModelOrDefault(key: String? = null, vararg args: Any?) = jsonModelOrDefaultProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.modelOrDefault(key: String? = null, vararg args: Any?) = json.jsonModelOrDefaultProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonObject.byModel(key: String? = null) = jsonModelProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.model(key: String? = null) = json.jsonModelProperty<T>(key)

inline fun <reified T: JsonModel> JsonObject.byModelOrDefault(key: String? = null) = jsonModelOrDefaultProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.modelOrDefault(key: String? = null) = json.jsonModelOrDefaultProperty<T>(key)

/*
    JsonModel?
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModel(key: String? = null, vararg args: Any?) = jsonModelOrNullProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.nullableModel(key: String? = null, vararg args: Any?) = json.jsonModelOrNullProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonObject?.byNullableModel(key: String? = null) = jsonModelOrNullProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.nullableModel(key: String? = null) = json.jsonModelOrNullProperty<T>(key)

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String? = null, vararg args: Any?) = jsonArrayProperty(key) { it.parse<T>(*args) }

inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String? = null, vararg args: Any?) = jsonArrayProperty(key) { it.parseOrNull<T>(*args) ?: jsonObjectOf().parse(*args) }

inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String? = null, vararg args: Any?) = jsonArrayProperty(key) { it.parseOrNull<T>(*args) }

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String? = null) = jsonArrayProperty(key) { it.parse<T>() }

inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String? = null) = jsonArrayProperty(key) { it.parseOrNull<T>() ?: jsonObjectOf().parse() }

inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String? = null) = jsonArrayProperty(key) { it.parseOrNull<T>() }


/*
    List<JsonModel>
 */

inline fun <reified T: JsonModel> JsonObject.byModelList(key: String? = null, vararg args: Any?) = jsonModelListProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.modelList(key: String? = null, vararg args: Any?) = json.jsonModelListProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonObject.byModelListOrDefault(key: String? = null, vararg args: Any?) = jsonModelListOrDefaultProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.modelListOrDefault(key: String? = null, vararg args: Any?) = json.jsonModelListOrDefaultProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonObject.byModelList(key: String? = null) = jsonModelListProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.modelList(key: String? = null) = json.jsonModelListProperty<T>(key)

inline fun <reified T: JsonModel> JsonObject.byModelListOrDefault(key: String? = null) = jsonModelListOrDefaultProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.modelListOrDefault(key: String? = null) = json.jsonModelListOrDefaultProperty<T>(key)


/*
    List<JsonModel?>
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModelList(key: String? = null, vararg args: Any?) = jsonModelListOrNullProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.nullableModelList(key: String? = null, vararg args: Any?) = json.jsonModelListOrNullProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonObject?.byNullableModelList(key: String? = null) = jsonModelListOrNullProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.nullableModelList(key: String? = null) = json.jsonModelListOrNullProperty<T>(key)
