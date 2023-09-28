package com.portalsoup.wireprotocol.dto

import com.portalsoup.wireprotocol.core.LocationStrategy
import kotlinx.serialization.Serializable


// simple idea for an element
@Serializable
data class Element(val locationStrategy: LocationStrategy, val identifier: String, val reference: String)

@Serializable
data class FindElementStrategy(val using: String, val value: String)
