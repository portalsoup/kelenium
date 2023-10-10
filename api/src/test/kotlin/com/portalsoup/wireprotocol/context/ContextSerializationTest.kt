package com.portalsoup.wireprotocol.context

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.context.dto.NewWindow
import com.portalsoup.wireprotocol.context.dto.WindowRect
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ContextSerializationTest: BaseTest() {
    @Test
    fun serializeNewWindowTest() {
        val handle = "test handle"
        val type = "test type"
        val response = generateExampleApiRespones("""
            {
                "handle": "$handle",
                "type": "$type"
            }
        """.trimIndent())

        assertThat(response.value, equalTo(NewWindow(handle, type)))

    }

    @Test
    fun serializeWindowRectTest() {
        val x = 1
        val y = 1
        val width = 1
        val height = 1
        val response = generateExampleApiRespones("""
            {
                "x": "$x",
                "y": "$y",
                "width": "$width",
                "height": "$height"
            }
        """.trimIndent())

        assertThat(response.value, equalTo(WindowRect(x, y, width, height)))
    }
}