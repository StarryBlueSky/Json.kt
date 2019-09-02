/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2019 Nephy Project Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jp.nephy.jsonkt.delegation

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

private object UninitializedObject

@Suppress("UNCHECKED_CAST")
open class CachingReadOnlyProperty<in R, out T> internal constructor(initializer: PropertyInitializer<T>): ReadOnlyProperty<R, T> {
    private var initializer: PropertyInitializer<T>? = initializer
    private var value: Any? = UninitializedObject

    override fun getValue(thisRef: R, property: KProperty<*>): T {
        val v0 = value
        if (v0 != UninitializedObject) {
            return value as T
        }

        return synchronized(this) {
            val v1 = value
            if (v1 != UninitializedObject) {
                value as T
            } else {
                val v = initializer!!(property)
                initializer = null
                value = v
                v
            }
        }
    }
}


actual class JsonDelegateProperty<out T> actual constructor(internal actual val key: String?, initializer: PropertyInitializer<T>)
    : ReadOnlyProperty<Any?, T> by CachingReadOnlyProperty<Any?, T>(initializer)
