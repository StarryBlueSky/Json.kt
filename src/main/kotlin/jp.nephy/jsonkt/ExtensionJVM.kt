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
import java.io.File
import java.nio.file.Path
import kotlin.reflect.KClass

/*
 * toJsonObject
 */

/**
 * @throws JsonCastException
 */
inline fun File.toJsonObject(): JsonObject {
    return reader().use {
        it.readText().toJsonObject()
    }
}

inline fun File?.toJsonObjectOrNull(): JsonObject? {
    return runCatching {
        this?.toJsonObject()
    }.getOrNull()
}

/**
 * @throws JsonCastException
 */
inline fun Path.toJsonObject(): JsonObject {
    return toFile().toJsonObject()
}

inline fun Path?.toJsonObjectOrNull(): JsonObject? {
    return runCatching {
        this?.toJsonObject()
    }.getOrNull()
}

/*
 * toJsonArray
 */

/**
 * @throws JsonCastException
 */
inline fun File.toJsonArray(): JsonArray {
    return reader().use {
        it.readText().toJsonArray()
    }
}

inline fun File?.toJsonArrayOrNull(): JsonArray? {
    return runCatching {
        this?.toJsonArray()
    }.getOrNull()
}

/**
 * @throws JsonCastException
 */
inline fun Path.toJsonArray(): JsonArray {
    return toFile().toJsonArray()
}

inline fun Path?.toJsonArrayOrNull(): JsonArray? {
    return runCatching { 
        this?.toJsonArray()
    }.getOrNull()
}

/*
 *  parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

inline fun <T: JsonModel> File?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runCatching {
        this?.parse(model, *args)
    }.getOrNull()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

inline fun <T: JsonModel> Path?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runCatching {
        this?.parse(model, *args)
    }.getOrNull()
}

/*
 *  inline parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

inline fun <reified T: JsonModel> File?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

inline fun <reified T: JsonModel> Path?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

/*
 * parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

inline fun <T: JsonModel> File?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model, *args)
}

inline fun <T: JsonModel> File?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

inline fun <T: JsonModel> Path?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model, *args)
}

inline fun <T: JsonModel> Path?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args).orEmpty()
}

/*
 *  inline parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

inline fun <reified T: JsonModel> File?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> File?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

inline fun <reified T: JsonModel> Path?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> Path?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}
