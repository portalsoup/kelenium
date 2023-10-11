package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InvalidSessionIdTest: BaseErrorResponseTest() {
    @Test
    fun serializeInvalidSessionIdTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.INVALID_SESSION_ID.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(InvalidSessionId::class.java))
    }
}