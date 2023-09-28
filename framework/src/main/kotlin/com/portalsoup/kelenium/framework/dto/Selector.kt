package com.portalsoup.kelenium.framework.dto

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.core.LocationStrategy

class Selector(private val connection: RemoteDriverConnection, val locationStrategy: LocationStrategy, val expression: String) {

    fun <E> find(l: WebElement.() -> E): E = l.invoke(find())
    fun find(): WebElement = connection.document().find().element(locationStrategy, expression)

    fun <E> findMany(l: List<WebElement>.() -> E): E = l.invoke(findMany())
    fun findMany(): List<WebElement> = connection.document().find().elements(locationStrategy, expression)

    fun filter(predicate: (WebElement) -> Boolean) = findMany().filter(predicate)
    fun <E> map(map: (WebElement) -> E) = findMany().map(map)
}
