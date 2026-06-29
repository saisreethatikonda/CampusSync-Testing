package com.cognizant.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.cognizant.util.FrameworkConstants;
import com.cognizant.util.GenericScreenshots;
import com.cognizant.util.PropertyFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected PropertyFileReader configReader;
    protected PropertyFileReader testDataReader;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        configReader = new PropertyFileReader(FrameworkConstants.CONFIG_FILE_PATH);
        testDataReader = new PropertyFileReader(FrameworkConstants.TEST_DATA_FILE_PATH);

        String browser = configReader.getValue("browser", "chrome").trim().toLowerCase();
        boolean headless = Boolean.parseBoolean(configReader.getValue("headless", "false"));
        long implicitWait = Long.parseLong(configReader.getValue("implicitWait", "5"));
        String applicationUrl = configReader.getValue("url", "https://example.com");

        driver = createDriver(browser, headless);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().window().maximize();
        driver.get(applicationUrl);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (driver != null) {
            if (!result.isSuccess()) {
                GenericScreenshots.capture(driver, result.getName());
            }
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver createDriver(String browser, boolean headless) {
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless");
                }
                return new FirefoxDriver(firefoxOptions);

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new");
                }
                return new EdgeDriver(edgeOptions);

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                return new ChromeDriver(chromeOptions);
        }
    }
}
