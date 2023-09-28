package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.Status
import com.portalsoup.wireprotocol.dto.SuccessResponse

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun WireProtocol.createSession(capabilities: String? = null): SuccessResponse<Session> = requestBuilder.post("/session", capabilities ?: "{}")
fun WireProtocol.deleteSession(session: Session): SuccessResponse<Unit?> = requestBuilder.delete("/session/${session.id}")
fun WireProtocol.status(): SuccessResponse<Status> = requestBuilder.get("/status")