package com.portalsoup.core.socket

import com.portalsoup.core.util.ILogging
import com.portalsoup.core.wireprotocol.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class RemoteDriver(
    path: String = System.getProperty("webdriver.path"),
    host: String = "127.0.0.1",
    port: Int = 4444,
    capabilities: String? = null
): AutoCloseable, ILogging {

    private val process: Process = ProcessBuilder(path).start()

    val session: Session by lazy { capabilities.takeUnless { it == null }?.let { createSession(it).value } ?: createSession().value }

    val serverUrl = "http://$host:$port"

    fun buildRequest(method: String, endpoint: String): HttpURLConnection {
        val url = URL(mergeUrl(serverUrl, endpoint))
        val conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = method
        conn.setRequestProperty("Content-Type", APPJSON)
        conn.setRequestProperty("Accept", APPJSON)
        return conn
    }
    inline infix fun <reified T> get(endpoint: String): T {
        return decodeResponseBody(buildRequest("GET", endpoint))
    }

    inline infix fun <reified T> delete(endpoint: String): T {
        return decodeResponseBody(buildRequest("DELETE", endpoint))
    }

    inline fun <reified T, reified B> post(endpoint: String, body: B): T {
        val conn = buildRequest("POST", endpoint)
        conn.doOutput = true

        conn.outputStream.use { os -> writeToOutputStream(os, body) }
        return decodeResponseBody(conn)
    }

    inline fun <reified B> writeToOutputStream(outputStream: OutputStream, body: B) {
        val input = when (body) {
            is String -> body
            else -> Json.encodeToString(body)
        }

        val inputBytes = input.toByteArray()
        outputStream.write(inputBytes, 0, inputBytes.size)
    }

    inline infix fun <reified T> decodeResponseBody(connection: HttpURLConnection): T {
        val responseStr = runCatching {
            BufferedReader(
                InputStreamReader(connection.inputStream, "utf-8")
            ).use { parseReader(it) }
        }
            .getOrElse { throw RemoteDriverClosedException(it) }
        log().debug(responseStr)
        return Json.decodeFromString(responseStr)
    }

    fun parseReader(bufferedReader: BufferedReader): String {
        val response = StringBuilder()
        var responseLine: String?
        while (bufferedReader.readLine().also { responseLine = it } != null) {
            response.append(responseLine!!.trim { it <= ' ' })
        }
        return response.toString()
    }

    private fun mergeUrl(url: String, endpoint: String): String {
        return endpoint
            .takeIf { it.startsWith("/") }
            ?.let { "$url$endpoint" }
            ?: "$url/$endpoint"
    }

    override fun close() {
        deleteSession()

        process.destroy()
        runCatching { process.waitFor() }
            .onFailure { process.destroyForcibly() }
    }

    companion object {
        val APPJSON = "application/json"
    }
}

class RemoteDriverClosedException(cause: Throwable): RuntimeException("This RemoteDriver instance has already been closed!", cause)

@Serializable
data class SuccessResponse<T>(val value: T)
