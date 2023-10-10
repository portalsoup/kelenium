package com.portalsoup.wireprotocol.response

import com.portalsoup.wireprotocol.serialization.serializers.BaseSuccessSerializer
import com.portalsoup.wireprotocol.serialization.dto.response.ResponsePayload
import kotlinx.serialization.Serializable

@Serializable(with = BaseSuccessSerializer::class)
abstract class BaseSuccess : ResponsePayload()
