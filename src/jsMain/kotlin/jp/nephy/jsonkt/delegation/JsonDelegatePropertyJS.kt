package jp.nephy.jsonkt.delegation

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private object UninitializedObject

actual class JsonDelegateProperty<out T> actual constructor(internal actual val key: String?, initializer: PropertyInitializer<T>):
    ReadOnlyProperty<Any?, T> {
    private var initializer: PropertyInitializer<T>? = initializer
    private var value: Any? = UninitializedObject

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value === UninitializedObject) {
            value = initializer!!(property)
            initializer = null
        }

        return value as T
    }
}
