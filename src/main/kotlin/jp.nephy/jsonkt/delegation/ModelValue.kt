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

@file:Suppress("UNUSED")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlinx.serialization.json.*

fun JsonModel.booleanValueOrNull(key: String): Boolean? {
    return json.getOrNull(key)?.booleanOrNull
}

fun JsonModel.booleanValue(key: String, default: Boolean): Boolean {
    return booleanValueOrNull(key) ?: default
}

fun JsonModel.booleanValue(key: String, default: () -> Boolean): Boolean {
    return booleanValueOrNull(key) ?: default()
}

fun JsonModel.intValueOrNull(key: String): Int? {
    return json.getOrNull(key)?.intOrNull
}

fun JsonModel.intValue(key: String, default: Int): Int {
    return intValueOrNull(key) ?: default
}

fun JsonModel.intValue(key: String, default: () -> Int): Int {
    return intValueOrNull(key) ?: default()
}

fun JsonModel.longValueOrNull(key: String): Long? {
    return json.getOrNull(key)?.longOrNull
}

fun JsonModel.longValue(key: String, default: Long): Long {
    return longValueOrNull(key) ?: default
}

fun JsonModel.longValue(key: String, default: () -> Long): Long {
    return longValueOrNull(key) ?: default()
}

fun JsonModel.floatValueOrNull(key: String): Float? {
    return json.getOrNull(key)?.floatOrNull
}

fun JsonModel.floatValue(key: String, default: Float): Float {
    return floatValueOrNull(key) ?: default
}

fun JsonModel.floatValue(key: String, default: () -> Float): Float {
    return floatValueOrNull(key) ?: default()
}

fun JsonModel.doubleValueOrNull(key: String): Double? {
    return json.getOrNull(key)?.doubleOrNull
}

fun JsonModel.doubleValue(key: String, default: Double): Double {
    return doubleValueOrNull(key) ?: default
}

fun JsonModel.doubleValue(key: String, default: () -> Double): Double {
    return doubleValueOrNull(key) ?: default()
}

fun JsonModel.stringValueOrNull(key: String): String? {
    return json.getOrNull(key)?.stringOrNull
}

fun JsonModel.stringValue(key: String, default: String): String {
    return stringValueOrNull(key) ?: default
}

fun JsonModel.stringValue(key: String, default: () -> String): String {
    return stringValueOrNull(key) ?: default()
}

inline fun <reified T: JsonModel> JsonModel.modelValue(key: String): T {
    return json[key].parse()
}

inline fun <reified T: JsonModel> JsonModel.modelValueOrNull(key: String): T? {
    return json.getObjectOrNull(key).parseOrNull()
}

inline fun <reified T: JsonModel> JsonModel.modelListValue(key: String): List<T> {
    return json[key].parseList()
}

inline fun <reified T: JsonModel> JsonModel.modelListValueOrNull(key: String): List<T>? {
    return json.getArrayOrNull(key).parseListOrNull()
}

inline fun <reified T: JsonModel> JsonModel.modelListValueOrEmpty(key: String): List<T> {
    return json.getArrayOrNull(key).parseListOrEmpty()
}
