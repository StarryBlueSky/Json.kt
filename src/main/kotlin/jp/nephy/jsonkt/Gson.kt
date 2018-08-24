package jp.nephy.jsonkt

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun gson(builder: (GsonBuilder.() -> Unit)? = null): Gson {
    return GsonBuilder().apply(builder ?: {
        serializeNulls()
    }).create()
}
