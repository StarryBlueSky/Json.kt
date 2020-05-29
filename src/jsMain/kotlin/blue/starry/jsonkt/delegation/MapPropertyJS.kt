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

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.*
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
