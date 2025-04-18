package com.portalsoup.wireprotocol.document.dto

import kotlinx.serialization.Serializable

@Serializable
data class Script(val script: String, val args : List<String>)