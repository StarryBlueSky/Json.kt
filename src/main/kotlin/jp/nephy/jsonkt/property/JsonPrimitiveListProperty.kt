package jp.nephy.jsonkt.property

import com.google.gson.JsonElement
import jp.nephy.jsonkt.getClass
import jp.nephy.jsonkt.jsonArray
import jp.nephy.jsonkt.jsonPrimitive

internal class JsonPrimitiveListProperty(pair: Map.Entry<String, JsonElement>, typeStrict: Boolean): AbstractJsonProperty(pair) {
    private val candidateMethodName = element.jsonArray.first().jsonPrimitive.toMethodName(strict = typeStrict)
    override val delegationName = "${candidateMethodName}List"

    init {
        if (! element.jsonArray.all { it.jsonPrimitive.toMethodName(strict = typeStrict) == candidateMethodName }) {
            throw IllegalArgumentException("Not all elements are same type. These must be ${element.jsonArray.first().jsonPrimitive.getClass().simpleName}.")
        }
    }
}
