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

package tests

import SampleModel
import blue.starry.jsonkt.asJsonElement
import blue.starry.jsonkt.jsonArrayOf
import blue.starry.jsonkt.jsonObjectOf
import blue.starry.jsonkt.parseObject
import json
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class ModelTest {
    private val model = json.parseObject { SampleModel(it) }

    @Test fun intTest() {
        assertEquals(model.intValue, 1)
        assertEquals(model.nullableIntValue, null)
        assertEquals(model.intListValue, listOf(2, 3, 5, 7))
        assertEquals(model.nullableIntListValue, listOf(1, null, 3))
    }

    @Test fun floatTest() {
        assertEquals(model.floatValue, 2.3f)
        assertEquals(model.nullableFloatValue, 4.5f)
    }

    @Test fun doubleTest() {
        assertEquals(model.doubleValue, 2.0001)
        assertEquals(model.nullableDoubleValue, 4.0001)
    }

    @Test fun stringTest() {
        assertEquals(model.stringValue, "hoge")
        assertEquals(model.nullableStringValue, "null")
    }

    @Test fun jsonObjectTest() {
        assertEquals(model.jsonObjectValue, jsonObjectOf("x" to 1, "y" to 2))
    }

    @Test fun jsonArrayTest() {
        assertEquals(model.jsonArrayValue, jsonArrayOf(jsonObjectOf(), jsonObjectOf("x" to 2)))
    }

    @Test fun jsonElementTest() {
        assertEquals(model.jsonElementValue, jsonObjectOf())
    }

    @Test fun jsonLiteralTest() {
        assertEquals(model.jsonLiteralValue, true.asJsonElement())
    }

    @Test fun jsonPrimitiveTest() {
        assertEquals(model.jsonPrimitiveValue, JsonPrimitive(123))
    }

    @Test fun modelTest() {
        assertEquals(model.modelValue.x, "1")
        assertEquals(model.modelValue.y, 2.0)
        assertEquals(model.modelValue.z, 3)

        assertEquals(model.modelListValue[0].x, "1")
        assertEquals(model.modelListValue[0].y, 2.0001)
        assertEquals(model.modelListValue[0].z, 3)
        assertEquals(model.modelListValue[1].x, null)
        assertEquals(model.modelListValue[1].y, 20.00001)
        assertEquals(model.modelListValue[1].z, 30)
    }

    @Test fun keyCaseTest() {
        assertEquals(model.lowerCamelCaseModel.lowerCamelCaseKey, "kotlin")
        assertEquals(model.upperCamelCaseModel.upperCamelCaseKey, "kotlin")
        assertEquals(model.snakeCaseModel.snakeCaseKey, "kotlin")
    }

    @Test fun intEnumTest() {
        assertEquals(model.intEnumValue, SampleModel.InnerIntEnum.Default)
        assertEquals(model.intEnumListValue, listOf(SampleModel.InnerIntEnum.One, SampleModel.InnerIntEnum.Two))
    }
}
