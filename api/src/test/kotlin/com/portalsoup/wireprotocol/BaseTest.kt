package com.portalsoup.wireprotocol

import com.portalsoup.wireprotocol.Executables.Geckodriver
import com.portalsoup.wireprotocol.api.WireProtocol
import com.portalsoup.wireprotocol.api.createSession
import com.portalsoup.wireprotocol.api.deleteSession
import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import com.portalsoup.wireprotocol.dto.Session
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


open class BaseTest {
    val webdriverPath: String = Geckodriver.absolutePathOf()
    lateinit var driver: Process

    @BeforeEach
    fun startWebdriver() {
        driver = ProcessBuilder(webdriverPath).start().also {
            it.onExit().thenAccept { println("The remote server [$webdriverPath] has terminated!") }
        }
        Thread.sleep(500)
    }

    @AfterEach
    fun stopWebdriver() {
        runCatching {
            driver.destroy()
            driver.waitFor()
        }.onFailure { driver.destroyForcibly() }
    }
    val headlessCapabilities =
                """
                {
                    "capabilities": {
                        "alwaysMatch": {
                            "moz:firefoxOptions": {
                                "args": [
                                    "-headless"
                                ]
                            }
                        }
                    }
                }
                """.trimIndent()

    fun getApi() = WireProtocol(HttpRequestBuilder("http://127.0.0.1:4444"))
    fun useSession(api: WireProtocol, l: (Session) -> Unit) =
        api.createSession(headlessCapabilities)
            .value
            .apply(l)
            .let { api.deleteSession(it) }
}