package com.portalsoup.wireprotocol

import com.portalsoup.wireprotocol.api.*
import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import com.portalsoup.wireprotocol.dto.Session

class RemoteDriver(
    path: String = System.getProperty("webdriver.path"),
    host: String = "127.0.0.1",
    port: Int = 4444,
    capabilitiesJson: String? = null
): AutoCloseable {

    private val process: Process = ProcessBuilder(path).start().also {
        it.onExit().thenAccept { println("The remote server [${requestBuilder.baseUrl}] has terminated!") }
    }

    val session: Session by lazy { createSession(capabilitiesJson).value }

    val requestBuilder = HttpRequestBuilder("http://$host:$port")

    override fun close() {
        deleteSession()
        runCatching {
            process.destroy()
            process.waitFor()
        }.onFailure { process.destroyForcibly() }
    }
}

