package com.portalsoup.wireprotocol.serialization.dto.response.success

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
class ElementRefList: ArrayList<ElementRef> {

    constructor(collection: Collection<ElementRef>): super(collection)
    companion object : ResponseIsType<JsonArray> {
        override fun isType(element: JsonArray): Boolean {
            return element.jsonArray.all { e -> e.jsonObject.keys.all { it.startsWith("element-") } }
        }
    }
}