package base;

import driver.DriverFactory;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.SignupPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MainTest {
    private String url;
    private int implicitWait;
    private String browser;

    @BeforeClass
    public void generateInvalidPasswords() {
        SignupPage signupPage = new SignupPage();
        signupPage.exportEmailPasswordPairsToCSV(1, "src/test/resources/generated-invalid-short-passwords.csv");
    }
    @BeforeMethod
    public void setUp() {
        setupBrowserDriver();
        loadUrl();
    }


    private void loadUrl() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(url);
    }

    private void setupBrowserDriver() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            browser = config.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (browser) {
            case "chrome":
                DriverFactory.setChromeDriver(implicitWait);
                break;
            case "firefox":
                DriverFactory.setFirefoxDriver(implicitWait);
                break;
            default:
                throw new IllegalStateException("Unsupported browser type");
        }

    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        // Retrieve the driver for the current thread
        WebDriver driver = DriverFactory.getDriver();

        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
            String fileName = "Screenshot_" + timestamp + ".png";

            // Constructing the relative path to the 'Screenshots' directory
            Path directory = Paths.get(System.getProperty("user.dir"), "Screenshots");
            Path path = Paths.get(directory.toString(), fileName);

            try {
                // Create the directory if it doesn't exist
                Files.createDirectories(directory);
                Files.copy(screenshotFile.toPath(), path);
                Allure.addAttachment("Screenshot on Failure", "image/png", Files.newInputStream(path), ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DriverFactory.quitDriver();
    }
}