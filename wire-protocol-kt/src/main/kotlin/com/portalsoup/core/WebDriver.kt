package com.portalsoup.core

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.net.Socket

/**
 * To reduce clutter, all requests at the wire protocol level are in WireProtocol.kt
 */
class WebDriver(
    path: String = System.getProperty("webdriver.gecko.driver"),
    host: String = "localhost",
    port: Int = 4444,
    capabilitiesJson: String = "{}"
) : AutoCloseable {

    private val process: Process = ProcessBuilder(path).start()

    val serverUrl = "http://$host:$port"

//    val session: Session by lazy {
////        waitForServer()
////        createSession()
//    }

    private val socket by lazy { Socket(host, port) }

    fun waitForServer() {
        socket.soTimeout = 2000
        runBlocking {
            launch {
                runCatching { socket.connect(null) }
                    .getOrNull()
            }
        }
    }
    override fun close() {
//        if (!deleteSession()) {
//            println("Session failed to close!")
//        }
        socket.close()
        process.destroy()
        process.waitFor()
    }
}
