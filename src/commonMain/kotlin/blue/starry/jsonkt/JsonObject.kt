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

fun jsonObjectOf(vararg pairs: JsonPair): JsonObject {
    return pairs.toJsonObject()
}

/*
 * toJsonObject
 */

/**
 * @throws JsonCastException
 */
fun String.toJsonObject(): JsonObject {
    return toJsonElement().cast()
}

/**
 * @throws JsonCastException
 */
fun JsonMap.toJsonObject(): JsonObject {
    return JsonObject(map { it.key to it.value.asJsonElement() }.toMap())
}

/**
 * @throws JsonCastException
 */
fun JsonPairIterable.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

/**
 * @throws JsonCastException
 */
fun JsonPairSequence.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

/**
 * @throws JsonCastException
 */
fun JsonPairArray.toJsonObject(): JsonObject {
    return toMap().toJsonObject()
}

/*
 * toJsonObjectOrNull
 */

fun String?.toJsonObjectOrNull(): JsonObject? {
    return runSafely {
        toJsonObject()
    }
}

fun JsonMap?.toJsonObjectOrNull(): JsonObject? {
    return runSafely {
        toJsonObject()
    }
}

fun JsonPairIterable?.toJsonObjectOrNull(): JsonObject? {
    return runSafely {
        toJsonObject()
    }
}

fun JsonPairSequence?.toJsonObjectOrNull(): JsonObject? {
    return runSafely {
        toJsonObject()
    }
}

fun JsonPairArray?.toJsonObjectOrNull(): JsonObject? {
    return runSafely {
        toJsonObject()
    }
}

/*
 * Copy
 */

@Suppress("UNCHECKED_CAST")
inline fun JsonObject.copy(block: (JsonMutableMap) -> Unit): JsonObject {
    return (toMutableMap() as JsonMutableMap).apply(block).toJsonObject()
}
