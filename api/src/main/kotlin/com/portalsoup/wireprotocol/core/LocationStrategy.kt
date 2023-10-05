package com.portalsoup.wireprotocol.core

import kotlinx.serialization.Serializable

//@Serializable
//enum class LocationStrategy(val id: String) {
//    CSS("css selector"),
//    LINK_TEXT("link text"),
//    PARTIAL_TEXT("partial link text"),
//    TAG("tag name"),
//    XPATH("xpath")
//}

@Serializable
sealed class LocationStrategy(val id: String) {

    abstract val expression: String

    @Serializable
    data class CSS(override val expression: String): LocationStrategy("css selector")

    @Serializable
    data class LinkText(override val expression: String): LocationStrategy("link text")

    @Serializable
    data class PartialLinkText(override val expression: String): LocationStrategy("partial link text")

    @Serializable
    data class TagName(override val expression: String): LocationStrategy("tag name")

    @Serializable
    data class Xpath(override val expression: String): LocationStrategy("xpath")
}