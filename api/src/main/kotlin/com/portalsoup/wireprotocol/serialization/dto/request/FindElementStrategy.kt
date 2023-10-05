package com.portalsoup.wireprotocol.serialization.dto.request

import kotlinx.serialization.Serializable


@Serializable
data class FindElementStrategy(val using: String, val value: String)
