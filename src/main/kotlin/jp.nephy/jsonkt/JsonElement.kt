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

inline fun <reified T: Any> JsonElement.cast(): T {
    return this as? T ?: throw JsonCastException(this, T::class)
}

/*
 * toJsonElement
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonElement(): JsonElement {
    return try {
        defaultJsonInstance.parseJson(this)
    } catch (e: Throwable) {
        throw JsonCastException(this, JsonElement::class)
    }
}

inline fun String?.toJsonElementOrNull(): JsonElement? {
    return runSafely {
        toJsonElement()
    }
}

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

inline val JsonElement.jsonObjectOrNull: JsonObject?
    get() = runSafely {
        jsonObject
    }

inline val JsonElement.jsonArrayOrNull: JsonArray?
    get() = runSafely {
        jsonArray
    }

inline val JsonElement.string: String
    get() = content
inline val JsonElement.stringOrNull: String?
    get() = contentOrNull

inline val JsonElement.primitiveOrNull: JsonPrimitive?
    get() = runSafely {
        primitive
    }

inline val JsonElement.literal: JsonLiteral
    get() = this as JsonLiteral
inline val JsonElement.literalOrNull: JsonLiteral?
    get() = runSafely {
        literal
    }

inline val JsonElement.char: Char
    get() = content.first()
inline val JsonElement.charOrNull: Char?
    get() = runSafely {
        char
    }

/*
 * Edit
 */

inline fun JsonElement.editAsObject(block: (JsonMutableMap) -> Unit): JsonObject {
    return jsonObject.edit(block)
}

inline fun JsonElement.editAsArray(block: (JsonMutableArray) -> Unit): JsonArray {
    return jsonArray.edit(block)
}
