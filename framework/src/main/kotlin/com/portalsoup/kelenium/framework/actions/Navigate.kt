package com.portalsoup.kelenium.framework.actions

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.api.*

class Navigate internal constructor(override val connection: RemoteDriverConnection): RemoteWebdriverOperation {
    fun to(url: String) {
        connection.wireProtocol.navigateTo(connection.session, url)
    }

    fun current(): String = connection.wireProtocol.currentUrl(connection.session).value

    fun back() {
        connection.wireProtocol.back(connection.session)
    }

    fun refresh() {
        connection.wireProtocol.refresh(connection.session)
    }

    fun title(): String = connection.wireProtocol.getTitle(connection.session).value
}