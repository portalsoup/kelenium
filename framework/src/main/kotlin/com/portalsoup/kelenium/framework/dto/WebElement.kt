package com.portalsoup.kelenium.framework.dto

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.api.*
import com.portalsoup.wireprotocol.dto.Element

class WebElement(private val connection: RemoteDriverConnection, private val element: Element) {

    fun <E> find(expression: String, l: (WebElement) -> E): E = l.invoke(find(expression))
    fun find(expression: String): WebElement {
        return connection.wireProtocol
            .findElementFromElement(connection.session, element, expression)
            .value
            .let { WebElement(connection, it) }
    }

    fun <E> findMany(expression: String, l: (List<WebElement>) -> E) = l.invoke(findMany(expression))
    fun findMany(expression: String): List<WebElement> {
        return connection.wireProtocol
            .findElementsFromElement(connection.session, element, expression)
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