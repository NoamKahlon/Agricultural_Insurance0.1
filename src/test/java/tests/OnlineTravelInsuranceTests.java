
package tests;
import flows.OnlineTravelInsuranceFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.OnlineTravelInsuranceData;

public class OnlineTravelInsuranceTests extends Base {


    OnlineTravelInsuranceFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new OnlineTravelInsuranceFlow(driver,basePage,onlineTravelInsurancePage,loggerUtils);
    }

    @Test(description = "TC-038 - Verify landing on online travel insurance flow")
    @Description("Open insurance flow, verify title and main intro block")
    public void test_StartInsuranceFlow() throws Exception {
        try {
            flow.initTravelFlow();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "StartFlowFail", false, true);
            throw e;
        }
    }


    @Test(description = "TC-039 - Verify previous insurance question appears")
    @Description("Click continue and verify previous insurance question and buttons")
    public void test_PreviousInsuranceQuestion() throws Exception {
        try {
            flow.navigateToDestinationSelectionStep();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "PreviousQuestionFail", false, true);
            throw e;
        }
    }


    @Test(description = "TC-040 - Verify passengers from Israel question appears")
    @Description("Continue twice and verify question and both options")
    public void test_PassengersFromIsraelQuestion() throws Exception {
        try {
            flow.navigateToPurchasedInsuranceQuestion();

            onlineTravelInsurancePage.clickFirstTimeOptionButton();
            flow.verifyPassengersFromIsraelQuestionStep();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "PassengersQuestionFail", false, true);
            throw e;
        }
    }



    @Test(description = "TC-041 - UI - Verify quote suggestion flow with previous insurance selected")
    @Description("Navigate to online travel insurance, select 'previously purchased insurance' and verify quote options page appears")
    public void test_QuoteWithPreviousInsurance() throws Exception {
        try {
            flow.navigateToPurchasedInsuranceQuestion();

            onlineTravelInsurancePage.clickAlreadyPurchasedOptionButton();
            loggerUtils.log("✅ Clicked on 'Already Purchased' option", "ClickedAlreadyPurchased", true, true);

            boolean welcomeTextDisplayed = !onlineTravelInsurancePage.getHappyToSeeYouAgainText().isEmpty();

            loggerUtils.log(welcomeTextDisplayed ? "✅ 'Welcome back' text is visible: '" : "❌ 'Welcome back' text not found",
                    "", welcomeTextDisplayed, false);

            boolean emailFieldDisplayed = onlineTravelInsurancePage.isPhoneFieldDisplayed();
            loggerUtils.log(emailFieldDisplayed ? "✅ phone field is visible" : "❌ phone field not visible",
                    "", emailFieldDisplayed, false);

            boolean idFieldDisplayed = onlineTravelInsurancePage.isIdFieldDisplayed(); // שנה לפי locator האמיתי אם שונה
            loggerUtils.log(idFieldDisplayed ? "✅ ID field is visible" : "❌ ID field not visible",
                    "", idFieldDisplayed, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC041_Failure", false, true);
            throw e;
        }
    }




    @Test(description = "TC-042 - UI - Block flow when not all passengers depart from Israel")
    @Description("Verify that the process is blocked when user selects 'not all passengers are departing from Israel'")
    public void test_BlockFlowWhenPassengersNotFromIsrael() throws Exception {
        try {

            flow.navigateToDestinationSelectionStep();
            onlineTravelInsurancePage.clickPassengersFromIsraelNoOption();

            loggerUtils.log("✅ Clicked on 'No' for passengers from Israel", "ClickedNoPassengers", true, true);

            boolean errorDisplayed = onlineTravelInsurancePage.isStopProcessErrorDisplayed();
            loggerUtils.log(errorDisplayed ? "✅ Error message visible" : "❌ Error message missing", "", errorDisplayed, false);

            boolean backButtonDisplayed = onlineTravelInsurancePage.isBackToHomeButtonDisplayed();
            loggerUtils.log(backButtonDisplayed ? "✅ 'Back to homepage' button visible" : "❌ 'Back to homepage' button missing", "", backButtonDisplayed, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false, true);
        }
    }

    @Test(description = "TC-043 - Verify online travel insurance quote flow is accessible and displayed correctly")
    @Description("Navigates to the online travel insurance quote page on Bituach Haklai site, verifies correct UI flow and elements for stage 1: destination selection")
    public void TC043() {
        try {

            flow.navigateToDestinationSelectionStep();
            flow.verifyDestinationSelectionStep();

            String currentStep = onlineTravelInsurancePage.verifyCurrentAndPreviousStepsByColor();
            boolean isCorrectStep = currentStep.equals(OnlineTravelInsuranceData.STEP_DESTINATION);
            loggerUtils.log(isCorrectStep ?
                    "✅ Current step is 'לאן נוסעים?'" : "❌ Current step is '" + currentStep + "' instead of 'לאן נוסעים?'",
                    "StepVerification", isCorrectStep, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false, true);
        }
    }

    @Test(description = "TC-044 - Verify date selection step in travel insurance quote flow")
    @Description("Navigates to the online travel insurance quote on Bituach Haklai site, completes destination step, and verifies correct UI and actions for stage 2: date selection (departure and return)")
    public void TC044() {
        try {

            flow.navigateToDestinationSelectionStep();
            flow.verifyDestinationSelectionStep();
            onlineTravelInsurancePage.clickEuropeCheckBox();
            onlineTravelInsurancePage.clickcontinueToNextStepButton();

            boolean isStartVisible = onlineTravelInsurancePage.verifyStartDateFieldDisplayed();
            loggerUtils.log(isStartVisible ? "✅ Start date field is visible" : "❌ Start date field is NOT visible",
                    "", isStartVisible, false);

            boolean isEndVisible = onlineTravelInsurancePage.verifyEndDateFieldDisplayed();
            loggerUtils.log(isEndVisible ? "✅ End date field is visible" : "❌ End date field is NOT visible",
                    "", isEndVisible, false);

            boolean backButtonVisible = onlineTravelInsurancePage.isBackButtonVisible();
            loggerUtils.log(backButtonVisible ? "✅ 'Back' button is visible" : "❌ 'Back' button is missing", "", backButtonVisible, false);

            boolean continueButtonVisible = onlineTravelInsurancePage.isContinueButtonVisible();
            loggerUtils.log(continueButtonVisible ? "✅ 'Continue' button is visible" : "❌ 'Continue' button is missing", "", continueButtonVisible, false);

            String stepsSummary = onlineTravelInsurancePage.verifyCurrentAndPreviousStepsByColor();
            loggerUtils.log("✅ " + stepsSummary, "", true, false);

        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false, true);
        }
    }
}