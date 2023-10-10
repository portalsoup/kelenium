package com.portalsoup.wireprotocol.session.dto

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import com.portalsoup.wireprotocol.serialization.dto.response.BaseSuccess
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class Status(val message: String, val ready: Boolean): BaseSuccess() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject
                .takeIf { it.containsKey("message") }
                ?.takeIf { it.containsKey("ready") }
                ?.let { true }
                ?: false
        }
    }
}