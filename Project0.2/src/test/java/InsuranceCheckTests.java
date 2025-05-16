import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class InsuranceCheckTests extends Base {

    @Test(description = "TC-055 - Verify car insurance exists on Bituach Haklai site")
    @Description("Checks if a known car license number shows insurance info")
    public void testCarInsuranceCheck() throws Exception {
        try {
            basePage.openMainPage();
            smartLog("✅ Opened main page", "MainPage", true);


            insuranceCheckPage.ScrollToIsTheVehicleInsuredButton();
            smartLog("✅ Scroll to insurance check section", "InsuranceSection", true);


            insuranceCheckPage.ClickIsTheVehicleInsuredButton();
            smartLog("✅ Navigated to insurance check section", "InsuranceSection", true);

            insuranceCheckPage.enterLicenseNumber();
            smartLog("✅ Entered license number", "LicenseInput", true);

            insuranceCheckPage.enterDate();
            smartLog("✅ Entered Date ", "enter Date", true);


            insuranceCheckPage.clickSearch();
            smartLog("✅ Clicked search", "Search", true);

            String actualResult = insuranceCheckPage.getResultText();
            String expectedMessage = "בתאריך 04/11/2024 נמצא ביטוח תקף לרכב שמספרו 23652125 בביטוח חקלאי";

            assertTextEquals(expectedMessage, actualResult, "Check result message presence");

        } catch (Exception e) {
            smartLog("❌ Test 'testCarInsuranceCheck' failed: " + e.getMessage(), "InsuranceCheck", false);
            throw e;
        }
    }
}
