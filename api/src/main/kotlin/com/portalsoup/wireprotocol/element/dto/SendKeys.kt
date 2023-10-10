package com.portalsoup.wireprotocol.element.dto

import kotlinx.serialization.Serializable

@Serializable
data class SendKeys(val text: String)