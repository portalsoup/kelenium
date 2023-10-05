package com.portalsoup.kelenium.framework.actions

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.kelenium.framework.element.WebElement
import com.portalsoup.wireprotocol.api.findElement
import com.portalsoup.wireprotocol.api.findElements
import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.serialization.dto.success.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.success.ElementRefList

class Document(override val connection: RemoteDriverConnection) : RemoteWebdriverOperation {
    class Find(override val connection: RemoteDriverConnection): RemoteWebdriverOperation {
        fun element(strategy: LocationStrategy, expression: String): WebElement =
            connection.wireProtocol
                .findElement(connection.session, strategy, expression)
                .value
                .let { when (it) {
                    is ElementRef -> it
                    else -> throw RuntimeException("Failed to find an element $it")
                } }
                .let { WebElement(connection, it) }

        fun elements(strategy: LocationStrategy, expression: String): List<WebElement> =
            connection.wireProtocol
                .findElements(connection.session, strategy, expression)
                .value
                .let {
                    when (it) {
                        is ElementRefList -> it
                        else -> throw RuntimeException("Malformed elements list in response")
                    }
                }
                .map { WebElement(connection, it) }
    }

    fun find() = Find(connection)
    fun <E> find(l: Find.() -> E): E = l.invoke(find())
}
