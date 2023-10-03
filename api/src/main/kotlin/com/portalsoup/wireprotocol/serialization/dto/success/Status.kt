package com.portalsoup.wireprotocol.serialization.dto.success

import kotlinx.serialization.Serializable

@Serializable
data class Status(val message: String, val ready: Boolean): BaseSuccess()