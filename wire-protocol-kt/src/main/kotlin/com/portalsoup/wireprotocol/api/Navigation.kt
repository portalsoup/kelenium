package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.dto.SuccessResponse
import com.portalsoup.wireprotocol.dto.UrlBody


// https://www.w3.org/TR/webdriver/#navigate-to
fun RemoteDriver.navigateTo(url: String): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/url", UrlBody(url))
fun RemoteDriver.currentUrl(): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/url")
fun RemoteDriver.back(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/back", "{}")
fun RemoteDriver.forward(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/forward", "{}")
fun RemoteDriver.refresh(): SuccessResponse<Unit?> = requestBuilder.post("/session/${session.id}/refresh", "{}")
fun RemoteDriver.getTitle(): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/title")