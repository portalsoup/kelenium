package com.portalsoup.wireprotocol.serialization.dto.success

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class Timeouts(var script: Int = 30000, var pageLoad: Int = 300000, var implicit: Int = 0) : BaseSuccess() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject
                .takeIf { it.containsKey("script") }
                ?.takeIf { it.containsKey("pageLoad") }
                ?.takeIf { it.containsKey("implicit") }
                ?.let { true }
                ?: false
        }
    }
}