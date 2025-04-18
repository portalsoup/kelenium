package com.portalsoup.wireprotocol.error.exceptions

class ElementNotFoundException(selector: String, cause: Throwable? = null):
    RuntimeException("The selected web element could not be found!  selector=[$selector]", cause)