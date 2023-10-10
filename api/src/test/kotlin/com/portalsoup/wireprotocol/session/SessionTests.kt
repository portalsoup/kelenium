package com.portalsoup.wireprotocol.session

import com.portalsoup.wireprotocol.BaseApiTest
import com.portalsoup.wireprotocol.session.api.status
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import com.portalsoup.wireprotocol.session.dto.Status
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.jupiter.api.Test

class SessionTests: BaseApiTest() {
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