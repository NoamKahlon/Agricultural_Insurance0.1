package tests;

import flows.CheckingTheExistenceOfCarInsuranceFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CheckingTheExistenceOfCarInsuranceData;

public class CheckingTheExistenceOfCarInsuranceTests extends Base {

    private CheckingTheExistenceOfCarInsuranceFlow flow;

    @BeforeMethod
    public void setUp() throws Exception {
        this.flow = new CheckingTheExistenceOfCarInsuranceFlow(checkingTheExistenceOfCarInsurancePage,loggerUtils);
        flow.nevigateToCheckingTheExistenceOfCarInsurance();
    }



    @Test(description = "TC-054 - Verify error message is shown for uninsured vehicle")
    @Description("Navigates to insurance check, enters an uninsured license number, and verifies the red error message is displayed")
    public void verifyInvalidCarInsuranceErrorDisplayed(){
        try {

            flow.searchCarNumberAndDate(false);

            String expectedError = CheckingTheExistenceOfCarInsuranceData.INVALID_LICENSE_ERROR_TEXT;
            String actualError = checkingTheExistenceOfCarInsurancePage.getErrorText();

            boolean isMatch = actualError.equals(expectedError);
            loggerUtils.log(isMatch ? "✅ Red error message is correct: " + actualError : "❌ Red error message is wrong! Expected: " + expectedError + ", Actual: " + actualError, "Verify red error message", isMatch, true);

            boolean isErrorRed = checkingTheExistenceOfCarInsurancePage.isErrorTextRed(); // לוודא שיש בדיקה לצבע
            loggerUtils.log(isErrorRed ? "✅ Error message is displayed in red" : "❌ Error message is not red", "ErrorColor", isErrorRed, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test 'testCarInsuranceNotFoundErrorDisplayed' failed: " + e.getMessage(),
                    "UninsuredVehicleCheck", false, true);
        }
    }

    @Test(description = "TC-055 - Verify valid car insurance exists")
    @Description("Checks if a known car license number shows insurance info")
    public void  verifyValidCarInsurance(){
        try {

            flow.searchCarNumberAndDate(true);

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
