package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnableToCaptureScreenTest: BaseErrorResponseTest() {
    @Test
    fun serializeUnableToCaptureScreenTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.UNABLE_TO_CAPTURE_SCREEN.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(UnableToCaptureScreen::class.java))
    }
}