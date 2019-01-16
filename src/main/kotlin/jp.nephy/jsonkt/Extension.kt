@file:Suppress("UNUSED", "NOTHING_TO_INLINE", "UNCHECKED_CAST")

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.InvalidJsonModelException
import jp.nephy.jsonkt.delegation.JsonModel
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonPrimitive
import kotlin.reflect.KClass

internal typealias JsonKey = String
internal typealias JsonValue = Any?

internal typealias JsonMap = Map<JsonKey, JsonValue>
internal typealias JsonPair = Pair<JsonKey, JsonValue>
internal typealias JsonIterable = Iterable<JsonValue>
internal typealias JsonSerializer<T> = SerializationStrategy<T>

/*
    toJsonString
 */

inline fun <T: JsonElement> T.toJsonString(serializer: JsonSerializer<T>? = null): String {
    return if (serializer != null) {
        Json.stringify(serializer, this)
    } else {
        toString()
    }
}

inline fun JsonModel.toJsonString(serializer: JsonSerializer<JsonObject>? = null): String {
    return json.toJsonString(serializer)
}

/**
 * @throws JsonCastException
 */
inline fun Iterable<JsonPair>.toJsonString(serializer: JsonSerializer<JsonArray>? = null): String {
    return toJsonArray().toJsonString(serializer)
}

/**
 * @throws JsonCastException
 */
inline fun Sequence<JsonPair>.toJsonString(serializer: JsonSerializer<JsonArray>? = null): String {
    return toJsonArray().toJsonString(serializer)
}

/**
 * @throws JsonCastException
 */
inline fun Array<out JsonPair>.toJsonString(serializer: JsonSerializer<JsonArray>? = null): String {
    return toJsonArray().toJsonString(serializer)
}
/*
 * toJsonElement
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonElement(): JsonElement {
    return toJsonElementOrNull() ?: throw JsonCastException(this, JsonElement::class)
}

inline fun String?.toJsonElementOrNull(): JsonElement? {
    return runCatching {
        this?.let { Json.plain.parseJson(it) }
    }.getOrNull()
}

/*
 * toJsonPrimitive
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonPrimitive(): JsonPrimitive {
    return toJsonPrimitiveOrNull() ?: throw JsonCastException(this, JsonPrimitive::class)
}

inline fun String?.toJsonPrimitiveOrNull(): JsonPrimitive? {
    return toJsonElementOrNull() as? JsonPrimitive
}

/*
 * toJsonLiteral
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonLiteral(): JsonLiteral {
    return toJsonLiteralOrNull() ?: throw JsonCastException(this, JsonLiteral::class)
}

inline fun String?.toJsonLiteralOrNull(): JsonLiteral? {
    return toJsonElementOrNull() as? JsonLiteral
}

/*
 * toJsonObject
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonObject(): JsonObject {
    return toJsonObjectOrNull() ?: throw JsonCastException(this, JsonObject::class)
}

/**
 * @throws JsonCastException
 */
inline fun JsonMap.toJsonObject(): JsonObject {
    return toJsonObjectOrNull() ?: throw JsonCastException(this, JsonObject::class)
}

/**
 * @throws JsonCastException
 */
inline fun Iterable<JsonPair>.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

/**
 * @throws JsonCastException
 */
inline fun Sequence<JsonPair>.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

/**
 * @throws JsonCastException
 */
inline fun Array<out JsonPair>.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

/*
 * toJsonObjectOrNull
 */

inline fun String?.toJsonObjectOrNull(): JsonObject? {
    return toJsonElementOrNull() as? JsonObject
}

inline fun JsonMap?.toJsonObjectOrNull(): JsonObject? {
    return runCatching {
        this?.let {  map ->
            JsonObject(map.map { it.key to it.value.asJsonElement() }.toMap())
        }
    }.getOrNull()
}

inline fun Iterable<JsonPair>?.toJsonObjectOrNull(): JsonObject? {
    return this?.toMap().toJsonObjectOrNull()
}

inline fun Sequence<JsonPair>?.toJsonObjectOrNull(): JsonObject? {
    return this?.toMap().toJsonObjectOrNull()
}

inline fun Array<out JsonPair>?.toJsonObjectOrNull(): JsonObject? {
    return this?.toMap().toJsonObjectOrNull()
}

/*
 * toJsonArray
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonArray(): JsonArray {
    return toJsonArrayOrNull() ?: throw JsonCastException(this, JsonArray::class)
}

/**
 * @throws JsonCastException
 */
inline fun JsonIterable.toJsonArray(): JsonArray {
    return toJsonArrayOrNull() ?: throw JsonCastException(this, JsonArray::class)
}

/**
 * @throws JsonCastException
 */
inline fun Sequence<JsonValue>.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

/**
 * @throws JsonCastException
 */
inline fun Array<out JsonValue>.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

/*
 * toJsonArrayOrNull
 */

inline fun String?.toJsonArrayOrNull(): JsonArray? {
    return toJsonElementOrNull() as? JsonArray
}

inline fun JsonIterable?.toJsonArrayOrNull(): JsonArray? {
    return runCatching {
        this?.let { iter ->
            JsonArray(iter.map { it.asJsonElement() })
        }
    }.getOrNull()
}

inline fun Sequence<JsonValue>?.toJsonArrayOrNull(): JsonArray? {
    return this?.toList().toJsonArrayOrNull()
}

inline fun Array<out JsonValue>?.toJsonArrayOrNull(): JsonArray? {
    return this?.toList().toJsonArrayOrNull()
}

/*
 * parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonObject.parse(model: KClass<T>): T {
    return parseOrNull(model) ?: throw InvalidJsonModelException(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonElement.parse(model: KClass<T>): T {
    return jsonObjectOrNull?.parse(model) ?: throw InvalidJsonModelException(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> String.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonMap.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Iterable<JsonPair>.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Sequence<JsonPair>.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Array<out JsonPair>.parse(model: KClass<T>): T {
    return toJsonObject().parse(model)
}

/*
 * parseOrNull
 */

inline fun <T: JsonModel> JsonObject?.parseOrNull(model: KClass<T>): T? {
    return runCatching {
        this?.let { model.newModelInstance(it) }
    }.getOrNull()
}

inline fun <T: JsonModel> JsonElement?.parseOrNull(model: KClass<T>): T? {
    return this?.jsonObjectOrNull.parseOrNull(model)
}

inline fun <T: JsonModel> String?.parseOrNull(model: KClass<T>): T? {
    return toJsonObjectOrNull().parseOrNull(model)
}

inline fun <T: JsonModel> JsonMap?.parseOrNull(model: KClass<T>): T? {
    return toJsonObjectOrNull().parseOrNull(model)
}

inline fun <T: JsonModel> Iterable<JsonPair>?.parseOrNull(model: KClass<T>): T? {
    return toJsonObjectOrNull().parseOrNull(model)
}

inline fun <T: JsonModel> Sequence<JsonPair>?.parseOrNull(model: KClass<T>): T? {
    return toJsonObjectOrNull().parseOrNull(model)
}

inline fun <T: JsonModel> Array<out JsonPair>?.parseOrNull(model: KClass<T>): T? {
    return toJsonObjectOrNull().parseOrNull(model)
}

/*
 * inline parse
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonObject.parse(): T {
    return parse(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonElement.parse(): T {
    return parse(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> String.parse(): T {
    return parse(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonMap.parse(): T {
    return parse(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Iterable<JsonPair>.parse(): T {
    return parse(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Sequence<JsonPair>.parse(): T {
    return parse(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Array<out JsonPair>.parse(): T {
    return parse(T::class)
}

/*
 * inline parseOrNull
 */

inline fun <reified T: JsonModel> JsonObject?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonElement?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

inline fun <reified T: JsonModel> String?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonMap?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

inline fun <reified T: JsonModel> Iterable<JsonPair>?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

inline fun <reified T: JsonModel> Sequence<JsonPair>?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

inline fun <reified T: JsonModel> Array<out JsonPair>?.parseOrNull(): T? {
    return parseOrNull(T::class)
}

/*
 * parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonArray.parseList(model: KClass<T>): List<T> {
    return parseListOrNull(model) ?: throw InvalidJsonModelException(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonElement.parseList(model: KClass<T>): List<T> {
    return jsonArrayOrNull.parseListOrNull(model) ?: throw InvalidJsonModelException(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> String.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonIterable.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Sequence<JsonValue>.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Array<out JsonValue>.parseList(model: KClass<T>): List<T> {
    return toJsonArray().parseList(model)
}

/*
 * parseListOrNull
 */

inline fun <T: JsonModel> JsonArray?.parseListOrNull(model: KClass<T>): List<T>? {
    return runCatching { 
        this?.mapNotNull { element -> 
            element.jsonObjectOrNull.parseOrNull(model).also { 
                if (it == null) {
                    throw InvalidJsonModelException(model)
                }
            }
        }
    }.getOrNull()
}

inline fun <T: JsonModel> JsonElement?.parseListOrNull(model: KClass<T>): List<T>? {
    return this?.jsonArrayOrNull.parseListOrNull(model)
}

inline fun <T: JsonModel> String?.parseListOrNull(model: KClass<T>): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model)
}

inline fun <T: JsonModel> JsonIterable?.parseListOrNull(model: KClass<T>): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model)
}

inline fun <T: JsonModel> Sequence<JsonValue>?.parseListOrNull(model: KClass<T>): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model)
}

inline fun <T: JsonModel> Array<out JsonValue>?.parseListOrNull(model: KClass<T>): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model)
}

/*
 * parseListOrEmpty
 */

inline fun <T: JsonModel> JsonArray?.parseListOrEmpty(model: KClass<T>): List<T> {
    return runCatching { 
        this?.mapNotNull { element -> 
            element.jsonObjectOrNull.parseOrNull(model)
        }
    }.getOrNull().orEmpty()
}

inline fun <T: JsonModel> JsonElement?.parseListOrEmpty(model: KClass<T>): List<T> {
    return this?.jsonArrayOrNull.parseListOrEmpty(model)
}

inline fun <T: JsonModel> String?.parseListOrEmpty(model: KClass<T>): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model)
}

inline fun <T: JsonModel> JsonIterable?.parseListOrEmpty(model: KClass<T>): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model)
}

inline fun <T: JsonModel> Sequence<JsonValue>?.parseListOrEmpty(model: KClass<T>): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model)
}

inline fun <T: JsonModel> Array<out JsonValue>?.parseListOrEmpty(model: KClass<T>): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model)
}

/*
 * inline parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonArray.parseList(): List<T> {
    return parseList(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonElement.parseList(): List<T> {
    return parseList(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> String.parseList(): List<T> {
    return parseList(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> JsonIterable.parseList(): List<T> {
    return parseList(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Sequence<JsonValue>.parseList(): List<T> {
    return parseList(T::class)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Array<out JsonValue>.parseList(): List<T> {
    return parseList(T::class)
}

/*
 * inline parseListOrNull
 */

inline fun <reified T: JsonModel> JsonArray?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonElement?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> String?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> JsonIterable?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> Sequence<JsonValue>?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

inline fun <reified T: JsonModel> Array<out JsonValue>?.parseListOrNull(): List<T>? {
    return parseListOrNull(T::class)
}

/*
 * inline parseListOrEmpty
 */

inline fun <reified T: JsonModel> JsonArray?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}

inline fun <reified T: JsonModel> JsonElement?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}

inline fun <reified T: JsonModel> String?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}

inline fun <reified T: JsonModel> JsonIterable?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}

inline fun <reified T: JsonModel> Sequence<JsonValue>?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}

inline fun <reified T: JsonModel> Array<out JsonValue>?.parseListOrEmpty(): List<T> {
    return parseListOrEmpty(T::class)
}
