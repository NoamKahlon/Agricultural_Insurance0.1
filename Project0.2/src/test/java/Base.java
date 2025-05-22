

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Dimension;

public class Base {


    BasePage basePage;
    OnlineTravelInsurancePage onlineTravelInsurancePage;
    ContactUsPage contactUsPage;
    SearchDocumentsAndForms searchDocumentsAndForms;
    CheckingTheExistenceOfCarInsurancePage checkingTheExistenceOfCarInsurancePage;

    public static WebDriver driver;
    static String configPath = "C:\\Users\\Noam\\Desktop\\Project BackUps\\Project0.2\\src\\test\\testData\\config.xml";
    String originalWindow;



    public enum LogMode {
        SOFT, HARD
    }

    private SoftAssert softAssert = new SoftAssert();

    public void smartLog(String message, String screenshotName, boolean isPass, LogMode mode) {
        Allure.step(message, () -> {
            if (!screenshotName.isEmpty()) {
                attachScreenshot(screenshotName);
            }

            if (!isPass) {
                if (mode == LogMode.HARD) {
                    throw new AssertionError(message); // עוצר את הבדיקה מייד
                } else {
                    softAssert.fail(message); // מדווח, לא עוצר מייד
                }
            }
        });
    }

    // יש לקרוא לזה בסוף כל טסט שמשתמש ב־LogMode.SOFT
    public void assertAllSoft() {
        softAssert.assertAll();
    }

    void assertTextEquals(String actual, String expected, String stepName) {
        boolean equals = actual.trim().equals(expected.trim());
        smartLog(
                (equals ? "✅ Passed: " : "❌ Failed: ") + stepName +
                        " (Expected: '" + expected + "'  Actual: '" + actual + "')", "Assert_" + stepName, equals, LogMode.HARD);

        Assert.assertEquals(actual.trim(), expected.trim(), stepName);
    }


   @BeforeSuite
    public void setup() throws ParserConfigurationException, IOException, SAXException {
       initDriver();




       driver.manage().window().setSize(new Dimension(1920, 1080));
       driver.manage().window().maximize();
   }

    @BeforeClass
    public void beforeClass(){
        basePage = new BasePage(driver);
        contactUsPage = new ContactUsPage(driver,basePage);
        searchDocumentsAndForms = new SearchDocumentsAndForms(driver,basePage);
        onlineTravelInsurancePage = new OnlineTravelInsurancePage(driver,basePage);
        checkingTheExistenceOfCarInsurancePage = new CheckingTheExistenceOfCarInsurancePage(driver,basePage);
    }


    @BeforeMethod(description = "Open Home Page")
    public void beforeMethod() {
        basePage.openMainPage();
        smartLog("✅ Opened homepage", "homepage", true, LogMode.HARD);

        originalWindow = driver.getWindowHandle(); // ⬅ רק אחרי שפתחת את העמוד הראשי
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
    public void after(){
        driver.quit();
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


//    public void waitForElementToAppear(By locator) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }

//
//    public static String takeScreenShot(String fileName) {
//        try {
//            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//            //String fileName = timeStamp + "_" + System.currentTimeMillis() + ".png";
//            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//            File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//            Path destinationPath = new File("test-output/images/" + fileName).toPath();
//            Files.createDirectories(destinationPath.getParent());
//            Files.copy(screenShotFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
//
//            // Add to allure report
//            Allure.addAttachment(fileName+" - " + timeStamp,
//                    Files.newInputStream(screenShotFile.toPath()));
//
//            return "images/" + fileName;
//        } catch (IOException e) {
//            System.out.println("Failed to save screenshot: " + e.getMessage());
//            return null;
//        }
//    }

    public void attachScreenshot(String name) {
        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initDriver() throws ParserConfigurationException, IOException, SAXException {

        System.out.println("📄 Config path: " + configPath);
        System.out.println("🔍 Browser loaded from config: " + readFromFile("browser", configPath));

        String browser = readFromFile("chrome", configPath);

        browser = "chrome";

        switch (browser) {
            case "edge":
                driver = new EdgeDriver();
                break;
            case "chrome":
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless"); // מריץ בלי ממשק גרפי
//                options.addArguments("--disable-gpu");

                driver = new ChromeDriver(options);
               // driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }
    }
}
