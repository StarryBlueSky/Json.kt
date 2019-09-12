package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.jsonObjectOf
import jp.nephy.jsonkt.parse
import jp.nephy.jsonkt.parseOrNull

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parse<T>(*parameters) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*parameters) ?: jsonObjectOf().parse(*parameters) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T?> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*parameters) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parse<T>(*parameters) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrDefaultProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*parameters) ?: jsonObjectOf().parse(*parameters) }
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrNullProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T?> {
    return jsonArrayProperty(key) { it.parseOrNull<T>(*parameters) }
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
