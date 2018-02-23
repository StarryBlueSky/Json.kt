package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal class JsonModelListProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "byModelList<$modelName>"
}
