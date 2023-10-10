package com.portalsoup.wireprotocol.session.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun WireProtocol.createSession(capabilities: String? = null): Response = requestBuilder.post("/session", capabilities ?: "{}")
fun WireProtocol.deleteSession(session: SessionCreated): Response = requestBuilder.delete("/session/${session.id}")
fun WireProtocol.status(): Response = requestBuilder.get("/status")