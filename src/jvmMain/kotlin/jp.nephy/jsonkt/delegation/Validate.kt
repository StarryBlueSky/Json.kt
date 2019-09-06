/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2019 Nephy Project Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

private typealias DelegateProperty = KProperty1<JsonModel, *>

data class ValidationResult(val model: KClass<out JsonModel>, val passed: Set<DelegateProperty>, val failed: Map<DelegateProperty, ValidationFailedReason>, val missing: Set<ValidationFailedReason.NotDeclaredInModelClass>)

interface ValidationFailedReason {
    val message: String

    data class NotDeclaredInModelClass(val jsonKey: String, val estimatedType: KType): ValidationFailedReason {
        override val message: String
            get() = "\"$jsonKey\" ($estimatedType) is not declared in model class."
    }

    data class TypeMismatch(val jsonKey: String, val declaredType: KType, val estimatedType: KType): ValidationFailedReason {
        override val message: String
            get() = "\"$jsonKey\" ($estimatedType) has return type which is different from estimated type ($estimatedType)."
    }

    data class NullabilityMismatch(val jsonKey: String, val declaredType: KType, val estimatedType: KType): ValidationFailedReason {
        override val message: String
            get() = "\"$jsonKey\" ($estimatedType) has different nullability from estimated type ($estimatedType)."
    }
}

@Suppress("UNCHECKED_CAST")
fun JsonModel.validate(strictNumber: Boolean = false): ValidationResult {
    val kClass = this::class
    val passed = mutableSetOf<DelegateProperty>()
    val failed = mutableMapOf<DelegateProperty, ValidationFailedReason>()
    val missing = mutableSetOf<ValidationFailedReason.NotDeclaredInModelClass>()

    val declaredKeys = mutableSetOf<String>()
    for (property in kClass.declaredMemberProperties as Collection<DelegateProperty>) {
        val delegation = property.accessing {
            property.getDelegate(this)
        } as? JsonDelegateProperty<*> ?: continue

        val jsonKey = delegation.key ?: property.name
        declaredKeys += jsonKey

        val declaredReturnType = property.returnType
        val actualReturnClass = json[jsonKey]?.estimatedClass(strictNumber)

        if (!declaredReturnType.isMarkedNullable && actualReturnClass == null) {
            failed += property to ValidationFailedReason.NullabilityMismatch(jsonKey, declaredReturnType, actualReturnClass.estimatedType)
        } else if (declaredReturnType != actualReturnClass) {
            failed += property to ValidationFailedReason.TypeMismatch(jsonKey, declaredReturnType, actualReturnClass.estimatedType)
        } else {
            passed += property
        }
    }

    json.keys.filter { it !in declaredKeys }.forEach { key ->
        val actualReturnType = json[key]?.estimatedClass(strictNumber).estimatedType

        missing += ValidationFailedReason.NotDeclaredInModelClass(key, actualReturnType)
    }

    return ValidationResult(kClass, passed, failed, missing)
}

private inline fun <T> KProperty<*>.accessing(block: () -> T): T {
    return if (isAccessible) {
        block()
    } else {
        isAccessible = true

        block().also {
            isAccessible = false
        }
    }
}

private fun JsonElement.estimatedClass(strictNumber: Boolean): KClass<*>? = when (this) {
    is JsonNull -> {
        null
    }
    is JsonPrimitive -> {
        when {
            isString -> String::class
            isBoolean -> Boolean::class
            strictNumber && isInt -> Int::class
            isLong -> Long::class
            strictNumber && isFloat -> Float::class
            isDouble -> Double::class
            else -> {
                throw UnsupportedOperationException("Unsupported type: $this")
            }
        }
    }
    is JsonObject -> {
        JsonObject::class
    }
    is JsonArray -> {
        JsonArray::class
    }
    else -> {
        throw UnsupportedOperationException("Unsupported type: $this")
    }
}

private val KClass<*>?.estimatedType: KType
    get() = this?.createType() ?: JsonElement::class.createType(nullable = true)

private val Set<KClass<*>?>.estimatedType: KType
    get() {
        val nonNullElements = filterNotNull()
        val representativeClass = nonNullElements.singleOrNull() ?: JsonElement::class

        return representativeClass.createType(nullable = size != nonNullElements.size)
    }
