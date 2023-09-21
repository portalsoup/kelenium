package com.portalsoup.core.wireprotocol

import kotlinx.serialization.Serializable

@Serializable
data class TimeoutsWrapper(val value: Timeouts)
@Serializable
data class Timeouts(val script: Int = 30000, val pageLoad: Int = 300000, val implicit: Int = 0)
//
//// https://www.w3.org/TR/webdriver2/#dfn-get-timeouts
//fun WebDriver.getTimeouts(): Timeouts = runBlocking {
//    ktorClient
//        .get("$serverUrl/session/${session.id}/timeouts")
//        .body<TimeoutsWrapper>()
//        .value
//}
//
//// https://www.w3.org/TR/webdriver2/#dfn-set-timeouts
//fun WebDriver.setTimeouts(timeouts: Timeouts) = runBlocking {
//    ktorClient
//        .post("$serverUrl/session/${session.id}/timeouts") {
//        setBody(timeouts)
//            contentType(ContentType.parse("application/json"))
//    }
//}
