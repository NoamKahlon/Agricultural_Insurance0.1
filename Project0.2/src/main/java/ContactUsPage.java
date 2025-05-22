import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactUsPage {

    private WebDriver driver;
    private BasePage basePage;

    public By contactBtn = By.cssSelector("a[href='/contact-us/']");
    public By contactForm = By.id("myid");
    public By fullNameField = By.id("fullname"); // fix
    public By phoneField = By.id("phone");
    public By emailField = By.id("email");
    public By subjectField = By.id("subject");
    public By messageField = By.id("message");
    public By updatesCheckbox = By.cssSelector("input[name='your-approve[]']");
    public By termsCheckbox = By.cssSelector("input[name='your-agree[]']");
    public By contactCenters = By.xpath("//*[contains(text(), 'מרכז מכירות') or contains(text(), 'מרכז תביעות') or contains(text(), 'מרכז שירות')]");
    public By submitButton = By.cssSelector("input[type='submit']");
    //public By submitButton = By.cssSelector("#myid > div:nth-child(6) > div > input");
    public By thankYouText = By.cssSelector(".notfound__header h2");
    //public By backButton = By.xpath("//a[contains(text(),'חזרה למסך הבית')]");
    public By backButton = By.linkText("חזרה לעמוד הבית");




    public ContactUsPage(WebDriver driver, BasePage basePage) {
        this.driver = driver;
        this.basePage = basePage;
    }

    public void clickOnContactUs() {
        basePage.click(contactBtn);
    }

    public String getTitle() {
        return basePage.getTitle();
    }

//    public List<String> getMissingElements(Map<String, By> fields) {
//        List<String> missing = new ArrayList<>();
//
//        for (Map.Entry<String, By> entry : fields.entrySet()) {
//            String name = entry.getKey();
//            By locator = entry.getValue();
//
//            if (!basePage.isDisplayed(locator)) {
//                missing.add(name);
//            }
//        }
//
//        return missing;
//    }


    public void pressSubmit() throws InterruptedException {
        basePage.click(submitButton);
        //basePage.forceClick(submitButton);
    }

    public String getFieldErrorText(By inputLocator) {
        WebElement inputElement = basePage.getElement(inputLocator);
        String inputId = inputElement.getAttribute("id");

        WebElement errorElement;

        if (inputId != null && !inputId.isEmpty()) {
            By errorLocator = By.id("error-" + inputId);
            basePage.waitForElementToAppear(errorLocator);
            errorElement = basePage.getElement(errorLocator);
        } else {
            errorElement = inputElement.findElement(By.xpath(
                    "./ancestor::span[contains(@class,'wpcf7-form-control-wrap')]/following-sibling::span[contains(@class,'wpcf7-not-valid-tip')]"
            ));
        }

        return errorElement.getText().trim();
    }
}
