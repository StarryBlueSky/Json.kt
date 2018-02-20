package jp.nephy.jsonkt

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonNull
import com.google.gson.JsonObject

class JsonKt {
    companion object {
        val jsonNull = JsonNull.INSTANCE!!

        fun toJsonObject(content: String) = Gson().toJsonObject(content)

        fun toPrettyString(json: JsonObject) = GsonBuilder().setPrettyPrinting().create().toJson(json)!!
        fun toPrettyString(model: JsonModel) = toPrettyString(model.json)

        inline fun <reified T: JsonModel> parse(json: JsonObject) = T::class.java.getConstructor(JsonObject::class.java).newInstance(json)!!
        inline fun <reified T: JsonModel> parse(content: String) = Gson().parse<T>(content)
    }
}

fun Gson.toJsonObject(content: String): JsonObject {
    return fromJson(content, JsonObject::class.java)
}

inline fun <reified T: JsonModel> Gson.parse(content: String): T {
    return JsonKt.parse(toJsonObject(content))
}
