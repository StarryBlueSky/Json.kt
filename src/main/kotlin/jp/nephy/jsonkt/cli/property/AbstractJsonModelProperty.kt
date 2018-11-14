package jp.nephy.jsonkt.cli.property

import kotlinx.serialization.json.JsonElement

internal abstract class AbstractJsonModelProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    val modelName = key.toSafeKotlinLiteral().toLowerCamelCase().capitalize()
}
