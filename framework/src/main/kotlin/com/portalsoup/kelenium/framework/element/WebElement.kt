package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.element.api.*
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.element.dto.SendKeys

class WebElement(override val connection: RemoteDriverConnection, val elementRef: ElementRef): Element {

    fun <E> findChild(locationStrategy: LocationStrategy, l: (WebElement) -> E): E =
        l.invoke(findChild(locationStrategy))
    fun <E> findMany(locationStrategy: LocationStrategy, l: (List<WebElement>) -> E) =
        l.invoke(findChildren(locationStrategy))

    override fun findChild(locationStrategy: LocationStrategy): WebElement {
        return connection.wireProtocol
            .findElementFromElement(connection.session, locationStrategy, elementRef)
            .value
            .let { when (it) {
                is ElementRef -> it
                else -> throw RuntimeException("Invalid element ref in response")
            } }
            .let { WebElement(connection, it) }
    }

    override fun findChildren(locationStrategy: LocationStrategy): List<WebElement> {
        return connection.wireProtocol
            .findElementsFromElement(connection.session, locationStrategy, elementRef)
            .value
            .let { when (it) {
                is List<*> -> it
                else -> throw RuntimeException("Invalid element ref list in response")
            } }
            .map { WebElement(connection, it as ElementRef) }
    }

    override fun text(): String = connection.wireProtocol.getElementText(connection.session, elementRef).value.let {
        when (it) {
            is String -> it
            else -> throw RuntimeException("Invalid text response is not a string")
        }
    }

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
        connection.wireProtocol.elementSendKeys(connection.session, elementRef, SendKeys(text))
    }

}