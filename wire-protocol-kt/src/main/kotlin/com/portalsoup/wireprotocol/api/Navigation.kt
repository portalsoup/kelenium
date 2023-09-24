package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteWireDriver
import com.portalsoup.wireprotocol.dto.SuccessResponse
import com.portalsoup.wireprotocol.dto.UrlBody


// https://www.w3.org/TR/webdriver/#navigate-to
fun RemoteWireDriver.navigateTo(url: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/url", UrlBody(url))
fun RemoteWireDriver.currentUrl(): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/url")
fun RemoteWireDriver.back(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/back", "{}")
fun RemoteWireDriver.forward(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/forward", "{}")
fun RemoteWireDriver.refresh(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/refresh", "{}")
fun RemoteWireDriver.getTitle(): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/title")