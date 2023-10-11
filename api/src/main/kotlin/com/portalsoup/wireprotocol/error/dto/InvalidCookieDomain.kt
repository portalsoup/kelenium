package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes.INVALID_COOKIE_DOMAIN
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class InvalidCookieDomain(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>(INVALID_COOKIE_DOMAIN) {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}