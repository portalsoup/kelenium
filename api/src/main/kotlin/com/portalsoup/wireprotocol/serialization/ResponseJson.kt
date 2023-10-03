package com.portalsoup.wireprotocol.serialization

import kotlinx.serialization.json.Json

val responseJson = Json {
    ignoreUnknownKeys = true
}