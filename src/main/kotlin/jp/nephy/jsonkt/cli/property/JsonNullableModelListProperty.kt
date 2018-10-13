package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonNullableModelListProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "byModelList<$modelName?>"
}
