package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal class JsonObjectProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    override val delegationName = "byJsonObject"
}
