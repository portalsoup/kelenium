package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver
import com.portalsoup.core.socket.RemoteDriver
import com.portalsoup.core.socket.SuccessResponse
import kotlinx.serialization.Serializable

@Serializable
data class UrlBody(val url: String)


// https://www.w3.org/TR/webdriver/#navigate-to
fun RemoteDriver.navigateTo(url: String): SuccessResponse<Unit?> = post("/session/${session.id}/url", UrlBody(url))
fun RemoteDriver.currentUrl(): SuccessResponse<String> = get("/session/${session.id}/url")
fun RemoteDriver.back(): SuccessResponse<Unit?> = post("/session/${session.id}/back", "{}")
fun RemoteDriver.forward(): SuccessResponse<Unit?> = post("/session/${session.id}/forward", "{}")
fun RemoteDriver.refresh(): SuccessResponse<Unit?> = post("/session/${session.id}/refresh", "{}")
fun RemoteDriver.getTitle(): SuccessResponse<String> = get("/session/${session.id}/title")