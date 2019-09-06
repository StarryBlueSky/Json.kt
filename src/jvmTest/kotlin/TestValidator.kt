import jp.nephy.jsonkt.delegation.validate
import jp.nephy.jsonkt.parse

fun main() {
    val obj = json.parse<Model>()
    val result = obj.validate()

    println("PASSED:")
    result.passed.forEach {
        println(it)
    }

    println("---")

    println("FAILED:")
    result.failed.forEach {
        println(it)
    }

    println("---")

    println("MISSING:")
    result.missing.forEach {
        println(it)
    }
}
