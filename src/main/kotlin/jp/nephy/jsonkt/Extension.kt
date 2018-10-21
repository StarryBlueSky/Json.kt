@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel
import java.io.File
import java.nio.file.Path
import kotlin.reflect.KClass

internal typealias GsonBuilder = com.google.gson.GsonBuilder.() -> Unit
internal typealias JsonMap = Map<String, Any?>
internal typealias JsonPair = Pair<String, Any?>
internal typealias JsonMapEntry = Map.Entry<String, Any?>

/*
 * toJsonString
 */

inline fun JsonElement.toJsonString(noinline builder: GsonBuilder = {}): String {
    return gson(builder).toJson(toGsonObject())
}

inline fun ImmutableJsonObject.toJsonString(noinline builder: GsonBuilder = {}): String {
    return gson(builder).toJson(toGsonObject())
}

inline fun ImmutableJsonArray.toJsonString(noinline builder: GsonBuilder = {}): String {
    return gson(builder).toJson(toGsonObject())
}

inline fun JsonModel.toJsonString(noinline builder: GsonBuilder = {}): String {
    return json.toJsonString(builder)
}

inline fun JsonMap.toJsonString(noinline builder: GsonBuilder = {}): String {
    return gson(builder).toJson(map { it.key to it.value.toJsonElement().toGsonObject() }.toMap())
}

inline fun Iterable<JsonPair>.toJsonString(noinline builder: GsonBuilder = {}): String {
    return toMap().toJsonString(builder)
}

inline fun Sequence<JsonPair>.toJsonString(noinline builder: GsonBuilder = {}): String {
    return toMap().toJsonString(builder)
}

inline fun Array<JsonPair>.toJsonString(noinline builder: GsonBuilder = {}): String {
    return toMap().toJsonString(builder)
}

/*
 * toJsonObject
 */

inline fun String.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return gson(builder).fromJson(this, com.google.gson.JsonObject::class.java).toJsonKt()
}

inline fun JsonMap.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

inline fun Iterable<JsonPair>.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

inline fun Sequence<JsonPair>.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

inline fun Array<JsonPair>.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

inline fun File.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return reader().use {
        it.readText().toJsonObject(builder)
    }
}

inline fun Path.toJsonObject(noinline builder: GsonBuilder = {}): ImmutableJsonObject {
    return toFile().toJsonObject(builder)
}

/*
 * toJsonArray
 */

inline fun String.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return gson(builder).fromJson(this, com.google.gson.JsonArray::class.java).toJsonKt()
}

inline fun JsonMap.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

inline fun Iterable<JsonPair>.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

inline fun Sequence<JsonPair>.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

inline fun Array<JsonPair>.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

inline fun File.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return reader().use {
        it.readText().toJsonArray(builder)
    }
}

inline fun Path.toJsonArray(noinline builder: GsonBuilder = {}): ImmutableJsonArray {
    return toFile().toJsonArray(builder)
}

inline fun Iterable<ImmutableJsonObject>.toImmutableJsonArray(): ImmutableJsonArray {
    return immutableJsonArrayOf(*toList().toTypedArray())
}

inline fun Sequence<ImmutableJsonObject>.toImmutableJsonArray(): ImmutableJsonArray {
    return immutableJsonArrayOf(*toList().toTypedArray())
}

/*
 * parse
 */

inline fun <T: JsonModel> ImmutableJsonObject.parse(model: Class<T>): T {
    return model.getConstructor(ImmutableJsonObject::class.java).newInstance(this)
}

inline fun <T: JsonModel> ImmutableJsonObject.parse(model: KClass<T>): T {
    return parse(model.java)
}

inline fun <T: JsonModel> String.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

inline fun <T: JsonModel> String.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

inline fun <T: JsonModel> JsonMap.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

inline fun <T: JsonModel> JsonMap.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

inline fun <T: JsonModel> Iterable<JsonPair>.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

inline fun <T: JsonModel> Sequence<JsonPair>.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

inline fun <T: JsonModel> Iterable<JsonPair>.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

inline fun <T: JsonModel> Sequence<JsonPair>.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

inline fun <T: JsonModel> Array<JsonPair>.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

inline fun <T: JsonModel> Array<JsonPair>.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

inline fun <T: JsonModel> File.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return reader().use {
        it.readText().parse(model, builder)
    }
}

inline fun <T: JsonModel> File.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

inline fun <T: JsonModel> Path.parse(model: Class<T>, noinline builder: GsonBuilder = {}): T {
    return toFile().parse(model, builder)
}

inline fun <T: JsonModel> Path.parse(model: KClass<T>, noinline builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

/*
 * inline parse
 */

inline fun <reified T: JsonModel> ImmutableJsonObject.parse(): T {
    return parse(T::class.java)
}

inline fun <reified T: JsonModel> String.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> JsonMap.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Iterable<JsonPair>.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Sequence<JsonPair>.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Array<JsonPair>.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> File.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Path.parse(noinline builder: GsonBuilder = {}): T {
    return parse(T::class.java, builder)
}

/*
 * parseList
 */

inline fun <T: JsonModel> ImmutableJsonArray.parseList(model: Class<T>): List<T> {
    return map { it.immutableJsonObject.parse(model) }
}

inline fun <T: JsonModel> ImmutableJsonArray.parseList(model: KClass<T>): List<T> {
    return parseList(model.java)
}

inline fun <T: JsonModel> Iterable<ImmutableJsonObject>.parseList(model: Class<T>): List<T> {
    return map { it.parse(model) }
}

inline fun <T: JsonModel> Iterable<ImmutableJsonObject>.parseList(model: KClass<T>): List<T> {
    return parseList(model.java)
}

inline fun <T: JsonModel> Sequence<ImmutableJsonObject>.parseList(model: Class<T>): Sequence<T> {
    return map { it.parse(model) }
}

inline fun <T: JsonModel> Sequence<ImmutableJsonObject>.parseList(model: KClass<T>): Sequence<T> {
    return parseList(model.java)
}

inline fun <T: JsonModel> String.parseList(model: Class<T>, noinline builder: GsonBuilder = {}): List<T> {
    return toJsonArray(builder).parseList(model)
}

inline fun <T: JsonModel> String.parseList(model: KClass<T>, noinline builder: GsonBuilder = {}): List<T> {
    return parseList(model.java, builder)
}

inline fun <T: JsonModel> File.parseList(model: Class<T>, noinline builder: GsonBuilder = {}): List<T> {
    return reader().use {
        it.readText().parseList(model, builder)
    }
}

inline fun <T: JsonModel> File.parseList(model: KClass<T>, noinline builder: GsonBuilder = {}): List<T> {
    return parseList(model.java, builder)
}

inline fun <T: JsonModel> Path.parseList(model: Class<T>, noinline builder: GsonBuilder = {}): List<T> {
    return toFile().parseList(model, builder)
}

inline fun <T: JsonModel> Path.parseList(model: KClass<T>, noinline builder: GsonBuilder = {}): List<T> {
    return parseList(model.java, builder)
}

/*
 * inline parseList
 */

inline fun <reified T: JsonModel> ImmutableJsonArray.parseList(): List<T> {
    return parseList(T::class.java)
}

inline fun <reified T: JsonModel> Iterable<ImmutableJsonObject>.parseList(): List<T> {
    return parseList(T::class.java)
}

inline fun <reified T: JsonModel> Sequence<ImmutableJsonObject>.parseList(): Sequence<T> {
    return parseList(T::class.java)
}

inline fun <reified T: JsonModel> String.parseList(noinline builder: GsonBuilder = {}): List<T> {
    return parseList(T::class.java, builder)
}

inline fun <reified T: JsonModel> File.parseList(noinline builder: GsonBuilder = {}): List<T> {
    return parseList(T::class.java, builder)
}

inline fun <reified T: JsonModel> Path.parseList(noinline builder: GsonBuilder = {}): List<T> {
    return parseList(T::class.java, builder)
}
