package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteWireDriver
import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.dto.Element
import com.portalsoup.wireprotocol.dto.FindElementStrategy
import com.portalsoup.wireprotocol.dto.SuccessResponse
import kotlinx.serialization.Serializable

// Locators
fun RemoteWireDriver.findElement(using: LocationStrategy, value: String): SuccessResponse<List<Element>> = requestBuilder.post<SuccessResponse<Map<String, String>>, FindElementStrategy>(
    "/session/${session.id}/element",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .map { Element(it.key, it.value) }
        .let { SuccessResponse(it) }
}
fun RemoteWireDriver.findElements(using: LocationStrategy, value: String): SuccessResponse<List<Element>> = requestBuilder.post<SuccessResponse<List<Map<String, String>>>, FindElementStrategy>(
    "/session/${session.id}/elements",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .also { println(it) }
        .flatMap { e -> e.map { Element(it.key, it.value) } }
        .let { SuccessResponse(it) }
}
fun RemoteWireDriver.findElementFromElement(parent: Element, using: LocationStrategy, value: String): SuccessResponse<Element> = requestBuilder.post<SuccessResponse<Map<String, String>>, FindElementStrategy>(
    "/session/${session.id}/element/${parent.reference}/element",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .map { Element(it.key, it.value) }
        .first()
        .let { SuccessResponse(it) }
}
fun RemoteWireDriver.findElementsFromElement(parent: Element, using: LocationStrategy, value: String): SuccessResponse<List<Element>> = requestBuilder.post<SuccessResponse<List<Map<String, String>>>, FindElementStrategy>(
    "/session/${session.id}/element/${parent.reference}/elements",
    FindElementStrategy(using.id, value)
).let { rawResponse ->
    rawResponse.value
        .also { println(it) }
        .flatMap { e -> e.map { Element(it.key, it.value) } }
        .let { SuccessResponse(it) }
}

fun RemoteWireDriver.findElementFromShadowRoot() = Unit
fun RemoteWireDriver.findElementsFromShadowRoot() = Unit
fun RemoteWireDriver.getActiveElement() = Unit
fun RemoteWireDriver.getElementShadowRoot() = Unit

// state

fun RemoteWireDriver.isElementSelected() = Unit
fun RemoteWireDriver.getElementAttribute() = Unit
fun RemoteWireDriver.getElementProperty() = Unit
fun RemoteWireDriver.getElementCssValue() = Unit
fun RemoteWireDriver.getElementText(element: Element): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/element/${element.reference}/text")
fun RemoteWireDriver.getElementTagName() = Unit
fun RemoteWireDriver.getElementRect() = Unit
fun RemoteWireDriver.isElementEnabled() = Unit
fun RemoteWireDriver.getComputedRole() = Unit
fun RemoteWireDriver.getComputdLabel() = Unit

// interaction

fun RemoteWireDriver.elementClick(element: Element): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/element/${element.reference}/click", "{}")
fun RemoteWireDriver.elementClear() = Unit
fun RemoteWireDriver.elementSendKeys(element: Element, keys: String): SuccessResponse<Unit?> = requestBuilder.post(
    "/session/${session.id}/element/${element.reference}/value",
            SendKeys(keys)
)

@Serializable
data class SendKeys(val text: String)