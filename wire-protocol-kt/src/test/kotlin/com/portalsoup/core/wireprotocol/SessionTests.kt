package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver
import org.junit.jupiter.api.Test

class SessionTests {

    val webdriverPath = "../geckodriver"

    fun initializeWebdriver(): WebDriver = WebDriver(webdriverPath, capabilitiesJson =
            """
                {
                    "capabilities": {
                        "alwaysMatch": {
                            "moz:firefoxOptions": [
                                "--headless"
                            ]
                        }
                    }
                }
            """.trimIndent())
    @Test
    fun invokeWebdriverTest() {
        initializeWebdriver().use {
            assert(it.session.id.isNotEmpty())
        }
    }
}