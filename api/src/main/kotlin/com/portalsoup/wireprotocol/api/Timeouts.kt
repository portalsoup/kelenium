package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.serialization.dto.Response
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.success.Timeouts

fun WireProtocol.getTimeouts(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/timeouts")
fun WireProtocol.setTimeouts(session: SessionCreated, timeouts: Timeouts): Response = requestBuilder.post("/session/${session.id}/timeouts", timeouts)
