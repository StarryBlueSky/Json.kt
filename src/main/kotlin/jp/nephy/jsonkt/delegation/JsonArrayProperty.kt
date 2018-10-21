package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal typealias JsonArrayProperty<T> = ReadOnlyProperty<Any?, List<T>>
internal typealias JsonArraySelector<T> = (ImmutableJsonObject) -> List<T>
internal typealias JsonArrayOperation<T> = (it: Map.Entry<String, JsonElement>) -> T

@Suppress("FUNCTIONNAME", "NOTHING_TO_INLINE", "UNCHECKED_CAST")
inline fun <T> JsonArrayProperty(json: ImmutableJsonObject, key: String?, modelClass: Class<T>?, noinline default: JsonArraySelector<T>?, noinline converter: JsonElementConverter<T>?, noinline operation: JsonArrayOperation<T>?, vararg params: Any) = object: JsonArrayProperty<T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        val jsonKey = key ?: property.name
        val jsonValue = json.getOrNull(jsonKey)
        val isMarkedNullable = try {
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
                if (jsonValue.isJsonObject() && operation != null) {
                    jsonValue.immutableJsonObject.map {
                        operation.invoke(it)
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
            if (it == null && !isMarkedNullable) {
                throw JsonNullPointerException(jsonKey, json)
            }

            it as T
        }
    }
}
