package com.portalsoup.wireprotocol.print.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Margin(
    @SerialName("marginTop") val top: Int = 1,
    @SerialName("marginBottom") val botton: Int = 1,
    @SerialName("marginLeft") val left: Int = 1,
    @SerialName("marginRight") val right: Int = 1
)