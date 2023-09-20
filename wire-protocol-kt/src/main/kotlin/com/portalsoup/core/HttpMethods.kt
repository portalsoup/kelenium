package com.portalsoup.core

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