import com.portalsoup.kelenium.framework.DriverServer.Geckodriver
import com.portalsoup.kelenium.framework.LocationStrategy.CSS
import com.portalsoup.kelenium.framework.driver.WebDriver

fun main() {
    WebDriver(Geckodriver, "127.0.0.1", 4444).use { driver ->
        driver.configure {
            timeouts {
                implicit = 1000
                pageLoad = 10000
                script = 10000
            }
        }

        performGoogleSearch(driver)
    }
}

fun performGoogleSearch(driver: WebDriver) {
    driver.navigateTo("https://google.com")

    val searchBarSelector = CSS("[title=Search]")
    val searchButtonSelector = CSS("[value='Google Search'][type=submit]")

    val searchBar = driver.findElement(searchBarSelector)

    val searchQuery = "reddit"
    searchBar.sendKeys(searchQuery)

    // a new instance of the button appears in a dropdown that covers the original
    val searchButton = driver.findElement(searchButtonSelector)

    println("Validated search query: ${searchBar.getText()}")

    Thread.sleep(100)
    searchButton.click()
}