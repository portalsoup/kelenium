package com.portalsoup.wireprotocol.serialization.dto.success

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class NewWindow(val handle: String, val type: String): BaseSuccess() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject
                .takeIf { it.containsKey("handle") }
                ?.takeIf { it.containsKey("type") }
                ?.let { true }
                ?: false
        }
    }
}