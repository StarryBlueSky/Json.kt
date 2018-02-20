package jp.nephy.jsonkt.delegate

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import jp.nephy.jsonkt.exception.InvalidJsonModelException
import jp.nephy.jsonkt.exception.JsonNullCastException
import kotlin.reflect.KProperty

class JsonArrayDelegate<T>(private val json: JsonObject, private val key: String?, private val default: ((JsonObject).() -> Collection<T>)?, private val lambda: ((JsonElement).() -> T)?, private val modelClass: Class<T>? = null) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
        val jsonKey = key ?: property.name
        val isMarkedNullable = try {
            @Suppress("UNCHECKED_CAST")
            null as T
            true
        } catch (e: TypeCastException) {
            false
        }

        fun wrap(results: Collection<Any?>?): List<T> {
            return if (results == null || results.isEmpty()) {
                emptyList()
            } else {
                results.map {
                    @Suppress("UNCHECKED_CAST")
                    if (it == null) {
                        if (! isMarkedNullable) {
                            throw JsonNullCastException(jsonKey, json)
                        }
                        null as T
                    } else {
                        it as T
                    }
                }
            }
        }

        return if (! json.has(jsonKey) || json.isJsonNull || ! json[jsonKey].isJsonArray) {
            wrap(default?.invoke(json))
        } else if (modelClass == null) {
            wrap(json[jsonKey].asJsonArray.map {
                if (it.isJsonNull) {
                    null
                } else {
                    lambda?.invoke(it)
                }
            })
        } else {
            wrap(json[jsonKey].asJsonArray.map {
                try {
                    modelClass.getConstructor(JsonObject::class.java).newInstance(it)
                } catch (e: NoSuchMethodException) {
                    throw InvalidJsonModelException(modelClass.canonicalName)
                }
            })
        }
    }
}
