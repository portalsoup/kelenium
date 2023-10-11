package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ScriptTimeoutTest: BaseErrorResponseTest() {
    @Test
    fun serializeScriptTimeoutTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.SCRIPT_TIMEOUT.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(ScriptTimeout::class.java))
    }
}