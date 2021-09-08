package com.portalsoup.core

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/**
 * DSL entrypoint to building urls
 */
fun kurl(lambda: KURLBuilder.() -> Unit) = KURLBuilder().apply(lambda).build()

/**
 * kotlin url for performing queries
 */
data class KURL(
    val host: String,
    val queryArgs: QueryParameters,
    val requestHeaders: Map<String, String>,
    val body: JSONObject
) {
    fun url(): URL = URL("$host$queryArgs")

    fun doGet(): JSONObject {
        return url().openConnection().run {
            this as HttpURLConnection

            requestMethod = "GET"
            setRequestHeaders(requestHeaders)


            JSONObject(inputStream.bufferedReader().readText())
        }
    }

    fun doPost(): JSONObject {
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

    fun doDelete(): JSONObject {
        url().openConnection().run {
            this as HttpURLConnection

            requestMethod = "DELETE"
            doOutput = true

            return JSONObject(inputStream.bufferedReader().readText())
        }
    }
}

class KURLBuilder {
    var host: String = ""
    var queryArgs: QueryParameters = QueryParameters(mapOf())
    var requestHeaders: Map<String, String> = mapOf()
    var body: JSONObject = JSONObject()

    fun host(lambda: KURLBuilder.() -> String) { host = KURLBuilder().let(lambda) }

    fun queryArgs(lambda: KURLBuilder.() -> QueryParameters) {
        queryArgs = KURLBuilder().let(lambda)
    }

    fun requestHeaders(lambda: KURLBuilder.() -> Map<String, String>) {
        requestHeaders = KURLBuilder().let(lambda)
    }

    fun body(lambda: KURLBuilder.() -> JSONObject) {
        body = KURLBuilder().let(lambda)
    }

    fun build() = KURL(host, queryArgs, requestHeaders, body)
}