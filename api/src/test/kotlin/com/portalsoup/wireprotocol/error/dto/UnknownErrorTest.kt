package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnknownErrorTest: BaseErrorResponseTest() {
    @Test
    fun serializeUnknownErrorTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.UNKNOWN_ERROR.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(UnknownError::class.java))
    }
}