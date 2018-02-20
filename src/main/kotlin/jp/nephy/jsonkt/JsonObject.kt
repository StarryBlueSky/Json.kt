package jp.nephy.jsonkt

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun jsonObject(vararg elements: Pair<String, Any?>): JsonObject {
    return Gson().run {
        toJsonObject(toJson(elements.toMap()))
    }
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
fun <R> JsonObject.map(operation: (it: Map.Entry<String, JsonElement>) -> R) = entries.map { operation(it) }
fun JsonObject.toMap() = entries.associateBy({ it.key }, { it.value })
