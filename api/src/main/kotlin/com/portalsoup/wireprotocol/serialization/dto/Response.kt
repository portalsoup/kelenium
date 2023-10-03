package com.portalsoup.wireprotocol.serialization.dto

import com.portalsoup.wireprotocol.serialization.ResponseSerializer
import kotlinx.serialization.Serializable

@Serializable(with = ResponseSerializer::class)
data class Response(val value: Any?)
