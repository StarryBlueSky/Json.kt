package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.InvalidJsonModelException
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createType

actual fun <T: Any> KClass<T>.newModelInstance(json: JsonObject): T {
    val constructor = constructors.find {
        it.parameters.size == 1 && it.parameters.first().type == JsonObject::class.createType()
    } ?: throw InvalidJsonModelException(this)

    return constructor.call(json)
}

actual fun KProperty<*>.returnsNullable(): Boolean {
    return returnType.isMarkedNullable
}

actual val KClass<*>.effectiveName: String?
    get() = qualifiedName
