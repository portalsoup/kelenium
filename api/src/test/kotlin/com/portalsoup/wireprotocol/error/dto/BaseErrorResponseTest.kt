package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.response.Response

open class BaseErrorResponseTest: BaseTest() {

    fun generateAndSerializeExampleFailureResponse(code: String): Response {
        return generateExampleApiRespones(
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