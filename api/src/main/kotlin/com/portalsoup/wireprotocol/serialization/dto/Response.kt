package com.portalsoup.wireprotocol.serialization.dto

import com.portalsoup.wireprotocol.serialization.ResponseSerializer
import com.portalsoup.wireprotocol.serialization.dto.failure.BaseFailure
import com.portalsoup.wireprotocol.serialization.dto.failure.SessionNotCreated
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.responseJson
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

@Serializable(with = ResponseSerializer::class)
data class Response(val value: Any?)
