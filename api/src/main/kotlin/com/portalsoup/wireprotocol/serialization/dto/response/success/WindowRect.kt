package com.portalsoup.wireprotocol.serialization.dto.response.success

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import com.portalsoup.wireprotocol.serialization.dto.request.Rect
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class WindowRect(
    override val x: Int,
    override val y: Int,
    override val width: Int,
    override val height: Int,
): Rect, BaseSuccess() {
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