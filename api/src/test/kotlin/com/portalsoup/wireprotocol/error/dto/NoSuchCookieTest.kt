package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoSuchCookieTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchCookieTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_COOKIE.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(NoSuchCookie::class.java))
    }
}