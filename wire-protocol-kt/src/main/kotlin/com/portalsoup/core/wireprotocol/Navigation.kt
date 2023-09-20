package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver
import com.portalsoup.core.ktor.ktorClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
data class CurrentUrlWrapper(val value: String)
@Serializable
data class Navigate(val url: String)

// https://www.w3.org/TR/webdriver/#navigate-to
fun WebDriver.navigateTo(url: String) = runBlocking {
    ktorClient.post("$serverUrl/session/${session.id}/url") {
        setBody(Navigate(url))
        contentType(ContentType("application", "json"))
    }
}

fun WebDriver.currentUrl(): String = runBlocking {
    ktorClient.get("$serverUrl/session/${session.id}/url")
        .also { println(it.bodyAsText()) }
        .body<CurrentUrlWrapper>()
        .value
}
fun WebDriver.back() = runBlocking {
    ktorClient.post("$serverUrl/session/${session.id}/back") {
        contentType(ContentType("application", "json"))
    }
}
fun WebDriver.forward() = Unit
fun WebDriver.refresh() = Unit
fun WebDriver.getTitle() = Unit