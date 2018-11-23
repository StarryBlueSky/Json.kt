@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import kotlinx.serialization.json.content
import kotlinx.serialization.json.contentOrNull

typealias JsonElement = kotlinx.serialization.json.JsonElement

inline val JsonElement.jsonObjectOrNull: JsonObject?
    get() = runCatching {
        jsonObject
    }.getOrNull()

inline val JsonElement.jsonArrayOrNull: JsonArray?
    get() = runCatching {
        jsonArray
    }.getOrNull()

/*
 * Operator functions
 */

@Throws(JsonKtException::class)
inline operator fun JsonElement.get(key: String): JsonElement {
    return jsonObject[key]
}

@Throws(JsonKtException::class, IndexOutOfBoundsException::class)
inline operator fun JsonElement.get(index: Int): JsonElement {
    return jsonArray[index]
}

/*
 * Compatibility
 */

inline val JsonElement.string: String
    get() = content
inline val JsonElement.stringOrNull: String?
    get() = contentOrNull

/*
 * Edit operations
 */

inline fun JsonElement.edit(noinline operation: (MutableMap<String, Any?>) -> Unit): JsonObject {
    return jsonObject.edit(operation)
}
