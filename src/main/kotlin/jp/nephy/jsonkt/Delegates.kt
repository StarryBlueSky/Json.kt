package jp.nephy.jsonkt

import com.google.gson.*
import jp.nephy.jsonkt.delegate.JsonArrayDelegate
import jp.nephy.jsonkt.delegate.JsonDelegate
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.net.URL

fun <T> JsonElement.byLambda(key: String? = null, default: (JsonObject.() -> T)? = null, lambda: JsonElement.() -> T) = JsonDelegate(jsonObject, key, default, lambda)
fun <T> JsonElement.byNullableLambda(key: String? = null, default: (JsonObject.() -> T?)? = null, lambda: JsonElement.() -> T?) = JsonDelegate(jsonObject, key, default, lambda)

fun <T> JsonElement.byLambdaList(key: String? = null, default: (JsonObject.() -> Collection<T>)? = null, lambda: JsonElement.() -> T) = JsonArrayDelegate(jsonObject, key, default, lambda)
fun <T> JsonElement.byNullableLambdaList(key: String? = null, default: (JsonObject.() -> Collection<T?>)? = null, lambda: JsonElement.() -> T?) = JsonArrayDelegate(jsonObject, key, default, lambda)

fun <T> JsonElement.byMap(key: String? = null, default: (JsonObject.() -> List<T>)? = null, operation: (it: Map.Entry<String, JsonElement>) -> T) = JsonArrayDelegate(jsonObject, key, default, null, operation)

inline fun <reified T: JsonModel?> JsonElement.byModel(key: String? = null, noinline default: (JsonObject.() -> T)? = null) = JsonDelegate(jsonObject, key, default, null, T::class.java)
inline fun <reified T: JsonModel?> JsonElement.byModel(vararg args: Any, key: String? = null, noinline default: (JsonObject.() -> T)? = null) = JsonDelegate(jsonObject, key, default, null, T::class.java, *args)
inline fun <reified T: JsonModel?> JsonElement.byModelList(key: String? = null, noinline default: (JsonObject.() -> Collection<T>)? = null) = JsonArrayDelegate(jsonObject, key, default, null, modelClass = T::class.java)
inline fun <reified T: JsonModel?> JsonElement.byModelList(vararg args: Any, key: String? = null, noinline default: (JsonObject.() -> Collection<T>)? = null) = JsonArrayDelegate(jsonObject, key, default, null, modelClass = T::class.java, params = *args)

fun JsonElement.byJsonElement(key: String? = null, default: (JsonObject.() -> JsonElement)? = null) = byLambda(key, default) { jsonElement }
val JsonElement.byJsonElement: JsonDelegate<JsonElement>
    get() = byJsonElement()
fun JsonElement.byNullableJsonElement(key: String? = null, default: (JsonObject.() -> JsonElement?)? = null) = byNullableLambda(key, default) { jsonElement }
val JsonElement.byNullableJsonElement: JsonDelegate<JsonElement?>
    get() = byNullableJsonElement()
fun JsonElement.byJsonElementList(key: String? = null, default: (JsonObject.() -> Collection<JsonElement>)? = null) = byLambdaList(key, default) { jsonElement }
val JsonElement.byJsonElementList: JsonArrayDelegate<JsonElement>
    get() = byJsonElementList()
fun JsonElement.byNullableJsonElementList(key: String? = null, default: (JsonObject.() -> Collection<JsonElement?>)? = null) = byNullableLambdaList(key, default) { jsonElement }
val JsonElement.byNullableJsonElementList: JsonArrayDelegate<JsonElement?>
    get() = byNullableJsonElementList()

fun JsonElement.byJsonObject(key: String? = null, default: (JsonObject.() -> JsonObject)? = null) = byLambda(key, default) { jsonObject }
val JsonElement.byJsonObject: JsonDelegate<JsonObject>
    get() = byJsonObject()
fun JsonElement.byNullableJsonObject(key: String? = null, default: (JsonObject.() -> JsonObject?)? = null) = byNullableLambda(key, default) { jsonObject }
val JsonElement.byNullableJsonObject: JsonDelegate<JsonObject?>
    get() = byNullableJsonObject()
fun JsonElement.byJsonObjectList(key: String? = null, default: (JsonObject.() -> Collection<JsonObject>)? = null) = byLambdaList(key, default) { jsonObject }
val JsonElement.byJsonObjectList: JsonArrayDelegate<JsonObject>
    get() = byJsonObjectList()
fun JsonElement.byNullableJsonObjectList(key: String? = null, default: (JsonObject.() -> Collection<JsonObject?>)? = null) = byNullableLambdaList(key, default) { jsonObject }
val JsonElement.byNullableJsonObjectList: JsonArrayDelegate<JsonObject?>
    get() = byNullableJsonObjectList()

fun JsonElement.byJsonArray(key: String? = null, default: (JsonObject.() -> JsonArray)? = null) = byLambda(key, default) { jsonArray }
val JsonElement.byJsonArray: JsonDelegate<JsonArray>
    get() = byJsonArray()
fun JsonElement.byNullableJsonArray(key: String? = null, default: (JsonObject.() -> JsonArray?)? = null) = byNullableLambda(key, default) { jsonArray }
val JsonElement.byNullableJsonArray: JsonDelegate<JsonArray?>
    get() = byNullableJsonArray()
fun JsonElement.byJsonArrayList(key: String? = null, default: (JsonObject.() -> Collection<JsonArray>)? = null) = byLambdaList(key, default) { jsonArray }
val JsonElement.byJsonArrayList: JsonArrayDelegate<JsonArray>
    get() = byJsonArrayList()
fun JsonElement.byNullableJsonArrayList(key: String? = null, default: (JsonObject.() -> Collection<JsonArray?>)? = null) = byNullableLambdaList(key, default) { jsonArray }
val JsonElement.byNullableJsonArrayList: JsonArrayDelegate<JsonArray?>
    get() = byNullableJsonArrayList()

fun JsonElement.byJsonPrimitive(key: String? = null, default: (JsonObject.() -> JsonPrimitive)? = null) = byLambda(key, default) { jsonPrimitive }
val JsonElement.byJsonPrimitive: JsonDelegate<JsonPrimitive>
    get() = byJsonPrimitive()
fun JsonElement.byNullableJsonPrimitive(key: String? = null, default: (JsonObject.() -> JsonPrimitive?)? = null) = byNullableLambda(key, default) { jsonPrimitive }
val JsonElement.byNullableJsonPrimitive: JsonDelegate<JsonPrimitive?>
    get() = byNullableJsonPrimitive()
fun JsonElement.byJsonPrimitiveList(key: String? = null, default: (JsonObject.() -> Collection<JsonPrimitive>)? = null) = byLambdaList(key, default) { jsonPrimitive }
val JsonElement.byJsonPrimitiveList: JsonArrayDelegate<JsonPrimitive>
    get() = byJsonPrimitiveList()
fun JsonElement.byNullableJsonPrimitiveList(key: String? = null, default: (JsonObject.() -> Collection<JsonPrimitive?>)? = null) = byNullableLambdaList(key, default) { jsonPrimitive }
val JsonElement.byNullableJsonPrimitiveList: JsonArrayDelegate<JsonPrimitive?>
    get() = byNullableJsonPrimitiveList()

fun JsonElement.byJsonNull(key: String? = null, default: (JsonObject.() -> JsonNull)? = null) = byLambda(key, default) { jsonNull }
val JsonElement.byJsonNull: JsonDelegate<JsonNull>
    get() = byJsonNull()
fun JsonElement.byNullableJsonNull(key: String? = null, default: (JsonObject.() -> JsonNull?)? = null) = byNullableLambda(key, default) { jsonNull }
val JsonElement.byNullableJsonNull: JsonDelegate<JsonNull?>
    get() = byNullableJsonNull()
fun JsonElement.byJsonNullList(key: String? = null, default: (JsonObject.() -> Collection<JsonNull>)? = null) = byLambdaList(key, default) { jsonNull }
val JsonElement.byJsonNullList: JsonArrayDelegate<JsonNull>
    get() = byJsonNullList()
fun JsonElement.byNullableJsonNullList(key: String? = null, default: (JsonObject.() -> Collection<JsonNull?>)? = null) = byNullableLambdaList(key, default) { jsonNull }
val JsonElement.byNullableJsonNullList: JsonArrayDelegate<JsonNull?>
    get() = byNullableJsonNullList()

fun JsonElement.byBool(key: String? = null, default: (JsonObject.() -> Boolean)? = null) = byLambda(key, default) { bool }
val JsonElement.byBool: JsonDelegate<Boolean>
    get() = byBool()
fun JsonElement.byNullableBool(key: String? = null, default: (JsonObject.() -> Boolean?)? = null) = byNullableLambda(key, default) { bool }
val JsonElement.byNullableBool: JsonDelegate<Boolean?>
    get() = byNullableBool()
fun JsonElement.byBoolList(key: String? = null, default: (JsonObject.() -> Collection<Boolean>)? = null) = byLambdaList(key, default) { bool }
val JsonElement.byBoolList: JsonArrayDelegate<Boolean>
    get() = byBoolList()
fun JsonElement.byNullableBoolList(key: String? = null, default: (JsonObject.() -> Collection<Boolean?>)? = null) = byNullableLambdaList(key, default) { bool }
val JsonElement.byNullableBoolList: JsonArrayDelegate<Boolean?>
    get() = byNullableBoolList()

fun JsonElement.byByte(key: String? = null, default: (JsonObject.() -> Byte)? = null) = byLambda(key, default) { byte }
val JsonElement.byByte: JsonDelegate<Byte>
    get() = byByte()
fun JsonElement.byNullableByte(key: String? = null, default: (JsonObject.() -> Byte?)? = null) = byNullableLambda(key, default) { byte }
val JsonElement.byNullableByte: JsonDelegate<Byte?>
    get() = byNullableByte()
fun JsonElement.byByteList(key: String? = null, default: (JsonObject.() -> Collection<Byte>)? = null) = byLambdaList(key, default) { byte }
val JsonElement.byByteList: JsonArrayDelegate<Byte>
    get() = byByteList()
fun JsonElement.byNullableByteList(key: String? = null, default: (JsonObject.() -> Collection<Byte?>)? = null) = byNullableLambdaList(key, default) { byte }
val JsonElement.byNullableByteList: JsonArrayDelegate<Byte?>
    get() = byNullableByteList()

fun JsonElement.byChar(key: String? = null, default: (JsonObject.() -> Char)? = null) = byLambda(key, default) { char }
val JsonElement.byChar: JsonDelegate<Char>
    get() = byChar()
fun JsonElement.byNullableChar(key: String? = null, default: (JsonObject.() -> Char?)? = null) = byNullableLambda(key, default) { char }
val JsonElement.byNullableChar: JsonDelegate<Char?>
    get() = byNullableChar()
fun JsonElement.byCharList(key: String? = null, default: (JsonObject.() -> Collection<Char>)? = null) = byLambdaList(key, default) { char }
val JsonElement.byCharList: JsonArrayDelegate<Char>
    get() = byCharList()
fun JsonElement.byNullableCharList(key: String? = null, default: (JsonObject.() -> Collection<Char?>)? = null) = byNullableLambdaList(key, default) { char }
val JsonElement.byNullableCharList: JsonArrayDelegate<Char?>
    get() = byNullableCharList()

fun JsonElement.byShort(key: String? = null, default: (JsonObject.() -> Short)? = null) = byLambda(key, default) { short }
val JsonElement.byShort: JsonDelegate<Short>
    get() = byShort()
fun JsonElement.byNullableShort(key: String? = null, default: (JsonObject.() -> Short?)? = null) = byNullableLambda(key, default) { short }
val JsonElement.byNullableShort: JsonDelegate<Short?>
    get() = byNullableShort()
fun JsonElement.byShortList(key: String? = null, default: (JsonObject.() -> Collection<Short>)? = null) = byLambdaList(key, default) { short }
val JsonElement.byShortList: JsonArrayDelegate<Short>
    get() = byShortList()
fun JsonElement.byNullableShortList(key: String? = null, default: (JsonObject.() -> Collection<Short?>)? = null) = byNullableLambdaList(key, default) { short }
val JsonElement.byNullableShortList: JsonArrayDelegate<Short?>
    get() = byNullableShortList()

fun JsonElement.byInt(key: String? = null, default: (JsonObject.() -> Int)? = null) = byLambda(key, default) { int }
val JsonElement.byInt: JsonDelegate<Int>
    get() = byInt()
fun JsonElement.byNullableInt(key: String? = null, default: (JsonObject.() -> Int?)? = null) = byNullableLambda(key, default) { int }
val JsonElement.byNullableInt: JsonDelegate<Int?>
    get() = byNullableInt()
fun JsonElement.byIntList(key: String? = null, default: (JsonObject.() -> Collection<Int>)? = null) = byLambdaList(key, default) { int }
val JsonElement.byIntList: JsonArrayDelegate<Int>
    get() = byIntList()
fun JsonElement.byNullableIntList(key: String? = null, default: (JsonObject.() -> Collection<Int?>)? = null) = byNullableLambdaList(key, default) { int }
val JsonElement.byNullableIntList: JsonArrayDelegate<Int?>
    get() = byNullableIntList()

fun JsonElement.byLong(key: String? = null, default: (JsonObject.() -> Long)? = null) = byLambda(key, default) { long }
val JsonElement.byLong: JsonDelegate<Long>
    get() = byLong()
fun JsonElement.byNullableLong(key: String? = null, default: (JsonObject.() -> Long?)? = null) = byNullableLambda(key, default) { long }
val JsonElement.byNullableLong: JsonDelegate<Long?>
    get() = byNullableLong()
fun JsonElement.byLongList(key: String? = null, default: (JsonObject.() -> Collection<Long>)? = null) = byLambdaList(key, default) { long }
val JsonElement.byLongList: JsonArrayDelegate<Long>
    get() = byLongList()
fun JsonElement.byNullableLongList(key: String? = null, default: (JsonObject.() -> Collection<Long?>)? = null) = byNullableLambdaList(key, default) { long }
val JsonElement.byNullableLongList: JsonArrayDelegate<Long?>
    get() = byNullableLongList()

fun JsonElement.byBigInteger(key: String? = null, default: (JsonObject.() -> BigInteger)? = null) = byLambda(key, default) { bigInteger }
val JsonElement.byBigInteger: JsonDelegate<BigInteger>
    get() = byBigInteger()
fun JsonElement.byNullableBigInteger(key: String? = null, default: (JsonObject.() -> BigInteger?)? = null) = byNullableLambda(key, default) { bigInteger }
val JsonElement.byNullableBigInteger: JsonDelegate<BigInteger?>
    get() = byNullableBigInteger()
fun JsonElement.byBigIntegerList(key: String? = null, default: (JsonObject.() -> Collection<BigInteger>)? = null) = byLambdaList(key, default) { bigInteger }
val JsonElement.byBigIntegerList: JsonArrayDelegate<BigInteger>
    get() = byBigIntegerList()
fun JsonElement.byNullableBigIntegerList(key: String? = null, default: (JsonObject.() -> Collection<BigInteger?>)? = null) = byNullableLambdaList(key, default) { bigInteger }
val JsonElement.byNullableBigIntegerList: JsonArrayDelegate<BigInteger?>
    get() = byNullableBigIntegerList()

fun JsonElement.byFloat(key: String? = null, default: (JsonObject.() -> Float)? = null) = byLambda(key, default) { float }
val JsonElement.byFloat: JsonDelegate<Float>
    get() = byFloat()
fun JsonElement.byNullableFloat(key: String? = null, default: (JsonObject.() -> Float?)? = null) = byNullableLambda(key, default) { float }
val JsonElement.byNullableFloat: JsonDelegate<Float?>
    get() = byNullableFloat()
fun JsonElement.byFloatList(key: String? = null, default: (JsonObject.() -> Collection<Float>)? = null) = byLambdaList(key, default) { float }
val JsonElement.byFloatList: JsonArrayDelegate<Float>
    get() = byFloatList()
fun JsonElement.byNullableFloatList(key: String? = null, default: (JsonObject.() -> Collection<Float?>)? = null) = byNullableLambdaList(key, default) { float }
val JsonElement.byNullableFloatList: JsonArrayDelegate<Float?>
    get() = byNullableFloatList()

fun JsonElement.byDouble(key: String? = null, default: (JsonObject.() -> Double)? = null) = byLambda(key, default) { double }
val JsonElement.byDouble: JsonDelegate<Double>
    get() = byDouble()
fun JsonElement.byNullableDouble(key: String? = null, default: (JsonObject.() -> Double?)? = null) = byNullableLambda(key, default) { double }
val JsonElement.byNullableDouble: JsonDelegate<Double?>
    get() = byNullableDouble()
fun JsonElement.byDoubleList(key: String? = null, default: (JsonObject.() -> Collection<Double>)? = null) = byLambdaList(key, default) { double }
val JsonElement.byDoubleList: JsonArrayDelegate<Double>
    get() = byDoubleList()
fun JsonElement.byNullableDoubleList(key: String? = null, default: (JsonObject.() -> Collection<Double?>)? = null) = byNullableLambdaList(key, default) { double }
val JsonElement.byNullableDoubleList: JsonArrayDelegate<Double?>
    get() = byNullableDoubleList()

fun JsonElement.byBigDecimal(key: String? = null, default: (JsonObject.() -> BigDecimal)? = null) = byLambda(key, default) { bigDecimal }
val JsonElement.byBigDecimal: JsonDelegate<BigDecimal>
    get() = byBigDecimal()
fun JsonElement.byNullableBigDecimal(key: String? = null, default: (JsonObject.() -> BigDecimal?)? = null) = byNullableLambda(key, default) { bigDecimal }
val JsonElement.byNullableBigDecimal: JsonDelegate<BigDecimal?>
    get() = byNullableBigDecimal()
fun JsonElement.byBigDecimalList(key: String? = null, default: (JsonObject.() -> Collection<BigDecimal>)? = null) = byLambdaList(key, default) { bigDecimal }
val JsonElement.byBigDecimalList: JsonArrayDelegate<BigDecimal>
    get() = byBigDecimalList()
fun JsonElement.byNullableBigDecimalList(key: String? = null, default: (JsonObject.() -> Collection<BigDecimal?>)? = null) = byNullableLambdaList(key, default) { bigDecimal }
val JsonElement.byNullableBigDecimalList: JsonArrayDelegate<BigDecimal?>
    get() = byNullableBigDecimalList()

fun JsonElement.byString(key: String? = null, default: (JsonObject.() -> String)? = null) = byLambda(key, default) { string }
val JsonElement.byString: JsonDelegate<String>
    get() = byString()
fun JsonElement.byNullableString(key: String? = null, default: (JsonObject.() -> String?)? = null) = byNullableLambda(key, default) { string }
val JsonElement.byNullableString: JsonDelegate<String?>
    get() = byNullableString()
fun JsonElement.byStringList(key: String? = null, default: (JsonObject.() -> Collection<String>)? = null) = byLambdaList(key, default) { string }
val JsonElement.byStringList: JsonArrayDelegate<String>
    get() = byStringList()
fun JsonElement.byNullableStringList(key: String? = null, default: (JsonObject.() -> Collection<String?>)? = null) = byNullableLambdaList(key, default) { string }
val JsonElement.byNullableStringList: JsonArrayDelegate<String?>
    get() = byNullableStringList()

fun JsonElement.byUri(key: String? = null, default: (JsonObject.() -> URI)? = null) = byLambda(key, default) { uri }
val JsonElement.byUri: JsonDelegate<URI>
    get() = byUri()
fun JsonElement.byNullableUri(key: String? = null, default: (JsonObject.() -> URI?)? = null) = byNullableLambda(key, default) { uri }
val JsonElement.byNullableUri: JsonDelegate<URI?>
    get() = byNullableUri()
fun JsonElement.byUriList(key: String? = null, default: (JsonObject.() -> Collection<URI>)? = null) = byLambdaList(key, default) { uri }
val JsonElement.byUriList: JsonArrayDelegate<URI>
    get() = byUriList()
fun JsonElement.byNullableUriList(key: String? = null, default: (JsonObject.() -> Collection<URI?>)? = null) = byNullableLambdaList(key, default) { uri }
val JsonElement.byNullableURIList: JsonArrayDelegate<URI?>
    get() = byNullableUriList()

fun JsonElement.byUrl(key: String? = null, default: (JsonObject.() -> URL)? = null) = byLambda(key, default) { url }
val JsonElement.byUrl: JsonDelegate<URL>
    get() = byUrl()
fun JsonElement.byNullableUrl(key: String? = null, default: (JsonObject.() -> URL?)? = null) = byNullableLambda(key, default) { url }
val JsonElement.byNullableUrl: JsonDelegate<URL?>
    get() = byNullableUrl()
fun JsonElement.byUrlList(key: String? = null, default: (JsonObject.() -> Collection<URL>)? = null) = byLambdaList(key, default) { url }
val JsonElement.byUrlList: JsonArrayDelegate<URL>
    get() = byUrlList()
fun JsonElement.byNullableUrlList(key: String? = null, default: (JsonObject.() -> Collection<URL?>)? = null) = byNullableLambdaList(key, default) { url }
val JsonElement.byNullableUrlList: JsonArrayDelegate<URL?>
    get() = byNullableUrlList()
