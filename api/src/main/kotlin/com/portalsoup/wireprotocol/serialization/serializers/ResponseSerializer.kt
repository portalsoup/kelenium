package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.serialization.dto.Response
import com.portalsoup.wireprotocol.serialization.dto.failure.BaseFailure
import com.portalsoup.wireprotocol.serialization.dto.success.BaseSuccess
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
    override fun deserialize(decoder: Decoder): Response {
        val jsonDecoder = decoder as? JsonDecoder ?: throw SerializationException("Couldn't get json decoder")
        val rootElement = jsonDecoder.decodeJsonElement()
        val value = rootElement.jsonObject["value"] ?: throw SerializationException("All responses should contain a root value property")

        return when (value) {
            is JsonPrimitive -> jsonPrimitive(value)
            is JsonNull -> jsonNull()
            is JsonObject -> jsonObject(jsonDecoder, value)
            is JsonArray -> jsonArray(decoder, value)
        }
    }

    override fun serialize(encoder: Encoder, value: Response) {
        throw SerializationException("Serialization is not supported for Response")

    }

    private fun jsonPrimitive(value: JsonPrimitive) = Response(value.content)
    private fun jsonNull() = Response(Unit)
    private fun jsonObject(decoder: JsonDecoder, value: JsonObject): Response =
         when (value.containsKey("error")) {
            true -> decoder.json.decodeFromJsonElement(BaseFailure.serializer(), value).let { Response(it) }
            false -> when (value.all { it.key.startsWith("element-") }) {
                true -> decoder.json.decodeFromJsonElement(ElementSerializer, value).let { Response(it) }
                false -> decoder.json.decodeFromJsonElement(BaseSuccess.serializer(), value).let { Response(it) }
            }
        }

    private fun jsonArray(decoder: JsonDecoder, value: JsonArray) = decoder.json
        .decodeFromJsonElement(ElementListSerializer, value)
        .let { Response(it) }
}