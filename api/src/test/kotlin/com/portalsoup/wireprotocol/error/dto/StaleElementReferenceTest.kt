package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StaleElementReferenceTest: BaseErrorResponseTest() {
    @Test
    fun serializeStaleElementReferenceTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.STALE_ELEMENT_REFERENCE.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(StaleElementReference::class.java))
    }
}