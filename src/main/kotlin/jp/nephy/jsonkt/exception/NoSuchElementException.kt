package jp.nephy.jsonkt.exception

class NoSuchElementException(key: String): IllegalStateException("\"$key\" is not found in json.")
