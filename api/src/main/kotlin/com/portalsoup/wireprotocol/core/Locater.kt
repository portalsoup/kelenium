package com.portalsoup.wireprotocol.core

import kotlinx.serialization.Serializable

@Serializable
data class Locater(val strategy: LocationStrategy, val expression: String)
