package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NoSuchWindowTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchWindowTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_WINDOW.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(NoSuchWindow::class.java))
    }
}