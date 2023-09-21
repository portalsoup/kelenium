package com.portalsoup.core.socket

import com.portalsoup.core.wireprotocol.Session
import com.portalsoup.core.wireprotocol.SessionWrapper
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class RemoteDriver(
    private val path: String = System.getProperty("webdriver.gecko.driver"),
    private val host: String = "127.0.0.1",
    private val port: Int = 4444,
    private val capabilitiesJson: String = "{}"
) {

//    private val process: Process = ProcessBuilder(path).start()

    val serverUrl = "http://$host:$port"

    fun createSession(endpoint: String, body: String): Session {
        val joinedUrl = endpoint.takeIf { it.startsWith("/") }?.let { "$serverUrl$endpoint" } ?: "$serverUrl/$endpoint"
        val url = URL(joinedUrl)
        val conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")
        conn.doOutput = true

        conn.outputStream.use { os ->
            val input: ByteArray = body.toByteArray()
            os.write(input, 0, input.size)
        }

        return BufferedReader(
            InputStreamReader(conn.inputStream, "utf-8")
        ).use { br ->
            val response = StringBuilder()
            var responseLine: String?
            while (br.readLine().also { responseLine = it } != null) {
                response.append(responseLine!!.trim { it <= ' ' })
            }
            Json.decodeFromString<SessionWrapper>(response.toString()).value
        }
    }
}

fun main() {
    System.setProperty("webdriver.gecko.driver", "${System.getProperty("user.home")}/.webdriver/geckodriver")
    val session: Session = RemoteDriver().createSession("/session", "{}")
    println(session)
}