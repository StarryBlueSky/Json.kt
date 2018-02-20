package jp.nephy.jsonkt.exception

class InvalidJsonModelException(name: String): IllegalStateException("Class \"$name\" does not have (..., com.google.gson.JsonObject) constructor.")
