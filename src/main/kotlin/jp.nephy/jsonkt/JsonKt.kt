@file:Suppress("UNUSED")

package jp.nephy.jsonkt

import kotlin.reflect.KClass

object JsonKt {
    internal val serializers = mutableMapOf<KClass<*>, Serializer<*>>()
    // TODO
    private val deserializers = mutableMapOf<KClass<*>, Deserializer<*>>()

    fun <T: Any> install(sourceClass: KClass<T>, serializer: Serializer<T>) {
        serializers[sourceClass] = serializer
    }
    inline fun <reified T: Any> install(serializer: Serializer<T>) {
        install(T::class, serializer)
    }

    fun <T: Any> install(targetClass: KClass<T>, serializer: Deserializer<T>) {
        deserializers[targetClass] = serializer
    }
    inline fun <reified T: Any> install(deserializer: Deserializer<T>) {
        install(T::class, deserializer)
    }

    interface Serializer<T: Any> {
        fun encode(data: T): String
    }

    interface Deserializer<T: Any> {
        fun decode(data: String): T
    }
}
