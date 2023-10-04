package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.serialization.dto.success.ElementRef
import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.SuccessResponse


fun WireProtocol.takeScreenshot(session: Session): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/screenshot")
fun WireProtocol.takeElementScreenshot(session: Session, element: ElementRef): SuccessResponse<String> = requestBuilder.get("/session/${session.id}/element/${element.reference}/screenshot")
