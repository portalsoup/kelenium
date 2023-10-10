package com.portalsoup.kelenium.framework.actions

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.screencapture.dto.takeElementScreenshot
import com.portalsoup.wireprotocol.screencapture.dto.takeScreenshot
import com.portalsoup.wireprotocol.serialization.dto.element.ElementRef

class Screenshot(override val connection: RemoteDriverConnection) : RemoteWebdriverOperation {
    fun screen(): String = connection.wireProtocol.takeScreenshot(connection.session).value.let {
        when(it) {
            is String -> it
            else -> throw RuntimeException("Couldn't get whole screen screenshot data from non-string response.")
        }
    }
    fun element(element: ElementRef): String = connection.wireProtocol.takeElementScreenshot(connection.session, element).value.let {
        when(it) {
            is String -> it
            else -> throw RuntimeException("Couldn't get element screenshot data from non-string response.")
        }
    }
}