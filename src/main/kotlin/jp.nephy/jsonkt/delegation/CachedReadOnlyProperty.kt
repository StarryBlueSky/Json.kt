package jp.nephy.jsonkt.delegation

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private object UninitializedObject: Any()

@Suppress("UNCHECKED_CAST", "LocalVariableName")
class CachedReadOnlyProperty<R, out T: Any?>(initializer: (R, KProperty<*>) -> T): ReadOnlyProperty<R, T> {
    private var initializer: ((R, KProperty<*>) -> T)? = initializer
    @Volatile private var _value: Any? = UninitializedObject

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        val _v0 = _value
        if (_v0 !== UninitializedObject) {
            return _value as T
        }

        return synchronized(this) {
            val _v1 = _value
            if (_v1 !== UninitializedObject) {
                _value as T
            } else {
                val _v = initializer!!(thisRef, property)
                initializer = null
                _value = _v
                _v
            }
        }
    }
}
