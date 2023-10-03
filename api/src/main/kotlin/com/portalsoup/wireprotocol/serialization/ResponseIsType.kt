package com.portalsoup.wireprotocol.serialization

import kotlinx.serialization.json.JsonObject

interface ResponseIsType {
    fun isType(element: JsonObject): Boolean
}