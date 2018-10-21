package jp.nephy.jsonkt.delegation

import jp.nephy.jsonkt.ImmutableJsonObject

interface JsonModel {
    val json: ImmutableJsonObject
}
