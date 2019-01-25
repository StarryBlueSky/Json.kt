package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.InvalidJsonModelException
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.primaryConstructor

//expect fun <T: Any> KClass<T>.newModelInstance(json: JsonObject): T
//
//expect fun KProperty<*>.returnsNullable(): Boolean
//
//expect val KClass<*>.effectiveName: String?

fun <T: Any> KClass<T>.newModelInstance(json: JsonObject, vararg args: Any?): T {
    return runCatching {
        primaryConstructor?.call(json, *args)
    }.getOrNull() ?: throw InvalidJsonModelException(this)
}

fun KProperty<*>.returnsNullable(): Boolean {
    return returnType.isMarkedNullable
}

val KClass<*>.effectiveName: String?
    get() = qualifiedName
