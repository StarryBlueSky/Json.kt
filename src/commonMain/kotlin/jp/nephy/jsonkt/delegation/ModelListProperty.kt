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
import jp.nephy.jsonkt.jsonObjectOf

@PublishedApi
internal inline fun <T: JsonModel> JsonObject.jsonModelListProperty(key: String? = null, crossinline block: (JsonObject) -> T): JsonArrayProperty<T> {
    return jsonArrayProperty(key) {
        block(it.jsonObject)
    }
}

@PublishedApi
internal inline fun <T: JsonModel> JsonObject.jsonModelListOrDefaultProperty(key: String? = null, crossinline block: (JsonObject) -> T): JsonArrayProperty<T> {
    return jsonArrayProperty(key) {
        runCatching {
            block(it.jsonObject)
        }.recoverCatching {
            block(jsonObjectOf())
        }.getOrThrow()
    }
}

@PublishedApi
internal inline fun <T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String? = null, crossinline block: (JsonObject) -> T): JsonArrayProperty<T?> {
    return nullableJsonArrayProperty(key) {
        block(it.jsonObject)
    }
}

@PublishedApi
internal inline fun <T: JsonModel> JsonModel.jsonModelListProperty(key: String? = null, crossinline block: (JsonObject) -> T): JsonArrayProperty<T> {
    return jsonArrayProperty(key) {
        block(it.jsonObject)
    }
}

@PublishedApi
internal inline fun <T: JsonModel> JsonModel.jsonModelListOrDefaultProperty(key: String? = null, crossinline block: (JsonObject) -> T): JsonArrayProperty<T> {
    return jsonArrayProperty(key) {
        runCatching {
            block(it.jsonObject)
        }.recoverCatching {
            block(jsonObjectOf())
        }.getOrThrow()
    }
}

@PublishedApi
internal inline fun <T: JsonModel> JsonModel?.jsonModelListOrNullProperty(key: String? = null, crossinline block: (JsonObject) -> T): JsonArrayProperty<T?> {
    return nullableJsonArrayProperty(key) {
        block(it.jsonObject)
    }
}

/*
    List<JsonModel>
 */

inline fun <T: JsonModel> JsonObject.byModelList(
    key: String? = null,
    crossinline block: (JsonObject) -> T
): JsonArrayProperty<T> = jsonModelListProperty(key, block)

inline fun <T: JsonModel> JsonModel.modelList(
    key: String? = null,
    crossinline block: (JsonObject) -> T
): JsonArrayProperty<T> = jsonModelListProperty(key, block)

inline fun <T: JsonModel> JsonObject.byModelListOrDefault(
    key: String? = null,
    crossinline block: (JsonObject) -> T
): JsonArrayProperty<T> = jsonModelListOrDefaultProperty(key, block)

inline fun <T: JsonModel> JsonModel.modelListOrDefault(
    key: String? = null,
    crossinline block: (JsonObject) -> T
): JsonArrayProperty<T> = jsonModelListOrDefaultProperty(key, block)

/*
    List<JsonModel?>
 */

inline fun <T: JsonModel> JsonObject?.byNullableModelList(
    key: String? = null,
    crossinline block: (JsonObject) -> T
): JsonArrayProperty<T?> = jsonModelListOrNullProperty(key, block)

inline fun <T: JsonModel> JsonModel?.nullableModelList(
    key: String? = null,
    crossinline block: (JsonObject) -> T
): JsonArrayProperty<T?> = jsonModelListOrNullProperty(key, block)
