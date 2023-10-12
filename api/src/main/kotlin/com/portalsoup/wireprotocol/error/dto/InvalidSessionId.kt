package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes.INVALID_SESSION_ID
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class InvalidSessionId(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject? = null
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>(INVALID_SESSION_ID) {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}