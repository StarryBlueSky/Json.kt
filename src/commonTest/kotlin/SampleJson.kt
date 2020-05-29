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

// language=JSON
const val json: String = """{
    "i": 1,
    "ni": null,
    "li": [2,3,5,7],
    "nli": [1,null,3],
    
    "f": 2.3,
    "nf": 4.5,
    
    "d": 2.0001,
    "nd": 4.0001,
    
    "c": "x",
    "nc": "y",
    
    "s": "hoge",
    "ns": "null",
    
    "o": {
        "x": 1,
        "y": 2
    },
    
    "a": [
        {
        },
        {
            "x": 2
        }
    ],
    
    "e": {},
    
    "l": true,
    
    "p": 123,

    "m": {
        "x": "1",
        "y": 2.0,
        "z": 3
    },
    "ml": [
        {
            "x": "1",
            "y": 2.0001,
            "z": 3
        },
        {
            "x": null,
            "y": 20.00001,
            "z": 30
        }
    ],
    
    "lcc": {
        "lowerCamelCaseKey": "kotlin"
    },
    "ucc": {
        "UpperCamelCaseKey": "kotlin"
    },
    "sc": {
        "snake_case_key": "kotlin"
    },
    
    "ie": 4,
    "lie": [1, 2]
}"""
