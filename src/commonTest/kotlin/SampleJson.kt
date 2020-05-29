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
