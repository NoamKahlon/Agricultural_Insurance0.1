package pages;
import org.openqa.selenium.By;


public class ContactUsPage {

    private BasePage basePage;

    private final By contactBtn = By.cssSelector("a[href='/contact-us/']");
    private final By fullNameField = By.id("fullname"); // fix
    private final By phoneField = By.id("phone");
    private final By emailField = By.id("email");
    private final By subjectField = By.id("subject");
    private final By messageField = By.id("message");
    private final By submitButton =  By.cssSelector("input[type='submit'][value='חזרו אלי']");
    private final By salesCenterTitle = By.cssSelector(".contact__box--blue h3");
    private final By supportCenterTitle = By.cssSelector(".contact__box--orange h3");
    private final By serviceCenterTitle = By.cssSelector(".contact__box--green h3");
    private final By thankYouText = By.cssSelector(".notfound__header h2");
    private final By backButton = By.linkText("חזרה לעמוד הבית");


    public ContactUsPage(BasePage basePage) {this.basePage = basePage;}

    public void clickOnContactUs() {
        basePage.click(contactBtn,true);
    }

    public String getTitle() {
        return basePage.getTitle();
    }

    public void pressSubmit() throws InterruptedException {
        basePage.forceClick(submitButton,true);
        Thread.sleep(200);
    }

    public boolean isFullNameFieldDisplayed() {
        return basePage.isDisplayed(fullNameField,false);
    }

    public boolean isPhoneFieldDisplayed() {
        return basePage.isDisplayed(phoneField,false);
    }

    public boolean isEmailFieldDisplayed() {
        return basePage.isDisplayed(emailField,false);
    }

    public boolean isSubjectFieldDisplayed() {
        return basePage.isDisplayed(subjectField,false);
    }

    public boolean isMessageFieldDisplayed() {
        return basePage.isDisplayed(messageField,false);
    }

    public boolean isSalesCenterTitleDisplayed() {
        return basePage.isDisplayed(salesCenterTitle,false);
    }

    public boolean isSupportCenterTitleDisplayed() {
        return basePage.isDisplayed(supportCenterTitle,false);
    }

    public boolean isServiceCenterTitleDisplayed() {
        return basePage.isDisplayed(serviceCenterTitle,false);
    }

    public boolean isThankYouTextDisplayed() {
        return basePage.isDisplayed(thankYouText,false);
    }

    public boolean isBackButtonDisplayed() {
        return basePage.isDisplayed(backButton,false);
    }

    public String getSalesCenterTitleText() {
        return basePage.getText(salesCenterTitle,false);
    }

    public String getSupportCenterTitleText() {
        return basePage.getText(supportCenterTitle,false);
    }

    public String getServiceCenterTitleText() {
        return basePage.getText(serviceCenterTitle,false);
    }

    public By getFullNameField() { return By.id("fullname"); }
    public By getPhoneField() { return By.id("phone"); }
    public By getEmailField() { return By.id("email"); }
    public By getSubjectField() { return By.id("subject"); }
    public By getMessageField() { return By.id("message"); }
    public By getSalesCenterTitle() { return By.cssSelector(".contact__box--blue h3"); }
    public By getSupportCenterTitle() { return By.cssSelector(".contact__box--orange h3"); }
    public By getServiceCenterTitle() { return By.cssSelector(".contact__box--green h3"); }
    public By getThankYouText() { return By.cssSelector(".notfound__header h2"); }
    public By getBackButton() { return By.linkText("חזרה לעמוד הבית"); }

}
