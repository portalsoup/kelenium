package com.portalsoup.wireprotocol.cookie.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import kotlinx.serialization.json.JsonElement


fun WireProtocol.getAllCookies(session: SessionCreated): Response = get("/session/${session.id}/cookie")
fun WireProtocol.getNamedCookie(session: SessionCreated, name: String): Response = get("/session/${session.id}/cookie/$name")
fun WireProtocol.addCookie(session: SessionCreated, cookie: JsonElement): Response = post("/session/${session.id}/cookie", cookie)
fun WireProtocol.deleteCookie(session: SessionCreated, name: String): Response = delete("/session/${session.id}/$name")
fun WireProtocol.deleteAllCookies(session: SessionCreated): Response = delete("/session/${session.id}/cookie")