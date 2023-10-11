package com.portalsoup.wireprotocol.screencapture.api

import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.session.dto.SessionCreated


fun WireProtocol.takeScreenshot(session: SessionCreated): Response = requestBuilder.get("/session/${session.id}/screenshot")
fun WireProtocol.takeElementScreenshot(session: SessionCreated, element: ElementRef): Response = requestBuilder.get("/session/${session.id}/element/${element.reference}/screenshot")
