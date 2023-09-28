package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.core.LocationStrategy.*
import com.portalsoup.wireprotocol.dto.Element
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ElementsTests: BaseTest() {


    @Test
    fun findElementTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)
            val element = api.findElement(it, CSS, "#first").value
            assertThat(api.getElementText(it, element).value, equalTo("One"))
        }
    }

    @Test
    fun findElementsTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val listItems = api.findElements(it, TAG, "li").value

            assertThat(listItems.size, equalTo(3))
            assertThat(api.getElementText(it, listItems[0]).value, equalTo("One"))
            assertThat(api.getElementText(it, listItems[1]).value, equalTo("Two"))
            assertThat(api.getElementText(it, listItems[2]).value, equalTo("Three"))
        }
    }

    @Test
    fun findElementFromElementTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val parent = api.findElement(it, CSS, "#parent").value
            val children = api.findElementFromElement(it, parent, CSS, ".match").value // The target one is technically the second to find globally
            assertThat(api.getElementText(it, children).value, equalTo("Inner 1"))

        }
    }

    @Test
    fun findElementFromElementsTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val parent = api.findElement(it, CSS, "#parent").value
            val children = api.findElementsFromElement(it, parent, CSS, ".match").value
            assertThat(children.size, equalTo(2))
            assertThat(api.getElementText(it, children[0]).value, equalTo("Inner 1"))
            assertThat(api.getElementText(it, children[1]).value, equalTo("Inner 2"))

        }
    }

    @Test
    fun clickElementTest() {
        val testPage = HtmlPages.ClickCounter.asUrl()

        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val button: Element = api.findElement(it, CSS,  "#counterButton").value
            val count: Element = api.findElement(it, CSS, "#count").value

            val originalCount = api.getElementText(it, count).value.toInt()
            assertThat(originalCount, equalTo(0))

            api.elementClick(it, button) // button increments count tag
            val newCount = api.getElementText(it, count).value.toInt()
            assertThat(newCount, equalTo(1))
        }
    }

    @Test
    fun getElementTextTest() {
        val api = getApi()
        useSession(api) {
            val testPage = HtmlPages.ClickCounter.asUrl()
            println(testPage)
            api.navigateTo(it, testPage)
            val element = api.findElement(it, CSS, "#message").value
            val text = api.getElementText(it, element).value
            assertThat(text, equalTo("0 clicks!"))
        }
    }

    @Test
    fun sendKeysTest() {
        val testPage = HtmlPages.TextField.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)
            val field = api.findElement(it, CSS, "#input").value
            val label = api.findElement(it, CSS, "#output").value
            assertThat(api.getElementText(it, label).value, equalTo("Default value"))
            api.elementSendKeys(it, field, "Test value ") // quirk in the html page, the final character is ignored
            assertThat(api.getElementText(it, label).value, equalTo("Test value"))
        }
    }
}