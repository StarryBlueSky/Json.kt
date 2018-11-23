package jp.nephy.jsonkt

typealias JsonObject = kotlinx.serialization.json.JsonObject

fun jsonObjectOf(vararg pairs: JsonPair): JsonObject {
    return pairs.toJsonObject()
}

/*
 * Compatibility
 */

@Suppress("UNCHECKED_CAST")
fun JsonObject.edit(editor: (MutableMap<String, Any?>) -> Unit): JsonObject {
    return (toMutableMap() as MutableMap<String, Any?>).apply(editor).toJsonObject()
}
