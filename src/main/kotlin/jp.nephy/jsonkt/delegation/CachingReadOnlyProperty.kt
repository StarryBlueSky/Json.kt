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

@Suppress("UNCHECKED_CAST", "LocalVariableName")
class CachingReadOnlyProperty<R, out T: Any?>(initializer: (R, KProperty<*>) -> T): ReadOnlyProperty<R, T> {
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
