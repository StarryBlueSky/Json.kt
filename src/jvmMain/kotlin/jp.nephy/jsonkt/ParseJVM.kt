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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

/*
 * parseObject
 */

inline fun <T: JsonModel> JsonElement.parseObject(model: KClass<T>): T {
    return try {
        model.primaryConstructor!!.call(jsonObject)
    } catch (e: Throwable) {
        throw InvalidJsonModelException(model)
    }
}

inline fun <T: JsonModel> JsonElement?.parseObjectOrNull(model: KClass<T>): T? {
    return runCatching {
        this?.parseObject(model)
    }.getOrNull()
}

/*
 * inline parseObject
 */

inline fun <reified T: JsonModel> JsonElement.parseObject(): T {
    return parseObject(T::class)
}

inline fun <reified T: JsonModel> JsonObject.parseObject(): T {
    return parseObject(T::class)
}

inline fun <reified T: JsonModel> String.parseObject(): T {
    return toJsonElement().parseObject(T::class)
}

inline fun <reified T: JsonModel> JsonMap.parseObject(): T {
    return toJsonObject().parseObject(T::class)
}

inline fun <reified T: JsonModel> JsonPairIterable.parseObject(): T {
    return toJsonObject().parseObject(T::class)
}

inline fun <reified T: JsonModel> JsonPairSequence.parseObject(): T {
    return toJsonObject().parseObject(T::class)
}

inline fun <reified T: JsonModel> JsonPairArray.parseObject(): T {
    return toJsonObject().parseObject(T::class)
}

/*
 * inline parseObjectOrNull
 */

inline fun <reified T: JsonModel> JsonElement?.parseObjectOrNull(): T? {
    return parseObjectOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonObject?.parseObjectOrNull(): T? {
    return parseObjectOrNull(T::class)
}

inline fun <reified T: JsonModel> String?.parseObjectOrNull(): T? {
    return toJsonElementOrNull().parseObjectOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonMap?.parseObjectOrNull(): T? {
    return toJsonObjectOrNull().parseObjectOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonPairIterable?.parseObjectOrNull(): T? {
    return toJsonObjectOrNull().parseObjectOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonPairSequence?.parseObjectOrNull(): T? {
    return toJsonObjectOrNull().parseObjectOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonPairArray?.parseObjectOrNull(): T? {
    return toJsonObjectOrNull().parseObjectOrNull(T::class)
}

/*
 * parseArray
 */

inline fun <T: JsonModel> JsonElement.parseArray(model: KClass<T>): List<T> {
    return try {
        jsonArray.map {
            it.parseObject(model)
        }
    } catch (e: Throwable) {
        throw InvalidJsonModelException(model)
    }
}

inline fun <T: JsonModel> JsonElement?.parseArrayOrNull(model: KClass<T>): List<T>? {
    return runCatching {
        this?.parseArray(model)
    }.getOrNull()
}

/*
 * inline parseArray
 */

inline fun <reified T: JsonModel> JsonElement.parseArray(): List<T> {
    return parseArray(T::class)
}

inline fun <reified T: JsonModel> JsonArray.parseArray(): List<T> {
    return parseArray(T::class)
}

inline fun <reified T: JsonModel> String.parseArray(): List<T> {
    return toJsonElement().parseArray(T::class)
}

inline fun <reified T: JsonModel> JsonValueIterable.parseArray(): List<T> {
    return toJsonArray().parseArray(T::class)
}

inline fun <reified T: JsonModel> JsonValueSequence.parseArray(): List<T> {
    return toJsonArray().parseArray(T::class)
}

inline fun <reified T: JsonModel> JsonValueArray.parseArray(): List<T> {
    return toJsonArray().parseArray(T::class)
}

/*
 * inline parseArrayOrNull
 */

inline fun <reified T: JsonModel> JsonElement?.parseArrayOrNull(): List<T>? {
    return parseArrayOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonArray?.parseArrayOrNull(): List<T>? {
    return parseArrayOrNull(T::class)
}

inline fun <reified T: JsonModel> String?.parseArrayOrNull(): List<T>? {
    return toJsonElementOrNull().parseArrayOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseArrayOrNull(): List<T>? {
    return toJsonArrayOrNull().parseArrayOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseArrayOrNull(): List<T>? {
    return toJsonArrayOrNull().parseArrayOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseArrayOrNull(): List<T>? {
    return toJsonArrayOrNull().parseArrayOrNull(T::class)
}
