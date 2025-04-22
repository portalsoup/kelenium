package com.portalsoup.kelenium.framework.driver

import com.portalsoup.kelenium.framework.PropertyObserver
import com.portalsoup.kelenium.framework.driver.config.WebDriverConfigurator
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.error.exceptions.SessionNotCreatedException
import com.portalsoup.wireprotocol.session.api.createSession
import com.portalsoup.wireprotocol.session.api.deleteSession
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import com.portalsoup.wireprotocol.timeout.api.setTimeouts
import com.portalsoup.wireprotocol.timeout.dto.Timeouts
import java.io.Closeable

abstract class AbstractWebDriver(driverPath: String, host: String, port: Int, capabilities: String? = null): Closeable {
    private val configDelegate = PropertyObserver(WebDriverConfigurator()).apply {
        addObserver(::updateTimeoutsIfChanged)
    }

    val wireProtocol: WireProtocol = WireProtocol(host, port)
    var configuration: WebDriverConfigurator by configDelegate
    internal lateinit var session: SessionCreated
    private lateinit var server: Process

    init {
        startServer(driverPath)
        startSession(capabilities)
    }

    private fun startServer(driverPath: String) {
        server = ProcessBuilder(driverPath).start().also {
            it.onExit().thenAccept { println("The remote server [$driverPath] has terminated.") }
        }

        while (!server.isAlive) {
            Thread.sleep(100)
        }
    }

    private fun stopServer() {
        runCatching {
            server.destroy()
            server.waitFor()
        }
            .onFailure { server.destroyForcibly() }
            .onFailure { println("Encountered an error while stopping the remote driver: $it") }
    }

    private fun startSession(capabilities: String?) {
        when (val rawSession = wireProtocol.createSession(capabilities).value) {
            is SessionCreated -> session = rawSession
            else -> throw SessionNotCreatedException()
        }
    }

    private fun updateTimeoutsIfChanged(old: WebDriverConfigurator, new: WebDriverConfigurator) {
        if (old.timeoutConfigurator != new.timeoutConfigurator) {
            wireProtocol.setTimeouts(session, Timeouts(
                script = new.timeoutConfigurator.script,
                pageLoad = new.timeoutConfigurator.pageLoad,
                implicit = new.timeoutConfigurator.implicit
            )
            )
        }
    }

    fun configure(f: WebDriverConfigurator.() -> Unit) {
        configuration = configuration.apply(f)
    }

    override fun close() {
        wireProtocol.deleteSession(session)
        configDelegate.close()
        if (server.isAlive) {
            stopServer()
        }
    }
}