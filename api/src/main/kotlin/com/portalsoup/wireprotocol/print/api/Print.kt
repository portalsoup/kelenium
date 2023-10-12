package com.portalsoup.wireprotocol.print.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.print.dto.PrintPage
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated

fun WireProtocol.printPage(session: SessionCreated, printSettings: PrintPage): Response = post("/session/${session.id}/print", printSettings)
