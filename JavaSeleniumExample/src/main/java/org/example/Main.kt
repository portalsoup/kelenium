package org.example

import org.openqa.selenium.By.ByCssSelector
import org.openqa.selenium.firefox.FirefoxDriver

fun main() {
    val driver = FirefoxDriver()
    driver.findElement(ByCssSelector("")).sendKeys()
}