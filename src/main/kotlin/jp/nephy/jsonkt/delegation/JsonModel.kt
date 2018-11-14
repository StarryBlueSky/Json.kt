package jp.nephy.jsonkt.delegation

import kotlinx.serialization.json.JsonObject

interface JsonModel {
    val json: JsonObject
}
