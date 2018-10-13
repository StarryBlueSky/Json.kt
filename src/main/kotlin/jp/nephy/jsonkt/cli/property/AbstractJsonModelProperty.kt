package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonElement

internal abstract class AbstractJsonModelProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    val modelName = key.toSafeKotlinLiteral().toLowerCamelCase().capitalize()
}
