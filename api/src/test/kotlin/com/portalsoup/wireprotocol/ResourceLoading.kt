package com.portalsoup.wireprotocol

import java.lang.AssertionError

interface Resource {
    val path: String

    fun absolutePathOf(): String = javaClass.classLoader
        .getResource(path)
        ?.path
        ?: throw AssertionError("Failed to locate $path in test resources!")

    fun asUrl(): String = absolutePathOf().let { "file://$it" }
}

enum class Executables(override val path: String): Resource {
    Geckodriver("geckodriver")
}
enum class HtmlPages(override val path: String): Resource {
    ClickCounter("static/ClickCounter.html"),
    ElementList("static/ElementList.html"),
    NestedElements("static/NestedElements.html"),
    TextField("static/TextField.html"),
    ShadowDom("static/ShadowDom.html")
    ;
}