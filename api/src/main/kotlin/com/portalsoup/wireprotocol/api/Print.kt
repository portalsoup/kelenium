package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.request.print.PrintPage
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated

fun WireProtocol.printPage(session: SessionCreated, printSettings: PrintPage): Response = requestBuilder.post("/session/${session.id}/print", printSettings)
