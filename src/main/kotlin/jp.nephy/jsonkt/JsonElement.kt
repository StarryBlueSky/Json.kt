@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.content
import kotlinx.serialization.json.contentOrNull
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract
import kotlin.reflect.KClass

typealias JsonElement = kotlinx.serialization.json.JsonElement

inline val JsonElement.jsonObjectOrNull: JsonObject?
    get() = runCatching {
        jsonObject
    }.getOrNull()

inline val JsonElement.jsonArrayOrNull: JsonArray?
    get() = runCatching {
        jsonArray
    }.getOrNull()

@ExperimentalContracts
inline val JsonElement?.isNull: Boolean
    get() {
        contract {
            returns(false) implies (this@isNull != null)
        }
        
        return this == null || isNull
    }

class JsonConversionException(val type: KClass<*>): JsonKtException("${type.effectiveName} cannot be converted to json. Please install JsonKt.Serializer to handle.")

/**
 * @throws JsonConversionException
 */
@Suppress("UNCHECKED_CAST")
fun <T: Any> T?.asJsonElement(): JsonElement {
    return when (this) {
        null -> JsonNull
        is JsonElement -> this
        is JsonModel -> json
        
        /* Collections */
        is Map<*, *> -> JsonObject(this.toList().map { it.first.toString() to it.second.asJsonElement() }.toMap())
        is Iterable<*> -> JsonArray(this.toList().map { it.asJsonElement() })
        is Sequence<*> -> JsonArray(this.toList().map { it.asJsonElement() })
        is Array<*> -> JsonArray(this.toList().map { it.asJsonElement() })
        
        /* Primitives */
        is Boolean -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Char -> JsonPrimitive(this.toString())
        is String -> JsonPrimitive(this)
        
        else -> (JsonKt.serializers[this::class] as? JsonKt.Serializer<T>)?.encode(this)?.toJsonElement() ?: throw JsonConversionException(this::class)
    }
}

/*
 * Operator functions
 */

inline operator fun JsonElement.get(key: String): JsonElement {
    return jsonObject[key]
}

inline operator fun JsonElement.get(index: Int): JsonElement {
    return jsonArray[index]
}

/*
 * Compatibility
 */

inline val JsonElement.string: String
    get() = content
inline val JsonElement.stringOrNull: String?
    get() = contentOrNull

inline val JsonElement.primitiveOrNull: JsonPrimitive?
    get() = runCatching { 
        primitive
    }.getOrNull()

inline val JsonElement.char: Char
    get() = content.first()
inline val JsonElement.charOrNull: Char?
    get() = contentOrNull?.firstOrNull()

/*
 * Edit operations
 */

inline fun JsonElement.edit(noinline operation: (MutableMap<String, Any?>) -> Unit): JsonObject {
    return jsonObject.edit(operation)
}
