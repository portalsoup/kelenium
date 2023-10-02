package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.core.LocationStrategy

class Selector(override val connection: RemoteDriverConnection, val locationStrategy: LocationStrategy, val expression: String): Element {

    fun getElement(): WebElement = connection.document().find().element(locationStrategy, expression)
    fun getElements(): List<WebElement> = connection.document().find().elements(locationStrategy, expression)
    
    fun <E> findChild(expression: String, l: WebElement.() -> E): E = l.invoke(findChild(expression))
    override fun findChild(expression: String): WebElement = getElement().findChild(expression)

    fun <E> findChildren(expression: String, l: List<WebElement>.() -> E): E = l.invoke(findChildren(expression))
    override fun findChildren(expression: String): List<WebElement> = getElement().findChildren(expression)

    fun filterChildren(expression: String, predicate: (WebElement) -> Boolean) = findChildren(expression).filter(predicate)
    fun <E> map(expression: String, map: (WebElement) -> E) = findChildren(expression).map(map)

    override fun text(): String = getElement().text()

    override fun property(name: String): String = getElement().property(name)

    override fun cssValue(css: String): String = getElement().cssValue(css)

    override fun isSelected(): Boolean = getElement().isSelected()

    override fun isEnabled(): Boolean = getElement().isEnabled()

    override fun tagName(): String = getElement().tagName()

    override fun rect() = getElement().rect()

    override fun role(): String = getElement().role()

    override fun label(): String = getElement().label()

    override fun click() = getElement().click()

    override fun clear() = getElement().clear()

    override fun sendKeys(text: String) = getElement().sendKeys(text)
}