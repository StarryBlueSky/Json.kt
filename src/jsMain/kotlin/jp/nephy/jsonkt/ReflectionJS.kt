package jp.nephy.jsonkt

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

actual fun <T: Any> KClass<T>.newModelInstance(json: JsonObject): T {
    TODO("Unsupported API")
}

actual fun KProperty<*>.returnsNullable(): Boolean {
    TODO("Unsupported API")
}

actual val KClass<*>.effectiveName: String?
    get() = simpleName
