package com.portalsoup.wireprotocol.session.dto

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import com.portalsoup.wireprotocol.serialization.dto.response.BaseSuccess
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Serializable
data class SessionCreated(@SerialName("sessionId") val id: String, val capabilities: JsonObject) : BaseSuccess() {

    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            println(element::class.simpleName)
            return element.jsonObject.containsKey("sessionId")
        }
    }

}