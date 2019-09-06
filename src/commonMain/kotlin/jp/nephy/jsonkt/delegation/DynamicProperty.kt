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

@file:Suppress("UNCHECKED_CAST")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.reflect.KProperty

internal typealias JsonElementConverter<T> = (JsonElement) -> T

typealias JsonObjectProperty<T> = JsonDelegateProperty<T>
internal typealias JsonObjectDefaultSelector<T> = (JsonObject) -> T

@PublishedApi
internal inline fun <T> KProperty<*>.jsonObject(json: JsonObject?, jsonKey: String, default: JsonObjectDefaultSelector<T>, converter: JsonElementConverter<T>): T {
    val jsonValue = json?.get(jsonKey)

    return runCatching {
        if (jsonValue.isNull()) {
            json?.let(default)
        } else {
            jsonValue.let(converter)
        }
    }.recoverCatching {
        json?.let(default)
    }.recoverCatching {
        if (!returnsNullable) {
            throw it
        }

        null
    }.mapCatching {
        if (it == null && !returnsNullable) {
            throw JsonNullPointerException(jsonKey, json)
        }

        it
    }.getOrThrow() as T
}

@PublishedApi
internal val jsonObjectDefaultSelector: Nothing
    get() = throw NotImplementedError("Default selector is not implemented.")

/**
 * @throws JsonNullPointerException
 */
inline fun <T> JsonObject?.jsonObjectProperty(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T> = { jsonObjectDefaultSelector },
    crossinline converter: JsonElementConverter<T>
) = JsonObjectProperty(key) { property ->
    val json = this
    val jsonKey = key ?: property.name

    property.jsonObject(json, jsonKey, default, converter)
}

/**
 * @throws JsonNullPointerException
 */
inline fun <T> JsonModel?.jsonObjectProperty(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T> = { jsonObjectDefaultSelector },
    crossinline converter: JsonElementConverter<T>
) = JsonObjectProperty(key) { property ->
    val json = this?.json
    val jsonKey = key ?: this?.keyConverter?.invoke(property) ?: property.name

    property.jsonObject(json, jsonKey, default, converter)
}

typealias JsonArrayProperty<T> = JsonDelegateProperty<List<T>>
internal typealias JsonArrayDefaultSelector<T> = (JsonObject) -> List<T>

@PublishedApi
internal inline fun <T> KProperty<*>.jsonArray(json: JsonObject?, jsonKey: String, default: JsonArrayDefaultSelector<T>, converter: JsonElementConverter<T>): List<T> {
    val jsonValue = json?.get(jsonKey)

    return runCatching {
        if (jsonValue.isNull() || jsonValue !is JsonArray) {
            json?.let(default)
        } else {
            jsonValue.map { element ->
                runCatching {
                    if (element.isNull()) {
                        null
                    } else {
                        element.let(converter)
                    }
                }.recoverCatching {
                    if (!returnsNullable) {
                        throw it
                    }

                    null
                }.mapCatching {
                    if (it == null && !returnsNullable) {
                        throw JsonNullPointerException(jsonKey, json)
                    }

                    it
                }.getOrThrow() as T
            }
        }
    }.recoverCatching {
        json?.let(default)
    }.getOrThrow() ?: emptyList()
}

/**
 * @throws JsonNullPointerException
 */
inline fun <T> JsonObject?.jsonArrayProperty(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T> = { emptyList() },
    crossinline converter: JsonElementConverter<T>
) = JsonArrayProperty(key) { property ->
    val json = this
    val jsonKey = key ?: property.name

    property.jsonArray(json, jsonKey, default, converter)
}

/**
 * @throws JsonNullPointerException
 */
inline fun <T> JsonModel?.jsonArrayProperty(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T> = { emptyList() },
    crossinline converter: JsonElementConverter<T>
) = JsonArrayProperty(key) { property ->
    val json = this?.json
    val jsonKey = key ?: this?.keyConverter?.invoke(property) ?: property.name

    property.jsonArray(json, jsonKey, default, converter)
}
