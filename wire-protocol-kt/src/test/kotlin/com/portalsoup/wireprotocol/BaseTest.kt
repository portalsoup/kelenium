package com.portalsoup.wireprotocol

import com.portalsoup.wireprotocol.Executables.Geckodriver

open class BaseTest {

    val webdriverPath: String = Geckodriver.absolutePathOf()

    fun initializeWebdriver() = RemoteDriver(
        path = webdriverPath,
        capabilitiesJson =
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
    )
}