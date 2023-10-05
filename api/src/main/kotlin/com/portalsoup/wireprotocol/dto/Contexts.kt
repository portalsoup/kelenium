package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable

@Serializable
data class TypeArgument(val type: String)

@Serializable
data class HandleArgument(val handle: String)

@Serializable
data class IdArgument(val id: String)
