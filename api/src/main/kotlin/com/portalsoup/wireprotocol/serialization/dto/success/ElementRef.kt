package com.portalsoup.wireprotocol.serialization.dto.success

import com.portalsoup.wireprotocol.core.LocationStrategy
import kotlinx.serialization.Serializable

// simple idea for an element
@Serializable
data class ElementRef(val identifier: String, val reference: String): BaseSuccess()