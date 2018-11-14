package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.InvalidJsonModelException
import jp.nephy.jsonkt.JsonNullPointerException
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal typealias JsonObjectProperty<T> = ReadOnlyProperty<Any?, T>
internal typealias JsonObjectSelector<T> = (JsonObject) -> T
internal typealias JsonElementConverter<T> = (JsonElement) -> T

@Suppress("FUNCTIONNAME", "NOTHING_TO_INLINE", "UNCHECKED_CAST")
inline fun <T> JsonObject.JsonObjectProperty(key: String?, modelClass: Class<T>?, noinline default: JsonObjectSelector<T>?, noinline converter: JsonElementConverter<T>?, vararg params: Any) = object: JsonObjectProperty<T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val jsonKey = key ?: property.name
        val jsonValue = getOrNull(jsonKey)

        return when {
            jsonValue == null || jsonValue.isNull -> {
                default?.invoke(this@JsonObjectProperty)
            }
            modelClass == null -> {
                converter?.invoke(jsonValue)
            }
            else -> {
                val constructor = try {
                    modelClass.getConstructor(*params.map { it.javaClass }.toTypedArray(), JsonObject::class.java)
                } catch (e: NoSuchMethodException) {
                    throw InvalidJsonModelException(modelClass.canonicalName)
                }

                constructor.newInstance(*params, jsonValue.jsonObject)
            }
        }.let {
            if (it == null && !property.returnType.isMarkedNullable) {
                throw JsonNullPointerException(jsonKey, this@JsonObjectProperty)
            }

            it as T
        }
    }
}
