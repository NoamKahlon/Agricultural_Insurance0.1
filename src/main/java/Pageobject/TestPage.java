package Pageobject;

import org.openqa.selenium.WebDriver;

public class TestPage {

    WebDriver driver;

    public TestPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openUrl() {
        driver.get("https://www.google.com/");
    }
    public String getTitle() {
        return driver.getTitle();
    }
}
