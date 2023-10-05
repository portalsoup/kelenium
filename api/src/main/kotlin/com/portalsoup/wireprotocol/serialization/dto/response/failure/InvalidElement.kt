package com.portalsoup.wireprotocol.serialization.dto.response.failure

import com.portalsoup.wireprotocol.serialization.ResponseIsType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject


@Serializable
class InvalidElement(
    override val error: String,
    override val message: String,
    override val stacktrace: String,
    override val data: JsonObject
) : BaseFailure() {
    companion object : ResponseIsType<JsonObject> {
        override fun isType(element: JsonObject): Boolean {
            return element.jsonObject
                .takeIf { it.containsKey("script") }
                ?.takeIf { it.containsKey("pageLoad") }
                ?.takeIf { it.containsKey("implicit") }
                ?.let { true }
                ?: false
        }
    }
}