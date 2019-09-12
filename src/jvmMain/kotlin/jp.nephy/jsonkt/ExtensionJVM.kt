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
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Path
import kotlin.reflect.KClass

/*
 * toJsonObject
 */

/**
 * @throws JsonCastException
 */
inline fun File.toJsonObject(charset: Charset = Charsets.UTF_8): JsonObject {
    return readText(charset).toJsonObject()
}

inline fun File?.toJsonObjectOrNull(charset: Charset = Charsets.UTF_8): JsonObject? {
    return runSafely {
        toJsonObject(charset)
    }
}

/**
 * @throws JsonCastException
 */
inline fun Path.toJsonObject(charset: Charset = Charsets.UTF_8): JsonObject {
    return toFile().toJsonObject(charset)
}

inline fun Path?.toJsonObjectOrNull(charset: Charset = Charsets.UTF_8): JsonObject? {
    return runSafely {
        toJsonObject(charset)
    }
}

/*
 * toJsonArray
 */

/**
 * @throws JsonCastException
 */
inline fun File.toJsonArray(charset: Charset = Charsets.UTF_8): JsonArray {
    return readText(charset).toJsonArray()
}

inline fun File?.toJsonArrayOrNull(charset: Charset = Charsets.UTF_8): JsonArray? {
    return runSafely {
        toJsonArray(charset)
    }
}

/**
 * @throws JsonCastException
 */
inline fun Path.toJsonArray(charset: Charset = Charsets.UTF_8): JsonArray {
    return toFile().toJsonArray(charset)
}

inline fun Path?.toJsonArrayOrNull(charset: Charset = Charsets.UTF_8): JsonArray? {
    return runSafely {
        toJsonArray(charset)
    }
}

/*
 *  parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parseAs(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T {
    return toJsonObject(charset).parseAs(model, *parameters)
}

inline fun <T: JsonModel> File?.parseAsOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, charset, *parameters)
    }
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parseAs(model: KClass<T>, charset: Charset = Charsets.UTF_8): T {
    return toJsonObject(charset).parseAs(model)
}

inline fun <T: JsonModel> File?.parseOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8): T? {
    return runSafely {
        parseAs(model, charset)
    }
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parseAs(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T {
    return toJsonObject(charset).parseAs(model, *parameters)
}

inline fun <T: JsonModel> Path?.parseAsOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T? {
    return runSafely {
        parseAs(model, charset, *parameters)
    }
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parseAs(model: KClass<T>, charset: Charset = Charsets.UTF_8): T {
    return toJsonObject(charset).parseAs(model)
}

inline fun <T: JsonModel> Path?.parseAsOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8): T? {
    return runSafely {
        parseAs(model, charset)
    }
}

/*
 *  inline parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parse(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T {
    return parseAs(T::class, charset, *parameters)
}

inline fun <reified T: JsonModel> File?.parseOrNull(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, charset, *parameters)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parse(charset: Charset = Charsets.UTF_8): T {
    return parseAs(T::class, charset)
}

inline fun <reified T: JsonModel> File?.parseOrNull(charset: Charset = Charsets.UTF_8): T? {
    return parseAsOrNull(T::class, charset)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parse(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T {
    return parseAs(T::class, charset, *parameters)
}

inline fun <reified T: JsonModel> Path?.parseOrNull(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): T? {
    return parseAsOrNull(T::class, charset, *parameters)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parse(charset: Charset = Charsets.UTF_8): T {
    return parseAs(T::class, charset)
}

inline fun <reified T: JsonModel> Path?.parseOrNull(charset: Charset = Charsets.UTF_8): T? {
    return parseAsOrNull(T::class, charset)
}

/*
 * parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parseListAs(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return toJsonArray(charset).parseListAs(model, *parameters)
}

inline fun <T: JsonModel> File?.parseListAsOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, charset, *parameters)
    }
}

inline fun <T: JsonModel> File?.parseListAsOrEmpty(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, charset, *parameters).orEmpty()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parseListAs(model: KClass<T>, charset: Charset = Charsets.UTF_8): List<T> {
    return toJsonArray(charset).parseListAs(model)
}

inline fun <T: JsonModel> File?.parseListAsOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8): List<T>? {
    return runSafely {
        parseListAs(model, charset)
    }
}

inline fun <T: JsonModel> File?.parseListAsOrEmpty(model: KClass<T>, charset: Charset = Charsets.UTF_8): List<T> {
    return parseListAsOrNull(model, charset).orEmpty()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parseListAs(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return toJsonArray(charset).parseListAs(model, *parameters)
}

inline fun <T: JsonModel> Path?.parseListOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T>? {
    return runSafely {
        parseListAs(model, charset, *parameters)
    }
}

inline fun <T: JsonModel> Path?.parseListAsOrEmpty(model: KClass<T>, charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return parseListAsOrNull(model, charset, *parameters).orEmpty()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parseListAs(model: KClass<T>, charset: Charset = Charsets.UTF_8): List<T> {
    return toJsonArray(charset).parseListAs(model)
}

inline fun <T: JsonModel> Path?.parseListAsOrNull(model: KClass<T>, charset: Charset = Charsets.UTF_8): List<T>? {
    return runSafely {
        parseListAs(model, charset)
    }
}

inline fun <T: JsonModel> Path?.parseListAsOrEmpty(model: KClass<T>, charset: Charset = Charsets.UTF_8): List<T> {
    return parseListAsOrNull(model, charset).orEmpty()
}

/*
 *  inline parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parseList(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return parseListAs(T::class, charset, *parameters)
}

inline fun <reified T: JsonModel> File?.parseListOrNull(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, charset, *parameters)
}

inline fun <reified T: JsonModel> File?.parseListOrEmpty(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, charset, *parameters)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parseList(charset: Charset = Charsets.UTF_8): List<T> {
    return parseListAs(T::class, charset)
}

inline fun <reified T: JsonModel> File?.parseListOrNull(charset: Charset = Charsets.UTF_8): List<T>? {
    return parseListAsOrNull(T::class, charset)
}

inline fun <reified T: JsonModel> File?.parseListOrEmpty(charset: Charset = Charsets.UTF_8): List<T> {
    return parseListAsOrEmpty(T::class, charset)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parseList(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return parseListAs(T::class, charset, *parameters)
}

inline fun <reified T: JsonModel> Path?.parseListOrNull(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T>? {
    return parseListAsOrNull(T::class, charset, *parameters)
}

inline fun <reified T: JsonModel> Path?.parseListOrEmpty(charset: Charset = Charsets.UTF_8, vararg parameters: Any?): List<T> {
    return parseListAsOrEmpty(T::class, charset, *parameters)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parseList(charset: Charset = Charsets.UTF_8): List<T> {
    return parseListAs(T::class, charset)
}

inline fun <reified T: JsonModel> Path?.parseListOrNull(charset: Charset = Charsets.UTF_8): List<T>? {
    return parseListAsOrNull(T::class, charset)
}

inline fun <reified T: JsonModel> Path?.parseListOrEmpty(charset: Charset = Charsets.UTF_8): List<T> {
    return parseListAsOrEmpty(T::class, charset)
}
