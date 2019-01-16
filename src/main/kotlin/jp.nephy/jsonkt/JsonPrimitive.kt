package jp.nephy.jsonkt

typealias JsonPrimitive = kotlinx.serialization.json.JsonPrimitive

@Suppress("IMPLICIT_CAST_TO_ANY")
inline fun <reified T: Any?> JsonPrimitive.cast(): T {
    return when (T::class) {
        Boolean::class -> {
            booleanOrNull
        }
        Int::class -> {
            intOrNull
        }
        Long::class -> {
            longOrNull
        }
        Float::class -> {
            floatOrNull
        }
        Double::class -> {
            doubleOrNull
        }
        Char::class -> {
            contentOrNull?.firstOrNull()
        }
        String::class -> {
            contentOrNull
        }
        else -> {
            throw IllegalArgumentException("${T::class} is not primitive type.")
        }
    } as T
}
