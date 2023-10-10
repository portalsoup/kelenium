package com.portalsoup.wireprotocol.response

import com.portalsoup.wireprotocol.serialization.serializers.BaseSuccessSerializer
import kotlinx.serialization.Serializable

@Serializable(with = BaseSuccessSerializer::class)
abstract class BaseSuccess : ResponsePayload()
