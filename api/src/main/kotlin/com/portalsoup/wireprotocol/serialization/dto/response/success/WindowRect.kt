package com.portalsoup.wireprotocol.serialization.dto.response.success

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class WindowRect(val x: Int, val y: Int, val width: Int, val height: Int): BaseSuccess() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject
                .takeIf { it.containsKey("x") }
                ?.takeIf { it.containsKey("y") }
                ?.takeIf { it.containsKey("width") }
                ?.takeIf { it.containsKey("height") }
                ?.let { true }
                ?: false
        }
    }
}