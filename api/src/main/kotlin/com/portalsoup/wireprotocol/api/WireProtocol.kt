package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
open class WireProtocol(@Transient @Contextual val requestBuilder: HttpRequestBuilder)