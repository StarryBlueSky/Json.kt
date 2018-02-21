package jp.nephy.jsonkt

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonNull
import com.google.gson.JsonObject

class JsonKt {
    companion object {
        val jsonNull = JsonNull.INSTANCE!!

        fun toJsonObject(data: Any) = toJsonObject(toJsonString(data))
        fun toJsonObject(content: String) = Gson().fromJson(content, JsonObject::class.java)!!

        fun toJsonString(data: Any) = Gson().toJson(data)!!
        fun toJsonString(json: JsonObject) = Gson().toJson(json)!!
        fun toJsonString(model: JsonModel) = toJsonString(model.json)

        fun toPrettyString(data: Any) = toPrettyString(toJsonObject(data))
        fun toPrettyString(json: JsonObject) = GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(json)!!
        fun toPrettyString(model: JsonModel) = toPrettyString(model.json)

        inline fun <reified T: JsonModel> parse(json: JsonObject) = T::class.java.getConstructor(JsonObject::class.java).newInstance(json)!!
        inline fun <reified T: JsonModel> parse(content: String): T = parse(toJsonObject(content))
    }
}
