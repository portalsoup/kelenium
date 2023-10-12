package com.portalsoup.wireprotocol.element.dto

import com.portalsoup.wireprotocol.response.BaseSuccess
import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

// simple idea for an element
@Serializable
data class ElementRef(val identifier: String, val reference: String): BaseSuccess() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject
                .takeIf { it.isNotEmpty() }
                ?.keys
                ?.all { it.startsWith("element-") }
                ?: false
        }
    }
}