package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.serialization.dto.response.BaseFailure
import com.portalsoup.wireprotocol.serialization.dto.response.error.*
import com.portalsoup.wireprotocol.serialization.responseJson
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

object BaseFailureSerializer : KSerializer<BaseFailure> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("BaseFailure")

    private inline fun <reified T: BaseFailure> decode(jsonElement: JsonElement) = responseJson.decodeFromJsonElement<T>(jsonElement)

    override fun deserialize(decoder: Decoder): BaseFailure {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("This serializer can only be used with Json")

        val jsonElement = jsonDecoder.decodeJsonElement().jsonObject

        return when {
            com.portalsoup.wireprotocol.serialization.dto.error.DetachedShadowRoot.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.DetachedShadowRoot>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.ElementClickIntercepted.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.ElementClickIntercepted>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.ElementNotInteractable.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.ElementNotInteractable>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.InsecureCertificate.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.InsecureCertificate>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.InvalidArgument.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.InvalidArgument>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.InvalidCookieDomain.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.InvalidCookieDomain>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.InvalidElementState.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.InvalidElementState>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.InvalidSelector.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.InvalidSelector>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.InvalidSessionId.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.InvalidSessionId>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.JavascriptError.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.JavascriptError>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.MoveTargetOutOfBounds.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.MoveTargetOutOfBounds>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.NoSuchAlert.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.NoSuchAlert>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.NoSuchCookie.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.NoSuchCookie>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.NoSuchElement.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.NoSuchElement>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.NoSuchFrame.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.NoSuchFrame>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.NoSuchShadowRoot.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.NoSuchShadowRoot>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.NoSuchWindow.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.NoSuchWindow>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.ScriptTimeout.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.ScriptTimeout>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.SessionNotCreated.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.SessionNotCreated>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.StaleElementReference.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.StaleElementReference>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.Timeout.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.Timeout>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnableToCaptureScreen.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnableToCaptureScreen>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnableToSetCookie.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnableToSetCookie>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnexpectedAlertOpen.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnexpectedAlertOpen>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnknownCommand.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnknownCommand>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnknownError.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnknownError>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnknownMethod.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnknownMethod>(jsonElement)
            com.portalsoup.wireprotocol.serialization.dto.error.UnsupportedOperation.isType(jsonElement) -> decode<com.portalsoup.wireprotocol.serialization.dto.error.UnsupportedOperation>(jsonElement)

            else -> throw SerializationException("Couldn't identify failed response: $jsonElement")
        }
    }

    override fun serialize(encoder: Encoder, value: BaseFailure) {
        throw SerializationException("Serialization is not supported for BaseFailure")
    }
}