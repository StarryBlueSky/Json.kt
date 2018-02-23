package jp.nephy.jsonkt

import com.google.gson.JsonPrimitive

fun JsonPrimitive.getClass(): Class<*> {
    return when {
        isBoolean -> Boolean::class.java
        isByte -> Byte::class.java
        isShort -> Short::class.java
        isInt -> Int::class.java
        isLong -> Long::class.java
        isFloat -> Float::class.java
        isDouble -> Double::class.java
        isChar -> Char::class.java
        isString -> String::class.java
        else -> throw IllegalArgumentException("Unknown JsonPrimitive $this.")
    }
}

val JsonPrimitive.isByte: Boolean
    get() = if (isNumber && ! toString().contains(".")) {
        val value = number.toLong()
        Byte.MIN_VALUE <= value && value <= Byte.MAX_VALUE
    } else {
        false
    }

val JsonPrimitive.isShort: Boolean
    get() = if (isNumber && ! toString().contains(".")) {
        val value = number.toLong()
        Short.MIN_VALUE <= value && value <= Short.MAX_VALUE
    } else {
        false
    }

val JsonPrimitive.isInt: Boolean
    get() = if (isNumber && ! toString().contains(".")) {
        val value = number.toLong()
        Int.MIN_VALUE <= value && value <= Int.MAX_VALUE
    } else {
        false
    }

val JsonPrimitive.isLong: Boolean
    get() = isNumber && ! toString().contains(".") && toString() == number.toLong().toString()

val JsonPrimitive.isFloat: Boolean
    get() = if (isNumber && toString().contains(".")) {
        val value = number.toDouble()
        value.toString() == number.toFloat().toString()
    } else {
        false
    }

val JsonPrimitive.isDouble: Boolean
    get() = isNumber && toString().contains(".") && toString() == number.toDouble().toString()

val JsonPrimitive.isChar: Boolean
    get() = isString && string.toCharArray().size == 1
