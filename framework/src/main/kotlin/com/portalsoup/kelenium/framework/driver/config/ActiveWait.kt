package com.portalsoup.kelenium.framework.driver.config

import com.portalsoup.kelenium.framework.DeepCopyable

data class ActiveWait(
    var pollingIntervalMs: Int = 500,
    var timeoutMs: Int = 30 * 1000,
): DeepCopyable<ActiveWait> {
    override fun deepCopy(): ActiveWait = this.copy()
}