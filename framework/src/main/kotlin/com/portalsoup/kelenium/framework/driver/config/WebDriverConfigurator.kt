package com.portalsoup.kelenium.framework.driver.config

import com.portalsoup.kelenium.framework.DeepCopyable

data class WebDriverConfigurator(
    internal var timeoutConfigurator: TimeoutConfigurator = TimeoutConfigurator()
): DeepCopyable<WebDriverConfigurator> {
    fun timeouts(f: TimeoutConfigurator.() -> Unit) {
        timeoutConfigurator.apply(f)
    }

    override fun deepCopy(): WebDriverConfigurator = this.copy(
        timeoutConfigurator = timeoutConfigurator.deepCopy()
    )
}