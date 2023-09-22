package com.portalsoup.wireprotocol.core

import com.portalsoup.wireprotocol.RemoteDriver
import com.portalsoup.wireprotocol.RemoteDriverClosedException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class HttpRequestBuilder(val baseUrl: String): ILogging {

    fun buildRequest(method: String, url: URL): HttpURLConnection {
        val conn = url.openConnection() as HttpURLConnection

        conn.requestMethod = method
        conn.setRequestProperty("Content-Type", RemoteDriver.APPJSON)
        conn.setRequestProperty("Accept", RemoteDriver.APPJSON)
        return conn
    }


    internal inline fun <reified T> get(endpoint: String): T {
        return decodeResponseBody(buildRequest("GET", mergeUrl(endpoint)))
    }

    internal inline fun <reified T> delete(endpoint: String): T {
        return decodeResponseBody(buildRequest("DELETE", mergeUrl(endpoint)))
    }

    internal inline fun <reified T, reified B> post(endpoint: String, body: B): T {
        val conn = buildRequest("POST", mergeUrl(endpoint))
        conn.doOutput = true

        conn.outputStream.use { os -> writeToOutputStream(os, body) }
        return decodeResponseBody(conn)
    }

    internal inline fun <reified B> writeToOutputStream(outputStream: OutputStream, body: B) {
        val input = when (body) {
            is String -> body
            else -> Json.encodeToString(body)
        }

        val inputBytes = input.toByteArray()
        outputStream.write(inputBytes, 0, inputBytes.size)
    }

    internal inline fun <reified T> decodeResponseBody(connection: HttpURLConnection): T {
        val responseStr = runCatching {
            BufferedReader(
                InputStreamReader(connection.inputStream, "utf-8")
            ).use { parseReader(it) }
        }
            .getOrElse { throw RemoteDriverClosedException(it) }
        log().debug(responseStr)
        return Json.decodeFromString(responseStr)
    }

    private fun parseReader(bufferedReader: BufferedReader): String {
        val response = StringBuilder()
        var responseLine: String?
        while (bufferedReader.readLine().also { responseLine = it } != null) {
            response.append(responseLine!!.trim { it <= ' ' })
        }
        return response.toString()
    }

    private infix fun mergeUrl(endpoint: String): URL {
        return endpoint
            .takeIf { it.startsWith("/") }
            ?.let { "$baseUrl$endpoint" }
            ?.let { URL(it) }
            ?: URL("$baseUrl/$endpoint")
    }
}