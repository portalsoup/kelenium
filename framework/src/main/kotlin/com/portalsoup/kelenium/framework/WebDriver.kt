package com.portalsoup.kelenium.framework

import com.portalsoup.kelenium.wireprotocol.core.Reactive
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.element.api.findElement
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.error.exceptions.ElementNotFoundException
import com.portalsoup.wireprotocol.error.exceptions.SessionNotCreatedException
import com.portalsoup.wireprotocol.navigation.api.navigateTo
import com.portalsoup.wireprotocol.session.api.createSession
import com.portalsoup.wireprotocol.session.api.deleteSession
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import java.io.Closeable

data class TimeoutConfigurator(
    var implicit: Long = 0,
    var pageLoad: Long = 5000,
    var script: Long = 5000
)

data class WebDriverConfigurator(private var timeoutConfigurator: TimeoutConfigurator = TimeoutConfigurator()) {
    fun timeouts(f: TimeoutConfigurator.() -> Unit) {
        timeoutConfigurator.apply(f)
    }
}

class WebDriver: Closeable {

    val wireProtocol: WireProtocol

    internal var configuration: WebDriverConfigurator by Reactive(WebDriverConfigurator(), ::onConfigurationChange)

    internal lateinit var session: SessionCreated
    private lateinit var server: Process

    constructor(driver: DriverServer, host: String, port: Int, capabililities: String? = null) {
        wireProtocol = WireProtocol(host, port)
        startServer(driver.absolutePath())
        startSession(capabililities)
    }

    fun configure(f: WebDriverConfigurator.() -> Unit) {
        configuration = configuration.copy().apply(f)
    }

    private fun onConfigurationChange(old: WebDriverConfigurator, new: WebDriverConfigurator) {
        println("Configuration changed: $old -> $new")
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

    fun startSession(capabilities: String?) {
        val rawSession = wireProtocol.createSession(capabilities).value
        when (rawSession) {
            is SessionCreated -> session = rawSession
            else -> throw SessionNotCreatedException()
        }
    }

    fun navigateTo(url: String) {
        wireProtocol.navigateTo(session, url)
    }

    fun findElement(locationStrategy: LocationStrategy): WebElement {
        when (val element = wireProtocol.findElement(session, locationStrategy.id.code, locationStrategy.expression).value) {
            is ElementRef -> return WebElement(this, element)
            else -> throw ElementNotFoundException(locationStrategy.expression)
        }
    }

    override fun close() {
        wireProtocol.deleteSession(session)

        if (server.isAlive) {
            stopServer()
        }
    }
}