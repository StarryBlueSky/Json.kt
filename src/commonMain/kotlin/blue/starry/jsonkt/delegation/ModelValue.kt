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

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.JsonNullPointerException
import blue.starry.jsonkt.primitiveOrNull
import blue.starry.jsonkt.stringOrNull
import kotlinx.serialization.json.*

fun JsonModel.booleanValueOrNull(key: String): Boolean? {
    return json[key]?.primitiveOrNull?.booleanOrNull
}

fun JsonModel.booleanValue(key: String): Boolean {
    return booleanValueOrNull(key) ?: throw JsonNullPointerException(key, json)
}

fun JsonModel.booleanValue(key: String, default: Boolean): Boolean {
    return booleanValueOrNull(key) ?: default
}

fun JsonModel.booleanValue(key: String, default: () -> Boolean): Boolean {
    return booleanValueOrNull(key) ?: default()
}

fun JsonModel.intValueOrNull(key: String): Int? {
    return json[key]?.primitiveOrNull?.intOrNull
}

fun JsonModel.intValue(key: String): Int {
    return intValueOrNull(key) ?: throw JsonNullPointerException(key, json)
}

fun JsonModel.intValue(key: String, default: Int): Int {
    return intValueOrNull(key) ?: default
}

fun JsonModel.intValue(key: String, default: () -> Int): Int {
    return intValueOrNull(key) ?: default()
}

fun JsonModel.longValueOrNull(key: String): Long? {
    return json[key]?.primitiveOrNull?.longOrNull
}

fun JsonModel.longValue(key: String): Long {
    return longValueOrNull(key) ?: throw JsonNullPointerException(key, json)
}

fun JsonModel.longValue(key: String, default: Long): Long {
    return longValueOrNull(key) ?: default
}

fun JsonModel.longValue(key: String, default: () -> Long): Long {
    return longValueOrNull(key) ?: default()
}

fun JsonModel.floatValueOrNull(key: String): Float? {
    return json[key]?.primitiveOrNull?.floatOrNull
}

fun JsonModel.floatValue(key: String): Float {
    return floatValueOrNull(key) ?: throw JsonNullPointerException(key, json)
}

fun JsonModel.floatValue(key: String, default: Float): Float {
    return floatValueOrNull(key) ?: default
}

fun JsonModel.floatValue(key: String, default: () -> Float): Float {
    return floatValueOrNull(key) ?: default()
}

fun JsonModel.doubleValueOrNull(key: String): Double? {
    return json[key]?.primitiveOrNull?.doubleOrNull
}

fun JsonModel.doubleValue(key: String): Double {
    return doubleValueOrNull(key) ?: throw JsonNullPointerException(key, json)
}

fun JsonModel.doubleValue(key: String, default: Double): Double {
    return doubleValueOrNull(key) ?: default
}

fun JsonModel.doubleValue(key: String, default: () -> Double): Double {
    return doubleValueOrNull(key) ?: default()
}

fun JsonModel.stringValueOrNull(key: String): String? {
    return json[key]?.stringOrNull
}

fun JsonModel.stringValue(key: String): String {
    return stringValueOrNull(key) ?: throw JsonNullPointerException(key, json)
}

fun JsonModel.stringValue(key: String, default: String): String {
    return stringValueOrNull(key) ?: default
}

fun JsonModel.stringValue(key: String, default: () -> String): String {
    return stringValueOrNull(key) ?: default()
}

