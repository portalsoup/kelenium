package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.core.LocationStrategy

// OG Selector?
class WebElementFactory(override val connection: RemoteDriverConnection, val locationStrategy: LocationStrategy, val expression: String): Element {

    fun element(): WebElement =
        connection.document().find().element(locationStrategy)
    fun elements(): List<WebElement> =
        connection.document().find().elements(locationStrategy)

    override fun findChild(locationStrategy: LocationStrategy): Element =
        element().findChild(locationStrategy)

    override fun findChildren(locationStrategy: LocationStrategy): List<Element> =
        element().findChildren(locationStrategy)

    override fun text(): String = element().text()

    override fun property(name: String): String = element().property(name)

    override fun cssValue(css: String): String = element().cssValue(css)

    override fun isSelected(): Boolean = element().isSelected()

    override fun isEnabled(): Boolean = element().isEnabled()

    override fun tagName(): String = element().tagName()

    override fun rect() = element().rect()

    override fun role(): String = element().role()

    override fun label(): String = element().label()

    override fun click() = element().click()

    override fun clear() = element().clear()

    override fun sendKeys(text: String) = element().sendKeys(text)
}