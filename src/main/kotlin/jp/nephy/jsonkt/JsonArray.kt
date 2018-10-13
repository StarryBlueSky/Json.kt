@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

import java.math.BigDecimal
import java.math.BigInteger

fun jsonArrayOf(vararg elements: Any?) = immutableJsonArrayOf(*elements)

fun immutableJsonArrayOf(vararg elements: Any?): ImmutableJsonArray {
    return ImmutableJsonArray(elements.map { it.toJsonElement() })
}

fun mutableJsonArrayOf(vararg elements: Any?): MutableJsonArray {
    return MutableJsonArray(elements.map { it.toJsonElement() }.toMutableList())
}

typealias JsonArray = ImmutableJsonArray
private typealias GsonJsonArray = com.google.gson.JsonArray

open class ImmutableJsonArray(private val elements: List<JsonElement>): GsonCompatible<GsonJsonArray>, List<JsonElement> {
    constructor(json: GsonJsonArray): this(json.map { JsonElement(it) })
    constructor(vararg elements: JsonElement): this(elements.toList())

    override fun toGsonObject(): GsonJsonArray {
        val array = GsonJsonArray()
        for (element in elements) {
            array.add(element.toGsonObject())
        }
        return array
    }

    override val size: Int
        get() = elements.size

    override fun contains(element: JsonElement): Boolean {
        return elements.contains(element)
    }

    override fun containsAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.containsAll(elements)
    }

    override fun get(index: Int): JsonElement {
        return elements[index]
    }

    override fun indexOf(element: JsonElement): Int {
        return elements.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun iterator(): Iterator<JsonElement> {
        return elements.iterator()
    }

    override fun lastIndexOf(element: JsonElement): Int {
        return elements.lastIndexOf(element)
    }

    override fun listIterator(): ListIterator<JsonElement> {
        return elements.listIterator()
    }

    override fun listIterator(index: Int): ListIterator<JsonElement> {
        return elements.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<JsonElement> {
        return elements.subList(fromIndex, toIndex)
    }

    fun toMutableJsonArray(): MutableJsonArray {
        return MutableJsonArray(toGsonObject())
    }

    private inline fun <reified T> cast(operation: (JsonElement) -> T): List<T> {
        return try {
            elements.map { operation(it) }
        } catch (e: TypeCastException) {
            emptyList()
        }
    }

    fun toImmutableJsonObjectList(): List<ImmutableJsonObject> {
        return cast { it.immutableJsonObject }
    }

    fun toMutableJsonObjectList(): List<MutableJsonObject> {
        return cast { it.mutableJsonObject }
    }

    fun toImmutableJsonArrayList(): List<ImmutableJsonArray> {
        return cast { it.immutableJsonArray }
    }

    fun toMutableJsonArrayList(): List<MutableJsonArray> {
        return cast { it.mutableJsonArray }
    }

    fun toJsonPrimitiveList(): List<JsonPrimitive> {
        return cast { it.jsonPrimitive }
    }

    fun toBooleanList(): List<Boolean> {
        return cast { it.boolean }
    }

    fun toByteList(): List<Byte> {
        return cast { it.byte }
    }

    fun toCharList(): List<Char> {
        return cast { it.char }
    }

    fun toShortList(): List<Short> {
        return cast { it.short }
    }

    fun toIntList(): List<Int> {
        return cast { it.int }
    }

    fun toLongList(): List<Long> {
        return cast { it.long }
    }

    fun toBigIntegerList(): List<BigInteger> {
        return cast { it.bigInteger }
    }

    fun toFloatList(): List<Float> {
        return cast { it.float }
    }

    fun toDoubleList(): List<Double> {
        return cast { it.double }
    }

    fun toBigDecimalList(): List<BigDecimal> {
        return cast { it.bigDecimal }
    }

    fun toStringValueList(): List<String> {
        return cast { it.string }
    }

    override fun equals(other: Any?): Boolean {
        return elements == (other as? ImmutableJsonArray)?.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }

    override fun toString(): String {
        return elements.toString()
    }
}

class MutableJsonArray(private val elements: MutableList<JsonElement>): ImmutableJsonArray(elements), MutableList<JsonElement> {
    constructor(json: GsonJsonArray): this(json.map { JsonElement(it) }.toMutableList())
    constructor(vararg elements: JsonElement): this(elements.toMutableList())

    override fun toGsonObject(): GsonJsonArray {
        val array = GsonJsonArray()
        for (element in elements) {
            array.add(element.toGsonObject())
        }
        return array
    }

    override val size: Int
        get() = elements.size

    override fun contains(element: JsonElement): Boolean {
        return elements.contains(element)
    }

    override fun containsAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.containsAll(elements)
    }

    override fun get(index: Int): JsonElement {
        return elements[index]
    }

    override fun indexOf(element: JsonElement): Int {
        return elements.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return elements.isEmpty()
    }

    override fun iterator(): MutableIterator<JsonElement> {
        return elements.iterator()
    }

    override fun lastIndexOf(element: JsonElement): Int {
        return elements.lastIndexOf(element)
    }

    override fun listIterator(): MutableListIterator<JsonElement> {
        return elements.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<JsonElement> {
        return elements.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<JsonElement> {
        return elements.subList(fromIndex, toIndex)
    }

    override fun add(element: JsonElement): Boolean {
        return elements.add(element)
    }

    override fun add(index: Int, element: JsonElement) {
        return elements.add(index, element)
    }

    override fun addAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.addAll(elements)
    }

    override fun addAll(index: Int, elements: Collection<JsonElement>): Boolean {
        return this.elements.addAll(index, elements)
    }

    override fun clear() {
        return elements.clear()
    }

    override fun remove(element: JsonElement): Boolean {
        return elements.remove(element)
    }

    override fun removeAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.removeAll(elements)
    }

    override fun removeAt(index: Int): JsonElement {
        return elements.removeAt(index)
    }

    override fun retainAll(elements: Collection<JsonElement>): Boolean {
        return this.elements.retainAll(elements)
    }

    override fun set(index: Int, element: JsonElement): JsonElement {
        return elements.set(index, element)
    }

    fun toImmutableJsonArray(): ImmutableJsonArray {
        return ImmutableJsonArray(toGsonObject())
    }

    override fun equals(other: Any?): Boolean {
        return elements == (other as? MutableJsonArray)?.elements
    }

    override fun hashCode(): Int {
        return elements.hashCode()
    }

    override fun toString(): String {
        return elements.toString()
    }
}

// TODO: check
inline fun MutableJsonArray.add(value: Any?) = add(value.toJsonElement())
inline fun MutableJsonArray.addAll(vararg values: Any?) = addAll(elements = values.map { it.toJsonElement() })


inline val JsonArray.immutableJsonObjectList: List<ImmutableJsonObject>
    get() = toImmutableJsonObjectList()

inline val JsonArray.mutableJsonObjectList: List<MutableJsonObject>
    get() = toMutableJsonObjectList()

inline val JsonArray.immutableJsonArrayList: List<ImmutableJsonArray>
    get() = toImmutableJsonArrayList()

inline val JsonArray.mutableJsonArrayList: List<MutableJsonArray>
    get() = toMutableJsonArrayList()

inline val JsonArray.jsonPrimitiveList: List<JsonPrimitive>
    get() = toJsonPrimitiveList()

inline val JsonArray.booleanList: List<Boolean>
    get() = toBooleanList()

inline val JsonArray.byteList: List<Byte>
    get() = toByteList()

inline val JsonArray.charList: List<Char>
    get() = toCharList()

inline val JsonArray.shortList: List<Short>
    get() = toShortList()

inline val JsonArray.intList: List<Int>
    get() = toIntList()

inline val JsonArray.longList: List<Long>
    get() = toLongList()

inline val JsonArray.bigIntegerList: List<BigInteger>
    get() = toBigIntegerList()

inline val JsonArray.floatList: List<Float>
    get() = toFloatList()

inline val JsonArray.doubleList: List<Double>
    get() = toDoubleList()

inline val JsonArray.bigDecimalList: List<BigDecimal>
    get() = toBigDecimalList()

inline val JsonArray.stringList: List<String>
    get() = toStringValueList()
