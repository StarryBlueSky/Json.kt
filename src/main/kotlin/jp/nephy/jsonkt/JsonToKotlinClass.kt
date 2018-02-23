package jp.nephy.jsonkt

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import jp.nephy.jsonkt.property.*

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
                            else -> JsonModelProperty(it)
                        }
                        it.value.isJsonArray -> when {
                            it.value.jsonArray.isEmpty() || it.value.jsonArray.all { it.isJsonArray } -> JsonArrayProperty(it)
                            it.value.jsonArray.all { it.isJsonObject } -> JsonModelListProperty(it)
                            it.value.jsonArray.all { it.isJsonPrimitive } -> JsonPrimitiveListProperty(it, typeStrict)
                            else -> throw IllegalStateException("Not all elements in array are same type. These must be JsonObject, JsonArray or JsonPrimitive. (${it.key}: ${it.value})")
                        }
                        it.value.isJsonNull -> JsonNullProperty(it)
                        it.value.isJsonPrimitive -> when {
                            it.value.jsonPrimitive.isNullablePrimitive -> JsonNullablePrimitiveProperty(it)
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
                            it.jsonObject.forEach {
                                if (values.contains(it.key)) {
                                    values[it.key]?.add(it.value)
                                } else {
                                    values[it.key] = mutableSetOf(it.value)
                                }
                            }
                        }

                        val altJson = jsonObject(*values.map {
                            when {
                                it.value.all { ! it.isJsonNull } -> it.key to it.value.run {
                                    if (all { it.isJsonObject }) {
                                        val innerValues = mutableMapOf<String, Pair<MutableSet<JsonElement>, Boolean>>()
                                        forEach {
                                            it.jsonObject.forEach {
                                                if (innerValues.contains(it.key)) {
                                                    innerValues[it.key]?.first?.add(it.value)
                                                } else {
                                                    val key = it.key
                                                    innerValues[it.key] = mutableSetOf(it.value) to (size != count { it.jsonObject.contains(key) })
                                                }
                                            }
                                        }

                                        jsonObject(*innerValues.map {
                                            it.key to it.value.run {
                                                first.first().apply {
                                                    if (second) {
                                                        jsonPrimitive.isNullablePrimitive = true
                                                    }
                                                }
                                            }
                                        }.toTypedArray())
                                    } else {
                                        first()
                                    }
                                }
                                it.value.all { it.isJsonNull } -> it.key to JsonKt.jsonNull
                                else -> it.key to it.value.find { ! it.isJsonNull }!!.apply {
                                    jsonPrimitive.isNullablePrimitive = true
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

private val nullableCache = mutableMapOf<JsonPrimitive, Boolean>()
private var JsonPrimitive.isNullablePrimitive: Boolean
    get() = nullableCache[this] ?: false
    set(value) = nullableCache.set(this, value)
