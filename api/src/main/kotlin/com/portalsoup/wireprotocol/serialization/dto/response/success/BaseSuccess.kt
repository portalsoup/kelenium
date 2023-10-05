package com.portalsoup.wireprotocol.serialization.dto.response.success

import com.portalsoup.wireprotocol.serialization.serializers.BaseSuccessSerializer
import com.portalsoup.wireprotocol.serialization.dto.response.ResponsePayload
import kotlinx.serialization.Serializable

@Serializable(with = BaseSuccessSerializer::class)
sealed class BaseSuccess : ResponsePayload()
