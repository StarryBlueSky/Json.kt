@file:Suppress("UNUSED", "NOTHING_TO_INLINE")
package jp.nephy.jsonkt

fun jsonArrayOf(vararg elements: Any?) = immutableJsonArrayOf(*elements)

fun immutableJsonArrayOf(vararg elements: Any?): ImmutableJsonArray {
    return ImmutableJsonArray(elements.map { it.toJsonElement() })
}

fun mutableJsonArrayOf(vararg elements: Any?): MutableJsonArray {
    return MutableJsonArray(elements.map { it.toJsonElement() }.toMutableList())
}

typealias JsonArray = ImmutableJsonArray
typealias GsonJsonArray = com.google.gson.JsonArray

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
}

// TODO: check
inline fun MutableJsonArray.add(value: Any?) = add(value.toJsonElement())

inline fun MutableJsonArray.addAll(vararg values: Any?) = addAll(elements = values.map { it.toJsonElement() })


