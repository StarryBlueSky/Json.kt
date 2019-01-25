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
inline fun <T: JsonModel> JsonObject.parse(model: KClass<T>, vararg args: Any?): T {
    return parseOrNull(model, *args) ?: throw InvalidJsonModelException(model)
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
inline fun <T: JsonModel> Iterable<JsonPair>.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Sequence<JsonPair>.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Array<out JsonPair>.parse(model: KClass<T>, vararg args: Any?): T {
    return toJsonObject().parse(model, *args)
}

/*
 * parseOrNull
 */

inline fun <T: JsonModel> JsonObject?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return runCatching {
        this?.let { model.newModelInstance(it, *args) }
    }.getOrNull()
}

inline fun <T: JsonModel> JsonElement?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return this?.jsonObjectOrNull.parseOrNull(model, *args)
}

inline fun <T: JsonModel> String?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return toJsonObjectOrNull().parseOrNull(model, *args)
}

inline fun <T: JsonModel> JsonMap?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return toJsonObjectOrNull().parseOrNull(model, *args)
}

inline fun <T: JsonModel> Iterable<JsonPair>?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return toJsonObjectOrNull().parseOrNull(model, *args)
}

inline fun <T: JsonModel> Sequence<JsonPair>?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return toJsonObjectOrNull().parseOrNull(model, *args)
}

inline fun <T: JsonModel> Array<out JsonPair>?.parseOrNull(model: KClass<T>, vararg args: Any?): T? {
    return toJsonObjectOrNull().parseOrNull(model, *args)
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
inline fun <reified T: JsonModel> Iterable<JsonPair>.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Sequence<JsonPair>.parse(vararg args: Any?): T {
    return parse(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Array<out JsonPair>.parse(vararg args: Any?): T {
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

inline fun <reified T: JsonModel> Iterable<JsonPair>?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> Sequence<JsonPair>?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> Array<out JsonPair>?.parseOrNull(vararg args: Any?): T? {
    return parseOrNull(T::class, *args)
}

/*
 * parseList
 */

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> JsonArray.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return parseListOrNull(model, *args) ?: throw InvalidJsonModelException(model)
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
inline fun <T: JsonModel> JsonIterable.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Sequence<JsonValue>.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <T: JsonModel> Array<out JsonValue>.parseList(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArray().parseList(model, *args)
}

/*
 * parseListOrNull
 */

inline fun <T: JsonModel> JsonArray?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return runCatching { 
        this?.mapNotNull { element -> 
            element.jsonObjectOrNull.parseOrNull(model, *args).also { 
                if (it == null) {
                    throw InvalidJsonModelException(model)
                }
            }
        }
    }.getOrNull()
}

inline fun <T: JsonModel> JsonElement?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return this?.jsonArrayOrNull.parseListOrNull(model, *args)
}

inline fun <T: JsonModel> String?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model, *args)
}

inline fun <T: JsonModel> JsonIterable?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model, *args)
}

inline fun <T: JsonModel> Sequence<JsonValue>?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model, *args)
}

inline fun <T: JsonModel> Array<out JsonValue>?.parseListOrNull(model: KClass<T>, vararg args: Any?): List<T>? {
    return toJsonArrayOrNull().parseListOrNull(model, *args)
}

/*
 * parseListOrEmpty
 */

inline fun <T: JsonModel> JsonArray?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return runCatching { 
        this?.mapNotNull { element -> 
            element.jsonObjectOrNull.parseOrNull(model, *args)
        }
    }.getOrNull().orEmpty()
}

inline fun <T: JsonModel> JsonElement?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return this?.jsonArrayOrNull.parseListOrEmpty(model, *args)
}

inline fun <T: JsonModel> String?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model, *args)
}

inline fun <T: JsonModel> JsonIterable?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model, *args)
}

inline fun <T: JsonModel> Sequence<JsonValue>?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model, *args)
}

inline fun <T: JsonModel> Array<out JsonValue>?.parseListOrEmpty(model: KClass<T>, vararg args: Any?): List<T> {
    return toJsonArrayOrNull().parseListOrEmpty(model, *args)
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
inline fun <reified T: JsonModel> JsonIterable.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Sequence<JsonValue>.parseList(vararg args: Any?): List<T> {
    return parseList(T::class, *args)
}

/**
 * @throws InvalidJsonModelException
 */
inline fun <reified T: JsonModel> Array<out JsonValue>.parseList(vararg args: Any?): List<T> {
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

inline fun <reified T: JsonModel> JsonIterable?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> Sequence<JsonValue>?.parseListOrNull(vararg args: Any?): List<T>? {
    return parseListOrNull(T::class, *args)
}

inline fun <reified T: JsonModel> Array<out JsonValue>?.parseListOrNull(vararg args: Any?): List<T>? {
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

inline fun <reified T: JsonModel> JsonIterable?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> Sequence<JsonValue>?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}

inline fun <reified T: JsonModel> Array<out JsonValue>?.parseListOrEmpty(vararg args: Any?): List<T> {
    return parseListOrEmpty(T::class, *args)
}
