package com.portalsoup.core.wireprotocol

import com.portalsoup.core.socket.RemoteDriver
import com.portalsoup.core.socket.SuccessResponse
import kotlinx.serialization.Serializable

@Serializable
data class NewWindow(val handle: String, val type: String)

enum class Type(val type: String) { WINDOW("window"), TAB("tab") }

@Serializable
data class TypeArgument(val type: String)

@Serializable
data class HandleArgument(val handle: String)

@Serializable
data class IdArgument(val id: String)

@Serializable
data class WindowRect(val x: Int, val y: Int, val width: Int, val height: Int)

fun RemoteDriver.getWindowHandle(): SuccessResponse<String> = get("/session/${session.id}/window")
fun RemoteDriver.closeWindow(): SuccessResponse<Array<String>> = delete("/session/${session.id}/window")
fun RemoteDriver.switchToWindow(handle: String): SuccessResponse<Unit?> = post("/session/${session.id}/window", HandleArgument(handle))
fun RemoteDriver.getWindowHandles(): SuccessResponse<Array<String>> = get("/session/${session.id}/window/handles")
fun RemoteDriver.newWindow(type: Type): SuccessResponse<NewWindow> = post("/session/${session.id}/window/new", TypeArgument(type.type))
fun RemoteDriver.getWindowRect(): SuccessResponse<WindowRect> = get("/session/${session.id}/window/rect")
fun RemoteDriver.setWindowRect(rect: WindowRect): SuccessResponse<WindowRect> = post("/session/${session.id}/window/rect", rect) // Window rect should be it's own property
fun RemoteDriver.maximizeWindow(): SuccessResponse<WindowRect> = post("/session/${session.id}/window/maximize", "{}")
fun RemoteDriver.minimizeWindow(): SuccessResponse<WindowRect> = post("/session/${session.id}/window/minimize", "{}")
fun RemoteDriver.fullScreenWindow(): SuccessResponse<WindowRect> = post("/session/${session.id}/window/fullscreen", "{}")

// TOOD I don't understand these frame ones yet
fun RemoteDriver.switchToFrame(id: String): SuccessResponse<Unit?> = post("/session/${session.id}/frame", IdArgument(id))
fun RemoteDriver.switchToParentFrame(): SuccessResponse<Unit?> = post("/session/${session.id}/frame/parent", "{}")
