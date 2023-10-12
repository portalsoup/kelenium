package com.portalsoup.wireprotocol.prompt.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.prompt.dto.AlertText
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated


fun WireProtocol.dismissAlert(session: SessionCreated): Response =
    post("/session/${session.id}/alert/dismiss", "{}")
fun WireProtocol.acceptAlert(session: SessionCreated): Response =
    post("/session/${session.id}/alert/accept", "{}")
fun WireProtocol.getAlertText(session: SessionCreated): Response =
    get("/session/${session.id}/alert/text")
fun WireProtocol.sendAlertText(session: SessionCreated, text: AlertText): Response =
    post("/session/${session.id}/alert/text", text)

