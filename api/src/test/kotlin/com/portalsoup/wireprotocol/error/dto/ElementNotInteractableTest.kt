package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.core.ErrorCodes
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class ElementNotInteractableTest: BaseErrorResponseTest() {
    @Test
    fun serializeElementNotInteractableTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.ELEMENT_NOT_INTERACTABLE.code)
        MatcherAssert.assertThat(response.value, IsInstanceOf.instanceOf(ElementNotInteractable::class.java))
    }
}