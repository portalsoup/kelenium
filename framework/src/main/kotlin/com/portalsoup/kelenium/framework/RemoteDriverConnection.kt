package com.portalsoup.kelenium.framework

import com.portalsoup.kelenium.framework.actions.ConnectionTimeouts
import com.portalsoup.kelenium.framework.actions.Document
import com.portalsoup.kelenium.framework.actions.Navigate
import com.portalsoup.kelenium.framework.actions.Screenshot
import com.portalsoup.wireprotocol.api.WireProtocol
import com.portalsoup.wireprotocol.api.createSession
import com.portalsoup.wireprotocol.api.deleteSession
import com.portalsoup.wireprotocol.api.status
import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import com.portalsoup.wireprotocol.serialization.dto.response.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.dto.response.success.Status
import com.portalsoup.wireprotocol.serialization.dto.response.success.Timeouts
import java.io.Closeable

class RemoteDriverConnection(
    requestBuilder: HttpRequestBuilder,
    private val capabilities: String? = null
): Closeable {
    internal val wireProtocol = WireProtocol(requestBuilder)
    internal val session: SessionCreated by lazy {
        when (val obj = wireProtocol.createSession(capabilities).value) {
            is SessionCreated -> obj
            else -> throw RuntimeException("Failed to create a new session")
        }
    }

    val navigate = Navigate(this)

    fun timeouts(l: Timeouts.() -> Unit) { Timeouts().apply(l).also { timeouts(it) } }
    val timeouts = ConnectionTimeouts(this)

    fun status(): Status = when (val obj = wireProtocol.status().value) {
        is Status -> obj
        else -> throw RuntimeException("Failed to read status: $obj")
    }
    fun capture(): Screenshot = Screenshot(this)

    fun document(): Document = Document(this)
    fun <E> document(l: Document.() -> E) = l.invoke(document())

    override fun close() {
        wireProtocol.deleteSession(session)
    }
}