@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel
import kotlinx.serialization.json.*
import java.io.File
import java.nio.file.Path
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

internal typealias JsonKey = String
internal typealias JsonValue = Any?

internal typealias JsonMap = Map<JsonKey, JsonValue>
internal typealias JsonPair = Pair<JsonKey, JsonValue>
internal typealias JsonIterable = Iterable<JsonValue>

/*
 * toJsonString
 */

inline fun JsonObject.toJsonString(): String {
    // TODO
    return toString()
}

inline fun JsonArray.toJsonString(): String {
    // TODO
    return toString()
}

inline fun JsonModel.toJsonString(): String {
    return json.toJsonString()
}

@Throws(JsonCastException::class)
inline fun JsonMap.toJsonString(): String {
    return toJsonObject().toJsonString()
}

@Throws(JsonCastException::class)
inline fun Iterable<JsonPair>.toJsonString(): String {
    return toMap().toJsonString()
}

@Throws(JsonCastException::class)
inline fun Sequence<JsonPair>.toJsonString(): String {
    return toMap().toJsonString()
}

@Throws(JsonCastException::class)
inline fun Array<out JsonPair>.toJsonString(): String {
    return toMap().toJsonString()
}

/*
 * toJsonElement
 */

@Throws(JsonCastException::class)
inline fun String.toJsonElement(): JsonElement {
    return try {
        JsonTreeParser(this).readFully()
    } catch (e: Throwable) {
        throw JsonCastException(this, JsonElement::class.qualifiedName.orEmpty())
    }
}

@Suppress("UNCHECKED_CAST")
@Throws(JsonConversionException::class)
fun <T: Any> T?.asJsonElement(): JsonElement {
    return when (this) {
        null -> JsonNull
        is JsonElement -> this

        /* Collections */
        is Map<*, *> -> JsonObject(this.toList().map { it.first.toString() to it.second.asJsonElement() }.toMap())
        is Iterable<*> -> JsonArray(this.toList().map { it.asJsonElement() })
        is Sequence<*> -> JsonArray(this.toList().map { it.asJsonElement() })
        is Array<*> -> JsonArray(this.toList().map { it.asJsonElement() })

        /* Primitives */
        is Boolean -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Char -> JsonPrimitive(this.toString())
        is String -> JsonPrimitive(this)

        else -> (JsonKt.serializers[this::class] as? JsonKt.Serializer<T>)?.encode(this)?.toJsonElement() ?: throw JsonConversionException(this::class.qualifiedName.orEmpty())
    }
}

/*
 * toJsonPrimitive
 */

@Throws(JsonCastException::class)
inline fun String.toJsonPrimitive(): JsonPrimitive {
    return toJsonElement() as? JsonPrimitive ?: throw JsonCastException(this, JsonPrimitive::class.qualifiedName.orEmpty())
}

/*
 * toJsonLiteral
 */

@Throws(JsonCastException::class)
inline fun String.toJsonLiteral(): JsonLiteral {
    return toJsonElement() as? JsonLiteral ?: throw JsonCastException(this, JsonLiteral::class.qualifiedName.orEmpty())
}

/*
 * toJsonObject
 */

@Throws(JsonCastException::class)
inline fun String.toJsonObject(): JsonObject {
    return toJsonElement() as? JsonObject ?: throw JsonCastException(this, JsonObject::class.qualifiedName.orEmpty())
}

@Throws(JsonCastException::class)
inline fun JsonMap.toJsonObject(): JsonObject {
    return JsonObject(map { it.key to it.value.asJsonElement() }.toMap())
}

@Throws(JsonCastException::class)
inline fun Iterable<JsonPair>.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

@Throws(JsonCastException::class)
inline fun Sequence<JsonPair>.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

@Throws(JsonCastException::class)
inline fun Array<out JsonPair>.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

@Throws(JsonCastException::class)
inline fun File.toJsonObject(): JsonObject {
    return reader().use {
        it.readText().toJsonObject()
    }
}

@Throws(JsonCastException::class)
inline fun Path.toJsonObject(): JsonObject {
    return toFile().toJsonObject()
}

/*
 * toJsonArray
 */

@Throws(JsonCastException::class)
inline fun String.toJsonArray(): JsonArray {
    return toJsonElement() as? JsonArray ?: throw JsonCastException(this, JsonArray::class.qualifiedName.orEmpty())
}

@Throws(JsonCastException::class)
inline fun JsonIterable.toJsonArray(): JsonArray {
    return JsonArray(map { it.asJsonElement() })
}

@Throws(JsonCastException::class)
inline fun Sequence<JsonValue>.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

@Throws(JsonCastException::class)
inline fun Array<out JsonValue>.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

@Throws(JsonCastException::class)
inline fun File.toJsonArray(): JsonArray {
    return reader().use {
        it.readText().toJsonArray()
    }
}

@Throws(JsonCastException::class)
inline fun Path.toJsonArray(): JsonArray {
    return toFile().toJsonArray()
}

/*
 * parse
 */

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> JsonObject.parse(model: KClass<T>): T {
    return runCatching {
        model.primaryConstructor?.call(this)
    }.getOrNull() ?: throw InvalidJsonModelException(model.qualifiedName.orEmpty())
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> String.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> JsonMap.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Iterable<JsonPair>.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Sequence<JsonPair>.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Array<out JsonPair>.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> File.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Path.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

/*
 * inline parse
 */

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> JsonObject.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> String.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> JsonMap.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Iterable<JsonPair>.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Sequence<JsonPair>.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Array<out JsonPair>.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> File.parse(): T {
    return parse(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Path.parse(): T {
    return parse(T::class)
}

/*
 * parseList
 */

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> JsonArray.parseList(model: KClass<T>): List<T> {
    return map {
        runCatching {
            it.jsonObject.parse(model)
        }.getOrNull() ?: throw InvalidJsonModelException(model.qualifiedName.orEmpty())
    }
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> String.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> JsonIterable.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Sequence<JsonValue>.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Array<out JsonValue>.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> File.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

@Throws(InvalidJsonModelException::class)
inline fun <T: JsonModel> Path.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

/*
 * inline parseList
 */

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> JsonArray.parseList(): List<T> {
    return parseList(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> String.parseList(): List<T> {
    return parseList(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> JsonIterable.parseList(): List<T> {
    return parseList(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Sequence<JsonValue>.parseList(): List<T> {
    return parseList(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Array<out JsonValue>.parseList(): List<T> {
    return parseList(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> File.parseList(): List<T> {
    return parseList(T::class)
}

@Throws(InvalidJsonModelException::class)
inline fun <reified T: JsonModel> Path.parseList(): List<T> {
    return parseList(T::class)
}
