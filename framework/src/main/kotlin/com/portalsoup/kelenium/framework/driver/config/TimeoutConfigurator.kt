package com.portalsoup.kelenium.framework.driver.config

import com.portalsoup.kelenium.framework.DeepCopyable

data class TimeoutConfigurator(
    var implicit: Int = 0,
    var pageLoad: Int = 5000,
    var script: Int = 5000
): DeepCopyable<TimeoutConfigurator> {
    override fun deepCopy(): TimeoutConfigurator = this.copy()
}