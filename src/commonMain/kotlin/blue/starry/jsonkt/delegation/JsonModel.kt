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

import blue.starry.jsonkt.JsonObject
import kotlin.reflect.KProperty

typealias JsonKeyConverter = (KProperty<*>) -> String

interface JsonModel {
    val json: JsonObject

    val keyCase: JsonKeyCase
        get() = JsonKeyCase.Unspecified

    val keyConverter: JsonKeyConverter
        get() = ::defaultJsonKeyConverter
}

private fun JsonModel.defaultJsonKeyConverter(property: KProperty<*>): String {
    return when (keyCase) {
        JsonKeyCase.Unspecified -> property.name
        JsonKeyCase.LowerCamelCase -> {
            property.name.replaceFirstChar { it.lowercase() }
        }
        JsonKeyCase.UpperCamelCase -> {
            property.name.replaceFirstChar {
                if (it.isLowerCase()) {
                    it.titlecase()
                } else {
                    it.toString()
                }
            }
        }
        JsonKeyCase.SnakeCase -> {
            buildString {
                for (c in property.name) {
                    if (c == c.uppercaseChar()) {
                        if (length > 0) {
                            append('_')
                        }
                        append(c.lowercaseChar())
                    } else {
                        append(c)
                    }
                }
            }
        }
    }
}

enum class JsonKeyCase {
    Unspecified, LowerCamelCase, UpperCamelCase, SnakeCase
}
