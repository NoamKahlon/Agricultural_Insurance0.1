package tests;

import flows.ContactUsFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactUsTests extends Base {

    private ContactUsFlow flow;

    @BeforeMethod
    public void setup() {
        this.flow = new ContactUsFlow(loggerUtils, basePage);
        flow.clickOnContactUs();
    }

    @Test(description = "TC-094 - Verify that Contact Us page loads with form fields visible")
    public void test_TC094_ContactUsPageIsDisplayed() {
        try {
            flow.verifyContactUsFormIsDisplayed();
        } catch (Exception e) {
            loggerUtils.log("❌ Failed to validate form: " + e.getMessage(), "FormVisibility", false);
        }
    }

    @Test(description = "TC-095 - Verify validation messages appear when Contact Us form is submitted empty")
    public void test_TC095_ContactFormValidationOnEmptySubmit() {
        try {
            flow.verifyContactUsFormIsDisplayed();
            flow.submitEmptyFormAndVerifyValidationMessages();
        } catch (Exception e) {
            loggerUtils.log("❌ Failed to validate empty Contact Us form: " + e.getMessage(),
                    "EmptyFormValidation", false);
        }
    }

    @Test(description = "TC-096 - Verify successful submission of Contact Us form and display of thank you screen")
    @Description("Submit form with valid details and verify thank you message and Back to Home button are displayed")
    public void test_TC096_SuccessfulContactFormSubmission() throws InterruptedException {
        try {
            flow.verifyContactUsFormIsDisplayed();
            flow.fillAndSubmitContactForm();
            flow.verifyThankYouScreenDisplayed();
        } catch (Exception e) {
            loggerUtils.log("❌ Failed to submit form or verify thank you screen: " + e.getMessage(),
                    "FormSubmitError", false);
        }
    }
}
