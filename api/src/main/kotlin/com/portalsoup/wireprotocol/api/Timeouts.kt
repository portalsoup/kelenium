package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.SuccessResponse
import com.portalsoup.wireprotocol.dto.Timeouts

fun WireProtocol.getTimeouts(session: Session): SuccessResponse<Timeouts> = requestBuilder.get("/session/${session.id}/timeouts")
fun WireProtocol.setTimeouts(session: Session, timeouts: Timeouts): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/timeouts", timeouts)
