@file:Suppress("UNUSED")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.reflect.KClass

interface JsonModel {
    val json: JsonObject
}

class InvalidJsonModelException(val modelClass: KClass<*>): JsonKtException("${modelClass.effectiveName} does not have valid constructor.")

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
inline fun <reified T: JsonModel> JsonObject?.jsonModelProperty(key: String? = null, vararg args: Any?) = jsonObjectProperty(key) { it.parse<T>(*args) }

inline fun <reified T: JsonModel> JsonObject?.nullableJsonModelProperty(key: String? = null, vararg args: Any?) = jsonObjectProperty(key) { it.parseOrNull<T>(*args) }

/*
    JsonModel
 */

inline fun <reified T: JsonModel> JsonObject.byModel(key: String? = null, vararg args: Any?) = jsonModelProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.model(key: String? = null, vararg args: Any?) = json.jsonModelProperty<T>(key, *args)

/*
    JsonModel?
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModel(key: String? = null, vararg args: Any?) = nullableJsonModelProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.nullableModel(key: String? = null, vararg args: Any?) = json.nullableJsonModelProperty<T>(key, *args)

/**
 * @throws InvalidJsonModelException
 * @throws JsonCastException
 */
inline fun <reified T: JsonModel> JsonObject?.jsonModelListProperty(key: String? = null, vararg args: Any?) = jsonArrayProperty(key) { it.parse<T>(*args) }

inline fun <reified T: JsonModel> JsonObject?.nullableJsonModelListProperty(key: String? = null, vararg args: Any?) = jsonArrayProperty(key) { it.parseOrNull<T>(*args) }

/*
    List<JsonModel>
 */

inline fun <reified T: JsonModel> JsonObject.byModelList(key: String? = null, vararg args: Any?) = jsonModelListProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.modelList(key: String? = null, vararg args: Any?) = json.jsonModelListProperty<T>(key, *args)

/*
    List<JsonModel?>
 */

inline fun <reified T: JsonModel> JsonObject?.byNullableModelList(key: String? = null, vararg args: Any?) = nullableJsonModelListProperty<T>(key, *args)

inline fun <reified T: JsonModel> JsonModel.nullableModelList(key: String? = null, vararg args: Any?) = json.nullableJsonModelListProperty<T>(key, *args)
