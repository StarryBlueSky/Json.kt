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

import blue.starry.jsonkt.delegation.JsonModel
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

/*
 * parseObject
 */

fun <T: JsonModel> JsonElement.parseObject(block: (JsonObject) -> T): T = block(jsonObject)

fun <T: JsonModel> JsonObject.parseObject(block: (JsonObject) -> T): T = (this as JsonElement).parseObject(block)

fun <T: JsonModel> String.parseObject(block: (JsonObject) -> T): T = toJsonElement().parseObject(block)

fun <T: JsonModel> JsonMap.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

fun <T: JsonModel> JsonPairIterable.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

fun <T: JsonModel> JsonPairSequence.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

fun <T: JsonModel> JsonPairArray.parseObject(block: (JsonObject) -> T): T = toJsonObject().parseObject(block)

/*
 * parseObjectOrNull
 */

fun <T: JsonModel> JsonElement?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

fun <T: JsonModel> JsonObject?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

fun <T: JsonModel> String?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

fun <T: JsonModel> JsonMap?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

fun <T: JsonModel> JsonPairIterable?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

fun <T: JsonModel> JsonPairSequence?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

fun <T: JsonModel> JsonPairArray?.parseObjectOrNull(block: (JsonObject) -> T): T? = runSafely {
    parseObject(block)
}

/*
 * parseArray
 */

fun <T: JsonModel> JsonElement.parseArray(block: (JsonObject) -> T): List<T> = jsonArray.map { block(it.jsonObject) }

fun <T: JsonModel> JsonArray.parseArray(block: (JsonObject) -> T): List<T> = (this as JsonElement).parseArray(block)

fun <T: JsonModel> String.parseArray(block: (JsonObject) -> T): List<T> = toJsonElement().parseArray(block)

fun <T: JsonModel> JsonValueIterable.parseArray(block: (JsonObject) -> T): List<T> = toJsonArray().parseArray(block)

fun <T: JsonModel> JsonValueSequence.parseArray(block: (JsonObject) -> T): List<T> = toJsonArray().parseArray(block)

fun <T: JsonModel> JsonValueArray.parseArray(block: (JsonObject) -> T): List<T> = toJsonArray().parseArray(block)

/*
 * parseArrayOrNull
 */

fun <T: JsonModel> JsonElement?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

fun <T: JsonModel> JsonArray?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

fun <T: JsonModel> String?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

fun <T: JsonModel> JsonValueIterable?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

fun <T: JsonModel> JsonValueSequence?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}

fun <T: JsonModel> JsonValueArray?.parseArrayOrNull(block: (JsonObject) -> T): List<T>? = runSafely {
    parseArray(block)
}
