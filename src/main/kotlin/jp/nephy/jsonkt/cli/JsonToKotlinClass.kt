@file:Suppress("UNUSED")
package jp.nephy.jsonkt.cli

import com.google.gson.JsonSyntaxException
import jp.nephy.jsonkt.*
import jp.nephy.jsonkt.cli.property.*
import java.io.File
import java.nio.file.Path

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

/*
 * toModelString
 */

fun JsonObject.toModelString(modelName: String? = null, typeStrict: Boolean? = null): String {
    return JsonToKotlinClass(this).convert(modelName, typeStrict)
}

fun String.toModelString(modelName: String? = null, typeStrict: Boolean? = null, builder: GsonBuilder = {}): String {
    return JsonToKotlinClass(toJsonObject(builder)).convert(modelName, typeStrict)
}

fun File.toModelString(modelName: String? = null, typeStrict: Boolean? = null, builder: GsonBuilder = {}): String {
    return toJsonObject(builder).toModelString(modelName, typeStrict)
}

fun Path.toModelString(modelName: String? = null, typeStrict: Boolean? = null, builder: GsonBuilder = {}): String {
    return toFile().toModelString(modelName, typeStrict, builder)
}

class JsonToKotlinClass(private val json: JsonObject) {
    fun convert(targetModelName: String?, typeStrict: Boolean?): String {
        val modelName = targetModelName.run {
            if (this == null || isBlank()) {
                "Model"
            } else {
                this
            }
        }

        val result = mutableListOf<String>()

        val parser = JsonObjectParser(json, typeStrict ?: false)
        result.add(parser.toModelString(modelName))

        if (result.isNotEmpty()) {
            result.add(0, "import jp.nephy.jsonkt.*")
            result.add(1, "import jp.nephy.jsonkt.delegation.*\n")
        }

        return result.joinToString("\n").trimEnd()
    }

    private class JsonObjectParser(private val json: JsonObject, private val typeStrict: Boolean) {
        fun toModelString(name: String): String {
            return buildString {
                val subModels = mutableListOf<String>()
                append("class $name(override val json: JsonObject): JsonModel {\n")
                json.map {
                    when {
                        it.value.isJsonObject() -> when {
                            it.value.toString() == "{}" -> JsonObjectProperty(it)
                            it.value.immutableJsonObject.isNullable -> JsonNullableModelProperty(it)
                            else -> JsonModelProperty(it)
                        }
                        it.value.isJsonArray() -> when {
                            it.value.immutableJsonArray.isEmpty() || it.value.immutableJsonArray.all { element -> element.isJsonArray() } -> JsonArrayProperty(it)
                            it.value.immutableJsonArray.all { element -> element.isJsonObject() } -> when {
                                it.value.immutableJsonArray.isNullable -> JsonNullableModelListProperty(it)
                                else -> JsonModelListProperty(it)
                            }
                            it.value.immutableJsonArray.all { element -> element.isJsonPrimitive() } -> JsonPrimitiveListProperty(it, typeStrict)
                            else -> throw IllegalStateException("Not all elements in array are same type. These must be JsonObject, JsonArray or JsonPrimitive. (${it.key}: ${it.value})")
                        }
                        it.value.isJsonNull() -> JsonNullProperty(it)
                        it.value.isJsonPrimitive() -> when {
                            it.value.jsonPrimitive.isNullable -> JsonNullablePrimitiveProperty(it)
                            else -> JsonPrimitiveProperty(it, typeStrict)
                        }
                        else -> throw IllegalArgumentException("Unknown type: ${it.value}")
                    }
                }.sortedBy { it.key }.forEach {
                    if (it is JsonModelProperty) {
                        val parser = JsonObjectParser(it.element.immutableJsonObject, typeStrict)
                        subModels.add(parser.toModelString(it.modelName))
                    } else if (it is JsonModelListProperty) {
                        val values = mutableMapOf<String, MutableSet<JsonElement>>()
                        it.element.immutableJsonArray.forEach { element ->
                            element.immutableJsonObject.forEach { k, v ->
                                if (values.contains(k)) {
                                    values[k]?.add(v)
                                } else {
                                    values[k] = mutableSetOf(v)
                                }
                            }
                        }

                        val altJson = immutableJsonObjectOf(*values.map { (k, v) ->
                            when {
                                v.all { element -> !element.isJsonNull() } -> k to v.run {
                                    if (all { element -> element.isJsonObject() }) {
                                        val innerValues = mutableMapOf<String, Pair<MutableSet<JsonElement>, Boolean>>()
                                        forEach { element ->
                                            element.immutableJsonObject.forEach { k, v ->
                                                if (innerValues.contains(k)) {
                                                    innerValues[k]?.first?.add(v)
                                                } else {
                                                    innerValues[k] = mutableSetOf(v) to (size != count { element -> element.immutableJsonObject.contains(k) })
                                                }
                                            }
                                        }

                                        immutableJsonObjectOf(*innerValues.map { (k, v) ->
                                            k to v.run {
                                                first.first().apply {
                                                    if (second) {
                                                        when {
                                                            isJsonPrimitive() -> jsonPrimitive.isNullable = true
                                                            isJsonObject() -> immutableJsonObject.isNullable = true
                                                            isJsonArray() -> immutableJsonArray.isNullable = true
                                                        }
                                                    }
                                                }
                                            }
                                        }.toTypedArray())
                                    } else {
                                        first()
                                    }
                                }
                                v.all { element -> element.isJsonNull() } -> k to jsonNull
                                else -> k to v.find { element -> !element.isJsonNull() }!!.apply {
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

private val nullablePrimitiveCache = mutableMapOf<JsonPrimitive, Boolean>()
private var JsonPrimitive.isNullable: Boolean
    get() = nullablePrimitiveCache[this] ?: false
    set(value) = nullablePrimitiveCache.set(this, value)

private val nullableObjectCache = mutableMapOf<JsonObject, Boolean>()
private var ImmutableJsonObject.isNullable: Boolean
    get() = nullableObjectCache[this] ?: false
    set(value) = nullableObjectCache.set(this, value)

private val nullableArrayCache = mutableMapOf<JsonArray, Boolean>()
private var ImmutableJsonArray.isNullable: Boolean
    get() = nullableArrayCache[this] ?: false
    set(value) = nullableArrayCache.set(this, value)
