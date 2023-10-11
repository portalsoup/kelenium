package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class JavascriptErrorTest: BaseErrorResponseTest() {
    @Test
    fun serializeJavascriptErrorTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.JAVASCRIPT_ERROR.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(JavascriptError::class.java))
    }
}