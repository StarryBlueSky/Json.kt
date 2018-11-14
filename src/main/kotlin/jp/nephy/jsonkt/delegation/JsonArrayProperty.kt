package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.InvalidJsonModelException
import jp.nephy.jsonkt.JsonNullPointerException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal typealias JsonArrayProperty<T> = ReadOnlyProperty<Any?, List<T>>
internal typealias JsonArraySelector<T> = (JsonObject) -> List<T>
internal typealias JsonArrayOperation<T> = (it: Map.Entry<String, JsonElement>) -> T

@Suppress("FUNCTIONNAME", "NOTHING_TO_INLINE", "UNCHECKED_CAST")
inline fun <T> JsonObject.JsonArrayProperty(key: String?, modelClass: Class<T>?, noinline default: JsonArraySelector<T>?, noinline converter: JsonElementConverter<T>?, noinline operation: JsonArrayOperation<T>?, vararg params: Any) = object: JsonArrayProperty<T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        val jsonKey = key ?: property.name
        val jsonValue = getOrNull(jsonKey)
        val isMarkedNullable = try {
            null as T
            true
        } catch (e: TypeCastException) {
            false
        }

        return when {
            jsonValue == null || jsonValue.isNull -> {
                default?.invoke(this@JsonArrayProperty).orEmpty()
            }
            jsonValue !is JsonArray -> {
                if (jsonValue is JsonObject && operation != null) {
                    jsonValue.map {
                        operation.invoke(it)
                    }
                } else {
                    default?.invoke(this@JsonArrayProperty).orEmpty()
                }
            }
            modelClass != null -> {
                val constructor = try {
                    modelClass.getConstructor(*params.map { it.javaClass }.toTypedArray(), JsonObject::class.java)
                } catch (e: NoSuchMethodException) {
                    throw InvalidJsonModelException(modelClass.canonicalName)
                }

                jsonValue.map {
                    constructor.newInstance(*params, it.jsonObject)
                }
            }
            else -> {
                jsonValue.map {
                    if (it.isNull) {
                        null
                    } else {
                        converter?.invoke(it)
                    }
                }
            }
        }.map {
            if (it == null && !isMarkedNullable) {
                throw JsonNullPointerException(jsonKey, this@JsonArrayProperty)
            }

            it as T
        }
    }
}
