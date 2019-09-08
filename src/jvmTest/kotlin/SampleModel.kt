@file:Suppress("UNUSED")

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.delegation.*

data class Model(override val json: JsonObject): JsonModel {
    override val keyCase: JsonKeyCase
        get() = JsonKeyCase.SnakeCase

    val a by int
    val b by float
    val c by string
    val d by intList
    val e by model<E>()
    val f by modelList<E>()

    val snakeKey by string

    data class E(override val json: JsonObject): JsonModel {
        val x by nullableString
        val y by double
        val z by int
    }
}
