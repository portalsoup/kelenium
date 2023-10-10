package com.portalsoup.wireprotocol.document.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.document.dto.Script
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated


fun WireProtocol.getPageSource(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/source")
fun WireProtocol.executeScript(session: SessionCreated, script: Script): Response = requestBuilder.post("/session/${session.id}/execute", script)
fun WireProtocol.executeAsyncScript(session: SessionCreated, script: Script): Response = requestBuilder.post("/session/${session.id}/execute/async", script)
