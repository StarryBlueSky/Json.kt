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

import jp.nephy.jsonkt.delegation.InvalidJsonModelException
import jp.nephy.jsonkt.delegation.JsonModel
import kotlin.reflect.KClass

/*
 * parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonObject.parse(model: KClass<T>, vararg args: Any?): T {
    return try {
        model.createModelInstance(this, *args)
    } catch (e: Throwable) {
        throw InvalidJsonModelException(model)
    }
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonElement.parse(model: KClass<T>, vararg args: Any?): T {
    return jsonObjectOrNull?.parse(model, *args) ?: throw InvalidJsonModelException(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> String.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonMap.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonPairIterable.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonPairSequence.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonPairArray.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/*
 * parseOrNull
 */

inline fun <T: JsonModel> JsonObject?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

inline fun <T: JsonModel> JsonElement?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

inline fun <T: JsonModel> String?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

inline fun <T: JsonModel> JsonMap?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

inline fun <T: JsonModel> JsonPairIterable?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

inline fun <T: JsonModel> JsonPairSequence?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

inline fun <T: JsonModel> JsonPairArray?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runSafely {
        parse(model, *args)
    }
}

/*
 * inline parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonObject.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonElement.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> String.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonMap.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonPairIterable.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonPairSequence.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonPairArray.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/*
 * inline parseOrNull
 */

inline fun <reified T: JsonModel> JsonObject?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonElement?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> String?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonMap?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonPairIterable?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonPairSequence?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonPairArray?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

/*
 * parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonArray.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return try {
        map {
            it.parse(model, *args)
        }
    } catch (e: Throwable) {
        throw InvalidJsonModelException(model)
    }
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonElement.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return jsonArrayOrNull.parseListOrNull(model, *args) ?: throw InvalidJsonModelException(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> String.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonValueIterable.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonValueSequence.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonValueArray.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/*
 * parseListOrNull
 */

inline fun <T: JsonModel> JsonArray?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runSafely {
        parseList(model, *args)
    }
}

inline fun <T: JsonModel> JsonElement?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runSafely {
        parseList(model, *args)
    }
}

inline fun <T: JsonModel> String?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runSafely {
        parseList(model, *args)
    }
}

inline fun <T: JsonModel> JsonValueIterable?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runSafely {
        parseList(model, *args)
    }
}

inline fun <T: JsonModel> JsonValueSequence?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runSafely {
        parseList(model, *args)
    }
}

inline fun <T: JsonModel> JsonValueArray?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runSafely {
        parseList(model, *args)
    }
}

/*
 * parseListOrEmpty
 */

inline fun <T: JsonModel> JsonArray?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

inline fun <T: JsonModel> JsonElement?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

inline fun <T: JsonModel> String?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

inline fun <T: JsonModel> JsonValueIterable?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

inline fun <T: JsonModel> JsonValueSequence?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

inline fun <T: JsonModel> JsonValueArray?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

/*
 * inline parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonArray.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonElement.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> String.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonValueIterable.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonValueSequence.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonValueArray.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/*
 * inline parseListOrNull
 */

inline fun <reified T: JsonModel> JsonArray?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonElement?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> String?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

/*
 * inline parseListOrEmpty
 */

inline fun <reified T: JsonModel> JsonArray?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> JsonElement?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> String?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> JsonValueIterable?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> JsonValueSequence?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> JsonValueArray?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}
