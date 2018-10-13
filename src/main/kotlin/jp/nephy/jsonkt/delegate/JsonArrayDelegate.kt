package jp.nephy.jsonkt.delegate

import jp.nephy.jsonkt.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal typealias JsonArraySelector<T> = (ImmutableJsonObject) -> List<T>

class JsonArrayDelegate<T>(private val json: ImmutableJsonObject, private val key: String?, private val modelClass: Class<T>?, private val default: JsonArraySelector<T>?, private val converter: JsonElementConverter<T>?, private vararg val params: Any): ReadOnlyProperty<Any?, List<T>> {
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        val jsonKey = key ?: property.name
        val jsonValue = json[jsonKey]
        val isMarkedNullable = try {
            @Suppress("UNCHECKED_CAST")
            null as T
            true
        } catch (e: TypeCastException) {
            false
        }

        return when {
            jsonValue == null || jsonValue.isJsonNull() -> {
                default?.invoke(json).orEmpty()
            }
            !jsonValue.isJsonArray() -> {
                if (jsonValue.isJsonObject() && converter != null) {
                    jsonValue.immutableJsonObject.map {
                        converter.invoke(it.value)
                    }
                } else {
                    default?.invoke(json).orEmpty()
                }
            }
            modelClass != null -> {
                val constructor = try {
                    modelClass.getConstructor(*params.map { it.javaClass }.toTypedArray(), ImmutableJsonObject::class.java)
                } catch (e: NoSuchMethodException) {
                    throw InvalidJsonModelException(modelClass)
                }

                jsonValue.immutableJsonArray.map {
                    constructor.newInstance(*params, it.immutableJsonObject)
                }
            }
            else -> {
                jsonValue.immutableJsonArray.map {
                    if (it.isJsonNull()) {
                        null
                    } else {
                        converter?.invoke(it)
                    }
                }
            }
        }.map {
            @Suppress("UNCHECKED_CAST")
            if (it == null && !isMarkedNullable) {
                throw JsonNullPointerException(jsonKey, json)
            }

            it as T
        }
    }
}
