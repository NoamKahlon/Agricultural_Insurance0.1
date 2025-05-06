import Pageobject.TestPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {
    protected static WebDriver driver;
    protected TestPage testPage;

    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        driver = new EdgeDriver();
    }

    @BeforeMethod
    public void initPage() {
        testPage = new TestPage(driver);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
