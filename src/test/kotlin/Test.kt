import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.delegation.JsonModel
import jp.nephy.jsonkt.parse

data class Model(override val json: JsonObject): JsonModel

fun main() {
    val json = "{}".parse<Model>()
}
