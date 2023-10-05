package com.portalsoup.wireprotocol.serialization.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class Script(val script: String, val args : List<String>)