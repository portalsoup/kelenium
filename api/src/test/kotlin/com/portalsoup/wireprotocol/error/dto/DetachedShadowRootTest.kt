package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes.DETACHED_SHADOW_ROOT
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.jupiter.api.Test

class DetachedShadowRootTest: BaseErrorResponseTest() {
    @Test
    fun serializeDetachedShadowRootTest() {
        val response = generateAndSerializeExampleFailureResponse(DETACHED_SHADOW_ROOT.code)
        assertThat(response.value, instanceOf(DetachedShadowRoot::class.java))
    }
}