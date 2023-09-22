package com.portalsoup.wireprotocol.dto

import kotlinx.serialization.Serializable


// simple idea for an element
@Serializable
data class Element(val locationStrategy: String, val reference: String)

@Serializable
data class FindElementStrategy(val using: String, val value: String)
