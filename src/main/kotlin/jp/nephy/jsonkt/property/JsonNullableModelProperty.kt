package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal class JsonNullableModelProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "byModel<$modelName?>"
}
