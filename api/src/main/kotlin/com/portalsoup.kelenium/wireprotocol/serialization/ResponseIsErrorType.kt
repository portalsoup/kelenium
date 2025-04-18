package com.portalsoup.wireprotocol.serialization

import com.portalsoup.wireprotocol.core.ErrorCodes
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

abstract class ResponseIsErrorType<J: JsonElement>(val code: ErrorCodes): ResponseIsType<J> {
    fun isCode(errorObject: JsonObject): Boolean = errorObject.jsonObject["error"]?.jsonPrimitive?.content?.takeIf { it == code.code }?.let { true } ?: false

}