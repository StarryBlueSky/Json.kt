package jp.nephy.jsonkt

open class JsonKtException(override val message: String): Exception()

class JsonCastException(json: JsonElement, type: Class<*>): JsonKtException("JsonKt cannot cast to ${type.canonicalName}: $json")

class JsonNullPointerException(key: String, json: JsonObject): JsonKtException("\"$key\" value is null or not found but return type does not be marked nullable: $json")

class JsonConversionException(type: Class<*>): JsonKtException("${type.canonicalName} cannot be converted to json.")

class InvalidJsonModelException(type: Class<*>): JsonKtException("${type.canonicalName} does not have (jp.nephy.jsonkt.JsonObject) constructor.")
