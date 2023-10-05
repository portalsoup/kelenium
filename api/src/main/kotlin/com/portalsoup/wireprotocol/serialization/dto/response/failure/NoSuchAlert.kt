package com.portalsoup.wireprotocol.serialization.dto.response.failure

import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class NoSuchAlert(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>("no such alert") {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}