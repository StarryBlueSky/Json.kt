package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.throwUnsupportedException

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T?> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListProperty(key: String?, vararg parameters: Any?) : JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrDefaultProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrNullProperty(key: String?, vararg parameters: Any?): JsonArrayProperty<T?> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrDefaultProperty(key: String?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonObject?.jsonModelListOrNullProperty(key: String?): JsonArrayProperty<T?> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListProperty(key: String?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrDefaultProperty(key: String?): JsonArrayProperty<T> {
    throwUnsupportedException()
}

@PublishedApi
internal actual inline fun <reified T: JsonModel> JsonModel?.jsonModelListOrNullProperty(key: String?): JsonArrayProperty<T?> {
    throwUnsupportedException()
}
