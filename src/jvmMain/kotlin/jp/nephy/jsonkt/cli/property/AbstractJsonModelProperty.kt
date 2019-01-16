package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal abstract class AbstractJsonModelProperty(pair: Map.Entry<String, JsonElement>, printComments: Boolean): AbstractJsonProperty(pair, printComments) {
    val modelName = key.toSafeKotlinLiteral().toLowerCamelCase().capitalize()
}
