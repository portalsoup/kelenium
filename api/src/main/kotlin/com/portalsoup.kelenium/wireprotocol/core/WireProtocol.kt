package com.portalsoup.wireprotocol.core

import com.portalsoup.wireprotocol.response.Response
import com.portalsoup.wireprotocol.serialization.serializers.ResponseSerializer
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket

open class WireProtocol(val host: String, val port: Int) {
    fun <R: Response> get(endpoint: String): R {
        return performHttpRequest("GET", endpoint)
            .let { Json.decodeFromString<R>(ResponseSerializer, it) }
    }

    inline fun <reified T, R: Response> post(endpoint: String, payload: T): R {
        return performHttpRequest("POST", endpoint, jsonToString(payload))
            .let { Json.decodeFromString<R>(ResponseSerializer, it) }
    }

    fun <R: Response> delete(endpoint: String): R {
        return performHttpRequest("DELETE", endpoint)
            .also { println(it) }
            .let { Json.decodeFromString<R>(ResponseSerializer, it) }
    }

    private fun prepareHttpRequest(method: String, endpoint: String, payload: String?): String {
        return """
                $method $endpoint HTTP/1.1
                Host: http://$host:$port
                Connection: close
                Content-Type: application/json
                Accept: application/json
                ${payload?.let { "Content-Length: ${it.length}" } ?: ""}
                
                ${payload ?: ""}
                
                
                """.trimIndent()
    }

    fun performHttpRequest(
        method: String,
        endpoint: String,
        payload: String? = null,
    ): String {

        val request = prepareHttpRequest(method, endpoint, payload)
        return Socket(host, port).use {
                val reader = writeRequest(it, request)
                handleResponse(reader)
            }

    }

    private fun writeRequest(socket: Socket, request: String): BufferedReader {
        // Create a socket connection to the host
        val out: OutputStream = socket.getOutputStream()
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

        // Send an HTTP GET request

        out.write(request.toByteArray())
        out.flush()

        return reader
    }

    private fun handleResponse(reader: BufferedReader): String {
        // Read and process the HTTP response
        val response = StringBuilder()
        var headersRead = false
        var line: String?

        while (reader.readLine().also { line = it } != null) {
            if (!headersRead) {
                if (line!!.isEmpty()) {
                    headersRead = true
                }
            } else {
                response.append(line).append("\n")
            }
        }

        val responseStr = response.toString()
        return responseStr
    }

    inline fun <reified J> jsonToString(obj: J): String {
        return when (obj) {
            is String -> obj
            else -> Json.encodeToString(obj)
        }.also { println("Encoded a payload: $it") }
    }
}