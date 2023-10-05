package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.serialization.dto.success.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.Response
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated


fun WireProtocol.takeScreenshot(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/screenshot")
fun WireProtocol.takeElementScreenshot(session: SessionCreated, element: ElementRef): Response = requestBuilder.get("/session/${session.id}/element/${element.reference}/screenshot")
