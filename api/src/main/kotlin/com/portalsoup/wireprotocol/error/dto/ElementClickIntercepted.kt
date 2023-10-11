package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes.ELEMENT_CLICK_INTERCEPTED
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class ElementClickIntercepted(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>(ELEMENT_CLICK_INTERCEPTED) {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}