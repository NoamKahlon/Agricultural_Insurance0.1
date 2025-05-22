import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingTheExistenceOfCarInsurancePage {

    private final WebDriver driver;
    private final BasePage basePage;

    // Strings
    private final String license = "23652125";
    private final String notValidlicense = "12345678";
    private final String dateValue = "04/11/2024"; // בפורמט שה-datepicker מקבל

    // Locators
    private final By licenseInputField = By.id("license-number");
    private final By IsTheVehicleInsuredButton = By.cssSelector("body > main > section.actions > div.quick__container > ul > li:nth-child(1) > a");
    private final By datePicker = By.id("search-date");
    private final By searchButton = By.id("check-sub");
    private final By successMessage = By.cssSelector(".success-msg.msg-form");
    private final By errorMessage =  By.cssSelector("div.error-msg.msg-form[style*='display: block']");



    private final By successMessageColor = By.cssSelector(".msg-form.success-msg .flex-cont");






    public CheckingTheExistenceOfCarInsurancePage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    public void enterLicenseNumber() {
        basePage.sendKeys(licenseInputField, license);
    }

    public void enterInvalidLicenseNumber() {
        basePage.sendKeys(licenseInputField, notValidlicense);
    }

    public void enterDate() {
        basePage.pickDate(datePicker, dateValue);
    }

    public void clickSearch() {
        basePage.click(searchButton);
    }

    public String getSuccessText() {
        return basePage.getText(successMessage);
    }

    public String getErrorText() {
        return basePage.getText(errorMessage);
    }

    public void ScrollToIsTheVehicleInsuredButton() throws Exception {
        basePage.scrollToElement(IsTheVehicleInsuredButton);
    }
    public void ClickIsTheVehicleInsuredButton() throws Exception {
        basePage.forceClick(IsTheVehicleInsuredButton);
    }

    public boolean isErrorTextRed() {
        WebElement result = driver.findElement(errorMessage);
        String color = result.getCssValue("color");
        return color.contains("rgba(255, 0, 0, 1)") || color.toLowerCase().contains("red");
    }

    public void isErrorTextRed222() {

        String color = driver.findElement(errorMessage).getCssValue("color");
        System.out.println("errorMessage COLOR VALUE: " + color);


    }

    public void isSuccessTextGreen222() {
        String color = driver.findElement(successMessage).getCssValue("color");
        System.out.println("successMessage COLOR VALUE: " + color);
    }


    public boolean isSuccessTextGreen() {
        WebElement result = driver.findElement(successMessageColor);
        String color = result.getCssValue("color");
        return color.contains("rgb(96, 187, 112)") || color.contains("60bb70");    }
}
