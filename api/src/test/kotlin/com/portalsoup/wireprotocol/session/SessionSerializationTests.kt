package com.portalsoup.wireprotocol.session

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import com.portalsoup.wireprotocol.session.dto.Status
import kotlinx.serialization.json.JsonObject
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class SessionSerializationTests: BaseTest() {
    @Test
    fun serializeSessionCreatedTest() {
        val id = "test id"
        val response = generateExampleApiResponse(
            """
            {
                "sessionId": "$id",
                "capabilities": {}
            }
        """.trimIndent()
        )

        MatcherAssert.assertThat(response.value, Matchers.equalTo(SessionCreated(id, JsonObject(emptyMap()))))
    }

    @Test
    fun serializeStatusTest() {
        val message = "test message"
        val ready = true
        val response = generateExampleApiResponse(
            """
            {
                "message": "$message",
                "ready": $ready
            }
        """.trimIndent()
        )

        MatcherAssert.assertThat(response.value, Matchers.equalTo(Status(message, ready)))
    }
}