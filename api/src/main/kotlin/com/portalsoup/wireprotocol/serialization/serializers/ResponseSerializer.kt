package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.element.dto.ElementRefList
import com.portalsoup.wireprotocol.response.BaseFailure
import com.portalsoup.wireprotocol.response.BaseSuccess
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.response.ResponsePayload
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * The custom serializer used by the root Response type.
 *
 * This serializer first determines whether the payload "value" is a nested object or primitive value before
 * boxing it Response.
 *
 * If the payload is a nested object, then it is determined to be an error state or not, and is then delegated to the
 * custom serializers used by either BaseSuccess or BaseFailure
 *
 * @see Response
 *
 * @see BaseFailure
 * @see BaseFailureSerializer
 *
 * @see BaseSuccess
 * @see BaseSuccessSerializer
 */
object ResponseSerializer : KSerializer<Response> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Response")
    @Suppress("IMPLICIT_CAST_TO_ANY") // rip maybe one day I'll find a way around this
    override fun deserialize(decoder: Decoder): Response {
        val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException("Couldn't get json decoder")
        val rootElement = jsonDecoder.decodeJsonElement()
        val value: JsonElement = rootElement.jsonObject["value"] ?: throw SerializationException("All responses should contain a root value property")

        return when (value) {
            is JsonPrimitive -> jsonPrimitive(value)
            is JsonNull -> jsonNull()
            is JsonObject -> jsonObject(jsonDecoder, value)
            is JsonArray -> jsonArray(decoder, value)
        }.let { Response(it) }
    }

    override fun serialize(encoder: Encoder, value: Response) {
        throw SerializationException("Serialization is not supported for Response")

    }

    private fun jsonPrimitive(value: JsonPrimitive): String = value.content
    private fun jsonNull() = Unit
    private fun jsonObject(decoder: JsonDecoder, value: JsonObject): ResponsePayload =
         when (value.containsKey("error")) {
            true -> decoder.json.decodeFromJsonElement(BaseFailure.serializer(), value)
            false -> when (value.all { it.key.startsWith("element-") }) {
                true -> decoder.json.decodeFromJsonElement(ElementSerializer, value)
                false -> decoder.json.decodeFromJsonElement(BaseSuccess.serializer(), value)
            }
        }
    private fun jsonArray(decoder: JsonDecoder, value: JsonArray): List<*> = decoder.json.let { json ->

        // First decode the contents to some type
        val payload = if (value.all { it is JsonObject }) {
            value.map { jsonObject(decoder, it as JsonObject) }
        } else if (value.all { it is JsonPrimitive }) {
            value.map { jsonPrimitive(it as JsonPrimitive) }
        } else {
            throw RuntimeException("")
        }

        // If the containing type matters to the list implementation, it can be chosen here
        if (payload.all { it is ElementRef }) {
            val refs = payload.map { it as ElementRef }
            return ElementRefList(refs)
        } else {
            return payload
        }
    }
}