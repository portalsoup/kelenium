package com.portalsoup.wireprotocol.serialization.dto

import com.portalsoup.wireprotocol.serialization.ResponseSerializer
import com.portalsoup.wireprotocol.serialization.dto.failure.SessionNotCreated
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.responseJson
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

@Serializable(with = ResponseSerializer::class)
data class Response(val value: Any)



fun main() {
    val one = """
        {
            "value": {
                "sessionId": "test-value",
                "capabilities": {}
            }
        }""".trimIndent()
    val two = """
        {
        	"value": {
        		"error": "session not created",
        		"message": "",
        		"stacktrace": "",
        		"data": {
        			"text": "Message from window.alert"
        		}
        	}
        }
    """.trimIndent()

    val three = """
        {
            "value": "test primitive"   
        }
    """.trimIndent()

    val r1: Response = responseJson.decodeFromString(one)
    val r2: Response = responseJson.decodeFromString(two)
    val r3: Response = responseJson.decodeFromString(three)

    assert(r1)
    assert(r2)
    assert(r3)
}

fun assert(response: Response) {
    when (response.value) {
        is SessionCreated -> println("Got a session: ${response.value.id}")
        is SessionNotCreated -> println("Session serialization failed ${response.value.error}")
        is String -> println("this is a primitive! ${response.value}")
        else -> println("Unexpected error $response")
    }
}