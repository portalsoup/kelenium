package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteWireDriver
import com.portalsoup.wireprotocol.dto.*

fun RemoteWireDriver.getWindowHandle(): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/window")
fun RemoteWireDriver.closeWindow(): SuccessResponse<Array<String>> = requestBuilder.delete("/session/${session.id}/window")
fun RemoteWireDriver.switchToWindow(handle: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/window", HandleArgument(handle))
fun RemoteWireDriver.getWindowHandles(): SuccessResponse<Array<String>> = requestBuilder.get("/session/${session.id}/window/handles")
fun RemoteWireDriver.newWindow(type: Type): SuccessResponse<NewWindow> = requestBuilder.post("/session/${session.id}/window/new", TypeArgument(type.type))
fun RemoteWireDriver.getWindowRect(): SuccessResponse<WindowRect> = requestBuilder.get("/session/${session.id}/window/rect")
fun RemoteWireDriver.setWindowRect(rect: WindowRect): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/rect", rect) // Window rect should be it's own property
fun RemoteWireDriver.maximizeWindow(): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/maximize", "{}")
fun RemoteWireDriver.minimizeWindow(): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/minimize", "{}")
fun RemoteWireDriver.fullScreenWindow(): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/fullscreen", "{}")

// TOOD I don't understand these frame ones yet
fun RemoteWireDriver.switchToFrame(id: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/frame", IdArgument(id))
fun RemoteWireDriver.switchToParentFrame(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/frame/parent", "{}")
