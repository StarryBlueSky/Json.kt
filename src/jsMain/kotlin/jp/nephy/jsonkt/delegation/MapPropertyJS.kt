package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import jp.nephy.jsonkt.jsonArrayOrNull
import jp.nephy.jsonkt.jsonObjectOrNull
import jp.nephy.jsonkt.primitiveOrNull
import jp.nephy.jsonkt.stringOrNull
import kotlinx.serialization.json.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
actual inline operator fun <reified T> JsonObject.getValue(thisRef: Any?, property: KProperty<*>): T {
    val value = this[property.name]

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
