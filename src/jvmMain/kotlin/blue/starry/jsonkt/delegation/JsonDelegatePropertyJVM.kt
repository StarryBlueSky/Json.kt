/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
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

@file:Suppress("unused")

package blue.starry.jsonkt.delegation

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class CachingReadOnlyProperty<in R, out T> internal constructor(
    private val initializer: PropertyInitializer<T>
): ReadOnlyProperty<R, T> {
    @Volatile private var value: Any? = UninitializedObject

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        val v1 = value
        if (v1 !== UninitializedObject) {
            return v1 as T
        }

        return synchronized(this) {
            val v2 = value
            if (v2 === UninitializedObject) {
                val v3 = initializer(property)
                value = v3
                v3
            } else {
                v2 as T
            }
        }
    }
}

actual class JsonDelegateProperty<out T> actual constructor(
    internal actual val key: String?,
    initializer: PropertyInitializer<T>
): ReadOnlyProperty<Any?, T> by CachingReadOnlyProperty<Any?, T>(initializer)
