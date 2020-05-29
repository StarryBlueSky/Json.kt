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

package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegation.JsonModel

/*
 * parseObject
 */

inline fun <T: JsonModel> JsonElement.parseObject(block: (JsonObject) -> T): T = block(jsonObject)

inline fun <T: JsonModel> JsonObject.parseObject(block: (JsonObject) -> T): T = (this as JsonElement).parseObject(block)

inline fun <T: JsonModel> String.parseObject(block: (JsonObject) -> T): T = toJsonElement().parseObject(block)

inline fun <T: JsonModel> JsonMap.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

inline fun <T: JsonModel> JsonPairIterable.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

inline fun <T: JsonModel> JsonPairSequence.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

inline fun <T: JsonModel> JsonPairArray.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

/*
 * parseObjectOrNull
 */

inline fun <T: JsonModel> JsonElement?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

inline fun <T: JsonModel> JsonObject?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

inline fun <T: JsonModel> String?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

inline fun <T: JsonModel> JsonMap?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

inline fun <T: JsonModel> JsonPairIterable?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

inline fun <T: JsonModel> JsonPairSequence?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

inline fun <T: JsonModel> JsonPairArray?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

/*
 * parseArray
 */

inline fun <T: JsonModel> JsonElement.parseArray(block: (JsonObject) -> T): List<T> = jsonArray.map { block(it.jsonObject) }

inline fun <T: JsonModel> JsonArray.parseArray(block: (JsonObject) -> T): List<T> = (this as JsonElement).parseArray(block)

inline fun <T: JsonModel> String.parseArray(block: (JsonObject) -> T): List<T> = toJsonElement().parseArray(block)

inline fun <T: JsonModel> JsonValueIterable.parseArray(block: (JsonObject) -> T): List<T> = toJsonArray().parseArray(block)

inline fun <T: JsonModel> JsonValueSequence.parseArray(block: (JsonObject) -> T): List<T> = toJsonArray().parseArray(block)

inline fun <T: JsonModel> JsonValueArray.parseArray(block: (JsonObject) -> T): List<T> = toJsonArray().parseArray(block)

/*
 * parseArrayOrNull
 */

inline fun <T: JsonModel> JsonElement?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

inline fun <T: JsonModel> JsonArray?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

inline fun <T: JsonModel> String?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

inline fun <T: JsonModel> JsonValueIterable?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

inline fun <T: JsonModel> JsonValueSequence?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

inline fun <T: JsonModel> JsonValueArray?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}
