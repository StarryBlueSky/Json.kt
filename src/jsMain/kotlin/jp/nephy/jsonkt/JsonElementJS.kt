package jp.nephy.jsonkt

import kotlinx.serialization.json.JsonPrimitive

@Suppress("UNCHECKED_CAST")
actual fun <T: Any> T?.asJsonElement(): JsonElement {
    return when (this) {
        null -> JsonNull
        is JsonElement -> this

        /* Collections */
        is Map<*, *> -> {
            JsonObject(this.map { it.key.toString() to it.value.asJsonElement() }.toMap())
        }
        is Iterable<*> -> {
            JsonArray(this.map { it.asJsonElement() })
        }
        is Sequence<*> -> {
            JsonArray(this.map { it.asJsonElement() }.toList())
        }
        is Array<*> -> {
            JsonArray(this.map { it.asJsonElement() })
        }

        /* Primitives */
        is Boolean -> JsonPrimitive(this)
        is Number -> JsonPrimitive(this)
        is Char -> JsonPrimitive(this.toString())
        is String -> JsonPrimitive(this)

        else -> (JsonKt.serializers[this::class] as? JsonKt.Serializer<T>)?.encode(this)?.toJsonElement() ?: throw JsonConversionException(this::class)
    }
}
