package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonObjectProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    override val delegationName = "immutableJsonObject"
}
