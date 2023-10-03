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
import com.portalsoup.wireprotocol.dto.Session
import com.portalsoup.wireprotocol.dto.Status
import java.io.Closeable

class RemoteDriverConnection(
    requestBuilder: HttpRequestBuilder,
    private val capabilities: String? = null
): Closeable {
    internal val wireProtocol = WireProtocol(requestBuilder)
    internal val session: Session by lazy { wireProtocol.createSession(capabilities).value }

    val navigate = Navigate(this)

    fun timeouts(l: Timeouts.() -> Unit) { Timeouts().apply(l).also { timeouts(it) } }
    val timeouts = ConnectionTimeouts(this)

    fun status(): Status = wireProtocol.status().value
    fun capture(): Screenshot = Screenshot(this)

    fun document(): Document = Document(this)
    fun <E> document(l: Document.() -> E) = l.invoke(document())

    override fun close() {
        wireProtocol.deleteSession(session)
    }
}