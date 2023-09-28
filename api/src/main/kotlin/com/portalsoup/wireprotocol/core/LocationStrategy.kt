package com.portalsoup.wireprotocol.core

import kotlinx.serialization.Serializable

@Serializable
enum class LocationStrategy(val id: String) {
    CSS("css selector"),
    LINK_TEXT("link text"),
    PARTIAL_TEXT("partial link text"),
    TAG("tag name"),
    XPATH("xpath")
}