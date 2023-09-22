package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver


// TODO this file represents unfinished commands in the wire protocol that have yet to be categorized formally into it's package


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