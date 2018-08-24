package jp.nephy.jsonkt

import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.io.File
import java.nio.file.Path
import kotlin.reflect.KClass

private typealias Gson = GsonBuilder.() -> Unit
private typealias JsonMap = Map<String, Any?>
private typealias JsonPair = Pair<String, Any?>

/*
 * toJsonString
 */

fun JsonElement.toJsonString(builder: Gson? = null): String {
    return gson(builder).toJson(this)
}
fun JsonKt.Companion.toJsonString(source: JsonElement, builder: Gson? = null): String {
    return source.toJsonString(builder)
}

fun JsonModel.toJsonString(builder: Gson? = null): String {
    return json.toJsonString(builder)
}
fun JsonKt.Companion.toJsonString(source: JsonModel, builder: Gson? = null): String {
    return source.toJsonString(builder)
}

fun JsonMap.toJsonString(builder: Gson? = null): String {
    return gson(builder).toJson(this)
}
fun JsonKt.Companion.toJsonString(source: JsonMap, builder: Gson? = null): String {
    return source.toJsonString(builder)
}

fun Iterable<JsonPair>.toJsonString(builder: Gson? = null): String {
    return toMap().toJsonString(builder)
}
fun JsonKt.Companion.toJsonString(source: Iterable<JsonPair>, builder: Gson? = null): String {
    return source.toJsonString(builder)
}

fun Array<JsonPair>.toJsonString(builder: Gson? = null): String {
    return toMap().toJsonString(builder)
}
fun JsonKt.Companion.toJsonString(vararg source: JsonPair, builder: Gson? = null): String {
    return source.toMap().toJsonString(builder)
}

/*
 * toModelString
 */

fun JsonObject.toModelString(modelName: String? = null, typeStrict: Boolean? = null): String {
    return JsonToKotlinClass(this).convert(modelName, typeStrict)
}
fun JsonKt.Companion.toModelString(source: JsonObject, modelName: String? = null, typeStrict: Boolean? = null): String {
    return source.toModelString(modelName, typeStrict)
}

fun String.toModelString(modelName: String? = null, typeStrict: Boolean? = null, builder: Gson? = null): String {
    return JsonToKotlinClass(toJsonObject(builder)).convert(modelName, typeStrict)
}
fun JsonKt.Companion.toModelString(source: String, modelName: String? = null, typeStrict: Boolean? = null): String {
    return source.toModelString(modelName, typeStrict)
}

fun File.toModelString(modelName: String? = null, typeStrict: Boolean? = null, builder: Gson? = null): String {
    return toJsonObject(builder).toModelString(modelName, typeStrict)
}
fun JsonKt.Companion.toModelString(source: File, modelName: String? = null, typeStrict: Boolean? = null): String {
    return source.toModelString(modelName, typeStrict)
}

fun Path.toModelString(modelName: String? = null, typeStrict: Boolean? = null): String {
    return toFile().toModelString(modelName, typeStrict)
}
fun JsonKt.Companion.toModelString(source: Path, modelName: String? = null, typeStrict: Boolean? = null): String {
    return source.toModelString(modelName, typeStrict)
}

/*
 * toJsonObject
 */

fun String.toJsonObject(builder: Gson? = null): JsonObject {
    return gson(builder).fromJson(this, JsonObject::class.java)
}
fun JsonKt.Companion.toJsonObject(source: String, builder: Gson? = null): JsonObject {
    return source.toJsonObject(builder)
}

fun JsonMap.toJsonObject(builder: Gson? = null): JsonObject {
    return toJsonString(builder).toJsonObject(builder)
}
fun JsonKt.Companion.toJsonObject(source: JsonMap, builder: Gson? = null): JsonObject {
    return source.toJsonObject(builder)
}

fun Iterable<JsonPair>.toJsonObject(builder: Gson? = null): JsonObject {
    return toJsonString(builder).toJsonObject(builder)
}
fun JsonKt.Companion.toJsonObject(source: Iterable<JsonPair>, builder: Gson? = null): JsonObject {
    return source.toJsonObject(builder)
}

fun Array<JsonPair>.toJsonObject(builder: Gson? = null): JsonObject {
    return toJsonString(builder).toJsonObject(builder)
}
fun JsonKt.Companion.toJsonObject(vararg source: JsonPair, builder: Gson? = null): JsonObject {
    return source.toMap().toJsonObject(builder)
}

fun File.toJsonObject(builder: Gson? = null): JsonObject {
    return reader().use {
        it.readText().toJsonObject(builder)
    }
}
fun JsonKt.Companion.toJsonObject(source: File, builder: Gson? = null): JsonObject {
    return source.toJsonObject(builder)
}

fun Path.toJsonObject(builder: Gson? = null): JsonObject {
    return toFile().toJsonObject(builder)
}
fun JsonKt.Companion.toJsonObject(source: Path, builder: Gson? = null): JsonObject {
    return source.toJsonObject(builder)
}

/*
 * toJsonArray
 */

fun String.toJsonArray(builder: Gson? = null): JsonArray {
    return gson(builder).fromJson(this, JsonArray::class.java)
}
fun JsonKt.Companion.toJsonArray(source: String, builder: Gson? = null): JsonArray {
    return source.toJsonArray(builder)
}

fun JsonMap.toJsonArray(builder: Gson? = null): JsonArray {
    return toJsonString(builder).toJsonArray(builder)
}
fun JsonKt.Companion.toJsonArray(source: JsonMap, builder: Gson? = null): JsonArray {
    return source.toJsonArray(builder)
}

fun Iterable<JsonPair>.toJsonArray(builder: Gson? = null): JsonArray {
    return toJsonString(builder).toJsonArray(builder)
}
fun JsonKt.Companion.toJsonArray(source: Iterable<JsonPair>, builder: Gson? = null): JsonArray {
    return source.toJsonArray(builder)
}

fun Array<JsonPair>.toJsonArray(builder: Gson? = null): JsonArray {
    return toJsonString(builder).toJsonArray(builder)
}
fun JsonKt.Companion.toJsonArray(vararg source: JsonPair, builder: Gson? = null): JsonArray {
    return source.toMap().toJsonArray(builder)
}

fun File.toJsonArray(builder: Gson? = null): JsonArray {
    return reader().use { 
        it.readText().toJsonArray(builder)
    }
}
fun JsonKt.Companion.toJsonArray(source: File, builder: Gson? = null): JsonArray {
    return source.toJsonArray(builder)
}

fun Path.toJsonArray(builder: Gson? = null): JsonArray {
    return toFile().toJsonArray(builder)
}
fun JsonKt.Companion.toJsonArray(source: Path, builder: Gson? = null): JsonArray {
    return source.toJsonArray(builder)
}

/*
 * parse
 */

fun <T: JsonModel> JsonObject.parse(model: Class<T>): T {
    return model.getConstructor(JsonObject::class.java).newInstance(this)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: JsonObject, model: Class<T>): T {
    return source.parse(model)
}

fun <T: JsonModel> JsonObject.parse(model: KClass<T>): T {
    return parse(model.java)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: JsonObject, model: KClass<T>): T {
    return source.parse(model)
}

fun <T: JsonModel> String.parse(model: Class<T>, builder: Gson? = null): T {
    return toJsonObject(builder).parse(model)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: String, model: Class<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> String.parse(model: KClass<T>, builder: Gson? = null): T {
    return parse(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: String, model: KClass<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> JsonMap.parse(model: Class<T>, builder: Gson? = null): T {
    return toJsonObject(builder).parse(model)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: JsonMap, model: Class<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> JsonMap.parse(model: KClass<T>, builder: Gson? = null): T {
    return parse(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: JsonMap, model: KClass<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> Iterable<JsonPair>.parse(model: Class<T>, builder: Gson? = null): T {
    return toJsonObject(builder).parse(model)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: Iterable<JsonPair>, model: Class<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> Iterable<JsonPair>.parse(model: KClass<T>, builder: Gson? = null): T {
    return parse(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: Iterable<JsonPair>, model: KClass<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> Array<JsonPair>.parse(model: Class<T>, builder: Gson? = null): T {
    return toJsonObject(builder).parse(model)
}
fun <T: JsonModel> JsonKt.Companion.parse(vararg source: JsonPair, model: Class<T>, builder: Gson? = null): T {
    return source.toMap().parse(model, builder)
}

fun <T: JsonModel> Array<JsonPair>.parse(model: KClass<T>, builder: Gson? = null): T {
    return parse(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(vararg source: JsonPair, model: KClass<T>, builder: Gson? = null): T {
    return source.toMap().parse(model, builder)
}

fun <T: JsonModel> File.parse(model: Class<T>, builder: Gson? = null): T {
    return reader().use { 
        it.readText().parse(model, builder)
    }
}
fun <T: JsonModel> JsonKt.Companion.parse(source: File, model: Class<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> File.parse(model: KClass<T>, builder: Gson? = null): T {
    return parse(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: File, model: KClass<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> Path.parse(model: Class<T>, builder: Gson? = null): T {
    return toFile().parse(model, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: Path, model: Class<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

fun <T: JsonModel> Path.parse(model: KClass<T>, builder: Gson? = null): T {
    return parse(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parse(source: Path, model: KClass<T>, builder: Gson? = null): T {
    return source.parse(model, builder)
}

/*
 * inline parse
 */

inline fun <reified T: JsonModel> JsonObject.parse(): T {
    return parse(T::class.java)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(source: JsonObject): T {
    return source.parse(T::class.java)
}

inline fun <reified T: JsonModel> String.parse(noinline builder: Gson? = null): T {
    return parse(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(source: String, noinline builder: Gson? = null): T {
    return source.parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> JsonMap.parse(noinline builder: Gson? = null): T {
    return parse(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(source: JsonMap, noinline builder: Gson? = null): T {
    return source.parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Iterable<JsonPair>.parse(noinline builder: Gson? = null): T {
    return parse(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(source: Iterable<JsonPair>, noinline builder: Gson? = null): T {
    return source.parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Array<JsonPair>.parse(noinline builder: Gson? = null): T {
    return parse(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(vararg source: JsonPair, noinline builder: Gson? = null): T {
    return source.toMap().parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> File.parse(noinline builder: Gson? = null): T {
    return parse(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(source: File, noinline builder: Gson? = null): T {
    return source.parse(T::class.java, builder)
}

inline fun <reified T: JsonModel> Path.parse(noinline builder: Gson? = null): T {
    return parse(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parse(source: Path, noinline builder: Gson? = null): T {
    return source.parse(T::class.java, builder)
}

/*
 * parseList
 */

fun <T: JsonModel> JsonArray.parseList(model: Class<T>): List<T> {
    return map { model.getConstructor(JsonObject::class.java).newInstance(this) }
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: JsonArray, model: Class<T>): List<T> {
    return source.parseList(model)
}

fun <T: JsonModel> JsonArray.parseList(model: KClass<T>): List<T> {
    return parseList(model.java)
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: JsonArray, model: KClass<T>): List<T> {
    return source.parseList(model)
}

fun <T: JsonModel> String.parseList(model: Class<T>, builder: Gson? = null): List<T> {
    return toJsonArray(builder).parseList(model)
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: String, model: Class<T>, builder: Gson? = null): List<T> {
    return source.parseList(model, builder)
}

fun <T: JsonModel> String.parseList(model: KClass<T>, builder: Gson? = null): List<T> {
    return parseList(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: String, model: KClass<T>, builder: Gson? = null): List<T> {
    return source.parseList(model, builder)
}

fun <T: JsonModel> File.parseList(model: Class<T>, builder: Gson? = null): List<T> {
    return reader().use {
        it.readText().parseList(model, builder)
    }
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: File, model: Class<T>, builder: Gson? = null): List<T> {
    return source.parseList(model, builder)
}

fun <T: JsonModel> File.parseList(model: KClass<T>, builder: Gson? = null): List<T> {
    return parseList(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: File, model: KClass<T>, builder: Gson? = null): List<T> {
    return source.parseList(model, builder)
}

fun <T: JsonModel> Path.parseList(model: Class<T>, builder: Gson? = null): List<T> {
    return toFile().parseList(model, builder)
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: Path, model: Class<T>, builder: Gson? = null): List<T> {
    return source.parseList(model, builder)
}

fun <T: JsonModel> Path.parseList(model: KClass<T>, builder: Gson? = null): List<T> {
    return parseList(model.java, builder)
}
fun <T: JsonModel> JsonKt.Companion.parseList(source: Path, model: KClass<T>, builder: Gson? = null): List<T> {
    return source.parseList(model, builder)
}

/*
 * inline parse
 */

inline fun <reified T: JsonModel> JsonArray.parseList(): List<T> {
    return parseList(T::class.java)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parseList(source: JsonArray): List<T> {
    return source.parseList(T::class.java)
}

inline fun <reified T: JsonModel> String.parseList(noinline builder: Gson? = null): List<T> {
    return parseList(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parseList(source: String, noinline builder: Gson? = null): List<T> {
    return source.parseList(T::class.java, builder)
}

inline fun <reified T: JsonModel> File.parseList(noinline builder: Gson? = null): List<T> {
    return parseList(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parseList(source: File, noinline builder: Gson? = null): List<T> {
    return source.parseList(T::class.java, builder)
}

inline fun <reified T: JsonModel> Path.parseList(noinline builder: Gson? = null): List<T> {
    return parseList(T::class.java, builder)
}
inline fun <reified T: JsonModel> JsonKt.Companion.parseList(source: Path, noinline builder: Gson? = null): List<T> {
    return source.parseList(T::class.java, builder)
}
