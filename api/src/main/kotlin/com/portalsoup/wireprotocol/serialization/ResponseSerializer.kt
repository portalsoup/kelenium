package com.portalsoup.wireprotocol.serialization

import com.portalsoup.wireprotocol.serialization.dto.Response
import com.portalsoup.wireprotocol.serialization.dto.failure.BaseFailure
import com.portalsoup.wireprotocol.serialization.dto.success.BaseSuccess
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

//@Serializer(forClass = Response::class)
object ResponseSerializer : KSerializer<Response> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Response")
    override fun deserialize(decoder: Decoder): Response {
        val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException("Couldn't get json decoder")
        val rootElement = jsonDecoder.decodeJsonElement()
        val value = rootElement.jsonObject["value"] ?: throw SerializationException("All responses should contain a root value property")

        if (value is JsonPrimitive) return Response(value.jsonPrimitive.content)

        val maybeError = value.jsonObject["error"]?.jsonPrimitive?.content

        val typedValue =  when (maybeError == null) {
            true -> jsonDecoder.json.decodeFromJsonElement(
                BaseSuccess.serializer(),
                value
            )
            false -> jsonDecoder.json.decodeFromJsonElement(
                BaseFailure.serializer(),
                value
            )
            else -> throw SerializationException("Unknown response type: $rootElement")
        }

        return Response(typedValue)
    }

    override fun serialize(encoder: Encoder, value: Response) {
        throw SerializationException("Serialization is not supported for Response")

    }
}