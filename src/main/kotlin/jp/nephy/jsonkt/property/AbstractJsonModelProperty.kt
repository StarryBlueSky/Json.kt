package jp.nephy.jsonkt.property

import com.google.gson.JsonElement

internal abstract class AbstractJsonModelProperty(pair: Map.Entry<String, JsonElement>): AbstractJsonProperty(pair) {
    val modelName = key.toSafeKotlinLiteral().capitalize()
}
