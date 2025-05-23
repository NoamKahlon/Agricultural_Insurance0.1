package tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Dimension;
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
    private static String configPath = "C:\\Users\\Noam\\IdeaProjects\\Project0.2\\src\\test\\testData\\config.xml";
    private String originalWindow;



    public SoftAssert softAssert;


   @BeforeSuite
    public void setup() throws ParserConfigurationException, IOException, SAXException {
           File allureResultsDir = new File("allure-results");
           if (allureResultsDir.exists()) {
               for (File file : allureResultsDir.listFiles()) {
                   file.delete();
               }
               System.out.println("✅ Deleted old Allure logs");
           }
   }


    //Runs before each test class
    @BeforeClass
    public void beforeClass() throws ParserConfigurationException, IOException, SAXException {
        if (driver == null) {
            initDriver(); // יבטיח שה-driver מאותחל תמיד, גם כשלא מריצים את כל הסוויטה
            driver.manage().window().setSize(new Dimension(1920, 1080));
            driver.manage().window().maximize();
        }
        basePage = new BasePage(driver);
        contactUsPage = new ContactUsPage(driver,basePage);
        searchDocumentsAndFormsPage = new SearchDocumentsAndFormsPage(basePage);
        onlineTravelInsurancePage = new OnlineTravelInsurancePage(driver,basePage);
        checkingTheExistenceOfCarInsurancePage = new CheckingTheExistenceOfCarInsurancePage(driver,basePage);
//        loggerUtils = new LoggerUtils(driver,softAssert);
    }


    @BeforeMethod(description = "Open Home Page")
    public void beforeMethod() {
        softAssert = new SoftAssert();
        loggerUtils = new LoggerUtils(driver, softAssert);
        basePage.openMainPage();
        loggerUtils.log("✅ Opened homepage", "homepage", true, true);
        originalWindow = driver.getWindowHandle();
    }


    @AfterMethod
    public void tearDown() {
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
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Driver quit successfully");
        } else {
            System.out.println("⚠️ Driver was null, nothing to quit");
        }
    }


    protected static String readFromFile(String keyData, String pathName)  {
        String value = null;
        try {
            // Load the XML file
            File xmlFile = new File(pathName.toString());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            // Normalize the document
            doc.getDocumentElement().normalize();

            // Extract values from XML
            value = doc.getElementsByTagName(keyData).item(0).getTextContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }


    public void attachScreenshot(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDriver() throws ParserConfigurationException, IOException, SAXException {
        String browser = readFromFile("browser", configPath);

        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }


        switch (browser) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless"); // מריץ בלי ממשק גרפי
//                options.addArguments("--disable-gpu");


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
