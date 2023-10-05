package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.serialization.dto.response.success.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated


fun WireProtocol.takeScreenshot(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/screenshot")
fun WireProtocol.takeElementScreenshot(session: SessionCreated, element: ElementRef): Response = requestBuilder.get("/session/${session.id}/element/${element.reference}/screenshot")
