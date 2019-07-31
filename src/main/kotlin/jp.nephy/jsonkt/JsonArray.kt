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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt

import kotlinx.serialization.json.*

inline fun jsonArrayOf(vararg elements: JsonValue): JsonArray {
    return elements.toJsonArray()
}

/*
 * toJsonArray
 */

/**
 * @throws JsonCastException
 */
inline fun String.toJsonArray(): JsonArray {
    return toJsonElement().cast()
}

/**
 * @throws JsonCastException
 */
inline fun JsonValueIterable.toJsonArray(): JsonArray {
    return JsonArray(map { it.asJsonElement() })
}

/**
 * @throws JsonCastException
 */
inline fun JsonValueSequence.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

/**
 * @throws JsonCastException
 */
inline fun JsonValueArray.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

/*
 * toJsonArrayOrNull
 */

inline fun String?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

inline fun JsonValueIterable?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

inline fun JsonValueSequence?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

inline fun JsonValueArray?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

/*
 * to list methods
 */

inline val JsonArray.jsonObjectList: List<JsonObject>
    get() = map { it.jsonObject }

inline val JsonArray.jsonArrayList: List<JsonArray>
    get() = map { it.jsonArray }

inline val JsonArray.jsonElementList: List<JsonElement>
    get() = toList()

inline val JsonArray.jsonPrimitiveList: List<JsonPrimitive>
    get() = map { it.primitive }

inline val JsonArray.booleanList: List<Boolean>
    get() = map { it.boolean }

inline val JsonArray.intList: List<Int>
    get() = map { it.int }

inline val JsonArray.longList: List<Long>
    get() = map { it.long }

inline val JsonArray.floatList: List<Float>
    get() = map { it.float }

inline val JsonArray.doubleList: List<Double>
    get() = map { it.double }

inline val JsonArray.stringList: List<String>
    get() = map { it.content }

/*
 * Edit
 */

@Suppress("UNCHECKED_CAST")
inline fun JsonArray.edit(block: (JsonMutableArray) -> Unit): JsonArray {
    return (toMutableList() as JsonMutableArray).apply(block).toJsonArray()
}
