package com.portalsoup.kelenium.framework

import java.lang.AssertionError

enum class DriverServer(val path: String) {
    Geckodriver("geckodriver");

    fun absolutePath(): String = javaClass.classLoader
        .getResource(path)
        ?.path
        ?: throw AssertionError("Failed to locate $path in test resources!")

    fun asUrl(): String = absolutePath().let { "file://$it" }
}