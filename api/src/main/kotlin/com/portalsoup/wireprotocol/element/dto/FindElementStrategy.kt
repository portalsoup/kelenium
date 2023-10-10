package com.portalsoup.wireprotocol.element.dto

import kotlinx.serialization.Serializable


@Serializable
data class FindElementStrategy(val using: String, val value: String)
