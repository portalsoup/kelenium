package com.portalsoup.core.wireprotocol

import com.portalsoup.core.socket.RemoteDriver
import com.portalsoup.core.socket.SuccessResponse
import kotlinx.serialization.Serializable

class Elements {
    fun main() {
        System.setProperty("webdriver.gecko.driver", "/home/portalsoup/IdeaProjects/kelenium/geckodriver")
        RemoteDriver().use { driver ->
            driver.navigateTo("https://duckduckgo.com")
            val elemMap = driver.findElement("css selector", "a:nth-child(2)")

            println(elemMap)

            val foundElements = elemMap.value.map {
                Element(it.key, it.value)
            }

            println("found elements: ${foundElements.size}")
            driver.elementClick(foundElements.first().reference)
            println(foundElements)
            Thread.sleep(5000)
        }
    }
}

// simple idea for an element
data class Element(val locationStrategy: String, val reference: String)

@Serializable
data class LocationStrategy(val using: String, val value: String)

// Locators
fun RemoteDriver.findElement(using: String, value: String): SuccessResponse<Map<String, String>> = post(
            endpoint = "/session/${session.id}/element",
            body = LocationStrategy(using, value)
        )
fun RemoteDriver.findElements() = Unit
fun RemoteDriver.findElementFromElement() = Unit
fun RemoteDriver.findElementsFromElements() = Unit
fun RemoteDriver.findElementFromShadowRoot() = Unit
fun RemoteDriver.findElementsFromShadowRoot() = Unit
fun RemoteDriver.getActiveElement() = Unit
fun RemoteDriver.getElementShadowRoot() = Unit

// state

fun RemoteDriver.isElementSelected() = Unit
fun RemoteDriver.getElementAttribute() = Unit
fun RemoteDriver.getElementProperty() = Unit
fun RemoteDriver.getElementCssValue() = Unit
fun RemoteDriver.getElementText() = Unit
fun RemoteDriver.getElementTagName() = Unit
fun RemoteDriver.getElementRect() = Unit
fun RemoteDriver.isElementEnabled() = Unit
fun RemoteDriver.getComputedRole() = Unit
fun RemoteDriver.getComputdLabel() = Unit

// interaction

fun RemoteDriver.elementClick(elementId: String): SuccessResponse<Unit?> = post("/session/${session.id}/element/$elementId/click", "{}")
fun RemoteDriver.elementClear() = Unit
fun RemoteDriver.elementSendKeys() = Unit