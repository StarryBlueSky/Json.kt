package jp.nephy.jsonkt.cli.property

import kotlinx.serialization.json.JsonElement

internal class JsonNullProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    override val delegationName = "nullableJsonElement"
}
