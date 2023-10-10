package com.portalsoup.wireprotocol.response

import com.portalsoup.wireprotocol.serialization.serializers.BaseFailureSerializer
import com.portalsoup.wireprotocol.serialization.dto.response.ResponsePayload
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable(with = BaseFailureSerializer::class)
abstract class BaseFailure : ResponsePayload() {
    abstract val error: String
    abstract val message: String
    abstract val stacktrace: String
    abstract val data: JsonObject
}
