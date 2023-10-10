package com.portalsoup.wireprotocol.document.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.document.Script
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated


fun WireProtocol.getPageSource(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/source")
fun WireProtocol.executeScript(session: SessionCreated, script: Script): Response = requestBuilder.post("/session/${session.id}/execute", script)
fun WireProtocol.executeAsyncScript(session: SessionCreated, script: Script): Response = requestBuilder.post("/session/${session.id}/execute/async", script)
