package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.dto.RemoteDriverClosedException
import com.portalsoup.wireprotocol.dto.Session
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SessionTests: BaseTest() {
    @Test
    fun remoteDriverOpenCloseLifecycleTest() {
        val api = getApi()
        useSession(api) { session: Session ->
            println(session.id)
            assert(session.id.isNotEmpty())
            println(api.status())
            assertThat(api.status().value.message, equalTo("Session already started"))

            api.deleteSession(session)
            assertThrows<RemoteDriverClosedException> { api.navigateTo(session, HtmlPages.TextField.asUrl()) }
        }
    }
}