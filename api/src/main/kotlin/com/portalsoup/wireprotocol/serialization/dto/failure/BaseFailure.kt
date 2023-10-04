package com.portalsoup.wireprotocol.serialization.dto.failure

import com.portalsoup.wireprotocol.serialization.serializers.BaseFailureSerializer
import com.portalsoup.wireprotocol.serialization.dto.ResponsePayload
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable(with = BaseFailureSerializer::class)
sealed class BaseFailure : ResponsePayload() {
    abstract val error: String
    abstract val message: String
    abstract val stacktrace: String
    abstract val data: JsonObject
}
