package jp.nephy.jsonkt

open class JsonKtException(override val message: String): Exception()

class JsonCastException(json: String, type: Class<*>): JsonKtException("JsonKt cannot cast to ${type.canonicalName}: $json") {
    constructor(json: JsonElement, type: Class<*>): this(json.toString(), type)
}

class JsonNullPointerException(key: String, json: ImmutableJsonObject): JsonKtException("json[\"$key\"] is null or not found but return type is non-null: $json")

class JsonConversionException(type: Class<*>): JsonKtException("${type.canonicalName} cannot be converted to json.")

class InvalidJsonModelException(type: Class<*>): JsonKtException("${type.canonicalName} does not have (jp.nephy.jsonkt.JsonObject) constructor.")
