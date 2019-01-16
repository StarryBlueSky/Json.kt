@file:Suppress("UNUSED", "NOTHING_TO_INLINE")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonObject

/*
    T
 */

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = jsonObjectProperty(key,  default, converter)

inline fun <T: Any> JsonObject.byLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = jsonObjectProperty(key,  converter = converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = json.jsonObjectProperty(key, default, converter)

inline fun <T: Any> JsonModel.lambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = json.jsonObjectProperty(key, converter = converter)

/*
    T?
 */

inline fun <T: Any?> JsonObject?.byNullableLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = jsonObjectProperty(key, default, converter)

inline fun <T: Any?> JsonObject?.byNullableLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = jsonObjectProperty(key, converter = converter)

inline fun <T: Any?> JsonModel.nullableLambda(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonObjectProperty(key, default, converter)

inline fun <T: Any?> JsonModel.nullableLambda(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonObjectProperty(key, converter = converter)

/*
    List<T>
 */

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = jsonArrayProperty(key, default, converter)

inline fun <T: Any> JsonObject.byLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = jsonArrayProperty(key, converter = converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T>,
    crossinline converter: JsonElementConverter<T>
) = json.jsonArrayProperty(key, default, converter)

inline fun <T: Any> JsonModel.lambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T>
) = json.jsonArrayProperty(key, converter = converter)

/*
    List<T?>
 */

inline fun <T: Any?> JsonObject.byNullableLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = jsonArrayProperty(key, default, converter)

inline fun <T: Any?> JsonObject.byNullableLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = jsonArrayProperty(key, converter =  converter)

inline fun <T: Any?> JsonModel.nullableLambdaList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T?>,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonArrayProperty(key, default, converter)

inline fun <T: Any?> JsonModel.nullableLambdaList(
    key: String? = null,
    crossinline converter: JsonElementConverter<T?>
) = json.jsonArrayProperty(key, converter = converter)
