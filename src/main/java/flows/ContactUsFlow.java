package flows;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import pages.BasePage;
import pages.ContactUsPage;
import pages.SearchDocumentsAndFormsPage;
import utils.ContactUsData;
import utils.LoggerUtils;

public class ContactUsFlow  {

    WebDriver driver;
    BasePage basePage;
    ContactUsPage contactUsPage;
    LoggerUtils loggerUtils;

    public ContactUsFlow(WebDriver driver, BasePage basePage, ContactUsPage contactUsPage, LoggerUtils loggerUtils) {
        this.driver = driver;
        this.basePage = basePage;
        this.contactUsPage = contactUsPage;
        this.loggerUtils = loggerUtils;
    }
    public void clickOnContactUs(){
        contactUsPage.clickOnContactUs();
        loggerUtils.log("✅ Clicked on Contact Us", "ClickContactUs", true, false);
    }

    public void VerifyContactUsFormIsDisplayed() {
        // שדות טופס
        loggerUtils.log(basePage.isDisplayedSafe(contactUsPage.fullNameField) ? "✅ Full Name field is displayed" : "❌ Full Name field is NOT displayed", "", basePage.isDisplayedSafe(contactUsPage.fullNameField), false);
        loggerUtils.log(basePage.isDisplayedSafe(contactUsPage.phoneField) ? "✅ Phone field is displayed" : "❌ Phone field is NOT displayed", "", basePage.isDisplayedSafe(contactUsPage.phoneField), false);
        loggerUtils.log(basePage.isDisplayedSafe(contactUsPage.emailField) ? "✅ Email field is displayed" : "❌ Email field is NOT displayed", "", basePage.isDisplayedSafe(contactUsPage.emailField), false);
        loggerUtils.log(basePage.isDisplayedSafe(contactUsPage.subjectField) ? "✅ Subject field is displayed" : "❌ Subject field is NOT displayed", "", basePage.isDisplayedSafe(contactUsPage.subjectField), false);
        loggerUtils.log(basePage.isDisplayedSafe(contactUsPage.messageField) ? "✅ Message field is displayed" : "❌ Message field is NOT displayed", "", basePage.isDisplayedSafe(contactUsPage.messageField), false);

        // השוואת כותרות של שלושת המרכזים
        verifyTitleText(contactUsPage.salesCenterTitle, ContactUsData.SALES_CENTER_TITLE);
        verifyTitleText(contactUsPage.supportCenterTitle, ContactUsData.CLAIMS_CENTER_TITLE);
        verifyTitleText(contactUsPage.serviceCenterTitle, ContactUsData.SERVICE_CENTER_TITLE);
    }


    private void verifyTitleText(By titleLocator, String expectedText) {
        String actualText = basePage.getText(titleLocator).trim();
        boolean isMatch = actualText.equals(expectedText);
        loggerUtils.log(isMatch ? "✅ Title text matches: " + expectedText : "❌ Title mismatch! Got: " + actualText + ", expected: " + expectedText, "" , isMatch, false);
    }



    // כלי עזר
    public void checkFieldErrorSoft(SoftAssert softAssert, By locator, String fieldName, String expectedError) {
        try {
            WebElement element = driver.findElement(locator);
            String actualError = element.findElement(By.xpath("./following-sibling::span")).getText().trim();
            boolean match = actualError.equals(expectedError);
            loggerUtils.log((match ? "✅" : "❌") + " " + fieldName + " error message is " +
                            (match ? "correct" : "incorrect: expected '" + expectedError + "', got '" + actualError + "'"),
                    fieldName + " Error", match, false);
            softAssert.assertEquals(actualError, expectedError, fieldName + " error mismatch");

        } catch (NoSuchElementException e) {
            loggerUtils.log("❌ Field not found: " + fieldName + " - " + locator.toString(), fieldName + " Error", false, false);
            softAssert.fail("Field not found: " + fieldName);
        } catch (Exception e) {
            loggerUtils.log("❌ Unexpected error while checking: " + fieldName + " - " + e.getMessage(), fieldName + " Error", false, false);
            softAssert.fail("Unexpected error: " + fieldName + " - " + e.getMessage());
        }
    }

    public void checkTextEmpty(By locator, String fieldName) {
        String text = basePage.getTextSafe(locator);
        boolean isEmpty = text.trim().isEmpty();
        loggerUtils.log(isEmpty ? "✅ " + fieldName + " is empty (as expected)" : "❌ " + fieldName + " is not empty", fieldName + " Content", isEmpty, false);
    }




}
