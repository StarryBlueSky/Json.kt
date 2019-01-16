@file:Suppress("UNCHECKED_CAST")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class JsonNullPointerException(val key: String, val json: JsonObject?): JsonKtException("json[\"$key\"] is null or is not present but return type is non-null:\n${json.toString().take(100)}...")

internal typealias JsonElementConverter<T> = (JsonElement) -> T

private typealias JsonObjectProperty<T> = ReadOnlyProperty<Any?, T>
internal typealias JsonObjectDefaultSelector<T> = (JsonObject) -> T

/**
 * @throws JsonNullPointerException
 */
inline fun <T: Any?> JsonObject?.jsonObjectProperty(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<T> = { throw NotImplementedError() },
    crossinline converter: JsonElementConverter<T> = { throw NotImplementedError() }
) = object: JsonObjectProperty<T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val json = this@jsonObjectProperty
        val jsonKey = key ?: property.name
        val jsonValue = json?.getOrNull(jsonKey)

        return runCatching {
            if (jsonValue.isNull) {
                json?.let(default)
            } else {
                jsonValue!!.let(converter)
            }
        }.recoverCatching {
            json?.let(default)
        }.onFailure { 
            if (!property.returnsNullable()) {
                throw it
            }
        }.getOrNull().let { 
            if (it == null && !property.returnsNullable()) {
                throw JsonNullPointerException(jsonKey, json)
            }
            
            it as T
        }
    }
}

private typealias JsonArrayProperty<T> = ReadOnlyProperty<Any?, List<T>>
internal typealias JsonArrayDefaultSelector<T> = (JsonObject) -> List<T>

/**
 * @throws JsonNullPointerException
 */
inline fun <T: Any?> JsonObject?.jsonArrayProperty(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<T> = { emptyList() },
    crossinline converter: JsonElementConverter<T> = { throw NotImplementedError() }
) = object: JsonArrayProperty<T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        val json = this@jsonArrayProperty ?: return emptyList()
        val jsonKey = key ?: property.name
        val jsonValue = getOrNull(jsonKey)

        return if (jsonValue.isNull || jsonValue !is JsonArray) {
            default.invoke(json)
        } else {
            runCatching {
                jsonValue.map { element ->
                    runCatching {
                        if (element.isNull) {
                            null
                        } else {
                            converter.invoke(element)
                        }
                    }.onFailure {
                        if (!property.returnsNullable()) {
                            throw it
                        }
                    }.getOrNull().let {
                        if (it == null && !property.returnsNullable()) {
                            throw JsonNullPointerException(jsonKey, json)
                        }

                        it as T
                    }
                }
            }.recoverCatching { 
                default.invoke(json)
            }.getOrThrow()
        }
    }
}
