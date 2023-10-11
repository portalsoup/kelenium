package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class InvalidCookieDomainTest: BaseErrorResponseTest() {
    @Test
    fun serializeInvalidCookieDomainTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.INVALID_COOKIE_DOMAIN.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(InvalidCookieDomain::class.java))
    }
}