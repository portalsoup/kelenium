package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.dto.*

fun WireProtocol.getWindowHandle(session: Session): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/window")
fun WireProtocol.closeWindow(session: Session): SuccessResponse<Array<String>> = requestBuilder.delete("/session/${session.id}/window")
fun WireProtocol.switchToWindow(session: Session, handle: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/window", HandleArgument(handle))
fun WireProtocol.getWindowHandles(session: Session): SuccessResponse<Array<String>> = requestBuilder.get("/session/${session.id}/window/handles")
fun WireProtocol.newWindow(session: Session, type: Type): SuccessResponse<NewWindow> = requestBuilder.post("/session/${session.id}/window/new", TypeArgument(type.type))
fun WireProtocol.getWindowRect(session: Session): SuccessResponse<WindowRect> = requestBuilder.get("/session/${session.id}/window/rect")
fun WireProtocol.setWindowRect(session: Session, rect: WindowRect): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/rect", rect) // Window rect should be it's own property
fun WireProtocol.maximizeWindow(session: Session): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/maximize", "{}")
fun WireProtocol.minimizeWindow(session: Session): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/minimize", "{}")
fun WireProtocol.fullScreenWindow(session: Session): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/fullscreen", "{}")

// TOOD I don't understand these frame ones yet
fun WireProtocol.switchToFrame(session: Session, id: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/frame", IdArgument(id))
fun WireProtocol.switchToParentFrame(session: Session): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/frame/parent", "{}")
