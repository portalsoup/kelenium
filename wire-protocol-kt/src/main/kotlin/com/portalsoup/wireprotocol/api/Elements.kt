package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.dto.Element
import com.portalsoup.wireprotocol.dto.LocationStrategy
import com.portalsoup.wireprotocol.dto.SuccessResponse

// Locators
fun RemoteDriver.findElement(using: String, value: String): SuccessResponse<List<Element>> = requestBuilder.post<SuccessResponse<Map<String, String>>, LocationStrategy>(
    "/session/${session.id}/element",
    LocationStrategy(using, value)
).let { rawResponse ->
    rawResponse.value
        .map { Element(it.key, it.value) }
        .let { SuccessResponse(it) }
}
fun RemoteDriver.findElements(using: String, value: String): SuccessResponse<Map<String, String>> = requestBuilder.post(
    "/session/${session.id}/elements",
    LocationStrategy(using, value)
)
fun RemoteDriver.findElementFromElement() = Unit
fun RemoteDriver.findElementsFromElements() = Unit
fun RemoteDriver.findElementFromShadowRoot() = Unit
fun RemoteDriver.findElementsFromShadowRoot() = Unit
fun RemoteDriver.getActiveElement() = Unit
fun RemoteDriver.getElementShadowRoot() = Unit

// state

fun RemoteDriver.isElementSelected() = Unit
fun RemoteDriver.getElementAttribute() = Unit
fun RemoteDriver.getElementProperty() = Unit
fun RemoteDriver.getElementCssValue() = Unit
fun RemoteDriver.getElementText(element: Element): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/element/${element.reference}/text")
fun RemoteDriver.getElementTagName() = Unit
fun RemoteDriver.getElementRect() = Unit
fun RemoteDriver.isElementEnabled() = Unit
fun RemoteDriver.getComputedRole() = Unit
fun RemoteDriver.getComputdLabel() = Unit

// interaction

fun RemoteDriver.elementClick(element: Element): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/element/${element.reference}/click", "{}")
fun RemoteDriver.elementClear() = Unit
fun RemoteDriver.elementSendKeys() = Unit