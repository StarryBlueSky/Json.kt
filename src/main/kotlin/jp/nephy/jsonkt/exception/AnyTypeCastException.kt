package jp.nephy.jsonkt.exception

class AnyTypeCastException(type: String): IllegalStateException("$type cannot be converted to Json.")
