package com.portalsoup.wireprotocol.screencapture

import com.portalsoup.wireprotocol.BaseApiTest

/*
    These tests require human intervention to verify that the jpgs that are created in your home folder are indeed there and
    appear correct.
 */
class ScreenCaptureTests: BaseApiTest() {
//
//    @Test
//    fun takeScreenshotTest() {
//        val testPage = HtmlPages.ClickCounter.asUrl()
//        val api = getApi()
//        useSession(api) {
//            api.navigateTo(it, testPage)
//            val base64 = api.takeScreenshot(it).value
//            val decodedImg: ByteArray = Base64.getDecoder().decode(base64.toByteArray())
//            val destinationFile = Paths.get(System.getProperty("user.home"), "testScreenshot.jpg")
//            Files.write(destinationFile, decodedImg)
//        }
//    }
//
//    @Test
//    fun takeElementScreenshotTest() {
//        val testPage = HtmlPages.ClickCounter.asUrl()
//        val api = getApi()
//        useSession(api) {
//            api.navigateTo(it, testPage)
//            val element = api.findElement(it, LocationStrategy.CSS, "#message").value
//            val base64 = api.takeElementScreenshot(it, element).value
//            val decodedImg: ByteArray = Base64.getDecoder().decode(base64.toByteArray())
//            val destinationFile = Paths.get(System.getProperty("user.home"), "testElementScreenshot.jpg")
//            Files.write(destinationFile, decodedImg)
//        }
//    }
}