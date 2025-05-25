package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import pages.*;
import utils.LoggerUtils;

public class Base {


    protected BasePage basePage;
    protected OnlineTravelInsurancePage onlineTravelInsurancePage;
    protected ContactUsPage contactUsPage;
    protected SearchDocumentsAndFormsPage searchDocumentsAndFormsPage;
    protected CheckingTheExistenceOfCarInsurancePage checkingTheExistenceOfCarInsurancePage;
    protected LoggerUtils loggerUtils;

    protected static WebDriver driver;
    private final static String configPath = "C:\\Users\\Noam\\IdeaProjects\\Project0.2\\src\\test\\testData\\config.xml";
    private String originalWindow;
    protected SoftAssert softAssert;


    @BeforeClass
    public void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        if (driver == null) {
            initDriver();
            driver.manage().window().maximize();
        }
        basePage = new BasePage(driver);
        contactUsPage = new ContactUsPage(basePage);
        searchDocumentsAndFormsPage = new SearchDocumentsAndFormsPage(basePage);
        onlineTravelInsurancePage = new OnlineTravelInsurancePage(driver,basePage);
        checkingTheExistenceOfCarInsurancePage = new CheckingTheExistenceOfCarInsurancePage(driver,basePage);
    }


    @BeforeMethod(description = "Open Home Page")
    public void beforeMethod() {
        softAssert = new SoftAssert();
        loggerUtils = new LoggerUtils(driver, softAssert);
        basePage.openMainPage();
        loggerUtils.log("✅ Opened homepage", "Home Page", true, true);
        originalWindow = driver.getWindowHandle();
    }


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
    public void after() {
        if (driver != null) {driver.quit();}
    }

    protected static String readFromFile(String keyData, String pathName)  {
        String value = null;
        try {
            File xmlFile = new File(pathName.toString());
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

        switch (browser) {
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
