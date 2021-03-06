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

@file:Suppress("UNUSED")

package blue.starry.jsonkt

import mu.KLogger
import mu.KotlinLogging
import kotlin.reflect.KClass

object JsonKt {
    val logger: KLogger = KotlinLogging.logger("JsonKt")
    internal val serializers = mutableMapOf<KClass<*>, Serializer<*>>()

    fun <T: Any> install(sourceClass: KClass<T>, serializer: Serializer<T>) {
        serializers[sourceClass] = serializer
    }
    inline fun <reified T: Any> install(serializer: Serializer<T>) {
        install(T::class, serializer)
    }

    fun <T: Any> uninstall(sourceClass: KClass<T>) {
        serializers.remove(sourceClass)
    }
    inline fun <reified T: Any> uninstall() {
        uninstall(T::class)
    }

    interface Serializer<T: Any> {
        fun encode(data: T): String
    }
}
