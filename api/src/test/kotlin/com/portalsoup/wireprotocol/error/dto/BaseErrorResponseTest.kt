package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.BaseApiTest
import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.response.Response

open class BaseErrorResponseTest: BaseApiTest() {

    fun generateAndSerializeExampleFailureResponse(code: String): Response {
        return generateExampleApiResponse(
            """
            {
                "error": "$code",
                "message": "",
                "stacktrace": "",
                "data": {}
            }
        """.trimIndent()
        )
    }
}