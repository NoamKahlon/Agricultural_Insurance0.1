package pages;

import org.openqa.selenium.By;
import utils.ContactUsData;

public class ContactUsPage {

    private final BasePage basePage;

    //////////// NAVIGATION /////////////
    private final By contactBtn = By.cssSelector("a[href='/contact-us/']");

    //////////// FORM FIELDS /////////////
    private final By fullNameField = By.id("fullname");
    private final By phoneField = By.id("phone");
    private final By emailField = By.id("email");
    private final By subjectField = By.id("subject");
    private final By messageField = By.id("message");

    //////////// BUTTONS /////////////
    private final By submitButton = By.cssSelector("input[type='submit'][value='חזרו אלי']");
    private final By backToHomeButton = By.linkText("חזרה לעמוד הבית");

    //////////// TITLES / TEXTS /////////////
    private final By salesCenterTitle = By.cssSelector(".contact__box--blue h3");
    private final By supportCenterTitle = By.cssSelector(".contact__box--orange h3");
    private final By serviceCenterTitle = By.cssSelector(".contact__box--green h3");
    private final By thankYouText = By.cssSelector(".notfound__header h2");


    public ContactUsPage(BasePage basePage) {
        this.basePage = basePage;
    }

    //////////// CLICK /////////////

    public void clickOnContactUs() {
        basePage.click(contactBtn);
    }

    public void pressSubmit() throws InterruptedException {
        basePage.forceClick(submitButton);
        Thread.sleep(200);
    }

    //////////// SET /////////////

    public void enterFullName(String fullName) {
        basePage.sendKeys(fullNameField, fullName);
    }

    public void enterPhone(String phone) {
        basePage.sendKeys(phoneField, phone);
    }

    public void enterEmail(String email) {
        basePage.sendKeys(emailField, email);
    }

    public void enterSubject(String subject) {
        basePage.chooseValueFromDropDownMenuSafe(subjectField, subject);
    }

    public void enterMessage(String message) {
        basePage.sendKeys(messageField, message);
    }

    public void fillForm() {
        enterFullName();
        enterPhone();
        enterEmail();
        enterSubject();
        enterMessage();
    }


    //////////// GET /////////////

    public String getTitle() {
        return basePage.getTitle();
    }

    public String getSalesCenterTitleText() {
        return basePage.getText(salesCenterTitle);
    }

    public String getSupportCenterTitleText() {
        return basePage.getText(supportCenterTitle);
    }

    public String getServiceCenterTitleText() {
        return basePage.getText(serviceCenterTitle);
    }

    public void enterFullName() {
        basePage.sendKeys(fullNameField, ContactUsData.VALID_NAME);
    }

    public void enterPhone() {
        basePage.sendKeys(phoneField, ContactUsData.VALID_PHONE);
    }

    public void enterEmail() {
        basePage.sendKeys(emailField, ContactUsData.VALID_EMAIL);
    }

    public void enterSubject() {
        basePage.chooseValueFromDropDownMenuSafe(subjectField, ContactUsData.VALID_SUBJECT);
    }

    public void enterMessage() {
        basePage.sendKeys(messageField, ContactUsData.VALID_MESSAGE);
    }

    //////////// VALIDATION /////////////

    public boolean isThankYouMessageVisible() {
        return basePage.isDisplayed(thankYouText);
    }

    public boolean isBackButtonVisible() {
        return basePage.isDisplayed(backToHomeButton);
    }


    public boolean isFullNameFieldDisplayed() {
        return basePage.isDisplayed(fullNameField);
    }

    public boolean isPhoneFieldDisplayed() {
        return basePage.isDisplayed(phoneField);
    }

    public boolean isEmailFieldDisplayed() {
        return basePage.isDisplayed(emailField);
    }

    public boolean isSubjectFieldDisplayed() {
        return basePage.isDisplayed(subjectField);
    }

    public boolean isMessageFieldDisplayed() {
        return basePage.isDisplayed(messageField);
    }

    public boolean isSalesCenterTitleDisplayed() {
        return basePage.isDisplayed(salesCenterTitle);
    }

    public boolean isSupportCenterTitleDisplayed() {
        return basePage.isDisplayed(supportCenterTitle);
    }

    public boolean isServiceCenterTitleDisplayed() {
        return basePage.isDisplayed(serviceCenterTitle);
    }

    public boolean isThankYouTextDisplayed() {
        return basePage.isDisplayed(thankYouText);
    }

    public boolean isBackButtonDisplayed() {
        return basePage.isDisplayed(backToHomeButton);
    }
}
