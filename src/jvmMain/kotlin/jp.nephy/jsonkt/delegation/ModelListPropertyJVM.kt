package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.jsonObjectOf
import jp.nephy.jsonkt.parse
import jp.nephy.jsonkt.parseOrNull

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String?, vararg args: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parse<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String?, vararg args: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*args) ?: jsonObjectOf().parse(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String?, vararg args: Any?): JsonArrayProperty<T?> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListProperty(key: String?, vararg args: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parse<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrDefaultProperty(key: String?, vararg args: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*args) ?: jsonObjectOf().parse(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrNullProperty(key: String?, vararg args: Any?): JsonArrayProperty<T?> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*args) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parse<T>() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parseOrNull<T>() ?: jsonObjectOf().parse() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String?): JsonArrayProperty<T?> {
    return jsonArrayProperty(key) { it.parseOrNull<T>() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListProperty(key: String?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parse<T>() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrDefaultProperty(key: String?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parseOrNull<T>() ?: jsonObjectOf().parse() }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrNullProperty(key: String?): JsonArrayProperty<T?> {
    return jsonArrayProperty(key) { it.parseOrNull<T>() }
}
