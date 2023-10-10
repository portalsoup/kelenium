package com.portalsoup.wireprotocol.cookie.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import kotlinx.serialization.json.JsonElement


fun WireProtocol.getAllCookies(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/cookie")
fun WireProtocol.getNamedCookie(session: SessionCreated, name: String): Response = requestBuilder.get("/session/${session.id}/cookie/$name")
fun WireProtocol.addCookie(session: SessionCreated, cookie: JsonElement): Response = requestBuilder.post("/session/${session.id}/cookie", cookie)
fun WireProtocol.deleteCookie(session: SessionCreated, name: String): Response = requestBuilder.delete("/session/${session.id}/$name")
fun WireProtocol.deleteAllCookies(session: SessionCreated): Response = requestBuilder.delete("/session/${session.id}/cookie")