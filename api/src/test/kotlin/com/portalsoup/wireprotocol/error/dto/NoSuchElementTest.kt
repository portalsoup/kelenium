package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoSuchElementTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchElementTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_ELEMENT.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(NoSuchElement::class.java))
    }
}