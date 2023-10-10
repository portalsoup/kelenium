package com.portalsoup.wireprotocol.timeout.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.timeout.Timeouts

fun WireProtocol.getTimeouts(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/timeouts")
fun WireProtocol.setTimeouts(session: SessionCreated, timeouts: Timeouts): Response = requestBuilder.post("/session/${session.id}/timeouts", timeouts)
