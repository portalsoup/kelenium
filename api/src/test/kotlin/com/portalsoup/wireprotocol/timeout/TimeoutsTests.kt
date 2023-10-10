package com.portalsoup.wireprotocol.timeout

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.serialization.dto.timeout.Timeouts
import com.portalsoup.wireprotocol.timeout.api.getTimeouts
import com.portalsoup.wireprotocol.timeout.api.setTimeouts
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class TimeoutsTests: BaseTest() {

    @Test
    fun readTimeouts() {
        val api = getApi()
        useSession(api) {
            val originalTimeouts = api.getTimeouts(it)
            assertThat(originalTimeouts.value, equalTo(Timeouts()))
        }
    }

    @Test
    fun setTimeouts() {
        val api = getApi()
        useSession(api) {
            val originalTimeouts = api.getTimeouts(it)
            assertThat(originalTimeouts.value, equalTo(Timeouts()))

            val newTimeouts = Timeouts(5000, 5000, 0)
            api.setTimeouts(it, newTimeouts)
            assertThat(api.getTimeouts(it).value, equalTo(newTimeouts))

        }
    }
}