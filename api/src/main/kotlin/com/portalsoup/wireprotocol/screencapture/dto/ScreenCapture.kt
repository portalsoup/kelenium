package com.portalsoup.wireprotocol.screencapture.dto

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.serialization.dto.element.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.response.Response
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated


fun WireProtocol.takeScreenshot(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/screenshot")
fun WireProtocol.takeElementScreenshot(session: SessionCreated, element: ElementRef): Response = requestBuilder.get("/session/${session.id}/element/${element.reference}/screenshot")
