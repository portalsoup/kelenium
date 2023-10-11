package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnableToSetCookieTest: BaseErrorResponseTest() {
    @Test
    fun serializeUnableToSetCookieTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.UNABLE_TO_SET_COOKIE.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(UnableToSetCookie::class.java))
    }
}