@file:Suppress("UNUSED")
package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegate.JsonModel
import java.io.File
import java.nio.file.Path
import kotlin.reflect.KClass

internal typealias GsonBuilder = com.google.gson.GsonBuilder.() -> Unit
private typealias JsonMap = Map<String, Any?>
private typealias JsonPair = Pair<String, Any?>

/*
 * toJsonString
 */

fun JsonElement.toJsonString(builder: GsonBuilder = {}): String {
    return gson(builder).toJson(this)
}

fun JsonModel.toJsonString(builder: GsonBuilder = {}): String {
    return json.toJsonString(builder)
}

fun JsonMap.toJsonString(builder: GsonBuilder = {}): String {
    return gson(builder).toJson(this)
}

fun Iterable<JsonPair>.toJsonString(builder: GsonBuilder = {}): String {
    return toMap().toJsonString(builder)
}

fun Array<JsonPair>.toJsonString(builder: GsonBuilder = {}): String {
    return toMap().toJsonString(builder)
}

/*
 * toJsonObject
 */

fun String.toJsonObject(builder: GsonBuilder = {}): ImmutableJsonObject {
    return ImmutableJsonObject(gson(builder).fromJson(this, com.google.gson.JsonObject::class.java))
}

fun JsonMap.toJsonObject(builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

fun Iterable<JsonPair>.toJsonObject(builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

fun Array<JsonPair>.toJsonObject(builder: GsonBuilder = {}): ImmutableJsonObject {
    return toJsonString(builder).toJsonObject(builder)
}

fun File.toJsonObject(builder: GsonBuilder = {}): ImmutableJsonObject {
    return reader().use {
        it.readText().toJsonObject(builder)
    }
}

fun Path.toJsonObject(builder: GsonBuilder = {}): ImmutableJsonObject {
    return toFile().toJsonObject(builder)
}

/*
 * toJsonArray
 */

fun String.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
    return ImmutableJsonArray(gson(builder).fromJson(this, com.google.gson.JsonArray::class.java))
}

fun JsonMap.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

fun Iterable<JsonPair>.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

fun Array<JsonPair>.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
    return toJsonString(builder).toJsonArray(builder)
}

fun File.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
    return reader().use {
        it.readText().toJsonArray(builder)
    }
}

fun Path.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
    return toFile().toJsonArray(builder)
}

//fun Iterable<JsonObject>.toJsonArray(builder: GsonBuilder = {}): ImmutableJsonArray {
//    TODO()
//}

// TODO: Any

/*
 * parse
 */

fun <T: JsonModel> JsonObject.parse(model: Class<T>): T {
    return model.getConstructor(ImmutableJsonObject::class.java).newInstance(this)
}

fun <T: JsonModel> JsonObject.parse(model: KClass<T>): T {
    return parse(model.java)
}

fun <T: JsonModel> String.parse(model: Class<T>, builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

fun <T: JsonModel> String.parse(model: KClass<T>, builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

fun <T: JsonModel> JsonMap.parse(model: Class<T>, builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

fun <T: JsonModel> JsonMap.parse(model: KClass<T>, builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

fun <T: JsonModel> Iterable<JsonPair>.parse(model: Class<T>, builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

fun <T: JsonModel> Iterable<JsonPair>.parse(model: KClass<T>, builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

fun <T: JsonModel> Array<JsonPair>.parse(model: Class<T>, builder: GsonBuilder = {}): T {
    return toJsonObject(builder).parse(model)
}

fun <T: JsonModel> Array<JsonPair>.parse(model: KClass<T>, builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

fun <T: JsonModel> File.parse(model: Class<T>, builder: GsonBuilder = {}): T {
    return reader().use {
        it.readText().parse(model, builder)
    }
}

fun <T: JsonModel> File.parse(model: KClass<T>, builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

fun <T: JsonModel> Path.parse(model: Class<T>, builder: GsonBuilder = {}): T {
    return toFile().parse(model, builder)
}

fun <T: JsonModel> Path.parse(model: KClass<T>, builder: GsonBuilder = {}): T {
    return parse(model.java, builder)
}

/*
 * inline parse
 */

inline fun <reified T: JsonModel> JsonObject.parse(): T {
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

fun <T: JsonModel> JsonArray.parseList(model: Class<T>): List<T> {
    return map { it.immutableJsonObject.parse(model) }
}

fun <T: JsonModel> JsonArray.parseList(model: KClass<T>): List<T> {
    return parseList(model.java)
}

fun <T: JsonModel> String.parseList(model: Class<T>, builder: GsonBuilder = {}): List<T> {
    return toJsonArray(builder).parseList(model)
}

fun <T: JsonModel> String.parseList(model: KClass<T>, builder: GsonBuilder = {}): List<T> {
    return parseList(model.java, builder)
}

fun <T: JsonModel> File.parseList(model: Class<T>, builder: GsonBuilder = {}): List<T> {
    return reader().use {
        it.readText().parseList(model, builder)
    }
}

fun <T: JsonModel> File.parseList(model: KClass<T>, builder: GsonBuilder = {}): List<T> {
    return parseList(model.java, builder)
}

fun <T: JsonModel> Path.parseList(model: Class<T>, builder: GsonBuilder = {}): List<T> {
    return toFile().parseList(model, builder)
}

fun <T: JsonModel> Path.parseList(model: KClass<T>, builder: GsonBuilder = {}): List<T> {
    return parseList(model.java, builder)
}

/*
 * inline parseList
 */

inline fun <reified T: JsonModel> JsonArray.parseList(): List<T> {
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
