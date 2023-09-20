package com.portalsoup.core

import com.portalsoup.core.ktor.ktorClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject//

// TODO this file represents unfinished commands in the wire protocol that have yet to be categorized formally into it's package

/*
 * Contexts *
 */

fun WebDriver.getWindowHandle() = Unit
fun WebDriver.closeWindow() = Unit
fun WebDriver.switchToWindow() = Unit
fun WebDriver.getWindowHandles() = Unit
fun WebDriver.newWindow() = Unit
fun WebDriver.switchToFrame() = Unit
fun WebDriver.switchToParentFrame() = Unit

// resizing sub-bundle
fun WebDriver.getWindowRect() = Unit
fun WebDriver.setWindowRect() = Unit // Window rect should be it's own property
fun WebDriver.maximizeWindow() = Unit
fun WebDriver.minimizeWindow() = Unit
fun WebDriver.fullScreenWindow() = Unit

/*
 * Elements
 */

// Locators
fun WebDriver.findElement() = Unit
fun WebDriver.findElements() = Unit
fun WebDriver.findElementFromElement() = Unit
fun WebDriver.findElementsFromElements() = Unit
fun WebDriver.findElementFromShadowRoot() = Unit
fun WebDriver.findElementsFromShadowRoot() = Unit
fun WebDriver.getActiveElement() = Unit
fun WebDriver.getElementShadowRoot() = Unit

// state

fun WebDriver.isElementSelected() = Unit
fun WebDriver.getElementAttribute() = Unit
fun WebDriver.getElementProperty() = Unit
fun WebDriver.getElementCssValue() = Unit
fun WebDriver.getElementText() = Unit
fun WebDriver.getElementTagName() = Unit
fun WebDriver.getElementRect() = Unit
fun WebDriver.isElementEnabled() = Unit
fun WebDriver.getComputedRole() = Unit
fun WebDriver.getComputdLabel() = Unit

// interaction

fun WebDriver.elementClick() = Unit
fun WebDriver.elementClear() = Unit
fun WebDriver.elementSendKeys() = Unit

/*
 * Document
 */

fun WebDriver.getPageSource() = Unit
fun WebDriver.executeScript() = Unit
fun WebDriver.executeAsyncScript() = Unit

/*
* Cookies
 */

fun WebDriver.getAllCookies() = Unit
fun WebDriver.getNamedCookie() = Unit
fun WebDriver.addCookie() = Unit
fun WebDriver.deleteCookie() = Unit
fun WebDriver.deleteAllCookies() = Unit

/*
 * Actions
 */

fun WebDriver.nullInputSource() = Unit
fun WebDriver.keyInputSource() = Unit
fun WebDriver.pointerInputSource() = Unit
fun WebDriver.wheelInputSource() = Unit

fun WebDriver.performActions() = Unit
fun WebDriver.releaseActions() = Unit

/*
 * User Prompts
 */

fun WebDriver.dismissAlert() = Unit
fun WebDriver.acceptAlert() = Unit
fun WebDriver.getAlertText() = Unit
fun WebDriver.sendAlertText() = Unit


/*
 * Screen capture
 */

fun WebDriver.takeScreenshot() = Unit
fun WebDriver.takeElementScreenshot() = Unit

/*
 * Print
 */

fun WebDriver.printPage() = Unit