package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.*;
import utils.LoggerUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class Base {

    // ========= WebDriver & Config =========
    protected static WebDriver driver;
    private static final String configPath = "C:\\Users\\Noam\\IdeaProjects\\Project0.2\\src\\main\\java\\utils\\config.xml";
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

    // ========= Utility Methods =========
    protected static String readFromFile(String keyData, String pathName) {
        String value = null;
        try {
            File xmlFile = new File(pathName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            value = doc.getElementsByTagName(keyData).item(0).getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }

    public void initDriver() throws ParserConfigurationException, IOException, SAXException {
        String browser = readFromFile("browser", configPath);

        switch (browser.toLowerCase()) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }
}
