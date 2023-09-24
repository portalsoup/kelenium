package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.RemoteWireDriver
import com.portalsoup.wireprotocol.core.LocationStrategy.*
import com.portalsoup.wireprotocol.dto.Element
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ElementsTests: BaseTest() {


    @Test
    fun findElementTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        initializeWebdriver().use {
            it.navigateTo(testPage)
            val element = it.findElement(CSS, "#first").value
            assertThat(element.size, equalTo(1))
            assertThat(it.getElementText(element.first()).value, equalTo("One"))
        }
    }

    @Test
    fun findElementsTest() {
        val testPage = HtmlPages.ElementList.asUrl()

        initializeWebdriver().use {
            it.navigateTo(testPage)

            val listItems = it.findElements(TAG, "li").value

            assertThat(listItems.size, equalTo(3))
            assertThat(it.getElementText(listItems[0]).value, equalTo("One"))
            assertThat(it.getElementText(listItems[1]).value, equalTo("Two"))
            assertThat(it.getElementText(listItems[2]).value, equalTo("Three"))
        }
    }

    @Test
    fun findElementFromElementTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        initializeWebdriver().use {
            it.navigateTo(testPage)

            val parent = it.findElement(CSS, "#parent").value.first()
            val children = it.findElementFromElement(parent, CSS, ".match").value // The target one is technically the second to find globally
            assertThat(it.getElementText(children).value, equalTo("Inner 1"))

        }
    }

    @Test
    fun findElementFromElementsTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        initializeWebdriver().use {
            it.navigateTo(testPage)

            val parent = it.findElement(CSS, "#parent").value.first()
            val children = it.findElementsFromElement(parent, CSS, ".match").value
            assertThat(children.size, equalTo(2))
            assertThat(it.getElementText(children[0]).value, equalTo("Inner 1"))
            assertThat(it.getElementText(children[1]).value, equalTo("Inner 2"))

        }
    }

    @Test
    fun clickElementTest() {
        val testPage = HtmlPages.ClickCounter.asUrl()

        initializeWebdriver().use {
            it.navigateTo(testPage)

            val button: Element = it.findElement(CSS,  "#counterButton").value.first()
            val count: Element = it.findElement(CSS, "#count").value.first()

            val originalCount = it.getElementText(count).value.toInt()
            assertThat(originalCount, equalTo(0))

            it.elementClick(button) // button increments count tag
            val newCount = it.getElementText(count).value.toInt()
            assertThat(newCount, equalTo(1))
        }
    }

    @Test
    fun getElementTextTest() {
        initializeWebdriver().use {
            val testPage = HtmlPages.ClickCounter.asUrl()
            println(testPage)
            it.navigateTo(testPage)
            val element = it.findElement(CSS, "#message").value
            val text = it.getElementText(element.first()).value
            assertThat(text, equalTo("0 clicks!"))
        }
    }

    @Test
    fun sendKeysTest() {
        val testPage = HtmlPages.TextField.asUrl()
        RemoteWireDriver(webdriverPath).use {
            it.navigateTo(testPage)
            val field = it.findElement(CSS, "#input").value.first()
            val label = it.findElement(CSS, "#output").value.first()
            assertThat(it.getElementText(label).value, equalTo("Default value"))
            it.elementSendKeys(field, "Test value ") // quirk in the html page, the final character is ignored
            assertThat(it.getElementText(label).value, equalTo("Test value"))
        }
    }
}