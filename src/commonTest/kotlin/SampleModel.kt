/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2020 StarryBlueSky
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

import blue.starry.jsonkt.JsonObject
import blue.starry.jsonkt.delegation.*

@Suppress("Unused", "KDocMissingDocumentation", "PublicApiImplicitType")
data class SampleModel(override val json: JsonObject): JsonModel {
    val intValue by int("i")
    val nullableIntValue by nullableInt("ni")
    val intListValue by intList("li")
    val nullableIntListValue by nullableIntList("nli")

    val floatValue by float("f")
    val nullableFloatValue by nullableFloat("nf")
//    val floatListValue by floatList("lf")
//    val nullableFloatListValue by nullableFloatList("nlf")

    val doubleValue by double("d")
    val nullableDoubleValue by nullableDouble("nd")
//    val doubleListValue by doubleList("ld")
//    val nullableDoubleListValue by nullableDoubleList("nld")

    val charValue by char("c")
    val nullableCharValue by nullableChar("nc")
//    val charListValue by charList("lc")
//    val nullableCharListValue by nullableCharList("nlc")

    val stringValue by string("s")
    val nullableStringValue by nullableString("ns")
//    val stringListValue by stringList("ls")
//    val nullableStringListValue by nullableStringList("nls")

    val jsonObjectValue by jsonElement("o")

    val jsonArrayValue by jsonElement("a")

    val jsonElementValue by jsonElement("e")

    val jsonLiteralValue by jsonLiteral("l")

    val jsonPrimitiveValue by jsonPrimitive("p")

    val modelValue by model("m") { InnerModel(it) }
    val modelListValue by modelList("ml") { InnerModel(it) }

    data class InnerModel(override val json: JsonObject): JsonModel {
        val x by nullableString
        val y by double
        val z by int
    }

    val lowerCamelCaseModel by model("lcc") { LowerCamelCaseKey(it) }

    data class LowerCamelCaseKey(override val json: JsonObject): JsonModel {
        override val keyCase: JsonKeyCase = JsonKeyCase.LowerCamelCase

        val lowerCamelCaseKey by string
    }

    val upperCamelCaseModel by model("ucc") { UpperCamelCaseKey(it) }

    data class UpperCamelCaseKey(override val json: JsonObject): JsonModel {
        override val keyCase: JsonKeyCase = JsonKeyCase.UpperCamelCase

        val upperCamelCaseKey by string
    }

    val snakeCaseModel by model("sc") { SnakeCaseKey(it) }

    data class SnakeCaseKey(override val json: JsonObject): JsonModel {
        override val keyCase: JsonKeyCase = JsonKeyCase.SnakeCase

        val snakeCaseKey by string
    }

    val intEnumValue by enum("ie") { InnerIntEnum.Default }
    val intEnumListValue by enumList<Int, InnerIntEnum>("lie")

    enum class InnerIntEnum(override val value: Int): IntJsonEnum {
        One(1), Two(2), Three(3), Default(4)
    }
}
