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

@file:Suppress("UNCHECKED_CAST")

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.*
import kotlin.reflect.KProperty

internal typealias JsonElementConverter<T> = (JsonElement) -> T

typealias JsonObjectProperty<T> = JsonDelegateProperty<T>
internal typealias JsonObjectDefaultSelector<T> = (JsonObject) -> T

@PublishedApi
internal inline fun <T: Any> KProperty<*>.jsonObject(
    json: JsonObject,
    jsonKey: String,
    default: JsonObjectDefaultSelector<T>,
    converter: JsonElementConverter<T>
): T {
    val jsonValue = json[jsonKey]

    if (jsonValue.isNull()) {
        return runCatching {
            json.let(default)
        }.onFailure {
            JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` because `jsonValue` is null, but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
        }.getOrThrow()
    }

    return runCatching {
        jsonValue.let(converter)
    }.onFailure {
        JsonKt.logger.info(it) { "`converter: JsonElementConverter<T>` throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.recoverCatching {
        json.let(default)
    }.onFailure {
        JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.getOrThrow()
}

@PublishedApi
internal inline fun <T> KProperty<*>.nullableJsonObject(
    json: JsonObject?,
    jsonKey: String,
    default: JsonObjectDefaultSelector<T>,
    converter: JsonElementConverter<T>
): T? {
    val jsonValue = json?.get(jsonKey)

    if (jsonValue.isNull()) {
        return runCatching {
            json?.let(default)
        }.onFailure {
            JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` because `jsonValue` is null, but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
        }.getOrThrow()
    }

    return runCatching {
        jsonValue.let(converter)
    }.onFailure {
        JsonKt.logger.info(it) { "`converter: JsonElementConverter<T>` throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.recoverCatching {
        json.let(default)
    }.onFailure {
        JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.getOrNull()
}

@PublishedApi
internal fun jsonObjectDefaultSelector(@Suppress("UNUSED_PARAMETER") json: JsonObject): Nothing {
    throw NotImplementedError("Default selector is not implemented.")
}

@PublishedApi
internal fun jsonObjectDefaultSelectorWithNull(@Suppress("UNUSED_PARAMETER") json: JsonObject): Nothing? {
    return null
}

fun <T: Any> JsonObject.jsonObjectProperty(
    key: String? = null,
    default: JsonObjectDefaultSelector<T> = ::jsonObjectDefaultSelector,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = JsonObjectProperty(key) { property ->
    val json = this
    val jsonKey = key ?: property.name

    property.jsonObject(json, jsonKey, default, converter)
}

fun <T: Any> JsonModel.jsonObjectProperty(
    key: String? = null,
    default: JsonObjectDefaultSelector<T> = ::jsonObjectDefaultSelector,
    converter: JsonElementConverter<T>
): JsonObjectProperty<T> = JsonObjectProperty(key) { property ->
    val json = json
    val jsonKey = key ?: keyConverter(property)

    property.jsonObject(json, jsonKey, default, converter)
}

fun <T> JsonObject?.nullableJsonObjectProperty(
    key: String? = null,
    default: JsonObjectDefaultSelector<T?> = ::jsonObjectDefaultSelectorWithNull,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = JsonObjectProperty(key) { property ->
    val json = this
    val jsonKey = key ?: property.name

    property.nullableJsonObject(json, jsonKey, default, converter)
}

fun <T> JsonModel?.nullableJsonObjectProperty(
    key: String? = null,
    default: JsonObjectDefaultSelector<T?> = ::jsonObjectDefaultSelectorWithNull,
    converter: JsonElementConverter<T?>
): JsonObjectProperty<T?> = JsonObjectProperty(key) { property ->
    val json = this?.json
    val jsonKey = key ?: this?.keyConverter?.invoke(property) ?: property.name

    property.nullableJsonObject(json, jsonKey, default, converter)
}

typealias JsonArrayProperty<T> = JsonDelegateProperty<List<T>>
internal typealias JsonArrayDefaultSelector<T> = (JsonObject) -> List<T>

@PublishedApi
internal inline fun <T: Any> KProperty<*>.jsonArray(
    json: JsonObject,
    jsonKey: String,
    default: JsonArrayDefaultSelector<T>,
    converter: JsonElementConverter<T>
): List<T> {
    val jsonValue = json[jsonKey]

    if (jsonValue.isNull() || jsonValue !is JsonArray) {
        return runCatching {
            json.let(default)
        }.onFailure {
            JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` because `jsonValue` is null or not JsonArray, but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
        }.getOrThrow()
    }

    return runCatching {
        jsonValue.map { element ->
            runCatching {
                if (element.isNull()) {
                    null
                } else {
                    element.let(converter)
                }
            }.mapCatching {
                if (it == null) {
                    throw JsonNullPointerException(jsonKey, json)
                }

                it
            }.getOrThrow()
        }
    }.onFailure {
        JsonKt.logger.info(it) { "`converter: JsonElementConverter<T>` throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.recoverCatching {
        json.let(default)
    }.onFailure {
        JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.getOrThrow()
}

@PublishedApi
internal inline fun <T> KProperty<*>.nullableJsonArray(
    json: JsonObject?,
    jsonKey: String,
    default: JsonArrayDefaultSelector<T>, converter: JsonElementConverter<T>
): List<T?> {
    val jsonValue = json?.get(jsonKey)

    if (jsonValue.isNull() || jsonValue !is JsonArray) {
        return runCatching {
            json?.let(default)
        }.onFailure {
            JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` because `jsonValue` is null or not JsonArray, but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
        }.getOrNull() ?: emptyList()
    }

    return runCatching {
        jsonValue.map { element ->
            runCatching {
                if (element.isNull()) {
                    null
                } else {
                    element.let(converter)
                }
            }.getOrNull()
        }
    }.onFailure {
        JsonKt.logger.info(it) { "`converter: JsonElementConverter<T>` throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.recoverCatching {
        json.let(default)
    }.onFailure {
        JsonKt.logger.info(it) { "Fallback to `default: JsonObjectDefaultSelector<T>` but it throws an error. jsonKey = `$jsonKey`, json =\n$json" }
    }.getOrNull() ?: emptyList()
}

@PublishedApi
internal fun <T> jsonArrayDefaultSelector(@Suppress("UNUSED_PARAMETER") json: JsonObject): List<T> {
    return emptyList()
}

inline fun <T: Any> JsonObject.jsonArrayProperty(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T> = ::jsonArrayDefaultSelector,
    crossinline converter: JsonElementConverter<T>
): JsonArrayProperty<T> = JsonArrayProperty(key) { property ->
    val json = this
    val jsonKey = key ?: property.name

    property.jsonArray(json, jsonKey, default, converter)
}

fun <T: Any> JsonModel.jsonArrayProperty(
    key: String? = null,
    default: JsonArrayDefaultSelector<T> = ::jsonArrayDefaultSelector,
    converter: JsonElementConverter<T>
): JsonArrayProperty<T> = JsonArrayProperty(key) { property ->
    val json = json
    val jsonKey = key ?: keyConverter(property)

    property.jsonArray(json, jsonKey, default, converter)
}

fun <T> JsonObject?.nullableJsonArrayProperty(
    key: String? = null,
    default: JsonArrayDefaultSelector<T?> = ::jsonArrayDefaultSelector,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = JsonArrayProperty(key) { property ->
    val json = this
    val jsonKey = key ?: property.name

    property.nullableJsonArray(json, jsonKey, default, converter)
}

fun <T> JsonModel?.nullableJsonArrayProperty(
    key: String? = null,
    default: JsonArrayDefaultSelector<T?> = ::jsonArrayDefaultSelector,
    converter: JsonElementConverter<T?>
): JsonArrayProperty<T?> = JsonArrayProperty(key) { property ->
    val json = this?.json
    val jsonKey = key ?: this?.keyConverter?.invoke(property) ?: property.name

    property.nullableJsonArray(json, jsonKey, default, converter)
}
