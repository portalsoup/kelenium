package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import com.portalsoup.wireprotocol.serialization.dto.response.BaseFailure
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class ScriptTimeout(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>("script timeout") {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}