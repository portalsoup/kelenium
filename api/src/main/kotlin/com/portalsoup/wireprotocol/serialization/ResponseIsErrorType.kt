package com.portalsoup.wireprotocol.serialization

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

abstract class ResponseIsErrorType<J: JsonElement>(val code: String): ResponseIsType<J> {
    fun isCode(errorObject: JsonObject): Boolean = errorObject.jsonObject["error"]?.jsonPrimitive?.content?.takeIf { it == code }?.let { true } ?: false

}