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
            smartLog("Check main page title", "Homepage Title", actualTitle.equals(expectedTitle), LogMode.HARD);
        } catch (Exception e) {
            smartLog("❌ Failed to load homepage or verify title: " + e.getMessage(), "Homepage Title", false, LogMode.HARD);
            throw e;
        }
    }

    @Test(description = "Verify that the Contact Us page is displayed with the contact form")
    @Description("Checks that the Contact Us page is accessible and the form is visible")
    public void testContactUsPageIsDisplayed() {
        basePage.openMainPage();
        smartLog("✅ Opened main page", "Main Page", true, LogMode.SOFT);

        contactUsPage.clickOnContactUs();
        smartLog("✅ Clicked on Contact Us", "Click Contact Us", true, LogMode.SOFT);

        smartLog(basePage.isDisplayedSafe(contactUsPage.fullNameField) ? "✅ Full Name field is displayed" : "❌ Full Name field is NOT displayed", "Full Name Field", basePage.isDisplayedSafe(contactUsPage.fullNameField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.phoneField) ? "✅ Phone field is displayed" : "❌ Phone field is NOT displayed", "Phone Field", basePage.isDisplayedSafe(contactUsPage.phoneField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.emailField) ? "✅ Email field is displayed" : "❌ Email field is NOT displayed", "Email Field", basePage.isDisplayedSafe(contactUsPage.emailField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.subjectField) ? "✅ Subject field is displayed" : "❌ Subject field is NOT displayed", "Subject Field", basePage.isDisplayedSafe(contactUsPage.subjectField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.messageField) ? "✅ Message field is displayed" : "❌ Message field is NOT displayed", "Message Field", basePage.isDisplayedSafe(contactUsPage.messageField), LogMode.SOFT);

        assertAllSoft();
    }

    @Test(description = "Verify contact form validation when submitted empty")
    @Description("Submit empty form and verify required field error messages")
    public void testContactFormValidationOnEmptySubmit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        try {

            contactUsPage.clickOnContactUs();

            contactUsPage.pressSubmit();
            smartLog("Submitted empty form", "Empty Submit", true, LogMode.SOFT);

            String expectedError = "חובה למלא שדה זה";

            checkTextEmpty(By.id("fullname"), "Full Name");
            checkTextEmpty(By.id("phone"), "Phone");
            checkTextEmpty(By.id("email"), "Email");
            checkTextEmpty(By.id("subject"), "Subject");
            checkTextEmpty(By.id("message"), "Message");

            checkFieldErrorSoft(softAssert, By.id("fullname"), "Full Name", expectedError);
            checkFieldErrorSoft(softAssert, By.id("phone"), "Phone", expectedError);
            checkFieldErrorSoft(softAssert, By.id("email"), "Email", expectedError);
            checkFieldErrorSoft(softAssert, By.id("subject"), "Subject", expectedError);
            checkFieldErrorSoft(softAssert, By.id("message"), "Message", expectedError);

        } catch (Exception e) {
            smartLog("❌ Failed to validate form is empty : " + e.getMessage(), "Empty Form Error", false, LogMode.HARD);
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    @Test(description = "Submit Contact Us form with valid data")
    @Description("Fill form with valid data and verify thank you screen")
    public void testSuccessfulContactFormSubmission() throws InterruptedException {
        try {
            contactUsPage.clickOnContactUs();

            basePage.sendKeysSafe(contactUsPage.fullNameField, "יוני חלון");
            basePage.sendKeysSafe(contactUsPage.phoneField, "0501234567");
            basePage.sendKeysSafe(contactUsPage.emailField, "test@example.com");
            basePage.chooseValueFromDropDownMenuSafe(contactUsPage.subjectField, "שירות");
            basePage.sendKeysSafe(contactUsPage.messageField, "הודעת בדיקה - נא לא ליצור קשר בפועל");

            Thread.sleep(2500);
            contactUsPage.pressSubmit();
            smartLog("Form submitted", "Form Submission", true, LogMode.SOFT);

            boolean thankYouVisible = basePage.isDisplayedSafe(contactUsPage.thankYouText);
            boolean backVisible = basePage.isDisplayedSafe(contactUsPage.backButton);

            smartLog("Verify Thank You message is displayed", "Thank You", thankYouVisible, LogMode.SOFT);
            smartLog("Verify Back to Home button is displayed", "Back Button", backVisible, LogMode.SOFT);

        } catch (Exception e) {
            smartLog("❌ Failed to submit form or verify confirmation: " + e.getMessage(), "Form Submission Error", false, LogMode.HARD);
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
                    fieldName + " Error", match, LogMode.SOFT);
            softAssert.assertEquals(actualError, expectedError, fieldName + " error mismatch");

        } catch (NoSuchElementException e) {
            smartLog("❌ Field not found: " + fieldName + " - " + locator.toString(), fieldName + " Error", false, LogMode.SOFT);
            softAssert.fail("Field not found: " + fieldName);
        } catch (Exception e) {
            smartLog("❌ Unexpected error while checking: " + fieldName + " - " + e.getMessage(), fieldName + " Error", false, LogMode.SOFT);
            softAssert.fail("Unexpected error: " + fieldName + " - " + e.getMessage());
        }
    }

    private void checkTextEmpty(By locator, String fieldName) {
        String text = basePage.getTextSafe(locator);
        boolean isEmpty = text.trim().isEmpty();
        smartLog(isEmpty ? "✅ " + fieldName + " is empty (as expected)" : "❌ " + fieldName + " is not empty", fieldName + " Content", isEmpty, LogMode.SOFT);
    }
}
