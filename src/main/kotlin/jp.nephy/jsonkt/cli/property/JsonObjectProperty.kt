package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonObjectProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    override val delegationName = "jsonObject"
}
