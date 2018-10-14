package jp.nephy.jsonkt.cli.property

import jp.nephy.jsonkt.*

internal abstract class AbstractJsonProperty(pair: Map.Entry<String, JsonElement>) {
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

    fun JsonPrimitive.toMethodName(strict: Boolean = false, nullable: Boolean = false): String {
        if (nullable) {
            this@AbstractJsonProperty.nullable = true
        }
        return when {
            isBoolean -> "boolean"
            isNumber() -> {
                when {
                    strict && isByte -> "byte"
                    strict && isShort -> "short"
                    isInt -> "int"
                    isLong -> "long"
                    isFloat -> "float"
                    isDouble -> "double"
                    else -> throw IllegalStateException("Unknown type: ${this.javaClass.canonicalName}.")
                }
            }
            strict && isChar -> "char"
            isString -> "string"
            else -> throw IllegalStateException("Unknown type: ${this.javaClass.canonicalName}.")
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
        return when {
            isJsonObject() -> "  // {...}"
            isJsonArray() -> {
                if (immutableJsonArray.isEmpty()) {
                    "  // []"
                } else {
                    val element = immutableJsonArray.first().toString()
                    when {
                        element.length > 50 -> "  // [...]"
                        immutableJsonArray.size == 1 -> "  // [$element]"
                        else -> "  // [${immutableJsonArray.first()}, ...]"
                    }
                }
            }
            nullable -> "  // ${toString()} or null"
            else -> "  // ${toString()}"
        }
    }
}
