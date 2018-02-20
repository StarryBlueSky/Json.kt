package jp.nephy.jsonkt.delegate

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import jp.nephy.jsonkt.contains
import jp.nephy.jsonkt.exception.InvalidJsonModelException
import jp.nephy.jsonkt.exception.JsonNullCastException
import jp.nephy.jsonkt.jsonArray
import jp.nephy.jsonkt.jsonObject
import jp.nephy.jsonkt.map
import kotlin.reflect.KProperty

class JsonArrayDelegate<out T>(private val json: JsonObject, private val key: String?, private val default: ((JsonObject).() -> Collection<T>)?, private val lambda: ((JsonElement).() -> T)?, private val operation: ((it: Map.Entry<String, JsonElement>) -> T)? = null, private val modelClass: Class<T>? = null, private vararg val params: Any) {
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

        return if (! json.contains(jsonKey) || json.isJsonNull) {
            wrap(default?.invoke(json))
        } else if (! json[jsonKey].isJsonArray) {
            if (json[jsonKey].isJsonObject && operation != null) {
                wrap(json[jsonKey].jsonObject.map {
                    operation.invoke(it)
                })
            } else {
                wrap(default?.invoke(json))
            }
        } else if (modelClass != null) {
            wrap(json[jsonKey].jsonArray.map {
                try {
                    modelClass.getConstructor(*params.map { it::class.java }.toTypedArray(), JsonObject::class.java).newInstance(*params, it)
                } catch (e: NoSuchMethodException) {
                    throw InvalidJsonModelException(modelClass.canonicalName)
                }
            })
        } else {
            wrap(json[jsonKey].jsonArray.map {
                if (it.isJsonNull) {
                null
                } else {
                lambda?.invoke(it)
                }
            })
        }
    }
}
