package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import com.portalsoup.wireprotocol.core.ErrorCodes.UNEXPECTED_ALERT_OPEN
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class UnexpectedAlertOpen(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject? = null
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>(UNEXPECTED_ALERT_OPEN) {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}