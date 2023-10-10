package com.portalsoup.wireprotocol.response

import com.portalsoup.wireprotocol.serialization.serializers.ResponseSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ResponseSerializer::class)
data class Response(val value: Any?)
