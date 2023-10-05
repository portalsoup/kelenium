package com.portalsoup.wireprotocol.serialization.dto.request.print

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page(
    @SerialName("pageWidth") val width: String = "21.59",
    @SerialName("pageHeight") val height: String = "27.94"
)