import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class CheckingTheExistenceOfCarInsuranceTests extends Base {

    @Test(description = "TC-055 - Verify car insurance exists on Bituach Haklai site")
    @Description("Checks if a known car license number shows insurance info")
    public void testCarInsuranceCheck() throws Exception {
        try {


            checkingTheExistenceOfCarInsurancePage.ScrollToIsTheVehicleInsuredButton();
            smartLog("✅ Scroll to insurance check section", "InsuranceSection", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.ClickIsTheVehicleInsuredButton();
            smartLog("✅ Navigated to insurance check section", "InsuranceSection", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.enterLicenseNumber();
            smartLog("✅ Entered license number", "LicenseInput", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.enterDate();
            smartLog("✅ Entered Date", "enter Date", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.clickSearch();
            smartLog("✅ Clicked search", "Search", true, LogMode.SOFT);

            String actualResult = checkingTheExistenceOfCarInsurancePage.getSuccessText();
            String expectedMessage = "בתאריך 04/11/2024 נמצא ביטוח תקף לרכב שמספרו 23652125 בביטוח חקלאי";

            assertTextEquals(expectedMessage, actualResult, "Check result message presence");

            boolean isSuccessTextGreen = checkingTheExistenceOfCarInsurancePage.isSuccessTextGreen(); // לוודא שיש בדיקה לצבע

            smartLog(isSuccessTextGreen ? "✅ Success message is displayed in green" : "❌ Success message is not green",
                    "SuccessColor", isSuccessTextGreen, LogMode.HARD);

            checkingTheExistenceOfCarInsurancePage.isSuccessTextGreen222();

        } catch (Exception e) {
            smartLog("❌ Test 'testCarInsuranceCheck' failed: " + e.getMessage(), "InsuranceCheck", false, LogMode.HARD);
            throw e;
        }finally {
            assertAllSoft();
        }
    }




    @Test(description = "TC-054 - Verify error message is shown for uninsured vehicle")
    @Description("Navigates to insurance check, enters an uninsured license number, and verifies the red error message is displayed")
    public void testCarInsuranceNotFoundErrorDisplayed() throws Exception {
        try {


            checkingTheExistenceOfCarInsurancePage.ScrollToIsTheVehicleInsuredButton();
            smartLog("✅ Scrolled to insurance check section", "InsuranceSection", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.ClickIsTheVehicleInsuredButton();
            smartLog("✅ Navigated to insurance check section", "InsuranceSection", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.enterInvalidLicenseNumber(); // מספר רכב שאין לו ביטוח
            smartLog("✅ Entered uninsured license number", "LicenseInput", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.enterDate();
            smartLog("✅ Entered Date", "DateInput", true, LogMode.SOFT);

            checkingTheExistenceOfCarInsurancePage.clickSearch();
            smartLog("✅ Clicked search", "SearchClick", true, LogMode.SOFT);

            String expectedError = "לא נמצא ביטוח בתוקף עבור הנתונים שהוזנו";
            String actualError = checkingTheExistenceOfCarInsurancePage.getErrorText();

            assertTextEquals(actualError, expectedError, "Verify red error message");

            boolean isErrorRed = checkingTheExistenceOfCarInsurancePage.isErrorTextRed(); // לוודא שיש בדיקה לצבע
            smartLog(isErrorRed ? "✅ Error message is displayed in red" : "❌ Error message is not red", "ErrorColor", isErrorRed, LogMode.SOFT);


            checkingTheExistenceOfCarInsurancePage.isErrorTextRed222();




        } catch (Exception e) {
            smartLog("❌ Test 'testCarInsuranceNotFoundErrorDisplayed' failed: " + e.getMessage(), "UninsuredVehicleCheck", false, LogMode.HARD);
            throw e;
        }finally {
            assertAllSoft();
        }
    }


}
