package jp.nephy.jsonkt

import kotlin.system.measureTimeMillis

// TODO: Kotlin 1.3でinline classをサポート

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val json = jsonObjectOf("a" to 24)
        val a by json
        println(a.jsonPrimitive)
    }
    print("$time ms")
}
