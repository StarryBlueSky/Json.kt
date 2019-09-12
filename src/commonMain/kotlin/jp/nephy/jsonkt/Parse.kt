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

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel
import kotlin.reflect.KClass

/*
 * parse
 */

expect inline fun <T: JsonModel> JsonElement.parseAs(model: KClass<T>, vararg parameters: Any?): T

inline fun <T: JsonModel> JsonObject.parseAs(model: KClass<T>, vararg parameters: Any?): T {
    return (this as JsonElement).parseAs(model, *parameters)
}

inline fun <T: JsonModel> String.parseAs(model: KClass<T>, vararg parameters: Any?): T {
    return toJsonElement().parseAs(model, *parameters)
}

inline fun <T: JsonModel> JsonMap.parseAs(model: KClass<T>, vararg parameters: Any?): T {
    return toJsonObject().parseAs(model, *parameters)
}

inline fun <T: JsonModel> JsonPairIterable.parseAs(model: KClass<T>, vararg parameters: Any?): T {
    return toJsonObject().parseAs(model, *parameters)
}

inline fun <T: JsonModel> JsonPairSequence.parseAs(model: KClass<T>, vararg parameters: Any?): T {
    return toJsonObject().parseAs(model, *parameters)
}

inline fun <T: JsonModel> JsonPairArray.parseAs(model: KClass<T>, vararg parameters: Any?): T {
    return toJsonObject().parseAs(model, *parameters)
}

expect inline fun <T: JsonModel> JsonElement.parseAs(model: KClass<T>): T

inline fun <T: JsonModel> JsonObject.parseAs(model: KClass<T>): T {
    return (this as JsonElement).parseAs(model)
}

inline fun <T: JsonModel> String.parseAs(model: KClass<T>): T {
    return toJsonObject().parseAs(model)
}

inline fun <T: JsonModel> JsonMap.parseAs(model: KClass<T>): T {
    return toJsonObject().parseAs(model)
}

inline fun <T: JsonModel> JsonPairIterable.parseAs(model: KClass<T>): T {
    return toJsonObject().parseAs(model)
}

inline fun <T: JsonModel> JsonPairSequence.parseAs(model: KClass<T>): T {
    return toJsonObject().parseAs(model)
}

inline fun <T: JsonModel> JsonPairArray.parse(model: KClass<T>): T {
    return toJsonObject().parseAs(model)
}

/*
 * parseOrNull
 */

inline fun <T: JsonModel> JsonElement?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonObject?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> String?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonMap?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonPairIterable?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonPairSequence?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonPairArray?.parseAsOrNull(model: KClass<T>, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonElement?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

inline fun <T: JsonModel> JsonObject?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

inline fun <T: JsonModel> String?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

inline fun <T: JsonModel> JsonMap?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

inline fun <T: JsonModel> JsonPairIterable?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

inline fun <T: JsonModel> JsonPairSequence?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

inline fun <T: JsonModel> JsonPairArray?.parseAsOrNull(model: KClass<T>): T? {
    return runSafely {
        parseAs(model)
    }
}

/*
 * inline parse
 */

inline fun <reified T: JsonModel> JsonElement.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonObject.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> String.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonMap.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonPairIterable.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonPairSequence.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonPairArray.parse(vararg parameters: Any?): T {
    return parseAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonElement.parse(): T {
    return parseAs(T::class)
}

inline fun <reified T: JsonModel> JsonObject.parse(): T {
    return parseAs(T::class)
}

inline fun <reified T: JsonModel> String.parse(): T {
    return parseAs(T::class)
}

inline fun <reified T: JsonModel> JsonMap.parse(): T {
    return parseAs(T::class)
}

inline fun <reified T: JsonModel> JsonPairIterable.parse(): T {
    return parseAs(T::class)
}

inline fun <reified T: JsonModel> JsonPairSequence.parse(): T {
    return parseAs(T::class)
}

inline fun <reified T: JsonModel> JsonPairArray.parse(): T {
    return parseAs(T::class)
}

/*
 * inline parseOrNull
 */

inline fun <reified T: JsonModel> JsonElement?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonObject?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> String?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonMap?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonPairIterable?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonPairSequence?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonPairArray?.parseOrNull(vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonElement?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonObject?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

inline fun <reified T: JsonModel> String?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonMap?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonPairIterable?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonPairSequence?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonPairArray?.parseOrNull(): T? {
    return parseAsOrNull(T::class)
}

/*
 * parseList
 */

expect inline fun <T: JsonModel> JsonElement.parseListAs(model: KClass<T>, vararg parameters: Any?): List<T>

inline fun <T: JsonModel> JsonArray.parseListAs(model: KClass<T>, vararg parameters: Any?): List<T> {
    return (this as JsonElement).parseListAs(model, *parameters)
}

inline fun <T: JsonModel> String.parseListAs(model: KClass<T>, vararg parameters: Any?): List<T> {
    return toJsonElement().parseListAs(model, *parameters)
}

inline fun <T: JsonModel> JsonValueIterable.parseListAs(model: KClass<T>, vararg parameters: Any?): List<T> {
    return toJsonArray().parseListAs(model, *parameters)
}

inline fun <T: JsonModel> JsonValueSequence.parseListAs(model: KClass<T>, vararg parameters: Any?): List<T> {
    return toJsonArray().parseListAs(model, *parameters)
}

inline fun <T: JsonModel> JsonValueArray.parseListAs(model: KClass<T>, vararg parameters: Any?): List<T> {
    return toJsonArray().parseListAs(model, *parameters)
}

expect inline fun <T: JsonModel> JsonElement.parseListAs(model: KClass<T>): List<T>

inline fun <T: JsonModel> JsonArray.parseListAs(model: KClass<T>): List<T> {
    return (this as JsonElement).parseListAs(model)
}

inline fun <T: JsonModel> String.parseListAs(model: KClass<T>): List<T> {
    return toJsonElement().parseListAs(model)
}

inline fun <T: JsonModel> JsonValueIterable.parseListAs(model: KClass<T>): List<T> {
    return toJsonArray().parseListAs(model)
}

inline fun <T: JsonModel> JsonValueSequence.parseListAs(model: KClass<T>): List<T> {
    return toJsonArray().parseListAs(model)
}

inline fun <T: JsonModel> JsonValueArray.parseListAs(model: KClass<T>): List<T> {
    return toJsonArray().parseListAs(model)
}

/*
 * parseListOrNull
 */

inline fun <T: JsonModel> JsonElement?.parseListAsOrNull(model: KClass<T>, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonArray?.parseListAsOrNull(model: KClass<T>, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, *parameters)
    }
}

inline fun <T: JsonModel> String?.parseListAsOrNull(model: KClass<T>, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonValueIterable?.parseListAsOrNull(model: KClass<T>, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonValueSequence?.parseListAsOrNull(model: KClass<T>, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonValueArray?.parseListAsOrNull(model: KClass<T>, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, *parameters)
    }
}

inline fun <T: JsonModel> JsonElement?.parseListAsOrNull(model: KClass<T>): List<T>? {
    return runSafely {
        parseListAs(model)
    }
}

inline fun <T: JsonModel> JsonArray?.parseListAsOrNull(model: KClass<T>): List<T>? {
    return runSafely {
        parseListAs(model)
    }
}

inline fun <T: JsonModel> String?.parseListAsOrNull(model: KClass<T>): List<T>? {
    return runSafely {
        parseListAs(model)
    }
}

inline fun <T: JsonModel> JsonValueIterable?.parseListAsOrNull(model: KClass<T>): List<T>? {
    return runSafely {
        parseListAs(model)
    }
}

inline fun <T: JsonModel> JsonValueSequence?.parseListAsOrNull(model: KClass<T>): List<T>? {
    return runSafely {
        parseListAs(model)
    }
}

inline fun <T: JsonModel> JsonValueArray?.parseListAsOrNull(model: KClass<T>): List<T>? {
    return runSafely {
        parseListAs(model)
    }
}

/*
 * parseListOrEmpty
 */

inline fun <T: JsonModel> JsonElement?.parseListAsOrEmpty(model: KClass<T>, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, *parameters).orEmpty()
}

inline fun <T: JsonModel> JsonArray?.parseListAsOrEmpty(model: KClass<T>, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, *parameters).orEmpty()
}

inline fun <T: JsonModel> String?.parseListAsOrEmpty(model: KClass<T>, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, *parameters).orEmpty()
}

inline fun <T: JsonModel> JsonValueIterable?.parseListAsOrEmpty(model: KClass<T>, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, *parameters).orEmpty()
}

inline fun <T: JsonModel> JsonValueSequence?.parseListAsOrEmpty(model: KClass<T>, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, *parameters).orEmpty()
}

inline fun <T: JsonModel> JsonValueArray?.parseListAsOrEmpty(model: KClass<T>, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, *parameters).orEmpty()
}

inline fun <T: JsonModel> JsonElement?.parseListAsOrEmpty(model: KClass<T>): List<T> {
    return parseListAsOrNull(model).orEmpty()
}

inline fun <T: JsonModel> JsonArray?.parseListAsOrEmpty(model: KClass<T>): List<T> {
    return parseListAsOrNull(model).orEmpty()
}

inline fun <T: JsonModel> String?.parseListAsOrEmpty(model: KClass<T>): List<T> {
    return parseListAsOrNull(model).orEmpty()
}

inline fun <T: JsonModel> JsonValueIterable?.parseListAsOrEmpty(model: KClass<T>): List<T> {
    return parseListAsOrNull(model).orEmpty()
}

inline fun <T: JsonModel> JsonValueSequence?.parseListAsOrEmpty(model: KClass<T>): List<T> {
    return parseListAsOrNull(model).orEmpty()
}

inline fun <T: JsonModel> JsonValueArray?.parseListAsOrEmpty(model: KClass<T>): List<T> {
    return parseListAsOrNull(model).orEmpty()
}

/*
 * inline parseList
 */

inline fun <reified T: JsonModel> JsonElement.parseList(vararg parameters: Any?): List<T> {
    return parseListAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonArray.parseList(vararg parameters: Any?): List<T> {
    return parseListAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> String.parseList(vararg parameters: Any?): List<T> {
    return parseListAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueIterable.parseList(vararg parameters: Any?): List<T> {
    return parseListAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueSequence.parseList(vararg parameters: Any?): List<T> {
    return parseListAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueArray.parseList(vararg parameters: Any?): List<T> {
    return parseListAs(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonElement.parseList(): List<T> {
    return parseListAs(T::class)
}

inline fun <reified T: JsonModel> JsonArray.parseList(): List<T> {
    return parseListAs(T::class)
}

inline fun <reified T: JsonModel> String.parseList(): List<T> {
    return parseListAs(T::class)
}

inline fun <reified T: JsonModel> JsonValueIterable.parseList(): List<T> {
    return parseListAs(T::class)
}

inline fun <reified T: JsonModel> JsonValueSequence.parseList(): List<T> {
    return parseListAs(T::class)
}

inline fun <reified T: JsonModel> JsonValueArray.parseList(): List<T> {
    return parseListAs(T::class)
}

/*
 * inline parseListOrNull
 */

inline fun <reified T: JsonModel> JsonElement?.parseListOrNull(vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonArray?.parseListOrNull(vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> String?.parseListOrNull(vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseListOrNull(vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseListOrNull(vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseListOrNull(vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonElement?.parseListOrNull(): List<T>? {
    return parseListAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonArray?.parseListOrNull(): List<T>? {
    return parseListAsOrNull(T::class)
}

inline fun <reified T: JsonModel> String?.parseListOrNull(): List<T>? {
    return parseListAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseListOrNull(): List<T>? {
    return parseListAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseListOrNull(): List<T>? {
    return parseListAsOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseListOrNull(): List<T>? {
    return parseListAsOrNull(T::class)
}

/*
 * inline parseListOrEmpty
 */

inline fun <reified T: JsonModel> JsonElement?.parseListOrEmpty(vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonArray?.parseListOrEmpty(vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, *parameters)
}

inline fun <reified T: JsonModel> String?.parseListOrEmpty(vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseListOrEmpty(vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseListOrEmpty(vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseListOrEmpty(vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, *parameters)
}

inline fun <reified T: JsonModel> JsonElement?.parseListOrEmpty(): List<T> {
    return parseListAsOrEmpty(T::class)
}

inline fun <reified T: JsonModel> JsonArray?.parseListOrEmpty(): List<T> {
    return parseListAsOrEmpty(T::class)
}

inline fun <reified T: JsonModel> String?.parseListOrEmpty(): List<T> {
    return parseListAsOrEmpty(T::class)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseListOrEmpty(): List<T> {
    return parseListAsOrEmpty(T::class)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseListOrEmpty(): List<T> {
    return parseListAsOrEmpty(T::class)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseListOrEmpty(): List<T> {
    return parseListAsOrEmpty(T::class)
}
