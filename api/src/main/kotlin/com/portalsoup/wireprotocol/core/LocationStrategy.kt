package com.portalsoup.wireprotocol.core

import kotlinx.serialization.Serializable

@Serializable
enum class LocatorCode(val code: String) {
    CSS("css selector"),
    LINK_TEXT("link text"),
    PARTIAL_TEXT("partial link text"),
    TAG("tag name"),
    XPATH("xpath")
}

@Serializable
sealed class LocationStrategy(val id: LocatorCode) {
    abstract val expression: String

    @Serializable
    data class CSS(override val expression: String): LocationStrategy(LocatorCode.CSS)

    @Serializable
    data class LinkText(override val expression: String): LocationStrategy(LocatorCode.LINK_TEXT)

    @Serializable
    data class PartialLinkText(override val expression: String): LocationStrategy(LocatorCode.PARTIAL_TEXT)

    @Serializable
    data class TagName(override val expression: String): LocationStrategy(LocatorCode.TAG)

    @Serializable
    data class Xpath(override val expression: String): LocationStrategy(LocatorCode.XPATH)
}