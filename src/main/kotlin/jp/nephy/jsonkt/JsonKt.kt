package jp.nephy.jsonkt

import com.google.gson.*
import java.nio.file.Path

class JsonKt {
    companion object {
        val jsonNull = JsonNull.INSTANCE!!

        fun getNullSupportGsonInstance() = GsonBuilder().serializeNulls().create()!!
        fun getPrettyPrintingGsonInstance() = GsonBuilder().serializeNulls().setPrettyPrinting().create()!!

        fun toJsonObject(data: Any) = toJsonObject(toJsonString(data))
        fun toJsonObject(path: Path) = toJsonObject(path.toFile().readText())
        fun toJsonObject(content: String) = getNullSupportGsonInstance().fromJson(content, JsonObject::class.java)!!

        fun toJsonArray(data: Any) = toJsonArray(toJsonString(data))
        fun toJsonArray(path: Path) = toJsonArray(path.toFile().readText())
        fun toJsonArray(content: String) = getNullSupportGsonInstance().fromJson(content, JsonArray::class.java)!!

        fun toJsonString(data: Any) = getNullSupportGsonInstance().toJson(data)!!
        fun toJsonString(json: JsonObject) = getNullSupportGsonInstance().toJson(json)!!
        fun toJsonString(model: JsonModel) = toJsonString(model.json)

        fun toPrettyString(data: Any) = toPrettyString(toJsonObject(data))
        fun toPrettyString(json: JsonObject) = getPrettyPrintingGsonInstance().toJson(json)!!
        fun toPrettyString(model: JsonModel) = toPrettyString(model.json)

        inline fun <reified T: JsonModel> parse(json: JsonObject) = T::class.java.getConstructor(JsonObject::class.java).newInstance(json)!!
        inline fun <reified T: JsonModel> parse(path: Path): T = parse(path.toFile().readText())
        inline fun <reified T: JsonModel> parse(content: String): T = parse(toJsonObject(content))

        fun toModelString(json: JsonObject, modelName: String? = null, typeStrict: Boolean? = null) = JsonToKotlinClass(json).convert(modelName, typeStrict)
        fun toModelString(path: Path, modelName: String? = null, typeStrict: Boolean? = null) = toModelString(path.toFile().readText(), modelName, typeStrict)
        fun toModelString(content: String, modelName: String? = null, typeStrict: Boolean? = null) = JsonToKotlinClass(toJsonObject(content)).convert(modelName, typeStrict)
        fun toModelStringFromStdin(): String {
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
                    return toModelString(text, modelName, typeStrict)
                } catch (e: JsonSyntaxException) {
                    System.err.write("Invalid json format: ${e.localizedMessage}\n".toByteArray())
                    continue
                }
            }
        }
    }
}
