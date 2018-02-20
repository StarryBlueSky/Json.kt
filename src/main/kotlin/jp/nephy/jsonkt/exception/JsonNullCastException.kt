package jp.nephy.jsonkt.exception

import com.google.gson.JsonObject

class JsonNullCastException(jsonKey: String, json: JsonObject): IllegalStateException("\"$jsonKey\" of $json is null or not found. Return type must be non-null.")
