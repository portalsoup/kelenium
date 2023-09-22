package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver

fun RemoteDriver.dismissAlert() = Unit
fun RemoteDriver.acceptAlert() = Unit
fun RemoteDriver.getAlertText() = Unit
fun RemoteDriver.sendAlertText() = Unit
