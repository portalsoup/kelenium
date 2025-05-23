package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.driver.WebDriver
import com.portalsoup.wireprotocol.element.api.elementClick
import com.portalsoup.wireprotocol.element.api.elementSendKeys
import com.portalsoup.wireprotocol.element.api.getElementText
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.element.dto.SendKeys
import kotlin.time.TimeSource

class WebElement {
    private val webDriver: WebDriver
    private val ref: ElementRef

    constructor(webDriver: WebDriver, ref: ElementRef) {
        this.webDriver = webDriver
        this.ref = ref
    }

    fun sendKeys(keys: String) {
        webDriver.wireProtocol.elementSendKeys(webDriver.session, ref, SendKeys(keys))
    }

    fun getText(): String? {
        return webDriver.wireProtocol.getElementText(webDriver.session, ref).value
            .takeIf { it is String }
            ?.let { it as String }
            ?.also { println("Got text: $it") }
    }

    fun click() {
        webDriver.wireProtocol.elementClick(webDriver.session, ref)
    }

    fun <R> wait(predicate: WebElement.() -> R) {
        val waitConfig = webDriver.configuration.activeWait
        val startTime = TimeSource.Monotonic.markNow()

        while (startTime.elapsedNow().inWholeMilliseconds < waitConfig.timeoutMs) {
            when (val result = predicate()) {
                is Boolean -> if (result == true) return
                else -> if (result != null) return
            }
        }
        throw RuntimeException("wait timed out after ${TimeSource.Monotonic.markNow()}")
    }
}