package jp.nephy.jsonkt

import kotlinx.serialization.json.JsonObject

open class JsonKtException internal constructor(override val message: String): Exception()

class JsonCastException(json: String, type: String): JsonKtException("JsonKt cannot cast to $type: $json")

class JsonNullPointerException(key: String, json: JsonObject): JsonKtException("json[\"$key\"] is null or is not present but return type is non-null: $json")

class JsonConversionException(type: String): JsonKtException("$type cannot be converted to json. Please install JsonKt.Serializer to handle.")

class InvalidJsonModelException(type: String): JsonKtException("$type does not have (jp.nephy.jsonkt.JsonObject) constructor.")
