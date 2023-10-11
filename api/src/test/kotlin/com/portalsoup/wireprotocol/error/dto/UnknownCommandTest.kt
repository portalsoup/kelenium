package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnknownCommandTest: BaseErrorResponseTest() {
    @Test
    fun serializeUnknownCommandTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.UNKNOWN_COMMAND.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(UnknownCommand::class.java))
    }
}