package com.portalsoup.core.wireprotocol

import com.portalsoup.core.BaseTest
import com.portalsoup.core.socket.RemoteDriver
import com.portalsoup.core.socket.RemoteDriverClosedException
import com.portalsoup.core.util.ILogging
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SessionTests: BaseTest() {
    @Test
    fun remoteDriverOpenCloseLifecycleTest() {
        val driver = initializeWebdriver()

        driver.use {
            val session = it.session

            println(session.id)
            assert(session.id.isNotEmpty())
            println(driver.status())
            assertThat(driver.status().value.message, equalTo("Session already started"))
        }

        assertThrows<RemoteDriverClosedException> { driver.status() }
    }
}