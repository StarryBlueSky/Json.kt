/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2019 Nephy Project Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel
import jp.nephy.jsonkt.delegation.JsonNullPointerException
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.content
import kotlinx.serialization.json.contentOrNull
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


inline fun JsonElement?.isNull(): Boolean {
    contract {
        returns(false) implies (this@isNull != null)
    }

    return this == null || isNull
}

class JsonConversionException(
    @Suppress("UNUSED_PARAMETER") val type: KClass<*>
): JsonKtException("${type.effectiveName} cannot be converted to json. Please install JsonKt.Serializer to handle.")

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
    return jsonObject[key] ?: throw JsonNullPointerException(key, jsonObject)
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
