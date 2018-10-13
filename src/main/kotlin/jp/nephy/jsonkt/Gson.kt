@file:Suppress("UNUSED")
package jp.nephy.jsonkt

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun gson(builder: GsonBuilder.() -> Unit = { serializeNulls() }): Gson {
    return GsonBuilder().apply(builder).create()
}

interface GsonCompatible<T: com.google.gson.JsonElement> {
    fun toGsonObject(): T
}

fun com.google.gson.JsonElement.toJsonKt(): JsonElement {
    return JsonElement(this)
}

fun com.google.gson.JsonObject.toJsonKt(): ImmutableJsonObject {
    return JsonObject(this)
}

fun com.google.gson.JsonArray.toJsonKt(): ImmutableJsonArray {
    return JsonArray(this)
}

fun com.google.gson.JsonPrimitive.toJsonKt(): JsonPrimitive {
    return JsonPrimitive(this)
}

fun com.google.gson.JsonNull.toJsonKt(): JsonElement {
    return jsonNull
}
