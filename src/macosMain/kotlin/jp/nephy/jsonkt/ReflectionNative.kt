package jp.nephy.jsonkt

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

actual fun <T: Any> KClass<T>.newModelInstance(json: JsonObject): T {
    TODO("Unsupported API")
}

actual fun KProperty<*>.returnsNullable(): Boolean {
    return returnType.isMarkedNullable
}

actual val KClass<*>.effectiveName: String?
    get() = qualifiedName
