package tests

import SampleModel
import jp.nephy.jsonkt.*
import kotlinx.serialization.json.JsonPrimitive
import json
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

    fun charTest() {
        assertEquals(model.charValue, 'x')
        assertEquals(model.nullableCharValue, 'y')
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
