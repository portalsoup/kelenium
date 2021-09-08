package com.portalsoup.core

import org.json.JSONObject
import java.lang.StringBuilder
import java.net.URL
import java.util.*

/**
 * To reduce clutter, all requests at the wire protocol level are in WireProtocol.kt
 */
class WebDriver(host: String = "http://localhost", port: Int = 4444) : AutoCloseable {

    private val process: Process = Runtime.getRuntime().exec(System.getProperty("webdriver.gecko.driver"))

    val serverUrl = "$host:$port"

    val sessionId: UUID = createSession()

    override fun close() {
        deleteSession()
        process.destroy()
        process.waitFor()
    }


    class Timeouts(val script: Int = 30000, val pageLoad: Int = 300000, val implicit: Int = 0) {
        override fun toString(): String {
            return """ // TODO serialize from data class
                {
                    "script": $script,
                    "pageLoad": $pageLoad,
                    "implicit": $implicit
                }
            """.trimIndent()
        }
    }

}
