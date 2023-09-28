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
        val api = getApi()
        useSession(api) {
            val handle = api.getWindowHandle(it).value
            assertDoesNotThrow { println(UUID.fromString(handle)) }
        }
    }

    @Test
    fun closeWindowTest() {
        assert(false) // The expected behavior here is still a bit unclear
    }

    @Test
    fun createAndSwitchToWindowTest() {
        val api = getApi()
        useSession(api) {
            val oldWindowHandle = api.getWindowHandle(it).value
            val newWindowHandle = api.newWindow(it, Type.WINDOW).value.handle

            assertThat(api.getWindowHandle(it).value, equalTo(oldWindowHandle))
            api.switchToWindow(it, newWindowHandle)
            assertThat(api.getWindowHandle(it).value, equalTo(newWindowHandle))
        }
    }

    @Test
    fun getAllWindowHandlesTest() {
        val api = getApi()
        useSession(api) {
            assertThat(api.getWindowHandles(it).value.size, equalTo(1))
            api.newWindow(it, Type.WINDOW).value.handle
            assertThat(api.getWindowHandles(it).value.size, equalTo(2))
        }
    }

    @Test
    fun getWindowRectTest() {
        val api = getApi()
        useSession(api) {
            val rect = api.getWindowRect(it).value
            assertThat(rect, notNullValue())
        }
    }

    @Test
    fun setWindowRectTest() {
        val api = getApi()
        useSession(api) {
            val newRect = WindowRect(5, 5, 500, 500)
            api.setWindowRect(it, newRect)
            val rect = api.getWindowRect(it).value
            assertThat(rect, equalTo(newRect))
        }
    }
}