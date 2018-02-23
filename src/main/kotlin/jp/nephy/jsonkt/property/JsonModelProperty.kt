package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal class JsonModelProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "byModel<$modelName>"
}
