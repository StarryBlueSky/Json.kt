package jp.nephy.jsonkt

import kotlin.reflect.KProperty

actual val KProperty<*>.returnsNullable: Boolean
    // TODO: Wait for Reflection API implementation
    //         `returnType.isMarkedNullable`
    get() = true
