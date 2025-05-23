import com.portalsoup.kelenium.framework.driver.DriverServer
import com.portalsoup.kelenium.framework.driver.LocationStrategy.CSS
import com.portalsoup.kelenium.framework.driver.WebDriver

fun main() {
    WebDriver(DriverServer.Geckodriver, "127.0.0.1", 4444).use { driver ->
        driver.configure {
            timeouts {
                implicit = 1000
                pageLoad = 10000
                script = 10000
            }
        }

        performGoogleSearch(driver)

        Thread.sleep(5000)
    }
}

fun performGoogleSearch(driver: WebDriver) {
    driver.navigate.to("https://google.com")

    val searchBarSelector = CSS("[title=Search]")
    val searchButtonSelector = CSS("[value='Google Search'][type=submit]")

    val searchBar = driver.findElement(searchBarSelector)

    val searchQuery = "reddit"
    searchBar.sendKeys(searchQuery)
    Thread.sleep(1000)

    val searchButton = driver.findElement(searchButtonSelector)

    println("Validated search query: ${searchBar.getText()}")

    Thread.sleep(500)
    searchButton.click()
    Thread.sleep(500)
    driver.navigate.back()
}