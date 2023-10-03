package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.SuccessResponse
import com.portalsoup.wireprotocol.dto.UrlBody
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated


// https://www.w3.org/TR/webdriver/#navigate-to
fun WireProtocol.navigateTo(session: SessionCreated, url: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/url", UrlBody(url))
fun WireProtocol.currentUrl(session: Session): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/url")
fun WireProtocol.back(session: Session): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/back", "{}")
fun WireProtocol.forward(session: Session): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/forward", "{}")
fun WireProtocol.refresh(session: Session): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/refresh", "{}")
fun WireProtocol.getTitle(session: Session): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/title")