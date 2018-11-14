package jp.nephy.jsonkt.cli.property

import kotlinx.serialization.json.JsonElement

internal class JsonModelListProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonModelProperty(pair, printComments) {
    override val delegationName = "modelList<$modelName>"
}
