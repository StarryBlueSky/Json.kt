package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal class JsonNullProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    override val delegationName = "byNullableJsonElement"
}
