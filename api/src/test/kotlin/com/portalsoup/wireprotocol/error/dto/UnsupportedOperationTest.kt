package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class UnsupportedOperationTest: BaseErrorResponseTest() {
    @Test
    fun serializeUnsupportedOperationTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.UNSUPPORTED_OPERATION.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(UnsupportedOperation::class.java))
    }
}