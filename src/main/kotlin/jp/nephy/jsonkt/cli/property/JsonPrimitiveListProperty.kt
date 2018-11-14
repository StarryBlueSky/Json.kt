package jp.nephy.jsonkt.cli.property

import kotlinx.serialization.json.JsonElement

internal class JsonPrimitiveListProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    private val candidateMethodName = toMethodName(jsonPrimitive = element.jsonArray.first().primitive)
    override val delegationName = "${candidateMethodName}List"

    init {
        if (! element.jsonArray.all { toMethodName(jsonPrimitive = it.primitive) == candidateMethodName }) {
            throw IllegalArgumentException("Not all elements are same type. These must be ${element.jsonArray.first().primitive::class.simpleName}.")
        }
    }
}
