package com.portalsoup.wireprotocol

import com.portalsoup.wireprotocol.api.*
import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import com.portalsoup.wireprotocol.dto.Session
import kotlinx.serialization.Serializable

class RemoteDriver(
    path: String = System.getProperty("webdriver.path"),
    host: String = "127.0.0.1",
    port: Int = 4444,
    capabilities: String? = null
): AutoCloseable {

    private val process: Process = ProcessBuilder(path).start().also {
        it.onExit().thenAccept { println("The remote server [${requestBuilder.baseUrl}] has terminated!") }
    }

    val session: Session by lazy { capabilities.takeUnless { it == null }?.let { createSession(it).value } ?: createSession().value }

    val requestBuilder = HttpRequestBuilder("http://$host:$port")

    override fun close() {
        deleteSession()

        process.destroy()
        runCatching { process.waitFor() }
            .onFailure { process.destroyForcibly() }
    }

    companion object {
        val APPJSON = "application/json"
    }
}

class RemoteDriverClosedException(cause: Throwable): RuntimeException("This webdriver instance has already been closed!", cause)

@Serializable
data class SuccessResponse<T>(val value: T)
