package com.portalsoup.wireprotocol.timeout.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import com.portalsoup.wireprotocol.timeout.dto.Timeouts

fun WireProtocol.getTimeouts(session: SessionCreated): Response = get("/session/${session.id}/timeouts")
fun WireProtocol.setTimeouts(session: SessionCreated, timeouts: Timeouts): Response = post("/session/${session.id}/timeouts", timeouts)
