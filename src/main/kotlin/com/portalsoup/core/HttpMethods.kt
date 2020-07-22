package com.portalsoup.core

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart
import org.json.JSONObject
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL

fun request(requestMethod: String, route: String, queryArgs: Map<String, String>, requestHeaders: Map<String, String>) {
    val url = URL(route)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = requestMethod
    connection.setRequestHeaders(requestHeaders)

    connection.doOutput = true

    val out = DataOutputStream(connection.outputStream)
    out.writeBytes(QueryParameters(queryArgs).toString())
    out.flush()
    out.close()
}

fun HttpURLConnection.setRequestHeaders(headers: Map<String, String>) {
    headers
        .forEach { (k, v) -> setRequestProperty(k, v) }
}

fun KURL.doGet(queryArgs: QueryParameters = QueryParameters(mapOf()), requestHeaders: Map<String, String> = mapOf()): JSONObject {
    return url().openConnection().run {
        this as HttpURLConnection

        requestMethod = "GET"
        setRequestHeaders(requestHeaders)


        JSONObject(inputStream.bufferedReader().readText())
    }
}

fun KURL.doPost(): JSONObject {
    url().openConnection().run {
        this as HttpURLConnection

        requestMethod = "POST"
        setRequestProperty("Accept", "application/json")
        doOutput = true

        outputStream.use {
            val input = body.toString().toByteArray(Charsets.UTF_8)
            it.write(input, 0, input.size)
        }

        return JSONObject(inputStream.bufferedReader().readText())
    }
}

fun KURL.doDelete(): JSONObject {
    url().openConnection().run {
        this as HttpURLConnection

        requestMethod = "DELETE"
        doOutput = true

        return JSONObject(inputStream.bufferedReader().readText())
    }
}