package com.portalsoup.wireprotocol.serialization.dto.success

import com.portalsoup.wireprotocol.serialization.BaseSuccessSerializer
import com.portalsoup.wireprotocol.serialization.dto.ResponsePayload
import kotlinx.serialization.Serializable

@Serializable(with = BaseSuccessSerializer::class)
sealed class BaseSuccess : ResponsePayload()
