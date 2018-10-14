package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonNullableModelProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "model<$modelName?>"
}
