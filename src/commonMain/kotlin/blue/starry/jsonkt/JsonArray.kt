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

import kotlinx.serialization.json.*

fun jsonArrayOf(vararg elements: JsonValue): JsonArray {
    return elements.toJsonArray()
}

/*
 * toJsonArray
 */

/**
 * @throws JsonCastException
 */
fun String.toJsonArray(): JsonArray {
    return toJsonElement().cast()
}

/**
 * @throws JsonCastException
 */
fun JsonValueIterable.toJsonArray(): JsonArray {
    return JsonArray(map { it.asJsonElement() })
}

/**
 * @throws JsonCastException
 */
fun JsonValueSequence.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

/**
 * @throws JsonCastException
 */
fun JsonValueArray.toJsonArray(): JsonArray {
    return toList().toJsonArray()
}

/*
 * toJsonArrayOrNull
 */

fun String?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

fun JsonValueIterable?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

fun JsonValueSequence?.toJsonArrayOrNull(): JsonArray? {
    return runSafely {
        toJsonArray()
    }
}

fun JsonValueArray?.toJsonArrayOrNull(): JsonArray? {
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

inline val JsonArray.jsonPrimitiveList: List<JsonPrimitive>
    get() = map { it.primitive }

inline val JsonArray.jsonLiteralList: List<JsonLiteral>
    get() = map { it.literal }

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

inline val JsonArray.charList: List<Char>
    get() = map { it.char }

inline val JsonArray.stringList: List<String>
    get() = map { it.string }

inline val JsonArray.nullableJsonObjectList: List<JsonObject?>
    get() = map { it.jsonObjectOrNull }

inline val JsonArray.nullableJsonArrayList: List<JsonArray?>
    get() = map { it.jsonArrayOrNull }

inline val JsonArray.nullableJsonPrimitiveList: List<JsonPrimitive?>
    get() = map { it.primitiveOrNull }

inline val JsonArray.nullableJsonLiteralList: List<JsonLiteral?>
    get() = map { it.literalOrNull }

inline val JsonArray.nullableBooleanList: List<Boolean?>
    get() = map { it.booleanOrNull }

inline val JsonArray.nullableIntList: List<Int?>
    get() = map { it.intOrNull }

inline val JsonArray.nullableLongList: List<Long?>
    get() = map { it.longOrNull }

inline val JsonArray.nullableFloatList: List<Float?>
    get() = map { it.floatOrNull }

inline val JsonArray.nullableDoubleList: List<Double?>
    get() = map { it.doubleOrNull }

inline val JsonArray.nullableCharList: List<Char?>
    get() = map { it.charOrNull }

inline val JsonArray.nullableStringList: List<String?>
    get() = map { it.stringOrNull }

/*
 * Copy
 */

@Suppress("UNCHECKED_CAST")
inline fun JsonArray.copy(block: (JsonMutableArray) -> Unit): JsonArray {
    return (toMutableList() as JsonMutableArray).apply(block).toJsonArray()
}
