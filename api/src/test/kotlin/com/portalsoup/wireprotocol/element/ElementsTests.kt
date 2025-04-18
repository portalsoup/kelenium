package com.portalsoup.wireprotocol.element

import com.portalsoup.wireprotocol.BaseApiTest
import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.element.api.*
import com.portalsoup.wireprotocol.element.dto.*
import com.portalsoup.wireprotocol.navigation.api.navigateTo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ElementsTests: BaseApiTest() {


    @Test
    fun findElementTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)
            val element = api.findElement(it, "css selector", "#first").value as ElementRef
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

            val listItems = api.findElements(it, "css selector", "li").value as ElementRefList

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

            val parent = api.findElement(it, "css selector", ("#parent")).value as ElementRef
            val children = api.findElementFromElement(it, "css selector", parent).value as ElementRef// The target one is technically the second to find globally
            assertThat(api.getElementText(it, children).value, equalTo("Inner 1"))

        }
    }

    //    @Test
    fun findElementFromElementsTest() {
        val testPage = HtmlPages.NestedElements.asUrl()
        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)

            val parent = api.findElement(it, "css selector", ("#parent")).value as ElementRef
            val children = api. findElementsFromElement(it, "css selector", parent).value as List<ElementRef>
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

            val button = api.findElement(it, "css selector", ("#counterButton")).value as ElementRef
            val count = api.findElement(it, "css selector", ("#count")).value as ElementRef

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
            val element = api.findElement(it, "css selector", ("#message")).value as ElementRef
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
            val field = api.findElement(it, "css selector", ("#input")).value as ElementRef
            val label = api.findElement(it, "css selector", ("#output")).value as ElementRef
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
            val label = api.findElement(it, "css selector", ("#output")).value as ElementRef
            assertThat(api.getElementTagName(it, label).value, equalTo("span"))
        }
    }
}