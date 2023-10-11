package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import com.portalsoup.wireprotocol.core.ErrorCodes.NO_SUCH_COOKIE
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class NoSuchCookie(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>(NO_SUCH_COOKIE) {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}