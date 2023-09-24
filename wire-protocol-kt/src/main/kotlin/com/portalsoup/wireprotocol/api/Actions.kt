package com.portalsoup.wireprotocol.api

import com.portalsoup.wireprotocol.RemoteWireDriver


fun RemoteWireDriver.nullInputSource() = Unit
fun RemoteWireDriver.keyInputSource() = Unit
fun RemoteWireDriver.pointerInputSource() = Unit
fun RemoteWireDriver.wheelInputSource() = Unit

fun RemoteWireDriver.performActions() = Unit
fun RemoteWireDriver.releaseActions() = Unit
