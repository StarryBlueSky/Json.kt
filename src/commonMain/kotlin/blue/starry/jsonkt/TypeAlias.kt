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

package blue.starry.jsonkt

internal typealias JsonKey = String
internal typealias JsonValue = Any?

internal typealias JsonMap = Map<JsonKey, JsonValue>
internal typealias JsonMutableMap = MutableMap<JsonKey, JsonValue>

internal typealias JsonMutableArray = MutableList<JsonValue>

internal typealias JsonPair = Pair<JsonKey, JsonValue>
internal typealias JsonPairIterable = Iterable<JsonPair>
internal typealias JsonPairSequence = Sequence<JsonPair>
internal typealias JsonPairArray = Array<out JsonPair>

internal typealias JsonValueIterable = Iterable<JsonValue>
internal typealias JsonValueSequence = Sequence<JsonValue>
internal typealias JsonValueArray = Array<out JsonValue>

typealias JsonObject = kotlinx.serialization.json.JsonObject
typealias JsonArray = kotlinx.serialization.json.JsonArray
typealias JsonElement = kotlinx.serialization.json.JsonElement
typealias JsonPrimitive = kotlinx.serialization.json.JsonPrimitive
typealias JsonNull = kotlinx.serialization.json.JsonNull
