package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonNullableModelProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonModelProperty(pair, printComments) {
    override val delegationName = "model<$modelName?>"
}
