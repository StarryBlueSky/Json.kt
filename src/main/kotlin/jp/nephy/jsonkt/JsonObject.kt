package jp.nephy.jsonkt

typealias JsonObject = kotlinx.serialization.json.JsonObject

fun jsonObjectOf(vararg pairs: JsonPair): JsonObject {
    return pairs.toJsonObject()
}

/*
 * Compatibility
 */

@Deprecated("ImmutableJsonObject is not used anymore.", replaceWith = ReplaceWith("JsonObject", "jp.nephy.jsonkt.JsonObject"))
typealias ImmutableJsonObject = JsonObject
