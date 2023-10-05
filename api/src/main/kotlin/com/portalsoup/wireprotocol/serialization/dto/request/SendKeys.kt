package com.portalsoup.wireprotocol.serialization.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class SendKeys(val text: String)