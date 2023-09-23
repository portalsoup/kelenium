package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import com.portalsoup.wireprotocol.HtmlPages
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class NavigationTests: BaseTest() {

    @Test
    fun basicNavigationTest() {
        val testPage = HtmlPages.ElementList.asUrl()
        initializeWebdriver().use {
            it.navigateTo(testPage)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage))
        }
    }

    @Test
    fun backTest() {
        val testPage1 = HtmlPages.ElementList.asUrl()
        val testPage2 = HtmlPages.ClickCounter.asUrl()
        initializeWebdriver().use {
            it.navigateTo(testPage1)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage1))
            it.navigateTo(testPage2)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage2))
            it.back()
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage1))
        }
    }

    @Test
    fun forwardTest() {
        val testPage1 = HtmlPages.ElementList.asUrl()
        val testPage2 = HtmlPages.ClickCounter.asUrl()
        initializeWebdriver().use {
            it.navigateTo(testPage1)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage1))
            it.navigateTo(testPage2)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage2))
            it.back()
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage1))
            it.forward()
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(testPage2))
        }
    }
}