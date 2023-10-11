package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class InsecureCertificateTest: BaseErrorResponseTest() {
    @Test
    fun serializeInsecureCertificateTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.INSECURE_CERTIFICATE.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(InsecureCertificate::class.java))
    }
}