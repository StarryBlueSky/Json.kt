package jp.nephy.jsonkt.delegate

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import jp.nephy.jsonkt.contains
import jp.nephy.jsonkt.exception.InvalidJsonModelException
import jp.nephy.jsonkt.exception.JsonNullCastException
import kotlin.reflect.KProperty

class JsonDelegate<out T>(private val json: JsonObject, private val key: String?, private val default: ((JsonObject).() -> T)?, private val lambda: ((JsonElement).() -> T)?, private val modelClass: Class<T>? = null, private vararg val params: Any) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val jsonKey = key ?: property.name
        val isMarkedNullable = property.returnType.isMarkedNullable

        fun wrap(result: Any?): T {
            @Suppress("UNCHECKED_CAST")
            return if (result == null) {
                if (! isMarkedNullable) {
                    throw JsonNullCastException(jsonKey, json)
                }
                null as T
            } else {
                result as T
            }
        }

        return if (! json.contains(jsonKey) || json.isJsonNull || json[jsonKey].isJsonNull) {
            wrap(default?.invoke(json))
        } else if (modelClass == null) {
            wrap(lambda?.invoke(json[jsonKey]))
        } else {
            try {
                modelClass.getConstructor(*params.map { it::class.java }.toTypedArray(), JsonObject::class.java).newInstance(*params, json[jsonKey])
            } catch (e: NoSuchMethodException) {
                throw InvalidJsonModelException(modelClass.canonicalName)
            }
        }
    }
}
