package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.dto.Timeouts
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test

class TimeoutsTests: BaseTest() {

    @Test
    fun readTimeouts() {
        initializeWebdriver().use {
            val originalTimeouts = it.getTimeouts()
            assertThat(originalTimeouts.value, equalTo(Timeouts()))
        }
    }

    @Test
    fun setTimeouts() {
        initializeWebdriver().use {
            val originalTimeouts = it.getTimeouts()
            assertThat(originalTimeouts.value, equalTo(Timeouts()))

            val newTimeouts = Timeouts(5000, 5000, 0)
            it.setTimeouts(newTimeouts)
            assertThat(it.getTimeouts().value, equalTo(newTimeouts))

        }
    }
}