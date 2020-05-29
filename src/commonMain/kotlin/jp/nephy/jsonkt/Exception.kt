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

@file:Suppress("UNUSED", "UNUSED_PARAMETER", "MemberVisibilityCanBePrivate", "CanBeParameter")

package jp.nephy.jsonkt

import kotlin.reflect.KClass

open class JsonKtException(override val message: String): Exception()

class JsonCastException(
    val element: Any,
    val type: KClass<*>
): JsonKtException("An element cannot be casted to ${type.simpleName}:\n${element.toString().take(100)}...")

class JsonConversionException(
    val type: KClass<*>
): JsonKtException("${type.simpleName} cannot be converted to json. Please install JsonKt.Serializer to handle.")

class JsonNullPointerException(
    val key: String,
    val json: JsonObject?
): JsonKtException("json[\"$key\"] is null or is not present but return type is non-null:\n${json.toString().take(100)}...")

class InvalidJsonModelException(
    @Suppress("UNUSED_PARAMETER") val modelClass: KClass<*>
): JsonKtException("${modelClass.simpleName} does not have valid constructor.")
