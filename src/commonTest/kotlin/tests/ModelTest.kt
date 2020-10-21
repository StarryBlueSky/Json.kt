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
import blue.starry.jsonkt.jsonArrayOf
import blue.starry.jsonkt.jsonObjectOf
import blue.starry.jsonkt.parseObject
import json
import kotlinx.serialization.json.JsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals

class ModelTest {
    private val model: SampleModel = run {
        json.parseObject { SampleModel(it) }
    }

    @Test
    fun intTest() {
        assertEquals(1, model.intValue)
        assertEquals(null, model.nullableIntValue)
        assertEquals(listOf(2, 3, 5, 7), model.intListValue)
        assertEquals(listOf(1, null, 3), model.nullableIntListValue)
    }

    @Test
    fun floatTest() {
        assertEquals(2.3f, model.floatValue)
        assertEquals(4.5f, model.nullableFloatValue)
    }

    @Test
    fun doubleTest() {
        assertEquals(2.0001, model.doubleValue)
        assertEquals(4.0001, model.nullableDoubleValue)
    }

    @Test
    fun stringTest() {
        assertEquals("hoge", model.stringValue)
        assertEquals("null", model.nullableStringValue)
    }

    @Test fun jsonObjectTest() {
        assertEquals(jsonObjectOf("x" to 1, "y" to 2), model.jsonObjectValue)
    }

    @Test fun jsonArrayTest() {
        assertEquals(jsonArrayOf(jsonObjectOf(), jsonObjectOf("x" to 2)), model.jsonArrayValue)
    }

    @Test fun jsonElementTest() {
        assertEquals(jsonObjectOf(), model.jsonElementValue)
    }

    @Test fun jsonPrimitiveTest() {
        assertEquals(JsonPrimitive(123), model.jsonPrimitiveValue)
    }

    @Test fun modelTest() {
        assertEquals("1", model.modelValue.x)
        assertEquals(2.0, model.modelValue.y)
        assertEquals(3, model.modelValue.z)

        assertEquals("1", model.modelListValue[0].x)
        assertEquals(2.0001, model.modelListValue[0].y)
        assertEquals(3, model.modelListValue[0].z)
        assertEquals(null, model.modelListValue[1].x)
        assertEquals(20.00001, model.modelListValue[1].y)
        assertEquals(30, model.modelListValue[1].z)
    }

    @Test fun keyCaseTest() {
        assertEquals("kotlin", model.lowerCamelCaseModel.lowerCamelCaseKey)
        assertEquals("kotlin", model.upperCamelCaseModel.upperCamelCaseKey)
        assertEquals("kotlin", model.snakeCaseModel.snakeCaseKey)
    }

    @Test fun intEnumTest() {
        assertEquals(SampleModel.InnerIntEnum.Default, model.intEnumValue)
        assertEquals(listOf(SampleModel.InnerIntEnum.One, SampleModel.InnerIntEnum.Two), model.intEnumListValue)
    }
}
