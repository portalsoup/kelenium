package com.portalsoup.core

import org.json.JSONObject
import java.net.URL

data class KURL(
    val host: String,
    val queryArgs: QueryParameters,
    val requestHeaders: Map<String, String>,
    val body: JSONObject
) {
    fun url(): URL = URL("$host$queryArgs")

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

fun kurl(lambda: KURLBuilder.() -> Unit) = KURLBuilder().apply(lambda).build()