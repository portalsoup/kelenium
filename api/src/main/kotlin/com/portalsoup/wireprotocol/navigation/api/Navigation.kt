package com.portalsoup.wireprotocol.navigation.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.navigation.dto.Url
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated


// https://www.w3.org/TR/webdriver/#navigate-to
fun WireProtocol.navigateTo(session: SessionCreated, url: String): Response = requestBuilder.post("/session/${session.id}/url", Url(url))
fun WireProtocol.currentUrl(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/url")
fun WireProtocol.back(session: SessionCreated): Response = requestBuilder.post("/session/${session.id}/back", "{}")
fun WireProtocol.forward(session: SessionCreated): Response = requestBuilder.post("/session/${session.id}/forward", "{}")
fun WireProtocol.refresh(session: SessionCreated): Response = requestBuilder.post("/session/${session.id}/refresh", "{}")
fun WireProtocol.getTitle(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/title")