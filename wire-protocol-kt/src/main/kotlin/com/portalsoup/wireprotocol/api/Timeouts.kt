package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteWireDriver
import com.portalsoup.wireprotocol.dto.SuccessResponse
import com.portalsoup.wireprotocol.dto.Timeouts

fun RemoteWireDriver.getTimeouts(): SuccessResponse<Timeouts> = requestBuilder.get("/session/${session.id}/timeouts")
fun RemoteWireDriver.setTimeouts(timeouts: Timeouts): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/timeouts", timeouts)
