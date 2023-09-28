package com.portalsoup.kelenium.framework

import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import java.io.Closeable
import java.net.Socket
import java.util.concurrent.atomic.AtomicInteger

abstract class ConnectionContext(open val host: String, open val port: Int, open val capabilities: String?)

abstract class RemoteWebDriver<D: RemoteWebDriver<D>>(val path: String): AutoCloseable {

    // Each time a new connection is opened, the new driver Process is via an
    // anonymous Closeable implementation.  This theoretically lets developers
    // re-use this class to create multiple connections over time
    private val resourcesToClose = mutableListOf<Closeable>()

    var host: String = "127.0.0.1"
    private var staticPort: Int = Int.MIN_VALUE
    private var knownUsedPorts = mutableListOf<Int>()

    internal var capabilities: String? = null

    fun withCapabilities(capabilities: String) {
        this.capabilities = capabilities
    }

    abstract val launchArgs: ConnectionContext.() -> List<String>

    fun staticPort(port: Int) {
        this.staticPort = port
    }

    abstract fun newConnectionContext(): ConnectionContext

    internal fun port(): Int {
        if (staticPort == Int.MIN_VALUE) {
            val nextPortToTry = AtomicInteger(4444)
            var stop = false
            do {
                val p = nextPortToTry.getAndIncrement()
                if (knownUsedPorts.contains(p)) {
                    continue
                } else {
                    knownUsedPorts.add(p)
                    runCatching { Socket(host, p) }
                        .onFailure { return p } // If it fails to connect, then that likely means that the port is available
                    if (p >= 9999) {
                        stop = true
                    }
                }
            } while (!stop)
            throw RuntimeException("Couldn't settle on an open port!")
        } else {
            return staticPort
        }
    }

    open fun connect(context: ConnectionContext): RemoteDriverConnection {
        buildProcess(context)

        return RemoteDriverConnection(HttpRequestBuilder("http://${context.host}:${context.port}"), context.capabilities)

    }

    private fun buildProcess(context: ConnectionContext) {
        ProcessBuilder(context.launchArgs()).start().also { process ->
            // Configure listeners and hooks
            addCloseableResource {
                runCatching {
                    process.destroy()
                    process.waitFor()
                }.onFailure { process.destroyForcibly() }
            }

            process.onExit().thenAccept { println("The remote server [${context.host}:${context.port}] has terminated!") }
        }
    }

    private fun addCloseableResource(resource: Closeable) {
        resourcesToClose.add(resource)
    }

    override fun close() {
        resourcesToClose.onEach { it.close() }
    }
}