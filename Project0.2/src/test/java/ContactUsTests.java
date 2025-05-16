import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactUsTests extends Base {

    @Test(description = "Verify that the home page loads without errors")
    @Description("Checks that the main page opens successfully and the title is as expected")
    public void testHomePageLoadsCorrectly() {
        try {
            basePage.openMainPage();
            String actualTitle = contactUsPage.getTitle();
            String expectedTitle = "ביטוח חקלאי - חברת הביטוח למגזר הקיבוצי והפרטי";
            assertTextEquals(actualTitle, expectedTitle, "Check main page title");
        } catch (Exception e) {
            smartLog("❌ Failed to load homepage or verify title: " + e.getMessage(), "Homepage Title", false);
            throw e;
        }
    }

    @Test(description = "Verify that the Contact Us page is displayed with the contact form")
    @Description("Checks that the Contact Us page is accessible and the form is visible")
    public void testContactUsPageIsDisplayed() {
        basePage.openMainPage();
        logStep("✅ Opened main page", "Main Page", true);

        contactUsPage.clickOnContactUs();
        logStep("✅ Clicked on Contact Us", "Click Contact Us", true);

        logStep(basePage.isDisplayedSafe(contactUsPage.fullNameField) ? "✅ Full Name field is displayed" : "❌ Full Name field is NOT displayed", "Full Name Field", basePage.isDisplayedSafe(contactUsPage.fullNameField));
        logStep(basePage.isDisplayedSafe(contactUsPage.phoneField) ? "✅ Phone field is displayed" : "❌ Phone field is NOT displayed", "Phone Field", basePage.isDisplayedSafe(contactUsPage.phoneField));
        logStep(basePage.isDisplayedSafe(contactUsPage.emailField) ? "✅ Email field is displayed" : "❌ Email field is NOT displayed", "Email Field", basePage.isDisplayedSafe(contactUsPage.emailField));
        logStep(basePage.isDisplayedSafe(contactUsPage.subjectField) ? "✅ Subject field is displayed" : "❌ Subject field is NOT displayed", "Subject Field", basePage.isDisplayedSafe(contactUsPage.subjectField));
        logStep(basePage.isDisplayedSafe(contactUsPage.messageField) ? "✅ Message field is displayed" : "❌ Message field is NOT displayed", "Message Field", basePage.isDisplayedSafe(contactUsPage.messageField));

        assertAll();
    }

    @Test(description = "Verify contact form validation when submitted empty")
    @Description("Submit empty form and verify required field error messages")
    public void testContactFormValidationOnEmptySubmit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        try {
            basePage.openMainPage();

            contactUsPage.clickOnContactUs();

//            Map<String, By> contactFormFields = new HashMap<>();
//            contactFormFields.put("Full Name", contactUsPage.fullNameField);
//            contactFormFields.put("Phone", contactUsPage.phoneField);
//            contactFormFields.put("Email", contactUsPage.emailField);
//            contactFormFields.put("Subject", contactUsPage.subjectField);
//            contactFormFields.put("Message", contactUsPage.messageField);
//
//            List<String> missing = contactUsPage.getMissingElements(contactFormFields);
//
//            for (String field : contactFormFields.keySet()) {
//                boolean found = !missing.contains(field);
//                smartLog((found ? "✅" : "❌") + " " + field + " is " + (found ? "visible" : "missing"), field, found);
//                softAssert.assertTrue(found, field + " is missing");
//            }

            contactUsPage.pressSubmit();
            smartLog("Submitted empty form", "Empty Submit", true);

            String expectedError = "חובה למלא שדה זה";


            String fullNameText = basePage.getTextSafe(By.id("fullname"));
            boolean isFullNameEmpty = fullNameText.trim().isEmpty();
            logStep(isFullNameEmpty ? "✅ Full Name is empty (as expected)" : "❌ Full Name is not empty",
                    "Full Name Content", isFullNameEmpty);

            String phoneText = basePage.getTextSafe(By.id("phone"));
            boolean isPhoneEmpty = phoneText.trim().isEmpty();
            logStep(isPhoneEmpty ? "✅ Phone is empty (as expected)" : "❌ Phone is not empty",
                    "Phone Content", isPhoneEmpty);

            String emailText = basePage.getTextSafe(By.id("email"));
            boolean isEmailEmpty = emailText.trim().isEmpty();
            logStep(isEmailEmpty ? "✅ Email is empty (as expected)" : "❌ Email is not empty",
                    "Email Content", isEmailEmpty);

            String subjectText = basePage.getTextSafe(By.id("subject"));
            boolean isSubjectEmpty = subjectText.trim().isEmpty();
            logStep(isSubjectEmpty ? "✅ Subject is empty (as expected)" : "❌ Subject is not empty",
                    "Subject Content", isSubjectEmpty);

            String messageText = basePage.getTextSafe(By.id("message"));
            boolean isMessageEmpty = messageText.trim().isEmpty();
            logStep(isMessageEmpty ? "✅ Message is empty (as expected)" : "❌ Message is not empty",
                    "Message Content", isMessageEmpty);


            checkFieldErrorSoft(softAssert, By.id("fullname"), "Full Name", expectedError);
            checkFieldErrorSoft(softAssert, By.id("phone"), "Phone", expectedError);
            checkFieldErrorSoft(softAssert, By.id("email"), "Email", expectedError);
            checkFieldErrorSoft(softAssert, By.id("subject"), "Subject", expectedError);
            checkFieldErrorSoft(softAssert, By.id("message"), "Message", expectedError);

        } catch (Exception e) {
            smartLog("❌ Failed to validate form is empty : " + e.getMessage(), "Empty Form Error", false);
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(description = "Submit Contact Us form with valid data")
    @Description("Fill form with valid data and verify thank you screen")
    public void testSuccessfulContactFormSubmission() throws InterruptedException {
        try {
            basePage.openMainPage();
            contactUsPage.clickOnContactUs();

            basePage.sendKeysSafe(contactUsPage.fullNameField, "יוני חלון");
            basePage.sendKeysSafe(contactUsPage.phoneField, "0501234567");
            basePage.sendKeysSafe(contactUsPage.emailField, "test@example.com");

            basePage.chooseValueFromDropDownMenuSafe(contactUsPage.subjectField, "שירות");

            basePage.sendKeysSafe(contactUsPage.messageField, "הודעת בדיקה - נא לא ליצור קשר בפועל");

            Thread.sleep(2500);
            contactUsPage.pressSubmit();
            smartLog("Form submitted", "Form Submission", true);

            boolean thankYouVisible = basePage.isDisplayedSafe(contactUsPage.thankYouText);
            boolean backVisible = basePage.isDisplayedSafe(contactUsPage.backButton);

            smartLog("Verify Thank You message is displayed", "Thank You", thankYouVisible);
            smartLog("Verify Back to Home button is displayed", "Back Button", backVisible);

        } catch (Exception e) {
            smartLog("❌ Failed to submit form or verify confirmation: " + e.getMessage(), "Form Submission Error", false);
            throw e;
        }
    }

    public void checkFieldErrorSoft(SoftAssert softAssert, By locator, String fieldName, String expectedError) {
        try {
            WebElement element = driver.findElement(locator);
            String actualError = element.findElement(By.xpath("./following-sibling::span")).getText().trim();

            boolean match = actualError.equals(expectedError);
            smartLog((match ? "✅" : "❌") + " " + fieldName + " error message is " +
                            (match ? "correct" : "incorrect: expected '" + expectedError + "', got '" + actualError + "'"),
                    fieldName + " Error", match);
            softAssert.assertEquals(actualError, expectedError, fieldName + " error mismatch");

        } catch (NoSuchElementException e) {
            smartLog("❌ Field not found: " + fieldName + " - " + locator.toString(), fieldName + " Error", false);
            softAssert.fail("Field not found: " + fieldName);
        } catch (Exception e) {
            smartLog("❌ Unexpected error while checking: " + fieldName + " - " + e.getMessage(), fieldName + " Error", false);
            softAssert.fail("Unexpected error: " + fieldName + " - " + e.getMessage());
        }
    }

    private void checkFieldError(By locator, String fieldName, String expectedError) {
        try {
            String actualError = contactUsPage.getFieldErrorText(locator);
            assertTextEquals(actualError, expectedError, fieldName + " error validation");
        } catch (Exception e) {
            smartLog("❌ Missing or incorrect error for field: " + fieldName, "" + fieldName, false);
        }
    }
}