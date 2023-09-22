package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.SuccessResponse
import com.portalsoup.wireprotocol.dto.Timeouts

fun RemoteDriver.getTimeouts(): SuccessResponse<Timeouts> = requestBuilder.get("/session/${session.id}/timeouts")
fun RemoteDriver.setTimeouts(timeouts: Timeouts): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/timeouts", timeouts)
