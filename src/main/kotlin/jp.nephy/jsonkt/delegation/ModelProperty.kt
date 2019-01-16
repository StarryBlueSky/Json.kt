@file:Suppress("UNUSED")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.reflect.KClass

interface JsonModel {
    val json: JsonObject
}

class InvalidJsonModelException(val modelClass: KClass<*>): JsonKtException("${modelClass.effectiveName} does not have (jp.nephy.jsonkt.JsonObject) constructor.")

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String? = null) = jsonObjectProperty(key) { it.parse<T>() }

inline fun <reified T: JsonModel> JsonObject?.nullableJsonModelProperty(key: String? = null) = jsonObjectProperty(key) { it.parseOrNull<T>() }

/*
    JsonModel
 */

inline fun <reified T: JsonModel> JsonObject.byModel(key: String? = null) = jsonModelProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.model(key: String? = null) = json.jsonModelProperty<T>(key)

/*
    JsonModel?
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModel(key: String? = null) = nullableJsonModelProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.nullableModel(key: String? = null) = json.nullableJsonModelProperty<T>(key)

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String? = null) = jsonArrayProperty(key) { it.parse<T>() }

inline fun <reified T: JsonModel> JsonObject?.nullableJsonModelListProperty(key: String? = null) = jsonArrayProperty(key) { it.parseOrNull<T>() }

/*
    List<JsonModel>
 */

inline fun <reified T: JsonModel> JsonObject.byModelList(key: String? = null) = jsonModelListProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.modelList(key: String? = null) = json.jsonModelListProperty<T>(key)

/*
    List<JsonModel?>
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModelList(key: String? = null) = nullableJsonModelListProperty<T>(key)

inline fun <reified T: JsonModel> JsonModel.nullableModelList(key: String? = null) = json.nullableJsonModelListProperty<T>(key)
