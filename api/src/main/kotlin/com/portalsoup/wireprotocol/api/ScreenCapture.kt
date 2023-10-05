package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.serialization.dto.success.ElementRef
import com.portalsoup.wireprotocol.dto.SuccessResponse
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated


fun WireProtocol.takeScreenshot(session: SessionCreated): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/screenshot")
fun WireProtocol.takeElementScreenshot(session: SessionCreated, element: ElementRef): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/element/${element.reference}/screenshot")
