package tests;

import flows.CheckingTheExistenceOfCarInsuranceFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CheckingTheExistenceOfCarInsuranceData;
import utils.ContactUsData;

public class CheckingTheExistenceOfCarInsuranceTests extends Base {

    CheckingTheExistenceOfCarInsuranceFlow flow;

//    public CheckingTheExistenceOfCarInsuranceTests(CheckingTheExistenceOfCarInsuranceFlow flow) {
//        this.flow = flow;
//    }

    @BeforeMethod
    public void setUp() throws Exception {
        this.flow = new CheckingTheExistenceOfCarInsuranceFlow(checkingTheExistenceOfCarInsurancePage,basePage,loggerUtils);
        flow.nevigateToCheckingTheExistenceOfCarInsurance();
    }



    @Test(description = "TC-054 - Verify error message is shown for uninsured vehicle")
    @Description("Navigates to insurance check, enters an uninsured license number, and verifies the red error message is displayed")
    public void verifyInvalidCarInsuranceErrorDisplayed(){
        try {


//            checkingTheExistenceOfCarInsurancePage.ScrollToCheckingTheExistenceOfCarInsurance();
//            loggerUtils.log("✅ Scrolled to insurance check section", "InsuranceSection", true, false);
//
//            checkingTheExistenceOfCarInsurancePage.ClickCheckingTheExistenceOfCarInsurance();
//            loggerUtils.log("✅ Navigated to insurance check section", "InsuranceSection", true, false);

            flow.searchCarNumberAndDate(false);

//            checkingTheExistenceOfCarInsurancePage.enterInvalidLicenseNumber(); // מספר רכב שאין לו ביטוח
//            loggerUtils.log("✅ Entered uninsured license number", "LicenseInput", true, false);
//
//            checkingTheExistenceOfCarInsurancePage.enterDate();
//            loggerUtils.log("✅ Entered Date", "DateInput", true, false);
//
//            checkingTheExistenceOfCarInsurancePage.clickSearch();
//            loggerUtils.log("✅ Clicked search", "SearchClick", true, false);

            String expectedError = CheckingTheExistenceOfCarInsuranceData.INVALID_LICENSE_ERROR_TEXT;
            String actualError = checkingTheExistenceOfCarInsurancePage.getErrorText();


            boolean isMatch = actualError.equals(expectedError);
            loggerUtils.log(isMatch ? "✅ Red error message is correct: " + actualError : "❌ Red error message is wrong! Expected: " + expectedError + ", Actual: " + actualError, "Verify red error message", isMatch, true);

            boolean isErrorRed = checkingTheExistenceOfCarInsurancePage.isErrorTextRed(); // לוודא שיש בדיקה לצבע
            loggerUtils.log(isErrorRed ? "✅ Error message is displayed in red" : "❌ Error message is not red", "ErrorColor", isErrorRed, false);




        } catch (Exception e) {
            loggerUtils.log("❌ Test 'testCarInsuranceNotFoundErrorDisplayed' failed: " + e.getMessage(), "UninsuredVehicleCheck", false, true);

        }finally {
            loggerUtils.softAssert.assertAll();
        }
    }

    @Test(description = "TC-055 - Verify valid car insurance exists")
    @Description("Checks if a known car license number shows insurance info")
    public void  verifyValidCarInsurance(){
        try {

            flow.searchCarNumberAndDate(true);


//            checkingTheExistenceOfCarInsurancePage.enterLicenseNumber();
//            loggerUtils.log("✅ Entered license number", "LicenseInput", true, false);
//
//            checkingTheExistenceOfCarInsurancePage.enterDate();
//            loggerUtils.log("✅ Entered Date", "enter Date", true, false);
//
//            checkingTheExistenceOfCarInsurancePage.clickSearch();
//            loggerUtils.log("✅ Clicked search", "Search", true, false);

            String actualResult = checkingTheExistenceOfCarInsurancePage.getSuccessText();
            String expectedMessage = CheckingTheExistenceOfCarInsuranceData.VALID_LICENSE_SUCCESS_TEXT;

            boolean isMatch = actualResult.equals(expectedMessage);
            loggerUtils.log(isMatch ? "✅ Green message is correct: " + actualResult : "❌ Green message is wrong! Expected: " + expectedMessage + ", Actual: " + actualResult, "Verify Green message", isMatch, true);




            boolean isSuccessTextGreen = checkingTheExistenceOfCarInsurancePage.isSuccessTextGreen();

            loggerUtils.log(isSuccessTextGreen ? "✅ Success message is displayed in green" : "❌ Success message is not green",
                    "SuccessColor", isSuccessTextGreen, true);


        } catch (Exception e) {
            loggerUtils.log("❌ Test 'testCarInsuranceCheck' failed: " + e.getMessage(), "InsuranceCheck", false, true);
            throw e;
        }
    }
}
