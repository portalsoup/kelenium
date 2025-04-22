package com.portalsoup.kelenium.framework.driver

import com.portalsoup.kelenium.framework.element.WebElement
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.element.api.findElement
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.error.exceptions.ElementNotFoundException
import com.portalsoup.wireprotocol.navigation.api.back
import com.portalsoup.wireprotocol.navigation.api.forward
import com.portalsoup.wireprotocol.navigation.api.navigateTo
import com.portalsoup.wireprotocol.navigation.api.refresh
import com.portalsoup.wireprotocol.session.dto.SessionCreated

class Navigation(private val wireProtocol: WireProtocol, private val session: SessionCreated) {
    fun to(url: String) {
        wireProtocol.navigateTo(session, url)
    }

    fun refresh() {
        wireProtocol.refresh(session)
    }

    fun back() {
        wireProtocol.back(session)
    }

    fun forward() {
        wireProtocol.forward(session)
    }
}

open class WebDriver(driver: DriverServer, host: String, port: Int, capabililities: String? = null) : AbstractWebDriver(driver.absolutePath(), host, port, capabililities) {

    val navigate: Navigation = Navigation(wireProtocol, session)

    fun findElement(locationStrategy: LocationStrategy): WebElement {
        when (val element = wireProtocol.findElement(session, locationStrategy.id.code, locationStrategy.expression).value) {
            is ElementRef -> return WebElement(this, element)
            else -> throw ElementNotFoundException(locationStrategy.expression)
        }
    }
}