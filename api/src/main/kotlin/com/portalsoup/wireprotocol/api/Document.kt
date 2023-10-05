package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.request.Script
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated


fun WireProtocol.getPageSource(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/source")
fun WireProtocol.executeScript(session: SessionCreated, script: Script): Response = requestBuilder.post("/session/${session.id}/execute", script)
fun WireProtocol.executeAsyncScript(session: SessionCreated, script: Script): Response = requestBuilder.post("/session/${session.id}/execute/async", script)
