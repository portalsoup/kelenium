package com.portalsoup.core.wireprotocol

import com.portalsoup.core.WebDriver
import com.portalsoup.core.ktor.ktorClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
// TODO This can actually be polymorphic as per https://www.w3.org/TR/webdriver2/#dfn-success for the success or error wrapper
data class SessionWrapper(val value: Session?)
@Serializable
data class Session(@SerialName("sessionId") val id: String, val capabilities: JsonObject)
@Serializable
data class StatusWrapper(val value: Status)
@Serializable
data class Status(val message: String, val ready: Boolean)
data class WebDriverSessionException(override val message: String, override val cause: Throwable? = null): RuntimeException(message, cause)

// https://www.w3.org/TR/webdriver2/#dfn-new-sessions
fun WebDriver.createSession(): Session = runBlocking {
    val response = ktorClient
        .post("$serverUrl/session") {
            header("Content-Type", "application/json")
            setBody("{}")
        }

    response
        .body<SessionWrapper>()
        .takeIf { it.value != null }
        ?.let { when (it.value) {
            is Session -> it.value
            else -> null
        } }
        ?: throw WebDriverSessionException("Failed to initialize session.  Session body was null!  \nRaw response: \n\t${response.bodyAsText()}")

}

// https://www.w3.org/TR/webdriver2/#dfn-delete-session
fun WebDriver.deleteSession(): Boolean =
    runBlocking {
        ktorClient
            .delete("$serverUrl/session/${session.id}")
            .body<SessionWrapper>()
            .takeIf { it.value != null }
            ?.let { println("ERROR: ${it.value}") }
            ?.let { false }
            ?: true
    }

//// https://www.w3.org/TR/webdriver2/#dfn-status
fun WebDriver.status(): Status = runBlocking {
    ktorClient
        .get("$serverUrl/status")
        .body<StatusWrapper>()
        .also { println(it) }
        .value
}