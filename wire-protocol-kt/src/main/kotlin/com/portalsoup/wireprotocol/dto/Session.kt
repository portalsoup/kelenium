package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Session(@SerialName("sessionId") val id: String, val capabilities: JsonObject)
@Serializable
data class Status(val message: String, val ready: Boolean)