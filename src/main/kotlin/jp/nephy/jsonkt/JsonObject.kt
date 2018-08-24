package jp.nephy.jsonkt

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun jsonObject(vararg elements: Pair<String, Any?>, builder: (GsonBuilder.() -> Unit)? = null): JsonObject {
    return elements.toMap().toJsonObject(builder)
}

val JsonObject.size: Int
    get() = entrySet().size
fun JsonObject.isEmpty() = entrySet().isEmpty()
fun JsonObject.isNotEmpty() = ! isEmpty()
fun JsonObject.contains(key: String) = has(key)
fun JsonObject.containsKey(key: String) = contains(key)
fun JsonObject.containsValue(value: JsonElement) = entrySet().any { it.value == value }
fun JsonObject.getOrNull(key: String): JsonElement? {
    return if (contains(key)) {
        get(key)
    } else {
        null
    }
}
fun JsonObject.getOrDefault(key: String, default: JsonElement) = getOrNull(key) ?: default
fun JsonObject.getOrElse(key: String, default: () -> JsonElement) = getOrNull(key) ?: default()
val JsonObject.keys: Set<String>
    get() = entrySet().map { it.key }.toSet()
val JsonObject.values: Collection<JsonElement>
    get() = entrySet().map { it.value }
val JsonObject.entries: Set<Map.Entry<String, JsonElement>>
    get() = entrySet()
fun JsonObject.forEach(operation: (it: Map.Entry<String, JsonElement>) -> Unit) = entries.forEach { operation(it) }
fun JsonObject.forEach(operation: (k: String, v: JsonElement) -> Unit) = entries.forEach { operation(it.key, it.value) }
fun <R> JsonObject.map(operation: (it: Map.Entry<String, JsonElement>) -> R) = entries.map { operation(it) }
fun JsonObject.toMap() = entries.associateBy({ it.key }, { it.value })

fun JsonObject.put(it: Pair<String, Any?>) = add(it.first, it.second.toJsonElement())
fun JsonObject.put(it: Map.Entry<String, Any?>) = add(it.key, it.value.toJsonElement())

fun JsonObject.putAll(vararg pairs: Pair<String, Any?>) = pairs.forEach { put(it) }
fun JsonObject.putAll(vararg entries: Map.Entry<String, Any?>) = entries.forEach { put(it) }
fun JsonObject.putAll(map: Map<String, Any?>) = map.entries.forEach { put(it) }
fun JsonObject.putAll(obj: JsonObject) = obj.entrySet().forEach { put(it) }
fun JsonObject.putAll(pairs: Sequence<Pair<String, Any?>>) = pairs.forEach { put(it) }
fun JsonObject.putAll(pairs: Iterable<Pair<String, Any?>>) = pairs.forEach { put(it) }
fun JsonObject.putAllEntries(entries: Sequence<Map.Entry<String, Any?>>) = entries.forEach { put(it) }
fun JsonObject.putAllEntries(entries: Iterable<Map.Entry<String, Any?>>) = entries.forEach { put(it) }

fun JsonObject.removeAll(vararg keys: String) = keys.forEach { remove(it) }
fun JsonObject.removeAll(keys: Iterable<String>) = keys.forEach { remove(it) }
fun JsonObject.removeAll(keys: Sequence<String>) = keys.forEach { remove(it) }
fun JsonObject.removeAllJsonKeys(vararg keys: JsonElement) = keys.forEach { remove(it.string) }
fun JsonObject.removeAllJsonKeys(keys: Iterable<JsonElement>) = keys.forEach { remove(it.string) }
fun JsonObject.removeAllJsonKeys(keys: Sequence<JsonElement>) = keys.forEach { remove(it.string) }
