package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;
import utils.LoggerUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;
import java.io.IOException;
import java.util.Set;

public class Base {

    // ========= WebDriver & Config =========
    protected static WebDriver driver;
    private String originalWindow;

    // ========= Utilities =========
    protected LoggerUtils loggerUtils;

    // ========= Pages =========
    protected BasePage basePage;

    // ========= Setup =========
    @BeforeClass
    public void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        if (driver == null) {
            initDriver();
            driver.manage().window().maximize();
        }

        basePage = new BasePage(driver);
        loggerUtils = new LoggerUtils(driver);
    }

    @BeforeMethod(description = "Open Home Page")
    public void beforeMethod() throws InterruptedException {
        basePage.openMainPage();
        loggerUtils.log("✅ Opened homepage", "home_page", true);
        basePage.closeCookieBannerIfPresent();
        originalWindow = driver.getWindowHandle();
    }

    // ========= Teardown =========
    @AfterMethod
    public void afterMethod() {
        if (driver == null) return;

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }

        driver.switchTo().window(originalWindow);
    }

    @AfterSuite
    public void afterSuite() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ========= Config Reader =========
    private String readFromConfig(String key) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("utils/config.xml")) {
            if (inputStream == null) {
                throw new RuntimeException("❌ config.xml not found in resources/utils/");
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputStream);
            doc.getDocumentElement().normalize();

            return doc.getElementsByTagName(key).item(0).getTextContent();
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to read config.xml: " + e.getMessage(), e);
        }
    }

    public void initDriver() {
        String browser = readFromConfig("browser");
        if (browser == null) {
            browser = "chrome";
        }

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("❌ Unsupported browser: " + browser);
        }
    }
}
