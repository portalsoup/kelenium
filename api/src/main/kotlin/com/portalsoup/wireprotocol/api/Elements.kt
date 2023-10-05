package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.response.success.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.request.FindElementStrategy
import com.portalsoup.wireprotocol.serialization.dto.request.SendKeys
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated

// Locators
fun WireProtocol.findElement(session: SessionCreated, using: LocationStrategy): Response =
    requestBuilder.post("/session/${session.id}/element", FindElementStrategy(using.id, using.expression))

fun WireProtocol.findElements(session: SessionCreated, using: LocationStrategy): Response =
    requestBuilder.post("/session/${session.id}/elements", FindElementStrategy(using.id, using.expression))

fun WireProtocol.findElementFromElement(session: SessionCreated, parent: ElementRef, value: String): Response =
    requestBuilder.post("/session/${session.id}/element/${parent.reference}/element", value)

fun WireProtocol.findElementsFromElement(session: SessionCreated, locationStrategy: LocationStrategy, parent: ElementRef): Response =
    requestBuilder.post("/session/${session.id}/element/${parent.reference}/elements", locationStrategy.expression)

fun WireProtocol.findElementFromShadowRoot(session: SessionCreated, strategy: FindElementStrategy, shadowId: String): Response =
    requestBuilder.post("/session/${session.id}/shadow/${shadowId}/element", strategy)

fun WireProtocol.findElementsFromShadowRoot(session: SessionCreated, strategy: FindElementStrategy, shadowId: String): Response =
    requestBuilder.post("/session/${session.id}/shadow/${shadowId}/elements", strategy)

fun WireProtocol.getActiveElement(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/element/active")

fun WireProtocol.getElementShadowRoot(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/shadow")

// state
fun WireProtocol.isElementSelected(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/element/{element id}/selected")

fun WireProtocol.getElementAttribute(session: SessionCreated, element: ElementRef, name: String): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/attribute/$name")

fun WireProtocol.getElementProperty(session: SessionCreated, element: ElementRef, name: String): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/property/$name")

fun WireProtocol.getElementCssValue(session: SessionCreated, element: ElementRef, name: String): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/css/$name")

fun WireProtocol.getElementText(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/text")

fun WireProtocol.getElementTagName(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/name")

fun WireProtocol.getElementRect(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/rect")

fun WireProtocol.isElementEnabled(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/enabled")

fun WireProtocol.getComputedRole(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/computedrole")

fun WireProtocol.getComputdLabel(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element/${element.reference}/computedlabel")

// interaction

fun WireProtocol.elementClick(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.post("/session/${session.id}/element/${element.reference}/click", "{}")
fun WireProtocol.elementClear(session: SessionCreated, element: ElementRef): Response =
    requestBuilder.get("/session/${session.id}/element${element.reference}/clear")
fun WireProtocol.elementSendKeys(session: SessionCreated, element: ElementRef, keys: SendKeys): Response =
    requestBuilder.post("/session/${session.id}/element/${element.reference}/value", keys)

