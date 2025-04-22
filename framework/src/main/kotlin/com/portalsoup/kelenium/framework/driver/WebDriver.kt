package com.portalsoup.kelenium.framework.driver

import com.portalsoup.kelenium.framework.DriverServer
import com.portalsoup.kelenium.framework.LocationStrategy
import com.portalsoup.kelenium.framework.WebElement
import com.portalsoup.wireprotocol.element.api.findElement
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.error.exceptions.ElementNotFoundException
import com.portalsoup.wireprotocol.navigation.api.navigateTo

data class ActiveWait(
    var pollingIntervalMs: Int = 500,
    var timeoutMs: Int = 30 * 1000,
)

open class WebDriver(driver: DriverServer, host: String, port: Int, capabililities: String? = null) : AbstractWebDriver(driver.absolutePath(), host, port, capabililities) {

    fun navigateTo(url: String) {
        wireProtocol.navigateTo(session, url)
    }

    fun findElement(locationStrategy: LocationStrategy): WebElement {
        when (val element = wireProtocol.findElement(session, locationStrategy.id.code, locationStrategy.expression).value) {
            is ElementRef -> return WebElement(this, element)
            else -> throw ElementNotFoundException(locationStrategy.expression)
        }
    }
}