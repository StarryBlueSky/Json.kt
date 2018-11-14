package jp.nephy.jsonkt

import kotlinx.serialization.json.JsonObject

typealias JsonObject = kotlinx.serialization.json.JsonObject

fun jsonObjectOf(vararg pairs: JsonPair): JsonObject {
    return pairs.toJsonObject()
}
