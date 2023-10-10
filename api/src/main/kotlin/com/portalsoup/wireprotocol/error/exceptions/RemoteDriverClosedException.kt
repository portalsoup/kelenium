package com.portalsoup.wireprotocol.exceptions

class RemoteDriverClosedException(cause: Throwable): RuntimeException("This webdriver instance has already been closed!", cause)