package flows;

import org.openqa.selenium.By;
import pages.BasePage;
import pages.ContactUsPage;
import utils.ContactUsData;
import utils.LoggerUtils;

/**
 * Flow class for automating and verifying the 'Contact Us' form functionality.
 */
public class ContactUsFlow {

    // ========== Members ==========
    private final ContactUsPage contactUsPage;
    private final LoggerUtils loggerUtils;
    private final BasePage basePage;

    /**
     * Constructor
     */
    public ContactUsFlow(LoggerUtils loggerUtils, BasePage basePage) {
        this.contactUsPage = new ContactUsPage(basePage);
        this.loggerUtils = loggerUtils;
        this.basePage = basePage;
    }

    // ========== Actions ==========

    /**
     * Clicks on the 'Contact Us' button from the navigation or landing page.
     */
    public void clickOnContactUs() {
        contactUsPage.clickOnContactUs();
        loggerUtils.log("✅ Clicked on Contact Us", "ClickContactUs", true);
    }

    /**
     * Fills all form fields and submits the contact form.
     */
    public void fillAndSubmitContactForm() throws InterruptedException {
        contactUsPage.enterFullName();
        contactUsPage.enterPhone();
        contactUsPage.enterEmail();
        contactUsPage.enterSubject();
        contactUsPage.enterMessage();
        contactUsPage.pressSubmit();
        loggerUtils.log("✅ Form submitted", "FormSubmission", true);
    }

    // ========== Verifications ==========

    /**
     * Verifies that all form fields and contact section titles are displayed correctly.
     */
    public void verifyContactUsFormIsDisplayed() {
        verifyField("Full Name", contactUsPage.isFullNameFieldDisplayed());
        verifyField("Phone", contactUsPage.isPhoneFieldDisplayed());
        verifyField("Email", contactUsPage.isEmailFieldDisplayed());
        verifyField("Subject", contactUsPage.isSubjectFieldDisplayed());
        verifyField("Message", contactUsPage.isMessageFieldDisplayed());

        verifyTitle("Sales Center", contactUsPage.getSalesCenterTitleText(), ContactUsData.SALES_CENTER_TITLE);
        verifyTitle("Support Center", contactUsPage.getSupportCenterTitleText(), ContactUsData.CLAIMS_CENTER_TITLE);
        verifyTitle("Service Center", contactUsPage.getServiceCenterTitleText(), ContactUsData.SERVICE_CENTER_TITLE);
    }

    /**
     * Submits the form without filling any fields and verifies that all validation messages appear.
     */
    public void submitEmptyFormAndVerifyValidationMessages() throws InterruptedException {
        contactUsPage.pressSubmit();
        loggerUtils.log("✅ Submitted empty form", "EmptySubmit", true);

        String expectedError = ContactUsData.EXPECTED_EMPTY_FIELD_ERROR;
        String[] fieldIds = {"fullname", "phone", "email", "subject", "message"};

        for (String fieldId : fieldIds) {
            By errorLocator = By.id("error-" + fieldId);
            String actualError = basePage.getText(errorLocator);
            boolean matches = actualError.equals(expectedError);

            loggerUtils.log(
                    matches
                            ? "✅ " + fieldId + " error matches expected"
                            : "❌ " + fieldId + " error mismatch. Expected: '" + expectedError + "', Got: '" + actualError + "'",
                    "Validation_" + fieldId,
                    matches
            );
        }
    }

    /**
     * Verifies the 'Thank You' screen is displayed after successful submission.
     */
    public void verifyThankYouScreenDisplayed() {
        boolean thankYouMessageVisible = contactUsPage.isThankYouMessageVisible();
        loggerUtils.log(
                thankYouMessageVisible
                        ? "✅ Thank you message is visible"
                        : "❌ Thank you message is NOT visible",
                "ThankYouMessage",
                thankYouMessageVisible
        );

        boolean backButtonVisible = contactUsPage.isBackButtonVisible();
        loggerUtils.log(
                backButtonVisible
                        ? "✅ Back to home button is visible"
                        : "❌ Back to home button is NOT visible",
                "BackToHomeButton",
                backButtonVisible
        );
    }

    // ========== Private Helpers ==========

    /**
     * Helper method to log whether a form field is visible.
     */
    private void verifyField(String name, boolean isDisplayed) {
        loggerUtils.log(
                isDisplayed
                        ? "✅ " + name + " field is displayed"
                        : "❌ " + name + " field is NOT displayed",
                name + "Field",
                isDisplayed
        );
    }

    /**
     * Helper method to verify that a title matches its expected text.
     */
    private void verifyTitle(String name, String actual, String expected) {
        boolean isMatch = actual.trim().equals(expected.trim());
        loggerUtils.log(
                isMatch
                        ? "✅ " + name + " title matches: " + expected
                        : "❌ " + name + " title mismatch! Got: " + actual + ", expected: " + expected,
                "Verify" + name.replace(" ", "") + "Title",
                isMatch
        );
    }
}
