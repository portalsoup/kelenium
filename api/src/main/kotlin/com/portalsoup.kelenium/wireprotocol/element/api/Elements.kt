package com.portalsoup.wireprotocol.element.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.element.dto.FindElementStrategy
import com.portalsoup.wireprotocol.element.dto.SendKeys
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.response.TextResponse
import com.portalsoup.wireprotocol.session.dto.SessionCreated

// Locators
fun WireProtocol.findElement(session: SessionCreated, code: String, expression: String): Response =
    post("/session/${session.id}/element", FindElementStrategy(code, expression))

fun WireProtocol.findElements(session: SessionCreated, code: String, expression: String): Response =
    post("/session/${session.id}/elements", FindElementStrategy(code, expression))

fun WireProtocol.findElementFromElement(session: SessionCreated, css: String, parent: ElementRef): Response =
    post("/session/${session.id}/element/${parent.reference}/element", css)

fun WireProtocol.findElementsFromElement(session: SessionCreated, css: String, parent: ElementRef): Response =
    post("/session/${session.id}/element/${parent.reference}/elements", css)

fun WireProtocol.findElementFromShadowRoot(session: SessionCreated, rootReference: String, code: String, expression: String): Response =
    post("/session/${session.id}/shadow/${rootReference}/element", FindElementStrategy(code, expression))

fun WireProtocol.findElementsFromShadowRoot(session: SessionCreated, code: String, expression: String, shadowId: String): Response =
    post("/session/${session.id}/shadow/${shadowId}/elements", FindElementStrategy(code, expression))

fun WireProtocol.getActiveElement(session: SessionCreated): Response =
    get("/session/${session.id}/element/active")

fun WireProtocol.getElementShadowRoot(session: SessionCreated, element: ElementRef): Response {
    return get("/session/${session.id}/element/${element.reference}/shadow")
}

// state
fun WireProtocol.isElementSelected(session: SessionCreated): Response =
    get("/session/${session.id}/element/{element id}/selected")

fun WireProtocol.getElementAttribute(session: SessionCreated, element: ElementRef, name: String): Response =
    get("/session/${session.id}/element/${element.reference}/attribute/$name")

fun WireProtocol.getElementProperty(session: SessionCreated, element: ElementRef, name: String): Response =
    get("/session/${session.id}/element/${element.reference}/property/$name")

fun WireProtocol.getElementCssValue(session: SessionCreated, element: ElementRef, name: String): Response =
    get("/session/${session.id}/element/${element.reference}/css/$name")

fun WireProtocol.getElementText(session: SessionCreated, element: ElementRef): TextResponse =
    get("/session/${session.id}/element/${element.reference}/text")

fun WireProtocol.getElementTagName(session: SessionCreated, element: ElementRef): Response =
    get("/session/${session.id}/element/${element.reference}/name")

fun WireProtocol.getElementRect(session: SessionCreated, element: ElementRef): Response =
    get("/session/${session.id}/element/${element.reference}/rect")

fun WireProtocol.isElementEnabled(session: SessionCreated, element: ElementRef): Response =
    get("/session/${session.id}/element/${element.reference}/enabled")

fun WireProtocol.getComputedRole(session: SessionCreated, element: ElementRef): Response =
    get("/session/${session.id}/element/${element.reference}/computedrole")

fun WireProtocol.getComputdLabel(session: SessionCreated, element: ElementRef): Response =
    get("/session/${session.id}/element/${element.reference}/computedlabel")

// interaction

fun WireProtocol.elementClick(session: SessionCreated, element: ElementRef): Response =
    post("/session/${session.id}/element/${element.reference}/click", "{}")
fun WireProtocol.elementClear(session: SessionCreated, element: ElementRef): Response =
    get("/session/${session.id}/element${element.reference}/clear")
fun WireProtocol.elementSendKeys(session: SessionCreated, element: ElementRef, keys: SendKeys): Response =
    post("/session/${session.id}/element/${element.reference}/value", keys)

