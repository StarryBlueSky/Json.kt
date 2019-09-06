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

package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.JsonArray
import jp.nephy.jsonkt.JsonElement
import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.JsonPrimitive

internal abstract class AbstractJsonProperty(pair: Map.Entry<String, JsonElement>, private val printComments: Boolean) {
    val key = pair.key
    val element = pair.value
    private var nullable = false

    abstract val delegationName: String
    private val propertyName = key.toSafeKotlinLiteral().toLowerCamelCase()

    fun toPropertyString(): String {
        val method = when {
            propertyName != key -> "$delegationName(\"${key.escapeKotlinStringLiteral()}\")"
            delegationName.contains("<") -> "$delegationName()"
            else -> delegationName
        }
        return "val $propertyName by $method${element.toKotlinComment()}"
    }

    fun toMethodName(jsonPrimitive: JsonPrimitive, nullable: Boolean = false): String {
        if (nullable) {
            this.nullable = true
        }
        return when {
            jsonPrimitive.booleanOrNull != null -> "boolean"
            jsonPrimitive.intOrNull != null -> "int"
            jsonPrimitive.longOrNull != null -> "long"
            jsonPrimitive.floatOrNull != null -> "float"
            jsonPrimitive.doubleOrNull != null -> "double"
            jsonPrimitive.contentOrNull != null -> "string"
            else -> throw IllegalStateException("Unknown type: ${jsonPrimitive::class.qualifiedName}.")
        }.let {
            if (nullable) {
                "nullable${it.capitalize()}"
            } else {
                it
            }
        }
    }

    fun String.toLowerCamelCase(): String {
        val part = split("_")
        return part.first().decapitalize() + part.drop(1).joinToString("") { it.capitalize() }
    }

    fun String.toSafeKotlinLiteral(): String {
        return replace("\\W".toRegex(), "_").replace("^\\d".toRegex()) { "_${it.value}" }
    }

    private fun String.escapeKotlinStringLiteral(): String {
        return replace("$", "\\$").replace("\"", "\\\"")
    }

    private fun JsonElement.toKotlinComment(): String {
        if (!printComments) {
            return ""
        }

        return when {
            this is JsonObject -> "  // {...}"
            this is JsonArray -> {
                val element = this.firstOrNull()?.toString()
                when {
                    element == null -> "  // []"
                    element.length > 50 -> "  // [...]"
                    size == 1 -> "  // [$element]"
                    else -> "  // [${this.first()}, ...]"
                }
            }
            nullable -> "  // ${toString()} or null"
            else -> "  // ${toString()}"
        }
    }
}
