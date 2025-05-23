package tests;


import flows.ContactUsFlow;
import flows.SearchDocumentsAndFormsFlow;
import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.ContactUsPage;
import utils.ContactUsData;
import utils.LoggerUtils;

public class ContactUsTests extends Base {

    ContactUsFlow flow;

    @BeforeMethod
    public void setup() {
        this.flow = new ContactUsFlow(driver, basePage, contactUsPage, loggerUtils);
        flow.clickOnContactUs();
    }

    // TC-094 - UI - מסך צור קשר
    @Test(description = "TC-094 - Verify that Contact Us page loads with form fields visible")
    @Description("Verify that the 'Contact Us' page is displayed correctly with the full form: name, phone, email, subject, message")
    public void test_TC094_ContactUsPageIsDisplayed() {
      try {
          flow.VerifyContactUsFormIsDisplayed();
      } catch (Exception e){
          loggerUtils.log("❌ Failed to validate form: " + e.getMessage(), "EmptyFormValidation", false, true);
          throw e;
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

            for (int i = 0; i < fieldIds.length; i++) {
                String fieldId = fieldIds[i];

                By errorLocator = By.id("error-" + fieldId);
                String actualError = basePage.getTextSafe(errorLocator);
                boolean matches = actualError.equals(expectedError);

                loggerUtils.log(matches ? "✅ " + fieldId + " error matches expected" : "❌ " + fieldId +
                        " error mismatch. Expected: '" + expectedError + "', Got: '" + actualError + "'", "" , matches, false);
            }

        } catch (Exception e) {
            loggerUtils.log("❌ Failed to validate empty Contact Us form: " + e.getMessage(), "EmptyFormValidation", false, true);
            throw e;
        } finally {
            loggerUtils.softAssert.assertAll();
        }
    }


    @Test(description = "TC-096 - Verify successful submission of Contact Us form and display of thank you screen")
    @Description("Submit form with valid details and verify thank you message and Back to Home button are displayed")
    public void test_TC096_SuccessfulContactFormSubmission() throws InterruptedException {
        try {
            flow.VerifyContactUsFormIsDisplayed();

            basePage.sendKeysSafe(contactUsPage.fullNameField, ContactUsData.VALID_NAME);
            basePage.sendKeysSafe(contactUsPage.phoneField, ContactUsData.VALID_PHONE);
            basePage.sendKeysSafe(contactUsPage.emailField, ContactUsData.VALID_EMAIL);
            basePage.chooseValueFromDropDownMenuSafe(contactUsPage.subjectField, ContactUsData.VALID_SUBJECT);
            basePage.sendKeysSafe(contactUsPage.messageField, ContactUsData.VALID_MESSAGE);

            contactUsPage.pressSubmit();
            loggerUtils.log("✅ Form submitted", "FormSubmission", true, false);

            boolean thankYouMessageIsVisible = basePage.isDisplayedSafe(contactUsPage.thankYouText);
            boolean backButtonIsVisible = basePage.isDisplayedSafe(contactUsPage.backButton);

            loggerUtils.log((thankYouMessageIsVisible ? "✅" : "❌") +
                            " Thank you message visible", "", thankYouMessageIsVisible, false);

            loggerUtils.log((backButtonIsVisible ? "✅" : "❌") +
                            " Back button is visible", "", backButtonIsVisible, false);


//            loggerUtils.assertWithLog("Thank you message is visible", thankYouMessageIsVisible, true);
//            loggerUtils.assertWithLog("Back button is visible", backButtonIsVisible, false);


            //softAssert.assertTrue(false, "🔴 SoftAssert test failure check");


//            loggerUtils.assertWithLog("❌ Thank you message is not visible",thankYouMessageIsVisible,false);
//            loggerUtils.assertWithLog("❌ Back button is not visible",backButtonIsVisible,false);
//
//


        } catch (Exception e) {
            loggerUtils.log("❌ Failed to submit form or verify thank you screen: " + e.getMessage(), "FormSubmitError", false, true);
            throw e;
        }finally {
            softAssert.assertAll();
        }
    }


}
