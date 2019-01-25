package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonNullableModelListProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonModelProperty(pair, printComments) {
    override val delegationName = "modelList<$modelName?>"
}
