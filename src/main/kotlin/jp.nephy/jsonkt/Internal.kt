package jp.nephy.jsonkt

@PublishedApi
internal inline fun <T, R> T?.runSafely(block: T.() -> R?): R? {
    if (this == null) {
        return null
    }

    return runCatching(block).getOrNull()
}
