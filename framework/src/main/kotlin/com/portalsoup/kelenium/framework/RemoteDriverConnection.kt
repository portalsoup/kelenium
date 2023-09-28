package com.portalsoup.kelenium.framework

import com.portalsoup.wireprotocol.api.WireProtocol
import com.portalsoup.wireprotocol.api.createSession
import com.portalsoup.wireprotocol.api.deleteSession
import com.portalsoup.wireprotocol.api.navigateTo
import com.portalsoup.wireprotocol.core.HttpRequestBuilder
import com.portalsoup.wireprotocol.dto.Session
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.Closeable
import java.io.File

class RemoteDriverConnection<D: RemoteWebDriver<D>>(
    val driver: D,
    val requestBuilder: HttpRequestBuilder,
    val capabilities: String? = null
): Closeable {
    private val wireProtocol = WireProtocol(requestBuilder)
    private val session: Session by lazy { wireProtocol.createSession(capabilities).value }



    override fun close() {
        wireProtocol.deleteSession(session)
    }

    fun navigateTo(url: String) {
        wireProtocol.navigateTo(session, url)
    }
}

fun findDriver(path: String): RemoteGeckoDriver {
    File(path)
        .apply {
            takeIf { it.exists() } ?: throw RuntimeException("Found no file at the specified path!")
            takeIf { it.canExecute() } ?: throw RuntimeException("Could not execute the driver executable!")
        }
    return RemoteGeckoDriver(path)
}

 suspend fun main() {
    val path = System.getProperty("user.home") + "/.webdriver/geckodriver"
    val driver = findDriver(path).use { driver ->
        val context = driver.newConnectionContext()
        val context2 = driver.newConnectionContext()

        println(context)
        println(context2)
        // TODO make this test async for POC

        val connection1 = GlobalScope.async {
            driver.connect(context).use {
                it.navigateTo("https://duckduckgo.com")
                Thread.sleep(5000)
            }}
        val connection2 = GlobalScope.async {
            driver.connect(context2).use {
                it.navigateTo("https://duckduckgo.com")
                Thread.sleep(5000)
            }}


        connection1.await()
        connection2.await()
    }
}