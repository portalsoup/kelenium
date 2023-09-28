package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewWindow(val handle: String, val type: String)

enum class Type(val type: String) {
    WINDOW("window"),
    TAB("tab")
}

@Serializable
data class TypeArgument(val type: String)

@Serializable
data class HandleArgument(val handle: String)

@Serializable
data class IdArgument(val id: String)

@Serializable
data class WindowRect(val x: Int, val y: Int, val width: Int, val height: Int)
