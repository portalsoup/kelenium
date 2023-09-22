package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.SuccessResponse
import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.Status

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun RemoteDriver.createSession(capabilities: String = "{}"): SuccessResponse<Session> = requestBuilder.post("/session", capabilities)
fun RemoteDriver.deleteSession(): SuccessResponse<Unit?> = requestBuilder.delete("/session/${session.id}")
fun RemoteDriver.status(): SuccessResponse<Status> = requestBuilder.get("/status")