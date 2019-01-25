package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonPrimitiveProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    override val delegationName = toMethodName(jsonPrimitive = element.primitive)
}
