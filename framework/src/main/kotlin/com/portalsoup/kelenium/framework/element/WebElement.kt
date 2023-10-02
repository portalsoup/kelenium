package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.api.*
import com.portalsoup.wireprotocol.dto.ElementRef

class WebElement(override val connection: RemoteDriverConnection, val elementRef: ElementRef): Element {

    fun <E> find(expression: String, l: (WebElement) -> E): E = l.invoke(findChild(expression))
    fun <E> findMany(expression: String, l: (List<WebElement>) -> E) = l.invoke(findChildren(expression))

    override fun findChild(expression: String): WebElement {
        return connection.wireProtocol
            .findElementFromElement(connection.session, elementRef, expression)
            .value
            .let { WebElement(connection, it) }
    }

    override fun findChildren(expression: String): List<WebElement> {
        return connection.wireProtocol
            .findElementsFromElement(connection.session, elementRef, expression)
            .value
            .map { WebElement(connection, it) }
    }

    override fun text(): String = connection.wireProtocol.getElementText(connection.session, elementRef).value

    override fun property(name: String): String = TODO()
    override fun cssValue(css: String): String = TODO()
    override fun isSelected(): Boolean = TODO()
    override fun isEnabled(): Boolean = TODO()
    override fun tagName(): String = TODO()
    override fun rect(): Unit = TODO()
    override fun role(): String = TODO()
    override fun label(): String = TODO()

    // Actions

    override fun click() {
        connection.wireProtocol.elementClick(connection.session, elementRef)
    }

    override fun clear(): Unit = TODO()

    override fun sendKeys(text: String) {
        connection.wireProtocol.elementSendKeys(connection.session, elementRef, text)
    }

}