package jp.nephy.jsonkt

import com.google.gson.*
import jp.nephy.jsonkt.property.*

fun generateModelClass(): String {
    print("Model name? (Optional): ")
    val modelName = readLine().orEmpty()
    print("Use type strict mode? (Y/n): ")
    val typeStrict = readLine().orEmpty().toLowerCase() == "y"
    println("Input json string. If blank line is input, quit.")

    while (true) {
        var text = ""
        while (true) {
            val line = readLine()
            if (line.isNullOrBlank()) {
                break
            }
            text += line
        }

        try {
            return text.toModelString(modelName, typeStrict)
        } catch (e: JsonSyntaxException) {
            System.err.write("Invalid json format: ${e.localizedMessage}\n".toByteArray())
            continue
        }
    }
}

internal class JsonToKotlinClass(private val json: JsonObject) {
    fun convert(targetModelName: String?, typeStrict: Boolean?): String {
        val modelName = targetModelName.run {
            if (isNullOrEmpty()) {
                "Model"
            } else {
                this
            }!!
        }

        val result = mutableListOf<String>()

        val parser = JsonObjectParser(json, typeStrict ?: false)
        result.add(parser.toModelString(modelName))

        if (result.isNotEmpty()) {
            result.add(0, "import jp.nephy.jsonkt.*")
            result.add(1, "import com.google.gson.JsonObject\n")
        }

        return result.joinToString("\n")
    }

    private class JsonObjectParser(private val json: JsonObject, private val typeStrict: Boolean) {
        fun toModelString(name: String): String {
            return buildString {
                val subModels = mutableListOf<String>()
                append("class $name(override val json: JsonObject): JsonModel {\n")
                json.map {
                    when {
                        it.value.isJsonObject -> when {
                            it.value.toString() == "{}" -> JsonObjectProperty(it)
                            it.value.jsonObject.isNullable -> JsonNullableModelProperty(it)
                            else -> JsonModelProperty(it)
                        }
                        it.value.isJsonArray -> when {
                            it.value.jsonArray.isEmpty() || it.value.jsonArray.all { it.isJsonArray } -> JsonArrayProperty(it)
                            it.value.jsonArray.all { it.isJsonObject } -> when {
                                it.value.asJsonArray.isNullable -> JsonNullableModelListProperty(it)
                                else -> JsonModelListProperty(it)
                            }
                            it.value.jsonArray.all { it.isJsonPrimitive } -> JsonPrimitiveListProperty(it, typeStrict)
                            else -> throw IllegalStateException("Not all elements in array are same type. These must be JsonObject, JsonArray or JsonPrimitive. (${it.key}: ${it.value})")
                        }
                        it.value.isJsonNull -> JsonNullProperty(it)
                        it.value.isJsonPrimitive -> when {
                            it.value.jsonPrimitive.isNullable -> JsonNullablePrimitiveProperty(it)
                            else -> JsonPrimitiveProperty(it, typeStrict)
                        }
                        else -> throw IllegalArgumentException("Unknown type: ${it.value}")
                    }
                }.sortedBy { it.key }.forEach {
                    if (it is JsonModelProperty) {
                        val parser = JsonObjectParser(it.element.jsonObject, typeStrict)
                        subModels.add(parser.toModelString(it.modelName))
                    } else if (it is JsonModelListProperty) {
                        val values = mutableMapOf<String, MutableSet<JsonElement>>()
                        it.element.jsonArray.forEach {
                            it.jsonObject.forEach { k, v ->
                                if (values.contains(k)) {
                                    values[k]?.add(v)
                                } else {
                                    values[k] = mutableSetOf(v)
                                }
                            }
                        }

                        val altJson = jsonObject(*values.map {
                            when {
                                it.value.all { ! it.isJsonNull } -> it.key to it.value.run {
                                    if (all { it.isJsonObject }) {
                                        val innerValues = mutableMapOf<String, Pair<MutableSet<JsonElement>, Boolean>>()
                                        forEach {
                                            it.jsonObject.forEach { k, v ->
                                                if (innerValues.contains(k)) {
                                                    innerValues[k]?.first?.add(v)
                                                } else {
                                                    innerValues[k] = mutableSetOf(v) to (size != count { it.jsonObject.contains(k) })
                                                }
                                            }
                                        }

                                        jsonObject(*innerValues.map {
                                            it.key to it.value.run {
                                                first.first().apply {
                                                    if (second) {
                                                        when {
                                                            isJsonPrimitive -> jsonPrimitive.isNullable = true
                                                            isJsonObject -> jsonObject.isNullable = true
                                                            isJsonArray -> jsonArray.isNullable = true
                                                        }
                                                    }
                                                }
                                            }
                                        }.toTypedArray())
                                    } else {
                                        first()
                                    }
                                }
                                it.value.all { it.isJsonNull } -> it.key to jsonNull
                                else -> it.key to it.value.find { ! it.isJsonNull }!!.apply {
                                    jsonPrimitive.isNullable = true
                                }
                            }
                        }.toTypedArray())

                        val parser = JsonObjectParser(altJson, typeStrict)
                        subModels.add(parser.toModelString(it.modelName))
                    }
                    append("    ${it.toPropertyString()}\n")
                }
                append("}\n\n")

                subModels.forEach {
                    append("$it\n")
                }
            }.replace("\n\n\n", "\n\n")
        }
    }
}

private val nullableCache = mutableMapOf<JsonElement, Boolean>()
private var JsonPrimitive.isNullable: Boolean
    get() = nullableCache[this] ?: false
    set(value) = nullableCache.set(this, value)
private var JsonObject.isNullable: Boolean
    get() = nullableCache[this] ?: false
    set(value) = nullableCache.set(this, value)
private var JsonArray.isNullable: Boolean
    get() = nullableCache[this] ?: false
    set(value) = nullableCache.set(this, value)
