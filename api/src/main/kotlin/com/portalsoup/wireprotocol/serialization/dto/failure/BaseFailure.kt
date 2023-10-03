package com.portalsoup.wireprotocol.serialization.dto.failure

import com.portalsoup.wireprotocol.serialization.BaseFailureSerializer
import com.portalsoup.wireprotocol.serialization.dto.ResponseBody
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable(with = BaseFailureSerializer::class)
sealed class BaseFailure : ResponseBody() {
    abstract val error: String
    abstract val message: String
    abstract val stacktrace: String
    abstract val data: JsonObject
}
