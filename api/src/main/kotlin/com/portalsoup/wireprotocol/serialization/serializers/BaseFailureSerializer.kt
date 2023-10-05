package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.serialization.dto.response.failure.BaseFailure
import com.portalsoup.wireprotocol.serialization.dto.response.failure.SessionNotCreated
import com.portalsoup.wireprotocol.serialization.responseJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

object BaseFailureSerializer : KSerializer<BaseFailure> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BaseFailure")

    override fun deserialize(decoder: Decoder): BaseFailure {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer can only be used with Json")

        val jsonElement = jsonDecoder.decodeJsonElement().jsonObject

        return when {
            SessionNotCreated.isType(jsonElement) -> responseJson.decodeFromJsonElement<SessionNotCreated>(jsonElement)
            else -> throw SerializationException("Couldn't identify failed response: $jsonElement")
        }
    }

    override fun serialize(encoder: Encoder, value: BaseFailure) {
        throw SerializationException("Serialization is not supported for BaseFailure")
    }
}