package com.portalsoup.wireprotocol.serialization

import com.portalsoup.wireprotocol.serialization.dto.success.BaseSuccess
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.success.Status
import com.portalsoup.wireprotocol.serialization.dto.success.Timeouts
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*


object BaseSuccessSerializer : KSerializer<BaseSuccess> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BaseSuccess")

    override fun deserialize(decoder: Decoder): BaseSuccess {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer can only be used with Json")

        val jsonElement = jsonDecoder.decodeJsonElement().jsonObject

        return when {
            SessionCreated.isType(jsonElement) -> responseJson.decodeFromJsonElement<SessionCreated>(jsonElement)
            Timeouts.isType(jsonElement) -> responseJson.decodeFromJsonElement<Timeouts>(jsonElement)
            Status.isType(jsonElement) -> responseJson.decodeFromJsonElement<Status>(jsonElement)
            else -> throw SerializationException("Couldn't identify successful response: $jsonElement")
        }
    }

    override fun serialize(encoder: Encoder, value: BaseSuccess) {
        throw SerializationException("Serialization is not supported for BaseSuccess")
    }
}