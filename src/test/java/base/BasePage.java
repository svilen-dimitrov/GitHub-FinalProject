package base;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait for element to be visible
    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for element to be present
    public void waitForElementToBePresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to be stale
    public void waitForElementToBeStale(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    // Wait for element to be clickable
    public void waitToBeClickable(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element to be enabled
    public void waitForElementToBeEnabled(WebElement element) {
        wait.until(driver -> element.isEnabled());
    }

    // Click an element using JavaScript
    public void clickWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Scroll an element into view using JavaScript
    public void scrollIntoViewWithJS(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void switchToIframeWhenItReady(By locator) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
    }

    // Switch back to the default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}