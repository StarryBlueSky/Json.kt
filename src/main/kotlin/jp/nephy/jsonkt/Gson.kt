@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

inline fun gson(builder: GsonBuilder = { serializeNulls() }): com.google.gson.Gson {
    return com.google.gson.GsonBuilder().apply(builder).create()
}

interface GsonCompatible<T: com.google.gson.JsonElement> {
    fun toGsonObject(): T
}

inline fun com.google.gson.JsonElement.toJsonKt(): JsonElement {
    return JsonElement(this)
}

inline fun com.google.gson.JsonObject.toJsonKt(): ImmutableJsonObject {
    return JsonObject(this)
}

inline fun com.google.gson.JsonArray.toJsonKt(): ImmutableJsonArray {
    return JsonArray(this)
}

inline fun com.google.gson.JsonPrimitive.toJsonKt(): JsonPrimitive {
    return JsonPrimitive(this)
}

inline fun com.google.gson.JsonNull.toJsonKt(): JsonElement {
    return jsonNull
}
