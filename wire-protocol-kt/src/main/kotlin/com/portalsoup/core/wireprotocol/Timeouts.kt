package com.portalsoup.core.wireprotocol

import com.portalsoup.core.socket.RemoteDriver
import com.portalsoup.core.socket.SuccessResponse
import kotlinx.serialization.Serializable

@Serializable
data class Timeouts(val script: Int = 30000, val pageLoad: Int = 300000, val implicit: Int = 0)
fun RemoteDriver.getTimeouts(): SuccessResponse<Timeouts> = get("/session/${session.id}/timeouts")
fun RemoteDriver.setTimeouts(timeouts: Timeouts): SuccessResponse<Unit?> = post("/session/${session.id}/timeouts", timeouts)
