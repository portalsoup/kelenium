package com.portalsoup.wireprotocol.session.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun WireProtocol.createSession(capabilities: String? = null): Response = post("/session", capabilities ?: "{}")
fun WireProtocol.deleteSession(session: SessionCreated): Response = delete("/session/${session.id}")
fun WireProtocol.status(): Response = get("/status")