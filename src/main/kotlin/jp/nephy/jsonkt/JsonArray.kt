package jp.nephy.jsonkt

import com.google.gson.JsonArray
import com.google.gson.JsonElement

fun jsonArray(vararg elements: Any?): JsonArray {
    val json = JsonArray()
    elements.forEach {
        json.add(it.toJsonElement())
    }

    return json
}

fun JsonArray.contains(value: Any) = contains(value.toJsonElement())

fun JsonArray.add(value: Any?) = add(value.toJsonElement())

fun JsonArray.addAll(vararg values: Any?) = values.forEach { add(it) }
fun JsonArray.addAll(values: Iterable<Any?>) = values.forEach { add(it) }
fun JsonArray.addAll(values: Sequence<Any?>) = values.forEach { add(it) }

fun JsonArray.removeAll(vararg values: JsonElement) = values.forEach { remove(it) }
fun JsonArray.removeAll(values: Iterable<JsonElement>) = values.forEach { remove(it) }
fun JsonArray.removeAll(values: Sequence<JsonElement>) = values.forEach { remove(it) }

fun JsonArray.removeAllIndexes(vararg indexes: Int) = indexes.forEach { remove(it) }
fun JsonArray.removeAllIndexes(indexes: Iterable<Int>) = indexes.forEach { remove(it) }
fun JsonArray.removeAllIndexes(indexes: Sequence<Int>) = indexes.forEach { remove(it) }
