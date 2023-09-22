package com.portalsoup.core.wireprotocol

import com.portalsoup.core.socket.RemoteDriver
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
// TODO This can actually be polymorphic as per https://www.w3.org/TR/webdriver2/#dfn-success for the success or error wrapper
data class SessionWrapper(val value: Session)
@Serializable
data class Session(@SerialName("sessionId") val id: String, val capabilities: JsonObject)
@Serializable
data class StatusWrapper(val value: Status)
@Serializable
data class Status(val message: String, val ready: Boolean)
@Serializable
data class DeleteSession(val value: RemoteDriver.Companion.EmptyJson?)

@Serializable
class EmptyCapabilities

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun RemoteDriver.createSession(): SessionWrapper = post("/session", EmptyCapabilities())

// https://www.w3.org/TR/webdriver2/#dfn-delete-session
fun RemoteDriver.deleteSession(session: Session):DeleteSession = delete("/session/${session.id}")

//// https://www.w3.org/TR/webdriver2/#dfn-status
fun RemoteDriver.status(): StatusWrapper = get("/status")