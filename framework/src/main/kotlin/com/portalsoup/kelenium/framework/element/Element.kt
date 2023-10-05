package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.core.LocationStrategy

interface Element {
     val connection: RemoteDriverConnection

     fun findChild(locationStrategy: LocationStrategy): Element
     fun findChildren(locationStrategy: LocationStrategy): List<Element>
     fun text(): String
     fun property(name: String): String
     fun cssValue(css: String): String
     fun isSelected(): Boolean
     fun isEnabled(): Boolean
     fun tagName(): String
     fun rect()
     fun role(): String
     fun label(): String
     fun click()
     fun clear()
     fun sendKeys(text: String)
}