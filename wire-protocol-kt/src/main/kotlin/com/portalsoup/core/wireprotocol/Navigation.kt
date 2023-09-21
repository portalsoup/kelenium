package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUrlWrapper(val value: String)
@Serializable
data class Navigate(val url: String)


// https://www.w3.org/TR/webdriver/#navigate-to
fun WebDriver.navigateTo(url: String) = Unit

fun WebDriver.currentUrl() = Unit
fun WebDriver.back() = Unit
fun WebDriver.forward() = Unit
fun WebDriver.refresh() = Unit
fun WebDriver.getTitle() = Unit