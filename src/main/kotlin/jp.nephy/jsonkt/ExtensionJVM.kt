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
inline fun <T: JsonModel> File.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

inline fun <T: JsonModel> File?.parseOrNull(model: KClass<T>): T? {
    return runCatching {
        this?.parse(model)
    }.getOrNull()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

inline fun <T: JsonModel> Path?.parseOrNull(model: KClass<T>): T? {
    return runCatching {
        this?.parse(model)
    }.getOrNull()
}

/*
 *  inline parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parse(): T {
    return parse(T::class)
}

inline fun <reified T: JsonModel> File?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parse(): T {
    return parse(T::class)
}

inline fun <reified T: JsonModel> Path?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

/*
 * parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> File.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

inline fun <T: JsonModel> File?.parseListOrNull(model: KClass<T>): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model)
}

inline fun <T: JsonModel> File?.parseListOrEmpty(model: KClass<T>): List<T> {
    return parseListOrNull(model).orEmpty()
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Path.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

inline fun <T: JsonModel> Path?.parseListOrNull(model: KClass<T>): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model)
}

inline fun <T: JsonModel> Path?.parseListOrEmpty(model: KClass<T>): List<T> {
    return parseListOrNull(model).orEmpty()
}

/*
 *  inline parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> File.parseList(): List<T> {
    return parseList(T::class)
}

inline fun <reified T: JsonModel> File?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> File?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Path.parseList(): List<T> {
    return parseList(T::class)
}

inline fun <reified T: JsonModel> Path?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> Path?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}
