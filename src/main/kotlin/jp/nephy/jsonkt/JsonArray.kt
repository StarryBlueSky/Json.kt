@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import kotlinx.serialization.json.*

typealias JsonArray = kotlinx.serialization.json.JsonArray

fun jsonArrayOf(vararg elements: JsonValue): JsonArray {
    return elements.toJsonArray()
}

/*
 * to list methods
 */

inline val JsonArray.jsonObjectList: List<JsonObject>
    get() = map { it.jsonObject }

inline val JsonArray.jsonArrayList: List<JsonArray>
    get() = map { it.jsonArray }

inline val JsonArray.jsonPrimitiveList: List<JsonPrimitive>
    get() = map { it.primitive }

inline val JsonArray.booleanList: List<Boolean>
    get() = map { it.boolean }

inline val JsonArray.intList: List<Int>
    get() = map { it.int }

inline val JsonArray.longList: List<Long>
    get() = map { it.long }

inline val JsonArray.floatList: List<Float>
    get() = map { it.float }

inline val JsonArray.doubleList: List<Double>
    get() = map { it.double }

inline val JsonArray.stringList: List<String>
    get() = map { it.content }

/*
 * Compatibility
 */

@Deprecated("ImmutableJsonArray is not used anymore.", replaceWith = ReplaceWith("JsonArray", "jp.nephy.jsonkt.JsonArray"))
typealias ImmutableJsonArray = JsonArray
