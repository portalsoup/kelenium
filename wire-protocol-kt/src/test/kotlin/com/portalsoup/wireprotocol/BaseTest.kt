package com.portalsoup.wireprotocol

open class BaseTest {

    val webdriverPath = "../geckodriver"

    val example1Url = "https://duckduckgo.com/"
    val example2Url = "https://www.google.com/"

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