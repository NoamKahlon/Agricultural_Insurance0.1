package flows;

import pages.BasePage;
import pages.CheckingTheExistenceOfCarInsurancePage;
import utils.CheckingTheExistenceOfCarInsuranceData;
import utils.LoggerUtils;

/**
 * Flow class for verifying car insurance existence using license number and date.
 * Covers valid and invalid search flows and message validations.
 */
public class CheckingTheExistenceOfCarInsuranceFlow {

    // ========== Members ==========
    private final CheckingTheExistenceOfCarInsurancePage checkingTheExistenceOfCarInsurancePage;
    private final LoggerUtils loggerUtils;

    /**
     * Constructor for the flow class.
     * @param basepage BasePage instance for shared actions
     * @param logger LoggerUtils instance for logging
     */
    public CheckingTheExistenceOfCarInsuranceFlow(BasePage basepage, LoggerUtils logger) {
        this.checkingTheExistenceOfCarInsurancePage = new CheckingTheExistenceOfCarInsurancePage(basepage);
        this.loggerUtils = logger;
    }

    // ========== Actions ==========

    /**
     * Scrolls to and clicks the car insurance checking section.
     */
    public void navigateToCheckingTheExistenceOfCarInsurance() throws Exception {
        checkingTheExistenceOfCarInsurancePage.scrollToCheckingTheExistenceOfCarInsurance();
        loggerUtils.log("✅ Scrolled to insurance check section", "InsuranceSection", true);

        checkingTheExistenceOfCarInsurancePage.clickCheckingTheExistenceOfCarInsurance();
        loggerUtils.log("✅ Clicked insurance check button", "InsuranceSection", true);
    }

    /**
     * Enters a valid or invalid license number and the date, then clicks search.
     * @param validCarNumber whether to use a valid license number
     */
    public void searchCarNumberAndDate(boolean validCarNumber) throws InterruptedException {
        if (validCarNumber) {
            checkingTheExistenceOfCarInsurancePage.enterLicenseNumber();
            loggerUtils.log("✅ Entered valid license number", "LicenseInput", true);
        } else {
            checkingTheExistenceOfCarInsurancePage.enterInvalidLicenseNumber();
            loggerUtils.log("✅ Entered uninsured license number", "LicenseInput", true);
        }

        checkingTheExistenceOfCarInsurancePage.enterDate();
        loggerUtils.log("✅ Entered date", "DateInput", true);

        checkingTheExistenceOfCarInsurancePage.clickSearch();
        loggerUtils.log("✅ Clicked Search button", "SearchClick", true);
    }

    // ========== Verifications ==========

    /**
     * Verifies the error message and its color when an invalid license number is submitted.
     */
    public void verifyInsuranceNotFoundErrorMessage() {
        String expectedError = CheckingTheExistenceOfCarInsuranceData.INVALID_LICENSE_ERROR_TEXT;
        String actualError = checkingTheExistenceOfCarInsurancePage.getErrorText();

        boolean isMatch = actualError.equals(expectedError);
        loggerUtils.log(
                isMatch
                        ? "✅ Red error message is correct: " + actualError
                        : "❌ Red error message is incorrect! Expected: " + expectedError + ", Got: " + actualError,
                "VerifyErrorMessage",
                isMatch
        );

        boolean isErrorRed = checkingTheExistenceOfCarInsurancePage.isErrorTextRed();
        loggerUtils.log(
                isErrorRed
                        ? "✅ Error message is red"
                        : "❌ Error message is not red",
                "ErrorColor",
                isErrorRed
        );
    }

    /**
     * Verifies the success message and its color when a valid license number is submitted.
     */
    public void verifyInsuranceFoundMessage() {
        String actualResult = checkingTheExistenceOfCarInsurancePage.getSuccessText();
        String expectedMessage = CheckingTheExistenceOfCarInsuranceData.VALID_LICENSE_SUCCESS_TEXT;

        boolean isMatch = actualResult.equals(expectedMessage);
        loggerUtils.log(
                isMatch
                        ? "✅ Success message is correct: " + actualResult
                        : "❌ Success message is incorrect! Expected: " + expectedMessage + ", Got: " + actualResult,
                "VerifySuccessMessage",
                isMatch
        );

        boolean isSuccessTextGreen = checkingTheExistenceOfCarInsurancePage.isSuccessTextGreen();
        loggerUtils.log(
                isSuccessTextGreen
                        ? "✅ Success message is green"
                        : "❌ Success message is not green",
                "SuccessColor",
                isSuccessTextGreen
        );
    }
}
