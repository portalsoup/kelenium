package com.portalsoup.wireprotocol.prompt.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.prompt.dto.AlertText
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated


fun WireProtocol.dismissAlert(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/alert/dismiss", "{}")
fun WireProtocol.acceptAlert(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/alert/accept", "{}")
fun WireProtocol.getAlertText(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/alert/text")
fun WireProtocol.sendAlertText(session: SessionCreated, text: AlertText): Response =
    requestBuilder.post("/session/${session.id}/alert/text", text)

