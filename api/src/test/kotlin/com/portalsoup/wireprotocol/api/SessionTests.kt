package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.response.success.Status
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.Test

class SessionTests: BaseTest() {
    @Test
    fun remoteDriverOpenCloseLifecycleTest() {
        val api = getApi()
        useSession(api) { session: SessionCreated ->
            println(session.id)
            assert(session.id.isNotEmpty())
            val status = api.status().value

            println(status)
            assertThat(status, instanceOf(Status::class.java))
            assertThat((status as Status).message, equalTo("Session already started"))

////            api.deleteSessdeleteSessionion(session)
//            assertThrows<RemoteDriverClosedException> { api.navigateTo(session, HtmlPages.TextField.asUrl()) }
        }
    }
}