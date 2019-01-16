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
