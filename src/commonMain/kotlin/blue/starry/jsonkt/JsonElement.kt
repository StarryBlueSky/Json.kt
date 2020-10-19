/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
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

@file:Suppress("UNUSED")

package blue.starry.jsonkt

import blue.starry.jsonkt.delegation.JsonModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.JsonNull.content
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

inline fun <reified T: Any> JsonElement.cast(): T {
    return this as? T ?: throw JsonCastException(this, T::class)
}

/*
 * toJsonElement
 */

/**
 * @throws JsonCastException
 */
fun String.toJsonElement(): JsonElement {
    return try {
        defaultJsonInstance.decodeFromString(this)
    } catch (e: Throwable) {
        throw JsonCastException(this, JsonElement::class)
    }
}

fun String?.toJsonElementOrNull(): JsonElement? {
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

        else -> (JsonKt.serializers[this::class] as? JsonKt.Serializer<T>)?.encode(this)?.toJsonElement() ?: throw JsonConversionException(
            this::class
        )
    }
}

/*
 * Operator functions
 */

operator fun JsonElement.get(key: String): JsonElement {
    return jsonObject[key] ?: throw JsonNullPointerException(key, jsonObject)
}

operator fun JsonElement.get(index: Int): JsonElement {
    return jsonArray[index]
}

/*
 * Compatibility
 */

inline val JsonElement.jsonObjectOrNull: JsonObject?
    get() = this as? JsonObject

inline val JsonElement.jsonArrayOrNull: JsonArray?
    get() = this as? JsonArray

inline val JsonElement.string: String
    get() {
        return jsonPrimitive.takeIf { it.isString }?.content ?: throw IllegalStateException("The value is not a string")
    }
inline val JsonElement.stringOrNull: String?
    get() {
        if (this !is JsonPrimitive || !isString) {
            return null
        }

        return content
    }

inline val JsonElement.primitiveOrNull: JsonPrimitive?
    get() = this as? JsonPrimitive

inline val JsonElement.char: Char
    get() = content.first()
inline val JsonElement.charOrNull: Char?
    get() = runSafely {
        char
    }

/*
 * Copy
 */

fun JsonElement.copyAsObject(block: (JsonMutableMap) -> Unit): JsonObject {
    return jsonObject.copy(block)
}

fun JsonElement.copyAsArray(block: (JsonMutableArray) -> Unit): JsonArray {
    return jsonArray.copy(block)
}
