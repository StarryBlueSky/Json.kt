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

class InvalidJsonModelException(
    @Suppress("UNUSED_PARAMETER") val modelClass: KClass<*>
): JsonKtException("${modelClass.simpleName} does not have valid constructor.")

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String?, vararg args: Any?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parse<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelOrDefaultProperty(key: String?, vararg args: Any?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parseOrNull<T>(*args) ?: jsonObjectOf().parse(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelOrNullProperty(key: String?, vararg args: Any?): JsonObjectProperty<T?> {
    return jsonObjectProperty(key) { it.parseOrNull<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelProperty(key: String?, vararg args: Any?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parse<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelOrDefaultProperty(key: String?, vararg args: Any?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parseOrNull<T>(*args) ?: jsonObjectOf().parse(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelOrNullProperty(key: String?, vararg args: Any?): JsonObjectProperty<T?> {
    return jsonObjectProperty(key) { it.parseOrNull<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parse<T>() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelOrDefaultProperty(key: String?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parseOrNull<T>() ?: jsonObjectOf().parse() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelOrNullProperty(key: String?): JsonObjectProperty<T?> {
    return jsonObjectProperty(key) { it.parseOrNull<T>() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelProperty(key: String?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parse<T>() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelOrDefaultProperty(key: String?): JsonObjectProperty<T> {
    return jsonObjectProperty(key) { it.parseOrNull<T>() ?: jsonObjectOf().parse() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelOrNullProperty(key: String?): JsonObjectProperty<T?> {
    return jsonObjectProperty(key) { it.parseOrNull<T>() }
}
