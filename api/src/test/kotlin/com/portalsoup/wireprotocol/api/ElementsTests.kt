package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.core.LocationStrategy.*
import com.portalsoup.wireprotocol.core.LocationStrategy
import com.portalsoup.wireprotocol.serialization.dto.request.SendKeys
import com.portalsoup.wireprotocol.serialization.dto.response.success.ElementRef
import com.portalsoup.wireprotocol.serialization.dto.response.success.ElementRefList
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
            val element = api.findElement(it, CSS("#first")).value as ElementRef
            val text = api.getElementText(it, element).value as String
            assertThat(text, equalTo("One"))
        }
    }

    @Test
    fun findElementsTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val listItems = api.findElements(it, TagName("li")).value as ElementRefList

            assertThat(listItems.size, equalTo(3))
            assertThat(api.getElementText(it, listItems[0]).value, equalTo("One"))
            assertThat(api.getElementText(it, listItems[1]).value, equalTo("Two"))
            assertThat(api.getElementText(it, listItems[2]).value, equalTo("Three"))
        }
    }

//    @Test
    fun findElementFromElementTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val parent = api.findElement(it, CSS("#parent")).value as ElementRef
            val children = api.findElementFromElement(it, parent, ".match").value as ElementRef// The target one is technically the second to find globally
            assertThat(api.getElementText(it, children).value, equalTo("Inner 1"))

        }
    }

//    @Test
    fun findElementFromElementsTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val parent = api.findElement(it, CSS("#parent")).value as ElementRef
            val children = api.findElementsFromElement(it, CSS(".match"), parent).value as ElementRefList
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

            val button = api.findElement(it, CSS("#counterButton")).value as ElementRef
            val count = api.findElement(it, CSS("#count")).value as ElementRef

            val originalCount = (api.getElementText(it, count).value as String).toInt()
            assertThat(originalCount, equalTo(0))

            api.elementClick(it, button) // button increments count tag
            val newCount = (api.getElementText(it, count).value as String).toInt()
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
            val element = api.findElement(it, CSS("#message")).value as ElementRef
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
            val field = api.findElement(it, CSS("#input")).value as ElementRef
            val label = api.findElement(it, CSS("#output")).value as ElementRef
            assertThat(api.getElementText(it, label).value, equalTo("Default value"))
            api.elementSendKeys(it, field, SendKeys("Test value ")) // quirk in the html page, the final character is ignored
            assertThat(api.getElementText(it, label).value, equalTo("Test value"))
        }
    }

    @Test
    fun tagNameTest() {
        val testPage = HtmlPages.TextField.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)
            val label = api.findElement(it, CSS("#output")).value as ElementRef
            assertThat(api.getElementTagName(it, label).value, equalTo("span"))
        }
    }
}