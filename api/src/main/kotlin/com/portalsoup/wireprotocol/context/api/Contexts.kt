package com.portalsoup.wireprotocol.context.api

import com.portalsoup.wireprotocol.context.dto.*
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated

fun WireProtocol.getWindowHandle(session: SessionCreated): Response =
    get("/session/${session.id}/window")
fun WireProtocol.closeWindow(session: SessionCreated): Response =
    delete("/session/${session.id}/window")
fun WireProtocol.switchToWindow(session: SessionCreated, handle: String): Response =
    post("/session/${session.id}/window", HandleArgument(handle))
fun WireProtocol.getWindowHandles(session: SessionCreated): Response =
    get("/session/${session.id}/window/handles")
fun WireProtocol.newWindow(session: SessionCreated, type: ContextType): Response =
    post("/session/${session.id}/window/new", TypeArgument(type.type))
fun WireProtocol.getWindowRect(session: SessionCreated): Response =
    get("/session/${session.id}/window/rect")
fun WireProtocol.setWindowRect(session: SessionCreated, rect: WindowRect): Response =
    post("/session/${session.id}/window/rect", rect)
fun WireProtocol.maximizeWindow(session: SessionCreated): Response =
    post("/session/${session.id}/window/maximize", "{}")
fun WireProtocol.minimizeWindow(session: SessionCreated): Response =
    post("/session/${session.id}/window/minimize", "{}")
fun WireProtocol.fullScreenWindow(session: SessionCreated): Response =
    post("/session/${session.id}/window/fullscreen", "{}")

// TOOD I don't understand these frame ones yet
fun WireProtocol.switchToFrame(session: SessionCreated, id: String): Response =
    post("/session/${session.id}/frame", IdArgument(id))
fun WireProtocol.switchToParentFrame(session: SessionCreated): Response =
    post("/session/${session.id}/frame/parent", "{}")
