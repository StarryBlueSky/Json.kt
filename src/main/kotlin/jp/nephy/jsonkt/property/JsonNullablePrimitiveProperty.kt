package jp.nephy.jsonkt.property

import com.google.gson.JsonElement
import jp.nephy.jsonkt.jsonPrimitive

internal class JsonNullablePrimitiveProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    override val delegationName = element.jsonPrimitive.toMethodName(nullable = true)
}
