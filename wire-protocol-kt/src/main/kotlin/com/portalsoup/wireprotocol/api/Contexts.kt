package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.SuccessResponse
import com.portalsoup.wireprotocol.dto.*

fun RemoteDriver.getWindowHandle(): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/window")
fun RemoteDriver.closeWindow(): SuccessResponse<Array<String>> = requestBuilder.delete("/session/${session.id}/window")
fun RemoteDriver.switchToWindow(handle: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/window", HandleArgument(handle))
fun RemoteDriver.getWindowHandles(): SuccessResponse<Array<String>> = requestBuilder.get("/session/${session.id}/window/handles")
fun RemoteDriver.newWindow(type: Type): SuccessResponse<NewWindow> = requestBuilder.post("/session/${session.id}/window/new", TypeArgument(type.type))
fun RemoteDriver.getWindowRect(): SuccessResponse<WindowRect> = requestBuilder.get("/session/${session.id}/window/rect")
fun RemoteDriver.setWindowRect(rect: WindowRect): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/rect", rect) // Window rect should be it's own property
fun RemoteDriver.maximizeWindow(): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/maximize", "{}")
fun RemoteDriver.minimizeWindow(): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/minimize", "{}")
fun RemoteDriver.fullScreenWindow(): SuccessResponse<WindowRect> = requestBuilder.post("/session/${session.id}/window/fullscreen", "{}")

// TOOD I don't understand these frame ones yet
fun RemoteDriver.switchToFrame(id: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/frame", IdArgument(id))
fun RemoteDriver.switchToParentFrame(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/frame/parent", "{}")
