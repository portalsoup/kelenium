package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable


@Serializable
data class FindElementStrategy(val using: String, val value: String)
