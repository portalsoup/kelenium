package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.serialization.dto.success.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable
object ElementListSerializer : KSerializer<ElementRefList> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BaseSuccess")

    override fun deserialize(decoder: Decoder): ElementRefList {
        val refs = ElementRefList()

        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer can only be used with Json")

        val jsonElement = jsonDecoder.decodeJsonElement()

        jsonElement.jsonArray.flatMap { e ->
            e.jsonObject.keys.map { ElementRef(it, e.jsonObject[it]!!.jsonPrimitive.content) }
        }.onEach { refs.add(it) }

//        val map = jsonDecoder.json.decodeFromJsonElement<Map<String, String>>(jsonElement)
//        println("Got the mapping: \n$map")
//        if (!map.all { it.key.startsWith("element-") }) {
//            throw SerializationException("Element serializer not given element references")
//        }
//
//        map.map { ElementRef(it.key, it.value) }.onEach { refs.add(it) }
        return refs
    }

    override fun serialize(encoder: Encoder, value: ElementRefList) {
        throw SerializationException("Serialization is not supported for BaseSuccess")
    }
}