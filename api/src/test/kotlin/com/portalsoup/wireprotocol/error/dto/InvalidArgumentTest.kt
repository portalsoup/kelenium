package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class InvalidArgumentTest: BaseErrorResponseTest() {
    @Test
    fun serializeInvalidArgumentTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.INVALID_ARGUMENT.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(InvalidArgument::class.java))
    }
}