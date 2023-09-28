package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable


class RemoteDriverClosedException(cause: Throwable): RuntimeException("This webdriver instance has already been closed!", cause)

@Serializable
data class SuccessResponse<T>(val value: T)