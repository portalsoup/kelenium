package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable


@Serializable
data class Timeouts(var script: Int = 30000, var pageLoad: Int = 300000, var implicit: Int = 0)