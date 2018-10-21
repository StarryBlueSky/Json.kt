package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal typealias JsonObjectProperty<T> = ReadOnlyProperty<Any?, T>
internal typealias JsonObjectSelector<T> = (ImmutableJsonObject) -> T
internal typealias JsonElementConverter<T> = (JsonElement) -> T

@Suppress("FUNCTIONNAME", "NOTHING_TO_INLINE", "UNCHECKED_CAST")
inline fun <T> JsonObjectProperty(json: ImmutableJsonObject, key: String?, modelClass: Class<T>?, noinline default: JsonObjectSelector<T>?, noinline converter: JsonElementConverter<T>?, vararg params: Any) = object: JsonObjectProperty<T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val jsonKey = key ?: property.name
        val jsonValue = json.getOrNull(jsonKey)

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
