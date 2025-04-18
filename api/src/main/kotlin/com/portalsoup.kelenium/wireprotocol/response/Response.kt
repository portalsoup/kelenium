package com.portalsoup.wireprotocol.response

import com.portalsoup.wireprotocol.serialization.serializers.ResponseSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ResponseSerializer::class)
sealed class Response

data class ValueResponse(val value: Any?): Response()
data class TextResponse(val text: String): Response()