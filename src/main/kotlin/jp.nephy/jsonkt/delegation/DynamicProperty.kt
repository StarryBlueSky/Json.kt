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


package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*

class JsonNullPointerException(val key: String, val json: JsonObject?): JsonKtException("json[\"$key\"] is null or is not present but return type is non-null:\n${json.toString().take(100)}...")

internal typealias JsonElementConverter<T> = (JsonElement) -> T

typealias JsonObjectProperty<T> = CachingReadOnlyProperty<JsonModel, T>
internal typealias JsonObjectDefaultSelector<T> = (JsonObject) -> T

/**
 * @throws JsonNullPointerException
 */
inline fun <T: Any?> JsonObject?.jsonObjectProperty(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T> = { throw NotImplementedError() },
    crossinline converter: JsonElementConverter<T> = { throw NotImplementedError() }
) = JsonObjectProperty { _, property ->
    val json = this@jsonObjectProperty
    val jsonKey = key ?: property.name
    val jsonValue = json?.get(jsonKey)

    val tmp = runCatching {
        if (jsonValue.isNull()) {
            json?.let(default)
        } else {
            jsonValue.let(converter)
        }
    }.recoverCatching {
        json?.let(default)
    }.onFailure {
        if (!property.isMarkedNullable) {
            throw it
        }
    }.getOrNull()

    if (tmp == null && !property.isMarkedNullable) {
        throw JsonNullPointerException(jsonKey, json)
    }

    @Suppress("UNCHECKED_CAST")
    tmp as T
}

typealias JsonArrayProperty<T> = CachingReadOnlyProperty<Any?, List<T>>
internal typealias JsonArrayDefaultSelector<T> = (JsonObject) -> List<T>

/**
 * @throws JsonNullPointerException
 */
inline fun <T: Any?> JsonObject?.jsonArrayProperty(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T> = { emptyList() },
    crossinline converter: JsonElementConverter<T> = { throw NotImplementedError() }
) = JsonArrayProperty { _, property ->
    val json = this@jsonArrayProperty ?: return@JsonArrayProperty emptyList<T>()
    val jsonKey = key ?: property.name
    val jsonValue = get(jsonKey)

    if (jsonValue.isNull() || jsonValue !is JsonArray) {
        default.invoke(json)
    } else {
        runCatching {
            jsonValue.map { element ->
                val tmp = runCatching {
                    if (element.isNull) {
                        null
                    } else {
                        converter.invoke(element)
                    }
                }.onFailure {
                    if (!property.isMarkedNullable) {
                        throw it
                    }
                }.getOrNull()

                if (tmp == null && !property.isMarkedNullable) {
                    throw JsonNullPointerException(jsonKey, json)
                }

                @Suppress("UNCHECKED_CAST")
                tmp as T
            }
        }.recoverCatching {
            default.invoke(json)
        }.getOrThrow()
    }
}
