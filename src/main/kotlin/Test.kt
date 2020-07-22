import com.portalsoup.core.*
import org.json.JSONObject

import java.lang.RuntimeException
import java.net.URL

var process: Process? = null

fun main() {
//    WebDriverManager.firefoxdriver().setup()
//    val driver: WebDriver = FirefoxDriver()
//    val driverLocation = System.getProperty("webdriver.gecko.driver")
//    println(driverLocation)

    System.setProperty("webdriver.gecko.driver", "${System.getProperty("user.home")}/.webdriver/geckodriver")
    val driver = WebDriver()
    println(driver.sessionId)

    try {
        println("Current status is ${driver.status()}")
        println("Current timeouts are: ${driver.getTimeouts()}")
        driver.setTimeouts(WebDriver.Timeouts(script = 50000, pageLoad = 50000, implicit = 50000))
        println("new timeouts are: ${driver.getTimeouts()}")

        driver.navigateTo("https://www.google.com")

        Thread.sleep(5000)

    } finally {
        driver.close()
    }
}
