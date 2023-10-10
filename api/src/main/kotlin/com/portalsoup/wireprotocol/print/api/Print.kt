package com.portalsoup.wireprotocol.print.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.print.PrintPage
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated

fun WireProtocol.printPage(session: SessionCreated, printSettings: PrintPage): Response = requestBuilder.post("/session/${session.id}/print", printSettings)
