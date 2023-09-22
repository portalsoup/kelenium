package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.core.LocationStrategy.CSS
import com.portalsoup.wireprotocol.core.LocationStrategy.XPATH
import com.portalsoup.wireprotocol.dto.Element
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test

class ElementsTests: BaseTest() {

    val duckduckgoLearnMoreButtonXpath = "//a[contains(@class, 'learnMore')]"

    @Test
    fun findElementTest() {
        initializeWebdriver().use {
            it.navigateTo(example1Url)
            val element = it.findElement(XPATH, duckduckgoLearnMoreButtonXpath).value
            assertThat(element.size, equalTo(1))
        }
    }

    @Test
    fun clickElementTest() {
        val testPage = resourceAsUrl("ClickTest.html")

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
            val testPage = resourceAsUrl("ClickTest.html")
            println(testPage)
            it.navigateTo(testPage)
            val element = it.findElement(CSS, "#message").value
            val text = it.getElementText(element.first()).value
            assertThat(text, equalTo("0 clicks!"))
        }
    }
}