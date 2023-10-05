package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.request.AlertText
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated


fun WireProtocol.dismissAlert(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/alert/dismiss", "{}")
fun WireProtocol.acceptAlert(session: SessionCreated): Response =
    requestBuilder.post("/session/${session.id}/alert/accept", "{}")
fun WireProtocol.getAlertText(session: SessionCreated): Response =
    requestBuilder.get("/session/${session.id}/alert/text")
fun WireProtocol.sendAlertText(session: SessionCreated, text: AlertText): Response =
    requestBuilder.post("/session/${session.id}/alert/text", text)

