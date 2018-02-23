package jp.nephy.jsonkt

fun main(args: Array<String>) {
    println(JsonKt.toModelString("{\"x\": 4, \"y\": 6, \"z\": 0.1, \"url\": \"https://www.google.co.jp\", \"array\": [1, 2, 3], \"array3\": [9, 19, 29], \"innermodel\": {\"deep\": \"You found me!\"}, \"objectsArray\": [{\"key\": \"a\"}, {\"key\": \"b\"}], \"valueRequiredModel\": {\"a\": 4}}"))

    DelegateTest()
}
