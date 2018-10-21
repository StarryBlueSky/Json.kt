@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

fun jsonObjectOf(vararg elements: JsonPair) = mutableJsonObjectOf(*elements)

fun immutableJsonObjectOf(vararg elements: JsonPair): ImmutableJsonObject {
    return JsonObject(*elements)
}

fun mutableJsonObjectOf(vararg elements: JsonPair): MutableJsonObject {
    return JsonObject(*elements)
}

typealias GsonJsonObject = com.google.gson.JsonObject
interface ImmutableJsonObject: GsonCompatible<GsonJsonObject>, Map<String, JsonElement> {
    override operator fun get(key: String): JsonElement
}
interface MutableJsonObject: ImmutableJsonObject, MutableMap<String, JsonElement> {
    operator fun set(key: String, value: Any?)
}

internal class JsonObject(vararg pairs: JsonPair): MutableJsonObject, LinkedHashMap<String, JsonElement>(pairs.map { it.first to it.second.toJsonElement() }.toMap()) {
    override operator fun get(key: String): JsonElement {
        return super.get(key) ?: throw JsonNullPointerException(key, this)
    }

    override fun set(key: String, value: Any?) {
        put(key, value)
    }

    override fun toGsonObject(): GsonJsonObject {
        val json = GsonJsonObject()
        for ((key, value) in this) {
            json.add(key, value.toGsonObject())
        }
        return json
    }
}

inline fun ImmutableJsonObject.asMutable(): MutableJsonObject = this as MutableJsonObject
inline fun MutableJsonObject.asImmutable(): ImmutableJsonObject = this

inline fun ImmutableJsonObject.getOrNull(key: String): JsonElement? {
    return try {
        get(key)
    } catch (e: JsonNullPointerException) {
        null
    }
}

inline fun MutableJsonObject.put(key: String, value: Any?) = put(key, value.toJsonElement())
inline fun MutableJsonObject.put(pair: JsonPair) = put(pair.first, pair.second)
inline fun MutableJsonObject.put(entry: JsonMapEntry) = put(entry.key, entry.value)

inline fun MutableJsonObject.putAll(map: JsonMap) = map.entries.forEach { put(it) }
inline fun MutableJsonObject.putAll(pairs: Iterable<JsonPair>) = pairs.forEach { put(it) }
inline fun MutableJsonObject.putAll(pairs: Sequence<JsonPair>) = pairs.forEach { put(it) }
inline fun MutableJsonObject.putAll(vararg pairs: JsonPair) = pairs.forEach { put(it) }
inline fun MutableJsonObject.putAll(vararg entries: JsonMapEntry) = entries.forEach { put(it) }
