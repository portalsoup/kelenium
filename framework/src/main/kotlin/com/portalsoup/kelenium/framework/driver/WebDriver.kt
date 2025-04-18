package com.portalsoup.kelenium.framework.driver

import com.portalsoup.kelenium.framework.element.WebElement
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.element.api.findElement
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.error.exceptions.ElementNotFoundException
import com.portalsoup.wireprotocol.error.exceptions.SessionNotCreatedException
import com.portalsoup.wireprotocol.navigation.api.*
import com.portalsoup.wireprotocol.session.api.createSession
import com.portalsoup.wireprotocol.session.api.deleteSession
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import com.portalsoup.wireprotocol.timeout.api.setTimeouts
import com.portalsoup.wireprotocol.timeout.dto.Timeouts
import java.io.Closeable
import kotlin.reflect.KProperty

data class TimeoutConfigurator(
    var implicit: Int = 0,
    var pageLoad: Int = 5000,
    var script: Int = 5000
)

data class ActiveWait(
    var pollingIntervalMs: Int = 500,
    var timeoutMs: Int = 30 * 1000,
)

data class WebDriverConfigurator(internal var timeoutConfigurator: TimeoutConfigurator = TimeoutConfigurator(), internal var activeWait: ActiveWait = ActiveWait()) {
    fun deepCopy(): WebDriverConfigurator = copy(
        timeoutConfigurator = TimeoutConfigurator().copy()
    )
    fun timeouts(f: TimeoutConfigurator.() -> Unit) {
        timeoutConfigurator.apply(f)
    }
}

class ConfigReactor(private val driver: WebDriver) {
    private var propValue = WebDriverConfigurator()
    operator fun getValue(thisRef: Any?, property: KProperty<*>): WebDriverConfigurator {
        return propValue
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: WebDriverConfigurator) {
        val oldValue = propValue
        propValue = value
        onChange(value, oldValue)
    }

    private fun onChange(old: WebDriverConfigurator, new: WebDriverConfigurator) {
        if (old.timeoutConfigurator != new.timeoutConfigurator) {
            driver.wireProtocol.setTimeouts(driver.session, Timeouts(
                script = new.timeoutConfigurator.script,
                pageLoad = new.timeoutConfigurator.pageLoad,
                implicit = new.timeoutConfigurator.implicit
            ))
        }
    }
}

class Navigator(val driver: WebDriver) {
    fun to(url: String) {
        driver.wireProtocol.navigateTo(driver.session, url)
    }

    fun current() {
        driver.wireProtocol.currentUrl(driver.session)
    }

    fun back() {
        driver.wireProtocol.back(driver.session)
    }

    fun forward() {
        driver.wireProtocol.forward(driver.session)
    }

    fun refresh() {
        driver.wireProtocol.refresh(driver.session)
    }

    fun title() {
        driver.wireProtocol.getTitle(driver.session)
    }
}

class WebDriver: Closeable {

    val wireProtocol: WireProtocol

    internal var configuration by ConfigReactor(this)

    internal lateinit var session: SessionCreated
    private lateinit var server: Process

    constructor(driver: DriverServer, host: String, port: Int, capabililities: String? = null) {
        wireProtocol = WireProtocol(host, port)
        startServer(driver.absolutePath())
        startSession(capabililities)
    }

    fun configure(f: WebDriverConfigurator.() -> Unit) {
        val newConfig = configuration.deepCopy().apply(f)
        configuration = newConfig
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

    fun navigate(f: Navigator.() -> Unit) {
        Navigator(this).apply(f)
    }

    fun navigate(): Navigator = Navigator(this)

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