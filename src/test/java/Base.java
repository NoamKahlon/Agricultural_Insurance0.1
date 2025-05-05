import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {


    protected static WebDriver driver;


    public Base() {
    }


    @BeforeClass
    public static void before(){
        driver = new ChromeDriver();
    }
    @BeforeEach
    public void beforeEach(){
        driver.get("https://www.google.com/");
    }

    @AfterAll
    public static void close(){
        driver.quit();

    }


}
