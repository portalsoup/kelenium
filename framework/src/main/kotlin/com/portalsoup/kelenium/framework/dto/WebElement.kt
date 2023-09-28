package com.portalsoup.kelenium.framework.dto

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.api.*
import com.portalsoup.wireprotocol.dto.Element

class WebElement(private val connection: RemoteDriverConnection, private val element: Element) {

    fun findChild(expression: String): WebElement {
        return connection.wireProtocol
            .findElement(connection.session, element.locationStrategy, expression)
            .value
            .let { WebElement(connection, it) }
    }

    fun findChildren(expression: String): List<WebElement> {
        return connection.wireProtocol
            .findElements(connection.session, element.locationStrategy, expression)
            .value
            .map { WebElement(connection, it) }
    }

    // State

    fun text(): String = connection.wireProtocol.getElementText(connection.session, element).value

    fun property(name: String): String = TODO()
    fun cssValue(css: String): String = TODO()
    fun isSelected(): Boolean = TODO()
    fun isEnabled(): Boolean = TODO()
    fun tagName(): String = TODO()
    fun rect(): Unit = TODO()
    fun role(): String = TODO()
    fun label(): String = TODO()

    // Actions

    fun click() {
        connection.wireProtocol.elementClick(connection.session, element)
    }

    fun clear(): Unit = TODO()

    fun sendKeys(text: String) {
        connection.wireProtocol.elementSendKeys(connection.session, element, text)
    }

}