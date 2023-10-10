package com.portalsoup.wireprotocol.context

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.context.api.*
import com.portalsoup.wireprotocol.context.dto.ContextType
import com.portalsoup.wireprotocol.context.dto.NewWindow
import com.portalsoup.wireprotocol.context.dto.WindowRect
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.lang.AssertionError
import java.util.*

class ContextsTests: BaseTest() {

    @Test
    fun getWindowHandleTest() {
        val api = getApi()
        useSession(api) {
            val handle = api.getWindowHandle(it).value.let {
                when (it) {
                    is String -> it
                    else -> throw AssertionError()
                }
            }
            assertDoesNotThrow { println(UUID.fromString(handle)) }
        }
    }

//    @Test
    fun closeWindowTest() {
        assert(false) // The expected behavior here is still a bit unclear
    }

    @Test
    fun createAndSwitchToWindowTest() {
        val api = getApi()
        useSession(api) { session ->
            val oldWindowHandle = api.getWindowHandle(session).value
            val newWindowHandle = api.newWindow(session, ContextType.WINDOW).value

            when (newWindowHandle) {
                is NewWindow -> {
                    assertThat(api.getWindowHandle(session).value, equalTo(oldWindowHandle))
                    api.switchToWindow(session, newWindowHandle.handle)
                    assertThat(api.getWindowHandle(session).value, equalTo(newWindowHandle.handle))
                }
                else -> throw AssertionError()
            }

        }
    }

    @Test
    fun getAllWindowHandlesTest() {
        val api = getApi()
        useSession(api) {
            val originalHandlesList = api.getWindowHandles(it).value as List<String>
            assertThat(originalHandlesList.size, equalTo(1))
            api.newWindow(it, ContextType.WINDOW)
            val newHandlesList = api.getWindowHandles(it).value as List<String>
            assertThat(newHandlesList.size, equalTo(2))
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