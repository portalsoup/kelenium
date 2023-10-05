package com.portalsoup.wireprotocol.serialization.dto.request.print

import kotlinx.serialization.Serializable

@Serializable
data class PrintPage(
    val orientation: String = "portrait", // landscape or portrait
    val scale: Float = 1f,
    val background: Boolean  =false,
    val page: Page,
    val margin: Margin,
    val shrinkToFit: Boolean = true,
    val pageRanges: List<Int> = emptyList()
)