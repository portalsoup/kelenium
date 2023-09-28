package com.portalsoup.kelenium.framework

data class GeckoDriverConnectionContext(override val host: String, override val port: Int, override val capabilities: String?, val launchArgs: List<String>): ConnectionContext(host, port, capabilities)
class RemoteGeckoDriver(path: String): RemoteWebDriver<RemoteGeckoDriver>(path) {

    var connectExisting = false

    override val launchArgs: ConnectionContext.() -> List<String> = {
        mutableListOf(
            path,
            "--port", port.toString(),
            "--host", host
        ).also { if (connectExisting) { it.add("--connect-existing") } }
    }

    override fun newConnectionContext(): ConnectionContext {
        val host = host
        val port = port()
        return object : ConnectionContext(host, port, capabilities) {}
                .let { launchArgs.invoke(it) }
            .let { GeckoDriverConnectionContext(host, port, null, it) }
    }
}