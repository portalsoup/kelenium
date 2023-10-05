package com.portalsoup.wireprotocol.serialization.dto

import com.portalsoup.wireprotocol.serialization.serializers.ResponseSerializer
import com.portalsoup.wireprotocol.serialization.dto.failure.BaseFailure
import com.portalsoup.wireprotocol.serialization.dto.failure.SessionNotCreated
import com.portalsoup.wireprotocol.serialization.dto.success.ElementRefList
import com.portalsoup.wireprotocol.serialization.dto.success.SessionCreated
import com.portalsoup.wireprotocol.serialization.responseJson
import kotlinx.serialization.Serializable

@Serializable(with = ResponseSerializer::class)
data class Response(val value: Any?)
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

    val four = """
        {
            "value": null   
        }
    """.trimIndent()

    val five = """
        {
            "value": {"element-6066-11e4-a52e-4f735466cecf":"fd1f2e0d-3116-4850-9487-0b153eea68bb"}
            
        }
    """.trimIndent()

    val r1: Response = responseJson.decodeFromString(one)
    val r2: Response = responseJson.decodeFromString(two)
    val r3: Response = responseJson.decodeFromString(three)
    val r4: Response = responseJson.decodeFromString(four)
    val r5: Response = responseJson.decodeFromString(five)

    assert(r1)
    assert(r2)
    assert(r3)
    assert(r4)
    assert(r5)
}

fun assert(response: Response) {
    when (response.value) {
        is SessionCreated -> println("Got a session: ${response.value.id}")
        is SessionNotCreated -> println("Session serialization failed ${response.value.error}")
        is String -> println("this is a primitive! ${response}")
        is Unit -> println("Got a null response")
        is ElementRefList -> println("Got some elements! ${response.value}")
        is BaseFailure -> println("Caught a generic failure!")
    }
}