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

package jp.nephy.jsonkt.cli

import jp.nephy.jsonkt.*
import jp.nephy.jsonkt.cli.property.*
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.set

fun generateModelClass(): String {
    print("Model name? (Optional): ")
    val modelName = readLine().orEmpty()
    print("Use type strict mode? (Y/n): ")
    val printComments = readLine().orEmpty().toLowerCase() == "y"
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
            return text.toModelString(modelName, printComments)
        } catch (e: Throwable) {
            System.err.write("Invalid json format: ${e.localizedMessage}\n".toByteArray())
            continue
        }
    }
}

/*
 * toModelString
 */

fun JsonObject.toModelString(modelName: String? = null, printComments: Boolean? = null): String {
    return JsonToKotlinClass(this).convert(modelName, printComments)
}

fun String.toModelString(modelName: String? = null, printComments: Boolean? = null): String {
    return JsonToKotlinClass(toJsonObject()).convert(modelName, printComments)
}

@Suppress("LiftReturnOrAssignment")
class JsonToKotlinClass internal constructor(private val json: JsonObject) {
    fun convert(targetModelName: String?, printComments: Boolean?): String {
        return buildString {
            appendln("import jp.nephy.jsonkt.*")
            appendln("import jp.nephy.jsonkt.delegation.*\n")

            with(JsonObjectParser(json)) {
                val modelName = targetModelName.orEmpty().ifBlank { "Model" }
                append(toModelString(modelName, printComments ?: true))
            }

            nullablePrimitiveCache.clear()
            nullableObjectCache.clear()
            nullableArrayCache.clear()
        }.trimEnd()
    }

    private class JsonObjectParser(private val json: JsonObject) {
        fun toModelString(name: String, printComments: Boolean): String {
            return buildString {
                val subModels = mutableListOf<String>()
                append("data class $name(override val json: JsonObject): JsonModel {\n")
                json.map { pair ->
                    val (key, value) = pair
                    when (value) {
                        is JsonObject -> when {
                            value.toString() == "{}" -> JsonObjectProperty(pair, printComments)
                            value.isNullable -> JsonNullableModelProperty(pair, printComments)
                            else -> JsonModelProperty(pair, printComments)
                        }
                        is JsonArray -> when {
                            value.isEmpty() || value.all { element -> element is JsonArray } -> JsonArrayProperty(pair, printComments)
                            value.all { element -> element is JsonObject } -> when {
                                value.isNullable -> JsonNullableModelListProperty(pair, printComments)
                                else -> JsonModelListProperty(pair, printComments)
                            }
                            value.all { element -> element is JsonPrimitive } -> JsonPrimitiveListProperty(pair, printComments)
                            else -> throw IllegalStateException("Not all elements in array are same type. These must be JsonObject, JsonArray or JsonPrimitive. ($key: $value)")
                        }
                        is JsonNull -> JsonNullProperty(pair, printComments)
                        is JsonPrimitive -> when {
                            value.isNullable -> JsonNullablePrimitiveProperty(pair, printComments)
                            else -> JsonPrimitiveProperty(pair, printComments)
                        }
                        else -> throw IllegalArgumentException("Unknown type: $value")
                    }
                }.sortedBy { it.key }.forEach {
                    if (it is JsonModelProperty) {
                        val parser = JsonObjectParser(it.element.jsonObject)
                        subModels.add(parser.toModelString(it.modelName, printComments))
                    } else if (it is JsonModelListProperty) {
                        val values = mutableMapOf<String, MutableSet<JsonElement>>()
                        it.element.jsonArray.forEach { element ->
                            element.jsonObject.forEach { k, v ->
                                if (k in values) {
                                    values[k]?.add(v)
                                } else {
                                    values[k] = mutableSetOf(v)
                                }
                            }
                        }

                        val altJson = jsonObjectOf(*values.map { (k, v) ->
                            when {
                                v.all { element -> !element.isNull } -> k to v.run {
                                    if (all { element -> element is JsonObject }) {
                                        val innerValues = mutableMapOf<String, Pair<MutableSet<JsonElement>, Boolean>>()
                                        forEach { element ->
                                            element.jsonObject.forEach { k, v ->
                                                if (k in innerValues) {
                                                    innerValues[k]?.first?.add(v)
                                                } else {
                                                    innerValues[k] = mutableSetOf(v) to (size != count { element -> k in element.jsonObject })
                                                }
                                            }
                                        }

                                        jsonObjectOf(*innerValues.map { (k, v) ->
                                            k to v.run {
                                                first.first().apply {
                                                    if (second) {
                                                        @Suppress("LiftReturnOrAssignment") when (this) {
                                                            is JsonPrimitive -> isNullable = true
                                                            is JsonObject -> isNullable = true
                                                            is JsonArray -> isNullable = true
                                                        }
                                                    }
                                                }
                                            }
                                        }.toTypedArray())
                                    } else {
                                        first()
                                    }
                                }
                                v.all { element -> element.isNull } -> k to JsonNull
                                else -> k to v.find { element -> !element.isNull }!!.apply {
                                    primitive.isNullable = true
                                }
                            }
                        }.toTypedArray())

                        val parser = JsonObjectParser(altJson)
                        subModels.add(parser.toModelString(it.modelName, printComments))
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

private val nullablePrimitiveCache = mutableMapOf<JsonPrimitive, Boolean>()
private var JsonPrimitive.isNullable: Boolean
    get() = nullablePrimitiveCache[this] ?: false
    set(value) = nullablePrimitiveCache.set(this, value)

private val nullableObjectCache = mutableMapOf<JsonObject, Boolean>()
private var JsonObject.isNullable: Boolean
    get() = nullableObjectCache[this] ?: false
    set(value) = nullableObjectCache.set(this, value)

private val nullableArrayCache = mutableMapOf<JsonArray, Boolean>()
private var JsonArray.isNullable: Boolean
    get() = nullableArrayCache[this] ?: false
    set(value) = nullableArrayCache.set(this, value)
