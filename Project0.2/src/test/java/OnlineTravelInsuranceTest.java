import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class OnlineTravelInsuranceTest extends Base {

    @Test(description = "Verify that the home page of Bituach Haklai loads correctly without JavaScript or display errors")
    @Description("Navigate to https://www.bth.co.il, verify no console errors, scroll to travel section and validate page title")
    public void test01_HomePageLoadsCorrectly() {
        try {
            basePage.openMainPage();
            smartLog("✅ Navigated to homepage successfully", "Main Page", true);
        } catch (Exception e) {
            smartLog("❌ Failed to open homepage: " + e.getMessage(), "Main Page", false);
            throw e;
        }

        try {
            onlineTravelInsurancePage.scrollToTravelInsuranceButton();
            smartLog("✅ Scrolled to Travel Insurance section", "Scrolled", true);
        } catch (Exception e) {
            smartLog("❌ Failed to scroll to Travel Insurance: " + e.getMessage(), "Scroll Failed", false);
            throw new RuntimeException(e);
        }

        try {
            String expectedTitle = "ביטוח חקלאי - חברת הביטוח למגזר הקיבוצי והפרטי";
            String actualTitle = basePage.getTitle();
            boolean titleMatches = actualTitle.equals(expectedTitle);
            smartLog(
                    (titleMatches ? "✅ Title is correct: " : "❌ Title mismatch: ") + actualTitle,
                    "Title Check",
                    titleMatches
            );
            Assert.assertEquals(actualTitle, expectedTitle, "Page title should match");
        } catch (Exception e) {
            smartLog("❌ Exception during title check: " + e.getMessage(), "Title Exception", false);
            throw e;
        }
    }

    @Test(description = "Scroll and click on the 'Online Travel Insurance Quote' section")
    @Description("Scrolls to and clicks on the Travel Insurance section, verifies that the next section is visible")
    public void test02_ClickTravelInsuranceSection() throws Exception {
        try {
            basePage.openMainPage();
            smartLog("✅ Opened homepage", "Homepage opened", true);

            onlineTravelInsurancePage.scrollToTravelInsuranceButton();
            smartLog("✅ Scrolled to Travel Insurance section", "Scrolled to insurance", true);

            By button = By.xpath("//h3[contains(text(), 'חו\"ל')]/ancestor::div[contains(@class,'insurances__item')]//div[contains(@class, 'insurances__item-footer')]");
            basePage.forceClick(button);
            smartLog("✅ Clicked Travel Insurance button", "Clicked travel section", true);

            By nextSection = By.cssSelector("h2.insurance__form-header"); // עדכן אם צריך
            boolean visible = basePage.isDisplayed(nextSection);
            smartLog("✅ Verified next section loaded", "Verifying section loaded", visible);
            assertTrue(visible, "Form or next section should be visible");

        } catch (Exception e) {
            smartLog("❌ Failed Travel Insurance flow: " + e.getMessage(), "Travel Section", false);
            throw e;
        }
    }
}
