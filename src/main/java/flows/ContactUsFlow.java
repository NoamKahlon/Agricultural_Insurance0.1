package flows;

import pages.BasePage;
import pages.ContactUsPage;
import utils.ContactUsData;
import utils.LoggerUtils;

public class ContactUsFlow  {

    private final ContactUsPage contactUsPage;
    private final LoggerUtils loggerUtils;
    private final BasePage basePage;

    public ContactUsFlow(ContactUsPage contactUsPage, LoggerUtils loggerUtils, BasePage basePage) {
        this.contactUsPage = contactUsPage;
        this.loggerUtils = loggerUtils;
        this.basePage = basePage;
    }

    public void clickOnContactUs(){
        contactUsPage.clickOnContactUs();
        loggerUtils.log("✅ Clicked on Contact Us", "ClickContactUs", true, false);
    }

    public void fillAndSubmitContactForm() throws InterruptedException {
        contactUsPage.enterFullName();
        contactUsPage.enterPhone();
        contactUsPage.enterEmail();
        contactUsPage.enterSubject();
        contactUsPage.enterMessage();

        contactUsPage.pressSubmit();
        loggerUtils.log("✅ Form submitted", "FormSubmission", true, false);
    }


    public void VerifyContactUsFormIsDisplayed() {
        verifyField("Full Name", contactUsPage.isFullNameFieldDisplayed());
        verifyField("Phone", contactUsPage.isPhoneFieldDisplayed());
        verifyField("Email", contactUsPage.isEmailFieldDisplayed());
        verifyField("Subject", contactUsPage.isSubjectFieldDisplayed());
        verifyField("Message", contactUsPage.isMessageFieldDisplayed());

        verifyTitle("Sales Center", contactUsPage.getSalesCenterTitleText(), ContactUsData.SALES_CENTER_TITLE);
        verifyTitle("Support Center", contactUsPage.getSupportCenterTitleText(), ContactUsData.CLAIMS_CENTER_TITLE);
        verifyTitle("Service Center", contactUsPage.getServiceCenterTitleText(), ContactUsData.SERVICE_CENTER_TITLE);
    }

    private void verifyField(String name, boolean isDisplayed) {
        loggerUtils.log(isDisplayed ? "✅ " + name + " field is displayed" : "❌ " + name + " field is NOT displayed",
               "" + "Field", isDisplayed, !isDisplayed);
    }

    private void verifyTitle(String name, String actual, String expected) {
        boolean isMatch = actual.trim().equals(expected.trim());
        loggerUtils.log(isMatch ? "✅ " + name + " title matches: " + expected : "❌ " + name + " title mismatch! Got: "
                + actual + ", expected: " + expected, "", isMatch, !isMatch);
    }

}
