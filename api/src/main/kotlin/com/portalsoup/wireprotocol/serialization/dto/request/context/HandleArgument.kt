package com.portalsoup.wireprotocol.serialization.dto.request.context

import kotlinx.serialization.Serializable

@Serializable
data class HandleArgument(val handle: String)