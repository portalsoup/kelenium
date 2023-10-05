package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.serialization.dto.response.success.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.request.FindElementStrategy
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated
import kotlinx.serialization.Serializable

// Locators
fun WireProtocol.findElement(session: SessionCreated, using: LocationStrategy, value: String): Response {
    return requestBuilder.post("/session/${session.id}/element", FindElementStrategy(using.id, value))
}

fun WireProtocol.findElements(session: SessionCreated, using: LocationStrategy, value: String): Response {
     return requestBuilder.post("/session/${session.id}/elements", FindElementStrategy(using.id, value))
}

fun WireProtocol.findElementFromElement(session: SessionCreated, parent: ElementRef, value: String): Response = TODO()
fun WireProtocol.findElementsFromElement(session: SessionCreated, locationStrategy: LocationStrategy, parent: ElementRef, value: String): Response = TODO()

fun WireProtocol.findElementFromShadowRoot() = Unit
fun WireProtocol.findElementsFromShadowRoot() = Unit
fun WireProtocol.getActiveElement() = Unit
fun WireProtocol.getElementShadowRoot() = Unit

// state

fun WireProtocol.isElementSelected() = Unit
fun WireProtocol.getElementAttribute() = Unit
fun WireProtocol.getElementProperty() = Unit
fun WireProtocol.getElementCssValue() = Unit
fun WireProtocol.getElementText(session: SessionCreated, element: ElementRef): Response = requestBuilder.get("/session/${session.id}/element/${element.reference}/text")
fun WireProtocol.getElementTagName(session: SessionCreated, element: ElementRef): Response = requestBuilder.get("/session/${session.id}/element/${element.reference}/name")
fun WireProtocol.getElementRect() = Unit
fun WireProtocol.isElementEnabled() = Unit
fun WireProtocol.getComputedRole() = Unit
fun WireProtocol.getComputdLabel() = Unit

// interaction

fun WireProtocol.elementClick(session: SessionCreated, element: ElementRef): Response = requestBuilder.post("/session/${session.id}/element/${element.reference}/click", "{}")
fun WireProtocol.elementClear() = Unit
fun WireProtocol.elementSendKeys(session: SessionCreated, element: ElementRef, keys: String): Response = requestBuilder.post(
    "/session/${session.id}/element/${element.reference}/value",
    SendKeys(keys)
)

@Serializable
data class SendKeys(val text: String)