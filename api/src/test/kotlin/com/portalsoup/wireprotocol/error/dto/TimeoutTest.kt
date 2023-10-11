package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class TimeoutTest: BaseErrorResponseTest() {
    @Test
    fun serializeTimeoutTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.TIMEOUT.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(Timeout::class.java))
    }
}