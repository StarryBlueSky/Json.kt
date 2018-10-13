package jp.nephy.jsonkt

import jp.nephy.jsonkt.delegate.byInt
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time = measureTimeMillis {
        val json = jsonObjectOf("a" to 1, "b" to 2)
        val b by json.byInt
        println(b)
    }
    print("$time ms")
}
