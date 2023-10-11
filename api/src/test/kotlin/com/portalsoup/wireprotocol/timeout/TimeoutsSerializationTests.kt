package com.portalsoup.wireprotocol.timeout

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.timeout.dto.Timeouts
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class TimeoutsSerializationTests: BaseTest() {
    @Test
    fun serializeTimeoutsTest() {
        val script = 5
        val pageLoad = 5
        val implicit = 5
        val response = generateExampleApiRespones(
            """
            {
                "script": "$script",
                "pageLoad": "$pageLoad",
                "implicit": "$implicit"
            }
        """.trimIndent())

        MatcherAssert.assertThat(response.value, Matchers.equalTo(Timeouts(script, pageLoad, implicit)))
    }
}