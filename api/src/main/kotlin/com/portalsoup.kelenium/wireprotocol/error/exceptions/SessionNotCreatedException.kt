package com.portalsoup.wireprotocol.error.exceptions

class SessionNotCreatedException(cause: Throwable? = null): RuntimeException("Could not open a new session!", cause)