
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingTheExistenceOfCarInsurancePage {

    private final WebDriver driver;
    private final BasePage basePage;

    private final String license = "23652125";
    private final String notValidlicense = "12345678";
    private final String dateValue = "04/11/2024";

    private final By licenseInputField = By.id("license-number");
    private final By IsTheVehicleInsuredButton = By.cssSelector("body > main > section.actions > div.quick__container > ul > li:nth-child(1) > a");
    private final By datePicker = By.id("search-date");
    private final By searchButton = By.id("check-sub");
    private final By successMessage = By.cssSelector(".success-msg.msg-form");
    private final By errorMessage =  By.cssSelector("div.error-msg.msg-form[style*='display: block']");






    public CheckingTheExistenceOfCarInsurancePage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    public void enterLicenseNumber() {
        basePage.sendKeys(licenseInputField, license,true);
    }

    public void enterInvalidLicenseNumber() {
        basePage.sendKeys(licenseInputField, notValidlicense,true);
    }

    public void enterDate() {
        basePage.pickDate(datePicker, dateValue);
    }

    public void clickSearch() {
        basePage.click(searchButton,true);
    }

    public String getSuccessText() {
        return basePage.getText(successMessage,true);
    }

    public String getErrorText() {
        return basePage.getText(errorMessage,true);
    }

    public boolean ScrollToCheckingTheExistenceOfCarInsurance() throws Exception {
       return basePage.scrollToElement(IsTheVehicleInsuredButton);
    }
    public void ClickCheckingTheExistenceOfCarInsurance() throws Exception {
        basePage.forceClick(IsTheVehicleInsuredButton,true);
    }

    public boolean isErrorTextRed() {
        WebElement result = driver.findElement(errorMessage);
        String color = result.getCssValue("color");
        return color.contains("rgba(255, 0, 0, 1)") || color.toLowerCase().contains("red");
    }

    public boolean isSuccessTextGreen() {
        WebElement result = basePage.getElement(successMessage);
        String color = result.getCssValue("color").trim(); // rgba(96, 187, 112, 1)

        String[] rgba = color.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(rgba[0].trim());
        int g = Integer.parseInt(rgba[1].trim());
        int b = Integer.parseInt(rgba[2].trim());

        return r == 96 && g == 187 && b == 112;
    }

}
