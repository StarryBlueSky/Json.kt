@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

fun jsonObjectOf(vararg elements: Pair<String, Any?>) = immutableJsonObjectOf(*elements)

fun immutableJsonObjectOf(vararg elements: Pair<String, Any?>): ImmutableJsonObject {
    return ImmutableJsonObject(elements.map { it.first to it.second.toJsonElement() }.toMap())
}

fun mutableJsonObjectOf(vararg elements: Pair<String, Any?>): MutableJsonObject {
    return MutableJsonObject(elements.map { it.first to it.second.toJsonElement() }.toMap().toMutableMap())
}

typealias JsonObject = ImmutableJsonObject
private typealias GsonJsonObject = com.google.gson.JsonObject

open class ImmutableJsonObject(private val elements: Map<String, JsonElement>): GsonCompatible<GsonJsonObject>, Map<String, JsonElement> {
    constructor(json: GsonJsonObject): this(json.entrySet().map { it.key to it.value.toJsonElement() }.toMap())
    constructor(vararg pairs: Pair<String, JsonElement>): this(pairs.toMap())

    override fun toGsonObject(): GsonJsonObject {
        val json = GsonJsonObject()
        for (element in elements) {
            json.add(element.key, element.value.toGsonObject())
        }
        return json
    }

    override val size: Int
        get() = elements.size

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun containsKey(key: String): Boolean {
        return elements.containsKey(key)
    }

    override fun containsValue(value: JsonElement): Boolean {
        return elements.containsValue(value)
    }

    override fun get(key: String): JsonElement? {
        return elements[key]
    }

    override val entries: Set<Map.Entry<String, JsonElement>>
        get() = elements.entries
    override val keys: Set<String>
        get() = elements.keys
    override val values: Collection<JsonElement>
        get() = elements.values

    fun toMutableJsonObject(): MutableJsonObject {
        return MutableJsonObject(toGsonObject())
    }

    override fun equals(other: Any?): Boolean {
        return elements == (other as? ImmutableJsonObject)?.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }

    override fun toString(): String {
        return elements.toString()
    }
}

class MutableJsonObject(private val elements: MutableMap<String, JsonElement>): ImmutableJsonObject(elements), MutableMap<String, JsonElement> {
    constructor(json: GsonJsonObject): this(json.entrySet().map { it.key to it.value.toJsonElement() }.toMap().toMutableMap())
    constructor(vararg pairs: Pair<String, JsonElement>): this(pairs.toMap().toMutableMap())

    override fun toGsonObject(): GsonJsonObject {
        val json = GsonJsonObject()
        for (element in elements) {
            json.add(element.key, element.value.toGsonObject())
        }
        return json
    }

    override val size: Int
        get() = elements.size

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun containsKey(key: String): Boolean {
        return elements.containsKey(key)
    }

    override fun containsValue(value: JsonElement): Boolean {
        return elements.containsValue(value)
    }

    override fun get(key: String): JsonElement? {
        return elements[key]
    }

    override val entries: MutableSet<MutableMap.MutableEntry<String, JsonElement>>
        get() = elements.entries
    override val keys: MutableSet<String>
        get() = elements.keys
    override val values: MutableCollection<JsonElement>
        get() = elements.values

    override fun put(key: String, value: JsonElement): JsonElement? {
        return elements.put(key, value)
    }

    override fun putAll(from: Map<out String, JsonElement>) {
        return elements.putAll(from)
    }

    override fun remove(key: String): JsonElement? {
        return elements.remove(key)
    }

    override fun clear() {
        return elements.clear()
    }

    fun toImmutableJsonObject(): ImmutableJsonObject {
        return ImmutableJsonObject(toGsonObject())
    }

    override fun equals(other: Any?): Boolean {
        return elements == (other as? MutableJsonObject)?.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }

    override fun toString(): String {
        return elements.toString()
    }
}

// TODO: check
inline fun MutableJsonObject.put(key: String, value: Any?) = put(key, value.toJsonElement())
inline fun MutableJsonObject.put(pair: Pair<String, Any?>) = put(pair.first, pair.second.toJsonElement())
inline fun MutableJsonObject.put(entry: Map.Entry<String, Any?>) = put(entry.key, entry.value.toJsonElement())

inline fun MutableJsonObject.putAll(vararg pairs: Pair<String, Any?>) = pairs.forEach { put(it) }
inline fun MutableJsonObject.putAll(vararg entries: Map.Entry<String, Any?>) = entries.forEach { put(it) }
inline fun MutableJsonObject.putAll(map: Map<String, Any?>) = map.entries.forEach { put(it) }
inline fun MutableJsonObject.putAll(pairs: Sequence<Pair<String, Any?>>) = pairs.forEach { put(it) }
inline fun MutableJsonObject.putAll(pairs: Iterable<Pair<String, Any?>>) = pairs.forEach { put(it) }
