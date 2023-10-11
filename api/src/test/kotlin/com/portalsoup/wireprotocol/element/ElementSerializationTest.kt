package com.portalsoup.wireprotocol.element

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.element.dto.ElementRef
import com.portalsoup.wireprotocol.element.dto.ElementRefList
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class ElementSerializationTest: BaseTest() {
    @Test
    fun serializeElementRefTest() {
        val identifier = "element-test identifier"
        val reference = "test reference"
        val response = generateExampleApiResponse("""{"$identifier": "$reference"}""")

        MatcherAssert.assertThat(response.value, Matchers.equalTo(ElementRef(identifier, reference)))
    }

    @Test
    fun serializeElementRefListTest() {
        val identifier1 = "element-identifier1"
        val reference1 = "test reference 1"
        val identifier2 = "element-identifier2"
        val reference2 = "test reference2"
        val response = generateExampleApiResponse("""
                [{
                    "$identifier1": "$reference1"
                }, {
                    "$identifier2": "$reference2"
                }]
            """.trimIndent())

        val expected = ElementRefList(listOf(
            ElementRef(identifier1, reference1),
            ElementRef(identifier2, reference2)
        ))

        MatcherAssert.assertThat(response.value, Matchers.equalTo(expected))
    }
}