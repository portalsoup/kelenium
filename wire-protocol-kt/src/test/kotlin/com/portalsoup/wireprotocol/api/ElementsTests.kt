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
}