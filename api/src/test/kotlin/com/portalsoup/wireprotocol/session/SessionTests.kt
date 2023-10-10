package com.portalsoup.wireprotocol.session

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.serialization.dto.session.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.session.Status
import com.portalsoup.wireprotocol.session.api.status
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