@file:Suppress("UNUSED")

package jp.nephy.jsonkt

import kotlin.reflect.KClass

open class JsonKtException(override val message: String): Exception()

class JsonCastException(val element: Any, val type: KClass<*>): JsonKtException("An element cannot be casted to ${type.effectiveName}:\n${element.toString().take(100)}...")
