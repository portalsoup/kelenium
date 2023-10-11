package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class InvalidElementStateTest: BaseErrorResponseTest() {
    @Test
    fun serializeInvalidElementStateTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.INVALID_ELEMENT_STATE.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(InvalidElementState::class.java))
    }
}