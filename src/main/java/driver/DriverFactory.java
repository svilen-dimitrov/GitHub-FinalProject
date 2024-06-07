package driver;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final String IGNORE_CERTIFICATE = "--ignore-certificate-errors";

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void setFirefoxDriver(int wait) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(IGNORE_CERTIFICATE);

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

        tlDriver.set(driver);
    }

    public static void setChromeDriver(int wait) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(IGNORE_CERTIFICATE);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

        tlDriver.set(driver);
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}