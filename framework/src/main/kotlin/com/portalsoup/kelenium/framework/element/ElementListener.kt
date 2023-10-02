package com.portalsoup.kelenium.framework.element

import com.portalsoup.kelenium.framework.RemoteDriverConnection
import kotlinx.coroutines.*

/*
 send a heartebeat every interval to just poll the continued existence of an element
 located at the locater?
 */
class ElementListener(val connection: RemoteDriverConnection, val element: Element) {

    val delayIntervalMs: Long = 1000

    private fun setupThread(predicate: () -> Boolean, l: () -> Unit) {
        GlobalScope.launch {
            while (isActive) {
                if (!predicate()) {
                    l()
                }
                delay(delayIntervalMs)
            }
        }
    }

//    fun <E> onStale(l: () -> E) {
//        setupThread({ element.isEnabled() })
//    }

}