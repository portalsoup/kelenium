package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import com.portalsoup.wireprotocol.core.ErrorCodes.MOVE_TARGET_OUT_OF_BOUNDS
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.ResponseIsErrorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
class MoveTargetOutOfBounds(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsErrorType<JsonObject>(MOVE_TARGET_OUT_OF_BOUNDS) {
        override fun isType(element: JsonObject): Boolean {
            return isCode(element)
        }
    }
}