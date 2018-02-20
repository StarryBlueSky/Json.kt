package jp.nephy.jsonkt.exception

import com.google.gson.JsonElement

class JsonTypeCastException(element: JsonElement?, type: String): IllegalStateException("$element cannot be converted to $type.")
