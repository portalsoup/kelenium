import com.portalsoup.core.*
import com.portalsoup.core.wireprotocol.*
import java.lang.Thread.sleep

var process: Process? = null

fun main() {
    System.setProperty("webdriver.gecko.driver", "${System.getProperty("user.home")}/.webdriver/geckodriver")
    WebDriver().use { driver ->
        println(driver.session)

        println("Current status is ${driver.status()}")
        println("Current timeouts are: ${driver.getTimeouts()}")
        driver.setTimeouts(Timeouts(script = 50000, pageLoad = 50000, implicit = 50000))
        println("new timeouts are: ${driver.getTimeouts()}")

        driver.navigateTo("https://www.google.com")
        Thread.sleep(2000)
        driver.navigateTo("https://www.duckduckgo.com")
        println("The current URL is now: ${driver.currentUrl()}")
        driver.back()
        Thread.sleep(2000)
        println("After back the current URL is now: ${driver.currentUrl()}")

//        Thread.sleep(5000)
    }
}
