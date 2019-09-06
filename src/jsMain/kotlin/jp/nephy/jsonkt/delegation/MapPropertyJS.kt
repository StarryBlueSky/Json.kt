package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlinx.serialization.json.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@PublishedApi
@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
internal actual inline fun <reified T> JsonObject.getValue(key: String): T {
    val value = this[key]

    return when (val kClass = T::class) {
        Boolean::class -> {
            value?.booleanOrNull
        }
        Int::class -> {
            value?.intOrNull
        }
        Long::class -> {
            value?.longOrNull
        }
        Float::class -> {
            value?.floatOrNull
        }
        Double::class -> {
            value?.doubleOrNull
        }
        Char::class -> {
            value?.stringOrNull?.firstOrNull()
        }
        String::class -> {
            value?.stringOrNull
        }
        JsonObject::class -> {
            value?.jsonObjectOrNull
        }
        JsonArray::class -> {
            value?.jsonArrayOrNull
        }
        JsonPrimitive::class -> {
            value?.primitiveOrNull
        }
        JsonLiteral::class -> {
            value?.literalOrNull
        }
        JsonNull::class -> {
            value?.jsonNull
        }
        JsonElement::class -> {
            value
        }
        else -> {
            throw UnsupportedOperationException("Unsupported class: ${kClass.simpleName}")
        }
    } as T
}
