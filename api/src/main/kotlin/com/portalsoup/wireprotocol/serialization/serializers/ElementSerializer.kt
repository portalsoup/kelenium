package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.serialization.dto.success.ElementRef
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.decodeFromJsonElement


@Serializable
object ElementSerializer : KSerializer<ElementRef> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BaseSuccess")

    override fun deserialize(decoder: Decoder): ElementRef {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer can only be used with Json")

        val jsonElement = jsonDecoder.decodeJsonElement()
        val map = jsonDecoder.json.decodeFromJsonElement<Map<String, String>>(jsonElement)
        if (!map.all { it.key.startsWith("element-") }) {
            throw SerializationException("Element serializer not given element references")
        }

        return map.entries.first().let { ElementRef(it.key, it.value) }
    }

    override fun serialize(encoder: Encoder, value: ElementRef) {
        throw SerializationException("Serialization is not supported for BaseSuccess")
    }
}