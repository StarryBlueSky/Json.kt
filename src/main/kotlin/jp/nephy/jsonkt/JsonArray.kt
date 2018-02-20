package jp.nephy.jsonkt

import com.google.gson.JsonArray

fun jsonArray(vararg elements: Any?): JsonArray {
    val json = JsonArray()
    elements.forEach {
        json.add(it.toJsonElement())
    }

    return json
}

fun JsonArray.contains(value: Any) = contains(value.toJsonElement())
