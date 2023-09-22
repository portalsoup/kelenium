package com.portalsoup.core.capabilities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("moz:firefoxOptions")
data class FirefoxOptions(
    val binary: String? = null,
    val args: List<String>,
    val profile: String,
    val log: Log,
//    val prefs: Map<String, Any>, // Can actually be String, Bool or Int so how do?
    val env: Map<String, String>
) {
    @Serializable
    data class Log(val level: String)
}