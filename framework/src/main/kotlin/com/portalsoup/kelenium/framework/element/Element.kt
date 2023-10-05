package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import com.portalsoup.wireprotocol.core.LocationStrategy

interface Element {
     val connection: RemoteDriverConnection

     fun findChild(locationStrategy: LocationStrategy, expression: String): Element
     fun findChildren(locationStrategy: LocationStrategy, expression: String): List<Element>
     fun text(): String
     fun property(name: String): String
     fun cssValue(css: String): String
     fun isSelected(): Boolean
     fun isEnabled(): Boolean
     fun tagName(): String
     fun rect(): Unit
     fun role(): String
     fun label(): String
     fun click(): Unit
     fun clear(): Unit
     fun sendKeys(text: String): Unit
}