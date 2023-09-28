package com.portalsoup.kelenium.framework.actions

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.api.takeElementScreenshot
import com.portalsoup.wireprotocol.api.takeScreenshot
import com.portalsoup.wireprotocol.dto.Element

class Screenshot(override val connection: RemoteDriverConnection) : RemoteWebdriverOperation {
    fun screen(): String = connection.wireProtocol.takeScreenshot(connection.session).value
    fun element(element: Element): String = connection.wireProtocol.takeElementScreenshot(connection.session, element).value
}