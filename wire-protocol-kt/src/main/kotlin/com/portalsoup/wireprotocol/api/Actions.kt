package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteDriver


fun RemoteDriver.nullInputSource() = Unit
fun RemoteDriver.keyInputSource() = Unit
fun RemoteDriver.pointerInputSource() = Unit
fun RemoteDriver.wheelInputSource() = Unit

fun RemoteDriver.performActions() = Unit
fun RemoteDriver.releaseActions() = Unit
