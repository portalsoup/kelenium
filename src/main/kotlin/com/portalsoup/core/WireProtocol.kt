package com.portalsoup.core

import org.json.JSONObject
import java.util.*

val emptyJSON = JSONObject("{}")

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun WebDriver.createSession(): UUID = kurl {
    host = "$serverUrl/session"

    requestHeaders {
        mapOf(
            "Content-Type" to "application/json"
        )
    }
    body = JSONObject("{}")
}
    .doPost()
    .getJSONObject("value")
    .getString("sessionId")
    .let { UUID.fromString(it) }

// https://www.w3.org/TR/webdriver2/#dfn-delete-session
fun WebDriver.deleteSession(): JSONObject = kurl {
    host = "$serverUrl/session/$sessionId"
}.doDelete()

// https://www.w3.org/TR/webdriver2/#dfn-status
fun WebDriver.status(): JSONObject = kurl {
    host = "$serverUrl/status"
}.doGet()

// https://www.w3.org/TR/webdriver2/#dfn-get-timeouts
fun WebDriver.getTimeouts(): JSONObject = kurl {
    host = "$serverUrl/session/$sessionId/timeouts"
}.doGet()

// https://www.w3.org/TR/webdriver2/#dfn-set-timeouts
fun WebDriver.setTimeouts(timeouts: WebDriver.Timeouts): JSONObject = kurl {
    host = "$serverUrl/session/$sessionId/timeouts"
    body = JSONObject(timeouts.toString())
}.doPost()

// https://www.w3.org/TR/webdriver/#navigate-to
fun WebDriver.navigateTo(url: String) = kurl {
    host = "$serverUrl/session/$sessionId/url"
    body = JSONObject("""
        {
            "url": "$url"
        }
    """.trimIndent())
}.doPost()