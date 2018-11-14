package jp.nephy.jsonkt.cli.property

import kotlinx.serialization.json.JsonElement

internal class JsonNullablePrimitiveProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    override val delegationName = toMethodName(jsonPrimitive = element.primitive, nullable = true)
}
