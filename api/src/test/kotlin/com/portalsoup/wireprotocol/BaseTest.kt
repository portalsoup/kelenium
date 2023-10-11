package com.portalsoup.wireprotocol

import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.serialization.serializers.ResponseSerializer
import kotlinx.serialization.json.Json

abstract class BaseTest {
    fun generateExampleApiResponse(payload: String): Response {
        val raw = "{\"value\": $payload}"
        val parsed = Json.parseToJsonElement(raw)
        return Json.decodeFromJsonElement(ResponseSerializer, parsed)
    }

}