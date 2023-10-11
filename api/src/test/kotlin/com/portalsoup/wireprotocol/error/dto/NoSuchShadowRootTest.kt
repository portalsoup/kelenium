package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoSuchShadowRootTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchShadowRootTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_SHADOW_ROOT.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(NoSuchShadowRoot::class.java))
    }
}