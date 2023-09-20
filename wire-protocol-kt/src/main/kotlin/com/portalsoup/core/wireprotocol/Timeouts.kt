package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver
import com.portalsoup.core.ktor.ktorClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable

@Serializable
data class TimeoutsWrapper(val value: Timeouts)
@Serializable
data class Timeouts(val script: Int = 30000, val pageLoad: Int = 300000, val implicit: Int = 0)

// https://www.w3.org/TR/webdriver2/#dfn-get-timeouts
fun WebDriver.getTimeouts(): Timeouts = runBlocking {
    ktorClient
        .get("$serverUrl/session/${session.id}/timeouts")
        .body<TimeoutsWrapper>()
        .value
}

// https://www.w3.org/TR/webdriver2/#dfn-set-timeouts
fun WebDriver.setTimeouts(timeouts: Timeouts) = runBlocking {
    ktorClient
        .post("$serverUrl/session/${session.id}/timeouts") {
        setBody(timeouts)
            contentType(ContentType.parse("application/json"))
    }
}
