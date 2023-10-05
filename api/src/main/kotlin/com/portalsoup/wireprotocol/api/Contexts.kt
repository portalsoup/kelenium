package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.serialization.dto.request.context.HandleArgument
import com.portalsoup.wireprotocol.serialization.dto.request.context.IdArgument
import com.portalsoup.wireprotocol.serialization.dto.request.context.TypeArgument
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.ContextType
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.response.success.WindowRect

fun WireProtocol.getWindowHandle(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/window")
fun WireProtocol.closeWindow(session: SessionCreated): Response =
    requestBuilder.delete("/session/${session.id}/window")
fun WireProtocol.switchToWindow(session: SessionCreated, handle: String): Response =
    requestBuilder.post("/session/${session.id}/window", HandleArgument(handle))
fun WireProtocol.getWindowHandles(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/window/handles")
fun WireProtocol.newWindow(session: SessionCreated, type: ContextType): Response =
    requestBuilder.post("/session/${session.id}/window/new", TypeArgument(type.type))
fun WireProtocol.getWindowRect(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/window/rect")
fun WireProtocol.setWindowRect(session: SessionCreated, rect: WindowRect): Response =
    requestBuilder.post("/session/${session.id}/window/rect", rect) // Window rect should be it's own property
fun WireProtocol.maximizeWindow(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/window/maximize", "{}")
fun WireProtocol.minimizeWindow(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/window/minimize", "{}")
fun WireProtocol.fullScreenWindow(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/window/fullscreen", "{}")

// TOOD I don't understand these frame ones yet
fun WireProtocol.switchToFrame(session: SessionCreated, id: String): Response =
    requestBuilder.post("/session/${session.id}/frame", IdArgument(id))
fun WireProtocol.switchToParentFrame(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/frame/parent", "{}")
