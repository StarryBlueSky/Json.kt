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

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.*
import kotlinx.serialization.json.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

@PublishedApi
@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
internal actual inline fun <reified T> JsonObject.getValue(key: String): T {
    val value = this[key]

    return when (val kClass = T::class) {
        Boolean::class -> {
            value?.jsonPrimitive?.booleanOrNull
        }
        Int::class -> {
            value?.jsonPrimitive?.intOrNull
        }
        Long::class -> {
            value?.jsonPrimitive?.longOrNull
        }
        Float::class -> {
            value?.jsonPrimitive?.floatOrNull
        }
        Double::class -> {
            value?.jsonPrimitive?.doubleOrNull
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
        JsonNull::class -> {
            value?.jsonNull
        }
        JsonElement::class -> {
            value
        }
        else -> {
            if (kClass.isSubclassOf(JsonModel::class)) {
                value?.parseObject(kClass as KClass<JsonModel>)
            } else {
                throw UnsupportedOperationException("Unsupported class: ${kClass.qualifiedName}")
            }
        }
    } as T
}
