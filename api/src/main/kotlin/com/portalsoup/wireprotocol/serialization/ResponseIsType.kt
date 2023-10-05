package com.portalsoup.wireprotocol.serialization

import kotlinx.serialization.json.JsonElement

/**
 * To be implemented by each Response's companion object.  This allows the serializer to invoke this on each
 * Response type prior to creating an instance when determining type during decoding.
 */
interface ResponseIsType<J: JsonElement> {
    fun isType(element: J): Boolean
}

