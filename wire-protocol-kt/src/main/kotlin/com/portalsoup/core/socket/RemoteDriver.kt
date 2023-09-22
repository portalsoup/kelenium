package com.portalsoup.core.socket

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class RemoteDriver(
    path: String = System.getProperty("webdriver.gecko.driver"),
    host: String = "127.0.0.1",
    port: Int = 4444,
): AutoCloseable {

    private val process: Process = ProcessBuilder(path).start()

    val serverUrl = "http://$host:$port"

    internal inline infix fun <reified T> get(endpoint: String): T {
        val url = URL(mergeUrl(serverUrl, endpoint))
        val conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")

        return decodeResponseBody(conn)

    }

    internal inline infix fun <reified T> delete(endpoint: String): T {
        val url = URL(mergeUrl(serverUrl, endpoint))
        val conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = "DELETE"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")

        return decodeResponseBody(conn)

    }

    internal inline fun <reified T, reified B> post(endpoint: String, body: B): T {
        val url = URL(mergeUrl(serverUrl, endpoint))
        val conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = "POST"
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Accept", "application/json")
        conn.doOutput = true

        conn.outputStream.use { os ->
            val input = when (body) {
                is String -> body
                else -> Json.encodeToString(body)
            }

            println(input)
            val inputBytes = input.toByteArray()
            os.write(inputBytes, 0, inputBytes.size)
        }

        return decodeResponseBody(conn)
    }

    private inline infix fun <reified T> decodeResponseBody(connection: HttpURLConnection): T {
        val responseStr = BufferedReader(
            InputStreamReader(connection.inputStream, "utf-8")
        ).use { br ->
            val response = StringBuilder()
            var responseLine: String?
            while (br.readLine().also { responseLine = it } != null) {
                response.append(responseLine!!.trim { it <= ' ' })
            }
            response.toString()

        }

        return Json.decodeFromString(responseStr)
    }

    private fun mergeUrl(url: String, endpoint: String): String {
        return endpoint
            .takeIf { it.startsWith("/") }
            ?.let { "$url$endpoint" }
            ?: "$url/$endpoint"
    }

    override fun close() {
        process.destroy()
        runCatching { process.waitFor() }
            .onFailure { process.destroyForcibly() }
    }

    companion object {
        @Serializable
        class EmptyJson
    }
}