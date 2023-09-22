package com.portalsoup.core.wireprotocol

import com.portalsoup.core.socket.RemoteDriver

fun RemoteDriver.dismissAlert() = Unit
fun RemoteDriver.acceptAlert() = Unit
fun RemoteDriver.getAlertText() = Unit
fun RemoteDriver.sendAlertText() = Unit
