package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnknownMethodTest: BaseErrorResponseTest() {
    @Test
    fun serializeUnknownMethodTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.UNKNOWN_METHOD.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(UnknownMethod::class.java))
    }
}