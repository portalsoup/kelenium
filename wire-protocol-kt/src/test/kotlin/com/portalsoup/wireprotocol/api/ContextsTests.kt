package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.dto.Type
import com.portalsoup.wireprotocol.dto.WindowRect
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.*

class ContextsTests: BaseTest() {

    @Test
    fun getWindowHandleTest() {
        initializeWebdriver().use {
            val handle = it.getWindowHandle().value
            assertDoesNotThrow { println(UUID.fromString(handle)) }
        }

    }

    @Test
    fun closeWindowTest() {
        initializeWebdriver().use {
            it.closeWindow()
        }

        assert(false) // The expected behavior here is a bit unclear
    }

    @Test
    fun createAndSwitchToWindowTest() {
        initializeWebdriver().use {
            val oldWindowHandle = it.getWindowHandle().value
            val newWindowHandle = it.newWindow(Type.WINDOW).value.handle

            assertThat(it.getWindowHandle().value, equalTo(oldWindowHandle))
            it.switchToWindow(newWindowHandle)
            assertThat(it.getWindowHandle().value, equalTo(newWindowHandle))

        }
    }

    @Test
    fun getAllWindowHandlesTest() {
        initializeWebdriver().use {
            assertThat(it.getWindowHandles().value.size, equalTo(1))
            it.newWindow(Type.WINDOW).value.handle
            assertThat(it.getWindowHandles().value.size, equalTo(2))
        }
    }

    @Test
    fun getWindowRectTest() {
        initializeWebdriver().use {
            val rect = it.getWindowRect().value
            assertThat(rect, notNullValue())
        }
    }

    @Test
    fun setWindowRectTest() {
        initializeWebdriver().use {
            val newRect = WindowRect(5, 5, 500, 500)
            it.setWindowRect(newRect)
            val rect = it.getWindowRect().value
            assertThat(rect, equalTo(newRect))
        }
    }
}