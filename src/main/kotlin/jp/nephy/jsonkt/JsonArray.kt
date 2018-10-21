@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import java.math.BigDecimal
import java.math.BigInteger

fun jsonArrayOf(vararg elements: Any?) = mutableJsonArrayOf(*elements)

fun immutableJsonArrayOf(vararg elements: Any?): ImmutableJsonArray = JsonArray(*elements)

fun mutableJsonArrayOf(vararg elements: Any?): MutableJsonArray = JsonArray(*elements)

typealias GsonJsonArray = com.google.gson.JsonArray
interface ImmutableJsonArray: GsonCompatible<GsonJsonArray>, List<JsonElement>
interface MutableJsonArray: ImmutableJsonArray, MutableList<JsonElement> {
    operator fun set(index: Int, element: Any?)
}

internal class JsonArray(vararg elements: Any?): MutableJsonArray, ArrayList<JsonElement>(elements.map { it.toJsonElement() }.toList()) {
    override operator fun set(index: Int, element: Any?) {
        add(index, element)
    }

    override fun toGsonObject(): GsonJsonArray {
        val array = GsonJsonArray()
        for (element in this) {
            array.add(element.toGsonObject())
        }
        return array
    }
}

inline fun ImmutableJsonArray.asMutable(): MutableJsonArray = this as MutableJsonArray
inline fun MutableJsonArray.asImmutable(): ImmutableJsonArray = this

inline fun MutableJsonArray.add(element: Any?) = add(element.toJsonElement())
inline fun MutableJsonArray.add(index: Int, element: Any?) = add(index, element.toJsonElement())
inline fun MutableJsonArray.addAll(vararg values: Any?) = addAll(elements = values.map { it.toJsonElement() })

inline fun <reified T> ImmutableJsonArray.cast(operation: (JsonElement) -> T): List<T> {
    return try {
        map { operation(it) }
    } catch (e: TypeCastException) {
        emptyList()
    }
}

inline fun ImmutableJsonArray.toImmutableJsonObjectList(): List<ImmutableJsonObject> {
    return cast { it.immutableJsonObject }
}
inline val ImmutableJsonArray.immutableJsonObjectList: List<ImmutableJsonObject>
    get() = toImmutableJsonObjectList()

inline fun ImmutableJsonArray.toMutableJsonObjectList(): List<MutableJsonObject> {
    return cast { it.mutableJsonObject }
}
inline val ImmutableJsonArray.mutableJsonObjectList: List<MutableJsonObject>
    get() = toMutableJsonObjectList()

inline fun ImmutableJsonArray.toImmutableJsonArrayList(): List<ImmutableJsonArray> {
    return cast { it.immutableJsonArray }
}
inline val ImmutableJsonArray.immutableJsonArrayList: List<ImmutableJsonArray>
    get() = toImmutableJsonArrayList()

inline fun ImmutableJsonArray.toMutableJsonArrayList(): List<MutableJsonArray> {
    return cast { it.mutableJsonArray }
}
inline val ImmutableJsonArray.mutableJsonArrayList: List<MutableJsonArray>
    get() = toMutableJsonArrayList()

inline fun ImmutableJsonArray.toJsonPrimitiveList(): List<JsonPrimitive> {
    return cast { it.jsonPrimitive }
}
inline val ImmutableJsonArray.jsonPrimitiveList: List<JsonPrimitive>
    get() = toJsonPrimitiveList()

inline fun ImmutableJsonArray.toBooleanList(): List<Boolean> {
    return cast { it.boolean }
}
inline val ImmutableJsonArray.booleanList: List<Boolean>
    get() = toBooleanList()

inline fun ImmutableJsonArray.toByteList(): List<Byte> {
    return cast { it.byte }
}
inline val ImmutableJsonArray.byteList: List<Byte>
    get() = toByteList()

inline fun ImmutableJsonArray.toCharList(): List<Char> {
    return cast { it.char }
}
inline val ImmutableJsonArray.charList: List<Char>
    get() = toCharList()

inline fun ImmutableJsonArray.toShortList(): List<Short> {
    return cast { it.short }
}
inline val ImmutableJsonArray.shortList: List<Short>
    get() = toShortList()

inline fun ImmutableJsonArray.toIntList(): List<Int> {
    return cast { it.int }
}
inline val ImmutableJsonArray.intList: List<Int>
    get() = toIntList()

inline fun ImmutableJsonArray.toLongList(): List<Long> {
    return cast { it.long }
}
inline val ImmutableJsonArray.longList: List<Long>
    get() = toLongList()

inline fun ImmutableJsonArray.toBigIntegerList(): List<BigInteger> {
    return cast { it.bigInteger }
}
inline val ImmutableJsonArray.bigIntegerList: List<BigInteger>
    get() = toBigIntegerList()

inline fun ImmutableJsonArray.toFloatList(): List<Float> {
    return cast { it.float }
}
inline val ImmutableJsonArray.floatList: List<Float>
    get() = toFloatList()

inline fun ImmutableJsonArray.toDoubleList(): List<Double> {
    return cast { it.double }
}
inline val ImmutableJsonArray.doubleList: List<Double>
    get() = toDoubleList()

inline fun ImmutableJsonArray.toBigDecimalList(): List<BigDecimal> {
    return cast { it.bigDecimal }
}
inline val ImmutableJsonArray.bigDecimalList: List<BigDecimal>
    get() = toBigDecimalList()

inline fun ImmutableJsonArray.toStringValueList(): List<String> {
    return cast { it.string }
}
inline val ImmutableJsonArray.stringList: List<String>
    get() = toStringValueList()
