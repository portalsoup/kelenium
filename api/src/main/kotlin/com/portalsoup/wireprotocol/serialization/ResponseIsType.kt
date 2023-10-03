package com.portalsoup.wireprotocol.serialization

import kotlinx.serialization.json.JsonObject

/**
 * To be implemented by each Response's companion object.  This allows the serializer to invoke this on each
 * Response type prior to creating an instance when determining type during decoding.
 */
interface ResponseIsType {
    fun isType(element: JsonObject): Boolean
}