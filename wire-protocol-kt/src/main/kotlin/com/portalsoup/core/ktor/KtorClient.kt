package com.portalsoup.core.ktor

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val ktorClient: HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
        json()
    }
}