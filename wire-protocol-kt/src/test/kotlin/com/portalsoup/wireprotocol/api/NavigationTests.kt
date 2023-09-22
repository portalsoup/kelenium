package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.BaseTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.Test

class NavigationTests: BaseTest() {

    @Test
    fun basicNavigationTest() {
        initializeWebdriver().use {
            it.navigateTo(example1Url)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example1Url))
        }
    }

    @Test
    fun backTest() {
        initializeWebdriver().use {
            it.navigateTo(example1Url)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example1Url))
            it.navigateTo(example2Url)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example2Url))
            it.back()
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example1Url))
        }
    }

    @Test
    fun forwardTest() {
        initializeWebdriver().use {
            it.navigateTo(example1Url)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example1Url))
            it.navigateTo(example2Url)
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example2Url))
            it.back()
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example1Url))
            it.forward()
            MatcherAssert.assertThat(it.currentUrl().value, Matchers.equalTo(example2Url))
        }
    }
}