package com.portalsoup.wireprotocol.serialization.serializers

import com.portalsoup.wireprotocol.error.dto.*
import com.portalsoup.wireprotocol.response.BaseFailure
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
            DetachedShadowRoot.isType(jsonElement) -> decode<DetachedShadowRoot>(jsonElement)
            ElementClickIntercepted.isType(jsonElement) -> decode<ElementClickIntercepted>(jsonElement)
            ElementNotInteractable.isType(jsonElement) -> decode<ElementNotInteractable>(jsonElement)
            InsecureCertificate.isType(jsonElement) -> decode<InsecureCertificate>(jsonElement)
            InvalidArgument.isType(jsonElement) -> decode<InvalidArgument>(jsonElement)
            InvalidCookieDomain.isType(jsonElement) -> decode<InvalidCookieDomain>(jsonElement)
            InvalidElementState.isType(jsonElement) -> decode<InvalidElementState>(jsonElement)
            InvalidSelector.isType(jsonElement) -> decode<InvalidSelector>(jsonElement)
            InvalidSessionId.isType(jsonElement) -> decode<InvalidSessionId>(jsonElement)
            JavascriptError.isType(jsonElement) -> decode<JavascriptError>(jsonElement)
            MoveTargetOutOfBounds.isType(jsonElement) -> decode<MoveTargetOutOfBounds>(jsonElement)
            NoSuchAlert.isType(jsonElement) -> decode<NoSuchAlert>(jsonElement)
            NoSuchCookie.isType(jsonElement) -> decode<NoSuchCookie>(jsonElement)
            NoSuchElement.isType(jsonElement) -> decode<NoSuchElement>(jsonElement)
            NoSuchFrame.isType(jsonElement) -> decode<NoSuchFrame>(jsonElement)
            NoSuchShadowRoot.isType(jsonElement) -> decode<NoSuchShadowRoot>(jsonElement)
            NoSuchWindow.isType(jsonElement) -> decode<NoSuchWindow>(jsonElement)
            ScriptTimeout.isType(jsonElement) -> decode<ScriptTimeout>(jsonElement)
            SessionNotCreated.isType(jsonElement) -> decode<SessionNotCreated>(jsonElement)
            StaleElementReference.isType(jsonElement) -> decode<StaleElementReference>(jsonElement)
            Timeout.isType(jsonElement) -> decode<Timeout>(jsonElement)
            UnableToCaptureScreen.isType(jsonElement) -> decode<UnableToCaptureScreen>(jsonElement)
            UnableToSetCookie.isType(jsonElement) -> decode<UnableToSetCookie>(jsonElement)
            UnexpectedAlertOpen.isType(jsonElement) -> decode<UnexpectedAlertOpen>(jsonElement)
            UnknownCommand.isType(jsonElement) -> decode<UnknownCommand>(jsonElement)
            UnknownError.isType(jsonElement) -> decode<UnknownError>(jsonElement)
            UnknownMethod.isType(jsonElement) -> decode<UnknownMethod>(jsonElement)
            UnsupportedOperation.isType(jsonElement) -> decode<UnsupportedOperation>(jsonElement)

            else -> throw SerializationException("Couldn't identify failed response: $jsonElement")
        }
    }

    override fun serialize(encoder: Encoder, value: BaseFailure) {
        throw SerializationException("Serialization is not supported for BaseFailure")
    }
}