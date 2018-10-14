package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement
import jp.nephy.jsonkt.jsonPrimitive

internal class JsonPrimitiveProperty(pair: Map.Entry<String, JsonElement>, typeStrict: Boolean): AbstractJsonProperty(pair) {
    override val delegationName = element.jsonPrimitive.toMethodName(strict = typeStrict)
}
