package com.portalsoup.core

import com.portalsoup.core.wireprotocol.Session
import com.portalsoup.core.wireprotocol.createSession
import com.portalsoup.core.wireprotocol.deleteSession

/**
 * To reduce clutter, all requests at the wire protocol level are in WireProtocol.kt
 */
class WebDriver(host: String = "http://localhost", port: Int = 4444) : AutoCloseable {

    private val webdriverPath = System.getProperty("webdriver.gecko.driver")
    private val process: Process = ProcessBuilder(webdriverPath).start()

    private val p2: Process = Runtime.getRuntime().exec(System.getProperty("webdriver.gecko.driver"))

    val serverUrl = "$host:$port"

    val session: Session = createSession()

    override fun close() {
        if (!deleteSession()) {
            println("Session failed to close!")
        }
        process.destroy()
        process.waitFor()
    }
}
