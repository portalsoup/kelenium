package com.portalsoup.wireprotocol.serialization.dto.failure

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
class SessionNotCreated(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsType {
        override fun isType(element: JsonObject): Boolean {
            val content = element.jsonObject["error"]?.jsonPrimitive?.content

            println(content)
            return (content ?: "") == "session not created"
        }
    }
}