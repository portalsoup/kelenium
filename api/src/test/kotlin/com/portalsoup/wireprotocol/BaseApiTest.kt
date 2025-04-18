package com.portalsoup.wireprotocol

import com.portalsoup.wireprotocol.DriverServer.Geckodriver
import com.portalsoup.wireprotocol.core.WireProtocol
import com.portalsoup.wireprotocol.session.api.createSession
import com.portalsoup.wireprotocol.session.api.deleteSession
import com.portalsoup.wireprotocol.session.dto.SessionCreated
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.lang.AssertionError


open class BaseApiTest: BaseTest() {
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

    fun getApi() = WireProtocol("127.0.0.1", 4444)

    fun useSession(api: WireProtocol, l: (SessionCreated) -> Unit) {
        when (val response = api.createSession().value) {
            is SessionCreated -> response.apply(l).let { api.deleteSession(it) }
            else -> throw AssertionError("Failed to create sesssion: $response")
        }
    }
}