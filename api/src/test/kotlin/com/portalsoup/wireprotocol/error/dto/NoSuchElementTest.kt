package com.portalsoup.wireprotocol.error.dto

import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.core.ErrorCodes
import com.portalsoup.wireprotocol.element.api.findElement
import com.portalsoup.wireprotocol.navigation.api.navigateTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.core.IsInstanceOf
import org.junit.jupiter.api.Test

class NoSuchElementTest: BaseErrorResponseTest() {
    @Test
    fun serializeNoSuchElementTest() {
        val response = generateAndSerializeExampleFailureResponse(ErrorCodes.NO_SUCH_ELEMENT.code)
        assertThat(response.value, IsInstanceOf.instanceOf(NoSuchElement::class.java))
    }

    @Test
    fun failToFindAnElementTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)
            val response = api.findElement(it, "css selector", "#doesnotexist").value
            assertThat(response, instanceOf(NoSuchElement::class.java))

        }
    }
}
