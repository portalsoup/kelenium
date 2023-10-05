package com.portalsoup.wireprotocol.serialization.dto.response.failure

import com.portalsoup.wireprotocol.serialization.serializers.BaseFailureSerializer
import com.portalsoup.wireprotocol.serialization.dto.response.ResponsePayload
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable(with = BaseFailureSerializer::class)
sealed class BaseFailure : ResponsePayload() {
    abstract val error: String
    abstract val message: String
    abstract val stacktrace: String
    abstract val data: JsonObject
}
