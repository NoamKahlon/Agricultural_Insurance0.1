import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InsuranceCheckPage {

    private final WebDriver driver;
    private final BasePage basePage;

    // Strings
    private final String license = "23652125";
    private final String dateValue = "04/11/2024"; // בפורמט שה-datepicker מקבל

    // Locators
    private final By licenseInputField = By.id("license-number");
    private final By IsTheVehicleInsuredButton = By.cssSelector("body > main > section.actions > div.quick__container > ul > li:nth-child(1) > a");
    private final By datePicker = By.id("search-date");
    private final By searchButton = By.id("check-sub");
    private final By resultMessage = By.cssSelector(".success-msg.msg-form");





    public InsuranceCheckPage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    public void enterLicenseNumber() {
        basePage.sendKeys(licenseInputField, license);
    }
    public void enterDate() {
        basePage.pickDate(datePicker, dateValue);
    }
    public void clickSearch() {
        basePage.Click(searchButton);
    }

    public String getResultText() {
        return basePage.getText(resultMessage);
    }

    public void ScrollToIsTheVehicleInsuredButton() throws Exception {
        basePage.scrollToElement(IsTheVehicleInsuredButton);
    }
    public void ClickIsTheVehicleInsuredButton() throws Exception {
        basePage.forceClick(IsTheVehicleInsuredButton);
    }


}
