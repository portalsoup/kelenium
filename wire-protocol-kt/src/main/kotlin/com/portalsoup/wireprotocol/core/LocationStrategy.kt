package com.portalsoup.wireprotocol.core

enum class LocationStrategy(val id: String) {
    CSS("css selector"),
    LINK_TEXT("link text"),
    PARTIAL_TEXT("partial link text"),
    NAME("tag name"),
    XPATH("xpath")
}