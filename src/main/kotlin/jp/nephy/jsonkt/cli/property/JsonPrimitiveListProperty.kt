package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement
import jp.nephy.jsonkt.immutableJsonArray
import jp.nephy.jsonkt.jsonPrimitive

internal class JsonPrimitiveListProperty(pair: Map.Entry<String, JsonElement>, typeStrict: Boolean): AbstractJsonProperty(pair) {
    private val candidateMethodName = element.immutableJsonArray.first().jsonPrimitive.toMethodName(strict = typeStrict)
    override val delegationName = "${candidateMethodName}List"

    init {
        if (! element.immutableJsonArray.all { it.jsonPrimitive.toMethodName(strict = typeStrict) == candidateMethodName }) {
            throw IllegalArgumentException("Not all elements are same type. These must be ${element.immutableJsonArray.first().jsonPrimitive.javaPrimitiveClass.simpleName}.")
        }
    }
}
