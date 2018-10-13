package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement
import jp.nephy.jsonkt.jsonPrimitive

internal class JsonNullablePrimitiveProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    override val delegationName = element.jsonPrimitive.toMethodName(nullable = true)
}
