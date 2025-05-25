package tests;

import flows.ContactUsFlow;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.ContactUsData;

public class ContactUsTests extends Base {

    private ContactUsFlow flow;

    @BeforeMethod
    public void setup() {
        this.flow = new ContactUsFlow(contactUsPage, loggerUtils);
        flow.clickOnContactUs();
    }


    @Test(description = "TC-094 - Verify that Contact Us page loads with form fields visible")
    @Description("Verify that the 'Contact Us' page is displayed correctly with the full form: name, phone, email, subject, message")
    public void test_TC094_ContactUsPageIsDisplayed() {
      try {

          flow.VerifyContactUsFormIsDisplayed();

      } catch (Exception e){

          loggerUtils.log("❌ Failed to validate form: " + e.getMessage(), "EmptyFormValidation", false, true);
      }
    }


    @Test(description = "TC-095 - Verify validation messages appear when Contact Us form is submitted empty")
    @Description("Submit Contact Us form with empty fields and validate red error messages appear under all required fields")
    public void test_TC095_ContactFormValidationOnEmptySubmit() throws InterruptedException {
        try {
            flow.VerifyContactUsFormIsDisplayed();

            contactUsPage.pressSubmit();
            loggerUtils.log("✅ Submitted empty form", "EmptySubmit", true, true);

            String expectedError = ContactUsData.EXPECTED_EMPTY_FIELD_ERROR;

            String[] fieldIds = {"fullname", "phone", "email", "subject", "message"};

            for (String fieldId : fieldIds) {
                By errorLocator = By.id("error-" + fieldId);
                String actualError = basePage.getText(errorLocator,false);
                boolean matches = actualError.equals(expectedError);

                loggerUtils.log(matches ? "✅ " + fieldId + " error matches expected" : "❌ " + fieldId +
                        " error mismatch. Expected: '" + expectedError + "', Got: '" + actualError + "'", "", matches, false);
            }

        } catch (Exception e) {
            loggerUtils.log("❌ Failed to validate empty Contact Us form: " + e.getMessage(),
                    "EmptyFormValidation", false, true);
        }
    }


    @Test(description = "TC-096 - Verify successful submission of Contact Us form and display of thank you screen")
    @Description("Submit form with valid details and verify thank you message and Back to Home button are displayed")
    public void test_TC096_SuccessfulContactFormSubmission() throws InterruptedException {
        try {
            flow.VerifyContactUsFormIsDisplayed();

            basePage.sendKeys(contactUsPage.getFullNameField(), ContactUsData.VALID_NAME,false);
            basePage.sendKeys(contactUsPage.getPhoneField(), ContactUsData.VALID_PHONE,false);
            basePage.sendKeys(contactUsPage.getEmailField(), ContactUsData.VALID_EMAIL,false);
            basePage.chooseValueFromDropDownMenuSafe(contactUsPage.getSubjectField(), ContactUsData.VALID_SUBJECT);
            basePage.sendKeys(contactUsPage.getMessageField(), ContactUsData.VALID_MESSAGE,false);

            contactUsPage.pressSubmit();
            loggerUtils.log("✅ Form submitted", "FormSubmission", true, false);

            boolean thankYouMessageIsVisible = basePage.isDisplayed(contactUsPage.getThankYouText(),false);
            boolean backButtonIsVisible = basePage.isDisplayed(contactUsPage.getBackButton(),false);

            loggerUtils.log(thankYouMessageIsVisible ? "✅ Thank you message is visible" : "❌ Thank you message is NOT visible",
                    "ThankYouMessage", thankYouMessageIsVisible, false);

            loggerUtils.log(backButtonIsVisible ? "✅ Back to home button is visible" : "❌ Back to home button is NOT visible",
                    "BackToHomeButton", backButtonIsVisible, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Failed to submit form or verify thank you screen: " + e.getMessage(),
                    "FormSubmitError", false, true);
        }
    }

}
