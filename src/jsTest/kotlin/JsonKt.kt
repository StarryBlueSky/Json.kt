
import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.delegation.*
import jp.nephy.jsonkt.jsonObjectOf
import jp.nephy.jsonkt.parse

fun main() {
    val json = jsonObjectOf("key" to 1, "key2" to 2.3, "enum" to 1)
    val model = json.parse<Model>()
    
    println(model.enum)
}

data class Model(override val json: JsonObject): JsonModel {
    val key by int
    val key2 by double
    val enum by json.byEnum { TestEnum.One }
}

enum class TestEnum(override val value: Int): IntJsonEnum {
    One(1), Two(2), Three(3)
}
