package jp.nephy.jsonkt.delegate

import jp.nephy.jsonkt.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal typealias JsonObjectSelector<T> = (ImmutableJsonObject) -> T
internal typealias JsonElementConverter<T> = (JsonElement) -> T

class JsonDelegate<T>(private val json: ImmutableJsonObject, private val key: String?, private val modelClass: Class<T>?, private val default: JsonObjectSelector<T>?, private val converter: JsonElementConverter<T>?, private vararg val params: Any): ReadOnlyProperty<Any?, T> {
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val jsonKey = key ?: property.name
        val jsonValue = json[jsonKey]

        @Suppress("UNCHECKED_CAST")
        return when {
            jsonValue == null || jsonValue.isJsonNull() -> {
                default?.invoke(json)
            }
            modelClass == null -> {
                converter?.invoke(jsonValue)
            }
            else -> {
                val constructor = try {
                    modelClass.getConstructor(*params.map { it.javaClass }.toTypedArray(), ImmutableJsonObject::class.java)
                } catch (e: NoSuchMethodException) {
                    throw InvalidJsonModelException(modelClass)
                }

                constructor.newInstance(*params, jsonValue.immutableJsonObject)
            }
        }.let {
            if (it == null && !property.returnType.isMarkedNullable) {
                throw JsonNullPointerException(jsonKey, json)
            }

            it as T
        }
    }
}
