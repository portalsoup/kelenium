package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.Status
import com.portalsoup.wireprotocol.dto.SuccessResponse

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun RemoteDriver.createSession(capabilities: String? = null): SuccessResponse<Session> = requestBuilder.post("/session", capabilities ?: "{}")
fun RemoteDriver.deleteSession(): SuccessResponse<Unit?> = requestBuilder.delete("/session/${session.id}")
fun RemoteDriver.status(): SuccessResponse<Status> = requestBuilder.get("/status")