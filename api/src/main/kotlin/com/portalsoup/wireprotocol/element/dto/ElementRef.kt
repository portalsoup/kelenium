package com.portalsoup.wireprotocol.element.dto

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import com.portalsoup.wireprotocol.serialization.dto.response.BaseSuccess
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

// simple idea for an element
@Serializable
data class ElementRef(val identifier: String, val reference: String): BaseSuccess() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            println(element::class.simpleName)
            return element.jsonObject
                .takeIf { it.isNotEmpty() }
                ?.keys
                ?.all { it.startsWith("element-") }
                ?: false
        }
    }
}