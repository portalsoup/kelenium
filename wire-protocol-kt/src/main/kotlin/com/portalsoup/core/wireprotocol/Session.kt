package com.portalsoup.core.wireprotocol

import com.portalsoup.core.socket.RemoteDriver
import com.portalsoup.core.socket.SuccessResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Session(@SerialName("sessionId") val id: String, val capabilities: JsonObject)
@Serializable
data class Status(val message: String, val ready: Boolean)

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun RemoteDriver.createSession(): SuccessResponse<Session> = post("/session", "{}")
fun RemoteDriver.deleteSession(): SuccessResponse<Unit?> = delete("/session/${session.id}")
fun RemoteDriver.status(): SuccessResponse<Status> = get("/status")