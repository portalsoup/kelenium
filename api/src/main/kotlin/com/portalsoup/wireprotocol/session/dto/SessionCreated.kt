package com.portalsoup.wireprotocol.session.dto

import com.portalsoup.wireprotocol.response.BaseSuccess
import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class SessionCreated(@SerialName("sessionId") val id: String, val capabilities: JsonObject) : BaseSuccess() {

    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject.containsKey("sessionId")
        }
    }

}