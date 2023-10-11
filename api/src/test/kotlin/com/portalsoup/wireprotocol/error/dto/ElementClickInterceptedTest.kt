package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class ElementClickInterceptedTest: BaseErrorResponseTest() {
    @Test
    fun serializeElementClickInterceptedTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.ELEMENT_CLICK_INTERCEPTED.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(ElementClickIntercepted::class.java))
    }
}
