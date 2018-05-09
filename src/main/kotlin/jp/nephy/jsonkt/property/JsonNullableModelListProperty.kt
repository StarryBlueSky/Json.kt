package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal class JsonNullableModelListProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "byModelList<$modelName?>"
}
