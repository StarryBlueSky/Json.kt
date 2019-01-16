@file:Suppress("UNUSED", "DEPRECATION")

package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.JsonKtException
import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.cast
import jp.nephy.jsonkt.effectiveName
import kotlin.reflect.KClass

@Deprecated("Directly implementing JsonEnum<T> is deprecated for type safety. Use {BooleanJsonEnum, IntJsonEnum, LongJsonEnum, FloatJsonEnum, DoubleJsonEnum, CharJsonEnum, StringJsonEnum} instead of JsonEnum<T>.")
interface JsonEnum<T: Any> {
    val value: T
}

interface BooleanJsonEnum: JsonEnum<Boolean>
interface IntJsonEnum: JsonEnum<Int>
interface LongJsonEnum: JsonEnum<Long>
interface FloatJsonEnum: JsonEnum<Float>
interface DoubleJsonEnum: JsonEnum<Double>
interface CharJsonEnum: JsonEnum<Char>
interface StringJsonEnum: JsonEnum<String>

class NoSuchEmumMemberException(val enumClass: KClass<*>, val value: Any): JsonKtException("Enum class ${enumClass.effectiveName} does not have member of value $value.")

/**
 * @throws NoSuchEmumMemberException
 */
inline fun <T: Any, reified E> findEnumMember(value: T): E where E: Enum<E>, E: JsonEnum<T> {
    return findEnumMemberOrNull(value) ?: throw NoSuchEmumMemberException(E::class, value)
}

inline fun <T: Any, reified E> findEnumMemberOrNull(value: T): E? where E: Enum<E>, E: JsonEnum<T> {
    return enumValues<E>().find { const -> const.value == value }
}

/*
    JsonEnum
 */

inline fun <reified T: Any, reified E> JsonObject.byEnum(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<E>
) where E: Enum<E>, E: JsonEnum<T> = jsonObjectProperty(key, default) { 
    val casted = it.primitive.cast<T>()
    
    findEnumMember(casted)
}

inline fun <reified T: Any, reified E> JsonObject.byEnum(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = jsonObjectProperty(key) { 
    val casted = it.primitive.cast<T>()

    findEnumMember<T, E>(casted)
}

inline fun <reified T: Any, reified E> JsonModel.enum(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<E>
) where E: Enum<E>, E: JsonEnum<T> = json.jsonObjectProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMember(casted)
}

inline fun <reified T: Any, reified E> JsonModel.enum(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = json.jsonObjectProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMember<T, E>(casted)
}

/*
    JsonEnum?
 */

inline fun <reified T: Any, reified E> JsonObject.byNullableEnum(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<E?>
) where E: Enum<E>, E: JsonEnum<T> = jsonObjectProperty(key, default) { 
    val casted = it.primitive.cast<T>()
    
    findEnumMemberOrNull(casted)
}

inline fun <reified T: Any, reified E> JsonObject.byNullableEnum(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = jsonObjectProperty(key) { 
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull<T, E>(casted)
}

inline fun <reified T: Any, reified E> JsonModel.nullableEnum(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<E?>
) where E: Enum<E>, E: JsonEnum<T> = json.jsonObjectProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull(casted)
}

inline fun <reified T: Any, reified E> JsonModel.nullableEnum(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = json.jsonObjectProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull<T, E>(casted)
}

inline fun <reified T: Any, reified E> JsonModel.enumOrNull(
    key: String? = null,
    crossinline default: JsonObjectDefaultSelector<E?>
) where E: Enum<E>, E: JsonEnum<T> = json.jsonObjectProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull(casted)
}

inline fun <reified T: Any, reified E> JsonModel.enumOrNull(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = json.jsonObjectProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull<T, E>(casted)
}

/*
    List<JsonEnum>
 */

inline fun <reified T: Any, reified E> JsonObject.byEnumList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<E>
) where E: Enum<E>, E: JsonEnum<T> = jsonArrayProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMember(casted)
}

inline fun <reified T: Any, reified E> JsonObject.byEnumList(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = jsonArrayProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMember<T, E>(casted)
}

inline fun <reified T: Any, reified E> JsonModel.enumList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<E>
) where E: Enum<E>, E: JsonEnum<T> = json.jsonArrayProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMember(casted)
}

inline fun <reified T: Any, reified E> JsonModel.enumList(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = json.jsonArrayProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMember<T, E>(casted)
}

/*
    List<JsonEnum?>
 */

inline fun <reified T: Any, reified E> JsonObject.byNullableEnumList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<E?>
) where E: Enum<E>, E: JsonEnum<T> = jsonArrayProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull(casted)
}

inline fun <reified T: Any, reified E> JsonObject.byNullableEnumList(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = jsonArrayProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull<T, E>(casted)
}

inline fun <reified T: Any, reified E> JsonModel.nullableEnumList(
    key: String? = null,
    crossinline default: JsonArrayDefaultSelector<E?>
) where E: Enum<E>, E: JsonEnum<T> = json.jsonArrayProperty(key, default) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull(casted)
}

inline fun <reified T: Any, reified E> JsonModel.nullableEnumList(
    key: String? = null
) where E: Enum<E>, E: JsonEnum<T> = json.jsonArrayProperty(key) {
    val casted = it.primitive.cast<T>()

    findEnumMemberOrNull<T, E>(casted)
}
