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

@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package blue.starry.jsonkt.delegation

import blue.starry.jsonkt.JsonLiteral
import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.literal
import blue.starry.jsonkt.literalOrNull

/*
 *  JsonLiteral
 */

inline fun JsonObject.byJsonLiteral(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonLiteral>
) = byLambda(key, default) { it.literal }

inline fun JsonObject.byJsonLiteral(
    key: String? = null,
    default: JsonLiteral
) = byLambda(key, default) { it.literal }

inline fun JsonObject.byJsonLiteral(key: String) = byLambda(key) { it.literal }

inline fun JsonModel.jsonLiteral(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonLiteral>
) = lambda(key, default) { it.literal }

inline fun JsonModel.jsonLiteral(
    key: String? = null,
    default: JsonLiteral
) = lambda(key, default) { it.literal }

inline fun JsonModel.jsonLiteral(key: String) = lambda(key) { it.literal }

inline val JsonObject.byJsonLiteral
    get() = byLambda { it.literal }

inline val JsonModel.jsonLiteral
    get() = lambda { it.literal }

/*
 *  JsonLiteral?
 */

inline fun JsonObject?.byNullableJsonLiteral(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonLiteral?>
) = byNullableLambda(key, default) { it.literalOrNull }

inline fun JsonObject?.byNullableJsonLiteral(
    key: String? = null,
    default: JsonLiteral?
) = byNullableLambda(key, default) { it.literalOrNull }

inline fun JsonObject?.byNullableJsonLiteral(key: String) = byNullableLambda(key) { it.literalOrNull }

inline fun JsonModel?.nullableJsonLiteral(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<JsonLiteral?>
) = nullableLambda(key, default) { it.literalOrNull }

inline fun JsonModel?.nullableJsonLiteral(
    key: String? = null,
    default: JsonLiteral?
) = nullableLambda(key, default) { it.literalOrNull }

inline fun JsonModel?.nullableJsonLiteral(key: String) = nullableLambda(key) { it.literalOrNull }

inline val JsonObject?.byNullableJsonLiteral
    get() = byNullableLambda { it.literalOrNull }

inline val JsonModel?.nullableJsonLiteral
    get() = nullableLambda { it.literalOrNull }

/*
 *  List<JsonLiteral>
 */

inline fun JsonObject.byJsonLiteralList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonLiteral>
) = byLambdaList(key, default) { it.literal }

inline fun JsonObject.byJsonLiteralList(
    key: String? = null,
    default: List<JsonLiteral>
) = byLambdaList(key, default) { it.literal }

inline fun JsonObject.byJsonLiteralList(key: String) = byLambdaList(key) { it.literal }

inline fun JsonModel.jsonLiteralList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonLiteral>
) = lambdaList(key, default) { it.literal }

inline fun JsonModel.jsonLiteralList(
    key: String? = null,
    default: List<JsonLiteral>
) = lambdaList(key, default) { it.literal }

inline fun JsonModel.jsonLiteralList(key: String) = lambdaList(key) { it.literal }

inline val JsonObject.byJsonLiteralList
    get() = byLambdaList { it.literal }

inline val JsonModel.jsonLiteralList
    get() = lambdaList { it.literal }

/*
 *  List<JsonLiteral?>
 */

inline fun JsonObject?.byNullableJsonLiteralList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonLiteral?>
) = byNullableLambdaList(key, default) { it.literalOrNull }

inline fun JsonObject?.byNullableJsonLiteralList(
    key: String? = null,
    default: List<JsonLiteral?>
) = byNullableLambdaList(key, default) { it.literalOrNull }

inline fun JsonObject?.byNullableJsonLiteralList(key: String) = byNullableLambdaList(key) { it.literalOrNull }

inline fun JsonModel?.nullableJsonLiteralList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<JsonLiteral?>
) = nullableLambdaList(key, default) { it.literalOrNull }

inline fun JsonModel?.nullableJsonLiteralList(
    key: String? = null,
    default: List<JsonLiteral?>
) = nullableLambdaList(key, default) { it.literalOrNull }

inline fun JsonModel?.nullableJsonLiteralList(key: String) = nullableLambdaList(key) { it.literalOrNull }

inline val JsonObject?.byNullableJsonLiteralList
    get() = byNullableLambdaList { it.literalOrNull }

inline val JsonModel?.nullableJsonLiteralList
    get() = nullableLambdaList { it.literalOrNull }
