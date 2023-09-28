package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.dto.Element
import com.portalsoup.wireprotocol.dto.FindElementStrategy
import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.SuccessResponse
import kotlinx.serialization.Serializable

// Locators
fun WireProtocol.findElement(session: Session, using: LocationStrategy, value: String): SuccessResponse<Element> = requestBuilder.post<SuccessResponse<Map<String, String>>, FindElementStrategy>(
    "/session/${session.id}/element",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .onEach { println("DEBUG: $it") }
        .map { Element(using, it.key, it.value) }
        .first()
        .let { SuccessResponse(it) }
}
fun WireProtocol.findElements(session: Session, using: LocationStrategy, value: String): SuccessResponse<List<Element>> = requestBuilder.post<SuccessResponse<List<Map<String, String>>>, FindElementStrategy>(
    "/session/${session.id}/elements",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .also { println(it) }
        .flatMap { e -> e.map { Element(using, it.key, it.value) } }
        .let { SuccessResponse(it) }
}
fun WireProtocol.findElementFromElement(session: Session, parent: Element, using: LocationStrategy, value: String): SuccessResponse<Element> = requestBuilder.post<SuccessResponse<Map<String, String>>, FindElementStrategy>(
    "/session/${session.id}/element/${parent.reference}/element",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .map { Element(using, it.key, it.value) }
        .first()
        .let { SuccessResponse(it) }
}
fun WireProtocol.findElementsFromElement(session: Session, parent: Element, using: LocationStrategy, value: String): SuccessResponse<List<Element>> = requestBuilder.post<SuccessResponse<List<Map<String, String>>>, FindElementStrategy>(
    "/session/${session.id}/element/${parent.reference}/elements",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .also { println(it) }
        .flatMap { e -> e.map { Element(using, it.key, it.value) } }
        .let { SuccessResponse(it) }
}

fun WireProtocol.findElementFromShadowRoot() = Unit
fun WireProtocol.findElementsFromShadowRoot() = Unit
fun WireProtocol.getActiveElement() = Unit
fun WireProtocol.getElementShadowRoot() = Unit

// state

fun WireProtocol.isElementSelected() = Unit
fun WireProtocol.getElementAttribute() = Unit
fun WireProtocol.getElementProperty() = Unit
fun WireProtocol.getElementCssValue() = Unit
fun WireProtocol.getElementText(session: Session, element: Element): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/element/${element.reference}/text")
fun WireProtocol.getElementTagName(session: Session, element: Element): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/element/${element.reference}/name")
fun WireProtocol.getElementRect() = Unit
fun WireProtocol.isElementEnabled() = Unit
fun WireProtocol.getComputedRole() = Unit
fun WireProtocol.getComputdLabel() = Unit

// interaction

fun WireProtocol.elementClick(session: Session, element: Element): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/element/${element.reference}/click", "{}")
fun WireProtocol.elementClear() = Unit
fun WireProtocol.elementSendKeys(session: Session, element: Element, keys: String): SuccessResponse<Unit?> = requestBuilder.post(
    "/session/${session.id}/element/${element.reference}/value",
            SendKeys(keys)
)

@Serializable
data class SendKeys(val text: String)