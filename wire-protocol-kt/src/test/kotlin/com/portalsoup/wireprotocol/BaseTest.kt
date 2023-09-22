package com.portalsoup.wireprotocol

import kotlin.math.abs

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

    fun absolutePathOfResource(path: String): String = javaClass.classLoader.getResource(path).path
    fun resourceAsUrl(path: String): String = absolutePathOfResource(path).let { "file://$it" }
}