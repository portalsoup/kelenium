package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SessionNotCreatedTest: BaseErrorResponseTest() {
    @Test
    fun serializeSessionNotCreatedTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.SESSION_NOT_CREATED.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(SessionNotCreated::class.java))
    }
}