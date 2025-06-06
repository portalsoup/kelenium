package com.portalsoup.wireprotocol.navigation

import com.portalsoup.wireprotocol.BaseApiTest
import com.portalsoup.wireprotocol.HtmlPages
import com.portalsoup.wireprotocol.navigation.api.back
import com.portalsoup.wireprotocol.navigation.api.currentUrl
import com.portalsoup.wireprotocol.navigation.api.forward
import com.portalsoup.wireprotocol.navigation.api.navigateTo
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class NavigationTests: BaseApiTest() {

    @Test
    fun basicNavigationTest() {
        val testPage = HtmlPages.ElementList.asUrl()

        val api = getApi()
        useSession(api) {
            api.navigateTo(it, testPage)
            MatcherAssert.assertThat(api.currentUrl(it).value, Matchers.equalTo(testPage))
        }
    }

    @Test
    fun backTest() {
        val testPage1 = HtmlPages.ElementList.asUrl()
        val testPage2 = HtmlPages.ClickCounter.asUrl()

        val api = getApi()
        useSession(api) { session ->
            api.navigateTo(session, testPage1)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage1))
            api.navigateTo(session, testPage2)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage2))
            api.back(session)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage1))
        }
    }

    @Test
    fun forwardTest() {
        val testPage1 = HtmlPages.ElementList.asUrl()
        val testPage2 = HtmlPages.ClickCounter.asUrl()

        val api = getApi()
        useSession(api) { session ->
            api.navigateTo(session, testPage1)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage1))
            api.navigateTo(session, testPage2)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage2))
            api.back(session)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage1))
            api.forward(session)
            MatcherAssert.assertThat(api.currentUrl(session).value, Matchers.equalTo(testPage2))
        }
    }
}