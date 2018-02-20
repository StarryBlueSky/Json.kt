package jp.nephy.jsonkt

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import jp.nephy.jsonkt.delegate.JsonArrayDelegate
import jp.nephy.jsonkt.delegate.JsonDelegate
import java.math.BigDecimal
import java.math.BigInteger
import java.net.URI
import java.net.URL

fun <T> JsonObject.byLambda(key: String? = null, default: (JsonObject.() -> T)? = null, lambda: JsonElement.() -> T) = JsonDelegate(jsonObject, key, default, lambda)
fun <T> JsonObject.byNullableLambda(key: String? = null, default: (JsonObject.() -> T?)? = null, lambda: JsonElement.() -> T?) = JsonDelegate(jsonObject, key, default, lambda)

fun <T> JsonObject.byLambdaList(key: String? = null, default: (JsonObject.() -> Collection<T>)? = null, lambda: JsonElement.() -> T) = JsonArrayDelegate(jsonObject, key, default, lambda)
fun <T> JsonObject.byNullableLambdaList(key: String? = null, default: (JsonObject.() -> Collection<T?>)? = null, lambda: JsonElement.() -> T?) = JsonArrayDelegate(jsonObject, key, default, lambda)

fun <T> JsonObject.byMap(key: String? = null, default: (JsonObject.() -> List<T>)? = null, operation: (it: Map.Entry<String, JsonElement>) -> T) = JsonArrayDelegate(jsonObject, key, default, null, operation)

inline fun <reified T: JsonModel?> JsonObject.byModel(key: String? = null, noinline default: (JsonObject.() -> T)? = null) = JsonDelegate(jsonObject, key, default, null, T::class.java)
inline fun <reified T: JsonModel?> JsonObject.byModel(vararg args: Any, key: String? = null, noinline default: (JsonObject.() -> T)? = null) = JsonDelegate(jsonObject, key, default, null, T::class.java, *args)
inline fun <reified T: JsonModel?> JsonObject.byModelList(key: String? = null, noinline default: (JsonObject.() -> Collection<T>)? = null) = JsonArrayDelegate(jsonObject, key, default, null, modelClass = T::class.java)
inline fun <reified T: JsonModel?> JsonObject.byModelList(vararg args: Any, key: String? = null, noinline default: (JsonObject.() -> Collection<T>)? = null) = JsonArrayDelegate(jsonObject, key, default, null, modelClass = T::class.java, params = *args)

fun JsonObject.byBool(key: String? = null, default: (JsonObject.() -> Boolean)? = null) = byLambda(key, default) { bool }
val JsonObject.byBool: JsonDelegate<Boolean>
    get() = byBool()
fun JsonObject.byNullableBool(key: String? = null, default: (JsonObject.() -> Boolean?)? = null) = byNullableLambda(key, default) { bool }
val JsonObject.byNullableBool: JsonDelegate<Boolean?>
    get() = byNullableBool()
fun JsonObject.byBoolList(key: String? = null, default: (JsonObject.() -> Collection<Boolean>)? = null) = byLambdaList(key, default) { bool }
val JsonObject.byBoolList: JsonArrayDelegate<Boolean>
    get() = byBoolList()
fun JsonObject.byNullableBoolList(key: String? = null, default: (JsonObject.() -> Collection<Boolean?>)? = null) = byNullableLambdaList(key, default) { bool }
val JsonObject.byNullableBoolList: JsonArrayDelegate<Boolean?>
    get() = byNullableBoolList()

fun JsonObject.byByte(key: String? = null, default: (JsonObject.() -> Byte)? = null) = byLambda(key, default) { byte }
val JsonObject.byByte: JsonDelegate<Byte>
    get() = byByte()
fun JsonObject.byNullableByte(key: String? = null, default: (JsonObject.() -> Byte?)? = null) = byNullableLambda(key, default) { byte }
val JsonObject.byNullableByte: JsonDelegate<Byte?>
    get() = byNullableByte()
fun JsonObject.byByteList(key: String? = null, default: (JsonObject.() -> Collection<Byte>)? = null) = byLambdaList(key, default) { byte }
val JsonObject.byByteList: JsonArrayDelegate<Byte>
    get() = byByteList()
fun JsonObject.byNullableByteList(key: String? = null, default: (JsonObject.() -> Collection<Byte?>)? = null) = byNullableLambdaList(key, default) { byte }
val JsonObject.byNullableByteList: JsonArrayDelegate<Byte?>
    get() = byNullableByteList()

fun JsonObject.byChar(key: String? = null, default: (JsonObject.() -> Char)? = null) = byLambda(key, default) { char }
val JsonObject.byChar: JsonDelegate<Char>
    get() = byChar()
fun JsonObject.byNullableChar(key: String? = null, default: (JsonObject.() -> Char?)? = null) = byNullableLambda(key, default) { char }
val JsonObject.byNullableChar: JsonDelegate<Char?>
    get() = byNullableChar()
fun JsonObject.byCharList(key: String? = null, default: (JsonObject.() -> Collection<Char>)? = null) = byLambdaList(key, default) { char }
val JsonObject.byCharList: JsonArrayDelegate<Char>
    get() = byCharList()
fun JsonObject.byNullableCharList(key: String? = null, default: (JsonObject.() -> Collection<Char?>)? = null) = byNullableLambdaList(key, default) { char }
val JsonObject.byNullableCharList: JsonArrayDelegate<Char?>
    get() = byNullableCharList()

fun JsonObject.byShort(key: String? = null, default: (JsonObject.() -> Short)? = null) = byLambda(key, default) { short }
val JsonObject.byShort: JsonDelegate<Short>
    get() = byShort()
fun JsonObject.byNullableShort(key: String? = null, default: (JsonObject.() -> Short?)? = null) = byNullableLambda(key, default) { short }
val JsonObject.byNullableShort: JsonDelegate<Short?>
    get() = byNullableShort()
fun JsonObject.byShortList(key: String? = null, default: (JsonObject.() -> Collection<Short>)? = null) = byLambdaList(key, default) { short }
val JsonObject.byShortList: JsonArrayDelegate<Short>
    get() = byShortList()
fun JsonObject.byNullableShortList(key: String? = null, default: (JsonObject.() -> Collection<Short?>)? = null) = byNullableLambdaList(key, default) { short }
val JsonObject.byNullableShortList: JsonArrayDelegate<Short?>
    get() = byNullableShortList()

fun JsonObject.byInt(key: String? = null, default: (JsonObject.() -> Int)? = null) = byLambda(key, default) { int }
val JsonObject.byInt: JsonDelegate<Int>
    get() = byInt()
fun JsonObject.byNullableInt(key: String? = null, default: (JsonObject.() -> Int?)? = null) = byNullableLambda(key, default) { int }
val JsonObject.byNullableInt: JsonDelegate<Int?>
    get() = byNullableInt()
fun JsonObject.byIntList(key: String? = null, default: (JsonObject.() -> Collection<Int>)? = null) = byLambdaList(key, default) { int }
val JsonObject.byIntList: JsonArrayDelegate<Int>
    get() = byIntList()
fun JsonObject.byNullableIntList(key: String? = null, default: (JsonObject.() -> Collection<Int?>)? = null) = byNullableLambdaList(key, default) { int }
val JsonObject.byNullableIntList: JsonArrayDelegate<Int?>
    get() = byNullableIntList()

fun JsonObject.byLong(key: String? = null, default: (JsonObject.() -> Long)? = null) = byLambda(key, default) { long }
val JsonObject.byLong: JsonDelegate<Long>
    get() = byLong()
fun JsonObject.byNullableLong(key: String? = null, default: (JsonObject.() -> Long?)? = null) = byNullableLambda(key, default) { long }
val JsonObject.byNullableLong: JsonDelegate<Long?>
    get() = byNullableLong()
fun JsonObject.byLongList(key: String? = null, default: (JsonObject.() -> Collection<Long>)? = null) = byLambdaList(key, default) { long }
val JsonObject.byLongList: JsonArrayDelegate<Long>
    get() = byLongList()
fun JsonObject.byNullableLongList(key: String? = null, default: (JsonObject.() -> Collection<Long?>)? = null) = byNullableLambdaList(key, default) { long }
val JsonObject.byNullableLongList: JsonArrayDelegate<Long?>
    get() = byNullableLongList()

fun JsonObject.byBigInteger(key: String? = null, default: (JsonObject.() -> BigInteger)? = null) = byLambda(key, default) { bigInteger }
val JsonObject.byBigInteger: JsonDelegate<BigInteger>
    get() = byBigInteger()
fun JsonObject.byNullableBigInteger(key: String? = null, default: (JsonObject.() -> BigInteger?)? = null) = byNullableLambda(key, default) { bigInteger }
val JsonObject.byNullableBigInteger: JsonDelegate<BigInteger?>
    get() = byNullableBigInteger()
fun JsonObject.byBigIntegerList(key: String? = null, default: (JsonObject.() -> Collection<BigInteger>)? = null) = byLambdaList(key, default) { bigInteger }
val JsonObject.byBigIntegerList: JsonArrayDelegate<BigInteger>
    get() = byBigIntegerList()
fun JsonObject.byNullableBigIntegerList(key: String? = null, default: (JsonObject.() -> Collection<BigInteger?>)? = null) = byNullableLambdaList(key, default) { bigInteger }
val JsonObject.byNullableBigIntegerList: JsonArrayDelegate<BigInteger?>
    get() = byNullableBigIntegerList()

fun JsonObject.byFloat(key: String? = null, default: (JsonObject.() -> Float)? = null) = byLambda(key, default) { float }
val JsonObject.byFloat: JsonDelegate<Float>
    get() = byFloat()
fun JsonObject.byNullableFloat(key: String? = null, default: (JsonObject.() -> Float?)? = null) = byNullableLambda(key, default) { float }
val JsonObject.byNullableFloat: JsonDelegate<Float?>
    get() = byNullableFloat()
fun JsonObject.byFloatList(key: String? = null, default: (JsonObject.() -> Collection<Float>)? = null) = byLambdaList(key, default) { float }
val JsonObject.byFloatList: JsonArrayDelegate<Float>
    get() = byFloatList()
fun JsonObject.byNullableFloatList(key: String? = null, default: (JsonObject.() -> Collection<Float?>)? = null) = byNullableLambdaList(key, default) { float }
val JsonObject.byNullableFloatList: JsonArrayDelegate<Float?>
    get() = byNullableFloatList()

fun JsonObject.byDouble(key: String? = null, default: (JsonObject.() -> Double)? = null) = byLambda(key, default) { double }
val JsonObject.byDouble: JsonDelegate<Double>
    get() = byDouble()
fun JsonObject.byNullableDouble(key: String? = null, default: (JsonObject.() -> Double?)? = null) = byNullableLambda(key, default) { double }
val JsonObject.byNullableDouble: JsonDelegate<Double?>
    get() = byNullableDouble()
fun JsonObject.byDoubleList(key: String? = null, default: (JsonObject.() -> Collection<Double>)? = null) = byLambdaList(key, default) { double }
val JsonObject.byDoubleList: JsonArrayDelegate<Double>
    get() = byDoubleList()
fun JsonObject.byNullableDoubleList(key: String? = null, default: (JsonObject.() -> Collection<Double?>)? = null) = byNullableLambdaList(key, default) { double }
val JsonObject.byNullableDoubleList: JsonArrayDelegate<Double?>
    get() = byNullableDoubleList()

fun JsonObject.byBigDecimal(key: String? = null, default: (JsonObject.() -> BigDecimal)? = null) = byLambda(key, default) { bigDecimal }
val JsonObject.byBigDecimal: JsonDelegate<BigDecimal>
    get() = byBigDecimal()
fun JsonObject.byNullableBigDecimal(key: String? = null, default: (JsonObject.() -> BigDecimal?)? = null) = byNullableLambda(key, default) { bigDecimal }
val JsonObject.byNullableBigDecimal: JsonDelegate<BigDecimal?>
    get() = byNullableBigDecimal()
fun JsonObject.byBigDecimalList(key: String? = null, default: (JsonObject.() -> Collection<BigDecimal>)? = null) = byLambdaList(key, default) { bigDecimal }
val JsonObject.byBigDecimalList: JsonArrayDelegate<BigDecimal>
    get() = byBigDecimalList()
fun JsonObject.byNullableBigDecimalList(key: String? = null, default: (JsonObject.() -> Collection<BigDecimal?>)? = null) = byNullableLambdaList(key, default) { bigDecimal }
val JsonObject.byNullableBigDecimalList: JsonArrayDelegate<BigDecimal?>
    get() = byNullableBigDecimalList()

fun JsonObject.byString(key: String? = null, default: (JsonObject.() -> String)? = null) = byLambda(key, default) { string }
val JsonObject.byString: JsonDelegate<String>
    get() = byString()
fun JsonObject.byNullableString(key: String? = null, default: (JsonObject.() -> String?)? = null) = byNullableLambda(key, default) { string }
val JsonObject.byNullableString: JsonDelegate<String?>
    get() = byNullableString()
fun JsonObject.byStringList(key: String? = null, default: (JsonObject.() -> Collection<String>)? = null) = byLambdaList(key, default) { string }
val JsonObject.byStringList: JsonArrayDelegate<String>
    get() = byStringList()
fun JsonObject.byNullableStringList(key: String? = null, default: (JsonObject.() -> Collection<String?>)? = null) = byNullableLambdaList(key, default) { string }
val JsonObject.byNullableStringList: JsonArrayDelegate<String?>
    get() = byNullableStringList()

fun JsonObject.byUri(key: String? = null, default: (JsonObject.() -> URI)? = null) = byLambda(key, default) { uri }
val JsonObject.byUri: JsonDelegate<URI>
    get() = byUri()
fun JsonObject.byNullableUri(key: String? = null, default: (JsonObject.() -> URI?)? = null) = byNullableLambda(key, default) { uri }
val JsonObject.byNullableUri: JsonDelegate<URI?>
    get() = byNullableUri()
fun JsonObject.byUriList(key: String? = null, default: (JsonObject.() -> Collection<URI>)? = null) = byLambdaList(key, default) { uri }
val JsonObject.byUriList: JsonArrayDelegate<URI>
    get() = byUriList()
fun JsonObject.byNullableUriList(key: String? = null, default: (JsonObject.() -> Collection<URI?>)? = null) = byNullableLambdaList(key, default) { uri }
val JsonObject.byNullableURIList: JsonArrayDelegate<URI?>
    get() = byNullableUriList()

fun JsonObject.byUrl(key: String? = null, default: (JsonObject.() -> URL)? = null) = byLambda(key, default) { url }
val JsonObject.byUrl: JsonDelegate<URL>
    get() = byUrl()
fun JsonObject.byNullableUrl(key: String? = null, default: (JsonObject.() -> URL?)? = null) = byNullableLambda(key, default) { url }
val JsonObject.byNullableUrl: JsonDelegate<URL?>
    get() = byNullableUrl()
fun JsonObject.byUrlList(key: String? = null, default: (JsonObject.() -> Collection<URL>)? = null) = byLambdaList(key, default) { url }
val JsonObject.byUrlList: JsonArrayDelegate<URL>
    get() = byUrlList()
fun JsonObject.byNullableUrlList(key: String? = null, default: (JsonObject.() -> Collection<URL?>)? = null) = byNullableLambdaList(key, default) { url }
val JsonObject.byNullableUrlList: JsonArrayDelegate<URL?>
    get() = byNullableUrlList()
