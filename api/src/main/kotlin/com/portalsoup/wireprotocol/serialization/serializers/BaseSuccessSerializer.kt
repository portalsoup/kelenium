package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.serialization.dto.response.BaseSuccess
import com.portalsoup.wireprotocol.context.dto.NewWindow
import com.portalsoup.wireprotocol.serialization.dto.context.WindowRect
import com.portalsoup.wireprotocol.serialization.dto.element.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.session.Status
import com.portalsoup.wireprotocol.serialization.dto.timeout.Timeouts
import com.portalsoup.wireprotocol.serialization.responseJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*


object BaseSuccessSerializer : KSerializer<BaseSuccess> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BaseSuccess")

    private inline fun <reified T: BaseSuccess> decode(jsonElement: JsonElement) = responseJson.decodeFromJsonElement<T>(jsonElement)

    override fun deserialize(decoder: Decoder): BaseSuccess {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer can only be used with Json")

        val jsonElement = jsonDecoder.decodeJsonElement().jsonObject
        
        return when {
            SessionCreated.isType(jsonElement) -> decode<SessionCreated>(jsonElement)
            Timeouts.isType(jsonElement) -> decode<Timeouts>(jsonElement)
            Status.isType(jsonElement) -> decode<Status>(jsonElement)
            ElementRef.isType(jsonElement) -> decode<ElementRef>(jsonElement)
            WindowRect.isType(jsonElement) -> decode<WindowRect>(jsonElement)
            NewWindow.isType(jsonElement) -> decode<NewWindow>(jsonElement)
            else -> throw SerializationException("Couldn't identify successful response: $jsonElement")
        }
    }

    override fun serialize(encoder: Encoder, value: BaseSuccess) {
        throw SerializationException("Serialization is not supported for BaseSuccess")
    }
}