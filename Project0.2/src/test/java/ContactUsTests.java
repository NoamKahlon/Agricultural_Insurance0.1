import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ContactUsTests extends Base {

    // TC-094 - UI - מסך צור קשר
    @Test(description = "TC-094 - Verify that Contact Us page loads with form fields visible")
    @Description("Verify that the 'Contact Us' page is displayed correctly with the full form: name, phone, email, subject, message")
    public void test_TC094_ContactUsPageIsDisplayed() {
        basePage.openMainPage();
        smartLog("✅ Opened main page", "Main Page", true, LogMode.SOFT);

        contactUsPage.clickOnContactUs();
        smartLog("✅ Clicked on Contact Us", "ClickContactUs", true, LogMode.SOFT);

        smartLog(basePage.isDisplayedSafe(contactUsPage.fullNameField) ? "✅ Full Name field is displayed" : "❌ Full Name field is NOT displayed", "FullName", basePage.isDisplayedSafe(contactUsPage.fullNameField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.phoneField) ? "✅ Phone field is displayed" : "❌ Phone field is NOT displayed", "Phone", basePage.isDisplayedSafe(contactUsPage.phoneField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.emailField) ? "✅ Email field is displayed" : "❌ Email field is NOT displayed", "Email", basePage.isDisplayedSafe(contactUsPage.emailField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.subjectField) ? "✅ Subject field is displayed" : "❌ Subject field is NOT displayed", "Subject", basePage.isDisplayedSafe(contactUsPage.subjectField), LogMode.SOFT);
        smartLog(basePage.isDisplayedSafe(contactUsPage.messageField) ? "✅ Message field is displayed" : "❌ Message field is NOT displayed", "Message", basePage.isDisplayedSafe(contactUsPage.messageField), LogMode.SOFT);

        assertAllSoft();
    }

    // TC-095 - UI - בדיקת שדות צור קשר
    @Test(description = "TC-095 - Verify validation messages appear when Contact Us form is submitted empty")
    @Description("Submit Contact Us form with empty fields and validate red error messages appear under all required fields")
    public void test_TC095_ContactFormValidationOnEmptySubmit() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        try {
            contactUsPage.clickOnContactUs();
            contactUsPage.pressSubmit();
            smartLog("Submitted empty form", "EmptySubmit", true, LogMode.SOFT);

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
            smartLog("❌ Failed to validate form is empty: " + e.getMessage(), "EmptyFormValidation", false, LogMode.HARD);
            throw e;
        } finally {
            softAssert.assertAll();
        }
    }

    // TC-096 - UI - מסך אישור השארת פניה
    @Test(description = "TC-096 - Verify successful submission of Contact Us form and display of thank you screen")
    @Description("Submit form with valid details and verify thank you message and Back to Home button are displayed")
    public void test_TC096_SuccessfulContactFormSubmission() throws InterruptedException {
        try {
            contactUsPage.clickOnContactUs();

            basePage.sendKeysSafe(contactUsPage.fullNameField, "יוני חלון");
            basePage.sendKeysSafe(contactUsPage.phoneField, "0501234567");
            basePage.sendKeysSafe(contactUsPage.emailField, "test@example.com");
            basePage.chooseValueFromDropDownMenuSafe(contactUsPage.subjectField, "שירות");
            basePage.sendKeysSafe(contactUsPage.messageField, "הודעת בדיקה - נא לא ליצור קשר בפועל");

            Thread.sleep(2500);
            contactUsPage.pressSubmit();
            smartLog("Form submitted", "FormSubmission", true, LogMode.SOFT);

            boolean thankYouVisible = basePage.isDisplayedSafe(contactUsPage.thankYouText);
            boolean backVisible = basePage.isDisplayedSafe(contactUsPage.backButton);

            smartLog("✅ Thank you message visible: " + thankYouVisible + ", Back button visible: " + backVisible,
                    "ThankYouScreen", thankYouVisible && backVisible, LogMode.SOFT);

        } catch (Exception e) {
            smartLog("❌ Failed to submit form or verify thank you screen: " + e.getMessage(), "FormSubmitError", false, LogMode.HARD);
            throw e;
        }
    }

    // כלי עזר
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
