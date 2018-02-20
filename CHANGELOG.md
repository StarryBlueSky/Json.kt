### v 1.1  
- JsonObject#byModelで引数をモデルクラスに渡せるようになった.  
    ```kotlin
    class ExampleModel(override val json: JsonObject): JsonModel {
        class InnerModel(x: Int, y: String, override val json: JsonObject): JsonModel {
            val a by json.byLambda { int * x }
            val b by json.byLambda { string + y }
        }

        val obj by json.byModel<InnerModel>(10, "!")
    }

    fun main(args: Array<String>) {
        val model = JsonKt.parse<ExampleModel>("{\"obj\": {\"a\": 10, \"b\": \"Kotlin\"}}")

        println(model.obj.a)  // 10 * 10 = 100
        println(model.obj.b)  // Kotlin!
    }
    ```
- JsonObject#mapを追加  
    ```kotlin
    val model = JsonKt.parse<ExampleModel>("{\"obj\": {\"a\": 10, \"b\": \"Kotlin\"}}")
    model.obj.json.map {
        println(it.key)  // a or b
        println(it.value)  // 10 or Kotlin
    }
    ```
- JsonElement, JsonObjectに関する拡張関数, フィールドを追加  
- Platform型が露出している問題を修正  

### v 1.0  
- 初版リリース  
