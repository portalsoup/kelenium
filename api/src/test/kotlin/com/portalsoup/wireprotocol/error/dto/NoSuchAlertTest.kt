package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class NoSuchAlertTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchAlertTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_ALERT.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(NoSuchAlert::class.java))
    }
}