package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable


@Serializable
data class Timeouts(val script: Int = 30000, val pageLoad: Int = 300000, val implicit: Int = 0)