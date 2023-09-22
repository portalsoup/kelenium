package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.SuccessResponse
import com.portalsoup.wireprotocol.dto.LocationStrategy
import kotlinx.serialization.Serializable

// Locators
fun RemoteDriver.findElement(using: String, value: String): SuccessResponse<Map<String, String>> = requestBuilder.post(
            endpoint = "/session/${session.id}/element",
            body = LocationStrategy(using, value)
        )
fun RemoteDriver.findElements() = Unit
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
fun RemoteDriver.getElementText() = Unit
fun RemoteDriver.getElementTagName() = Unit
fun RemoteDriver.getElementRect() = Unit
fun RemoteDriver.isElementEnabled() = Unit
fun RemoteDriver.getComputedRole() = Unit
fun RemoteDriver.getComputdLabel() = Unit

// interaction

fun RemoteDriver.elementClick(elementId: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/element/$elementId/click", "{}")
fun RemoteDriver.elementClear() = Unit
fun RemoteDriver.elementSendKeys() = Unit