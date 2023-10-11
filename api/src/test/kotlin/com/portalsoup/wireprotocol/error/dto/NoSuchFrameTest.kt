package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class NoSuchFrameTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchFrameTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_FRAME.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(NoSuchFrame::class.java))
    }
}