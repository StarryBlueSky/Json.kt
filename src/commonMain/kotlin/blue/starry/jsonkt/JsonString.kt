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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonElementSerializer

/*
 * stringify
 */

fun JsonElement.stringify(configuration: JsonConfiguration = defaultJsonConfiguration): String {
    return if (configuration == defaultJsonConfiguration) {
        defaultJsonInstance
    } else {
        Json(configuration)
    }.stringify(JsonElementSerializer, this)
}

/**
 * @throws JsonCastException
 */
fun JsonPairIterable.stringify(configuration: JsonConfiguration = defaultJsonConfiguration): String {
    return toJsonArray().stringify(configuration)
}

/**
 * @throws JsonCastException
 */
fun JsonPairSequence.stringify(configuration: JsonConfiguration = defaultJsonConfiguration): String {
    return toJsonArray().stringify(configuration)
}

/**
 * @throws JsonCastException
 */
fun JsonPairArray.stringify(configuration: JsonConfiguration = defaultJsonConfiguration): String {
    return toJsonArray().stringify(configuration)
}

fun JsonModel.stringify(configuration: JsonConfiguration = defaultJsonConfiguration): String {
    return json.stringify(configuration)
}
