package com.portalsoup.kelenium.framework.actions

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.timeout.api.getTimeouts
import com.portalsoup.wireprotocol.timeout.api.setTimeouts
import com.portalsoup.wireprotocol.serialization.dto.timeout.Timeouts

class ConnectionTimeouts internal constructor(override val connection: RemoteDriverConnection): RemoteWebdriverOperation {
    operator fun invoke(timeouts: Timeouts) = set(timeouts)
    operator fun invoke(): Timeouts = get()

    fun get(): Timeouts = connection.wireProtocol.getTimeouts(connection.session).let {
        when (val timeouts = it.value) {
            is Timeouts -> timeouts
            else -> throw RuntimeException("Malformed timeouts response.")
        }
    }

    fun set(timeouts: Timeouts) {
        connection.wireProtocol.setTimeouts(connection.session, timeouts)
    }

    fun script(timeout: Int) = get().copy(script = timeout).let { connection.wireProtocol.setTimeouts(connection.session, it) }
    fun pageLoad(timeout: Int) = get().copy(pageLoad = timeout).let { connection.wireProtocol.setTimeouts(connection.session, it) }
    fun implicit(timeout: Int) = get().copy(implicit = timeout).let { connection.wireProtocol.setTimeouts(connection.session, it) }
}
