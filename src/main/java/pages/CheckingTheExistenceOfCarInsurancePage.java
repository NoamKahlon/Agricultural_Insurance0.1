
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckingTheExistenceOfCarInsurancePage {


    // //////////// BASE ////////////
    private final BasePage basePage;

    // //////////// TEST DATA ////////////
    private final String license = "23652125";
    private final String notValidlicense = "12345678";
    private final String dateValue = "04/11/2024";

    // //////////// FORM FIELDS ////////////
    private final By licenseInputField = By.id("license-number");
    private final By datePicker = By.id("search-date");

    // //////////// BUTTONS ////////////
    private final By searchButton = By.id("check-sub");
    private final By isTheVehicleInsuredButton = By.cssSelector("body > main > section.actions > div.quick__container > ul > li:nth-child(1) > a");

    // //////////// MESSAGES / VALIDATION TEXTS ////////////
    private final By successMessage = By.cssSelector(".success-msg.msg-form");
    private final By errorMessage = By.cssSelector("div.error-msg.msg-form[style*='display: block']");


    public CheckingTheExistenceOfCarInsurancePage(BasePage basePage) {
        this.basePage = basePage;
    }


    //////////// GET ///////////////

    public String getErrorText() {
        return basePage.getText(errorMessage);
    }

    public String getSuccessText() {
        return basePage.getText(successMessage);
    }

    //////////// SET ///////////////

    public void enterLicenseNumber() { basePage.sendKeys(licenseInputField, license);}

    public void enterInvalidLicenseNumber() { basePage.sendKeys(licenseInputField, notValidlicense);}

    public void enterDate() {
        basePage.pickDate(datePicker, dateValue);
    }

    //////////// CLICK ///////////////

    public void clickSearch() {
        basePage.click(searchButton);
    }

    public void clickCheckingTheExistenceOfCarInsurance() throws Exception {
        basePage.forceClick(isTheVehicleInsuredButton);
    }

    //////////// SCROLL /////////////

    public boolean scrollToCheckingTheExistenceOfCarInsurance() throws Exception
    { return basePage.scrollToElement(isTheVehicleInsuredButton);}

    //////////// VALIDATIONS /////////////

    public boolean isErrorTextRed() {
        WebElement result = basePage.getElement(errorMessage);
        String color = result.getCssValue("color").trim();

        System.out.println("Error message color: " + color); // DEBUG LOG

        try {
            String[] parts = color
                    .replace("rgba(", "")
                    .replace("rgb(", "")
                    .replace(")", "")
                    .split(",");

            int r = Integer.parseInt(parts[0].trim());
            int g = Integer.parseInt(parts[1].trim());
            int b = Integer.parseInt(parts[2].trim());

            return r == 255 && g == 0 && b == 0; // צבע אדום מלא
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSuccessTextGreen() {
        WebElement result = basePage.getElement(successMessage);
        String color = result.getCssValue("color").trim(); // תומך גם ב־rgba וגם ב־rgb

        try {
            String[] parts = color
                    .replace("rgba(", "")
                    .replace("rgb(", "")
                    .replace(")", "")
                    .split(",");

            int r = Integer.parseInt(parts[0].trim());
            int g = Integer.parseInt(parts[1].trim());
            int b = Integer.parseInt(parts[2].trim());

            return r == 96 && g == 187 && b == 112;
        } catch (Exception e) {
            return false;
        }
    }


}
