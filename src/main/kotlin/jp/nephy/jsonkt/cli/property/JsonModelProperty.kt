package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal class JsonModelProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonModelProperty(pair) {
    override val delegationName = "byModel<$modelName>"
}
