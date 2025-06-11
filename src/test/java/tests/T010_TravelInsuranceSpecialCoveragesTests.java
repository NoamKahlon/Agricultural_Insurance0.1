package tests;

import flows.T010_TravelInsuranceSpecialCoveragesFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T010_TravelInsuranceSpecialCoveragesTests extends Base {

    private T010_TravelInsuranceSpecialCoveragesFlow flow;

    @BeforeMethod
    public void setUp(){
        flow = new T010_TravelInsuranceSpecialCoveragesFlow(loggerUtils,basePage);
    }

    @Test(description = "Verify the full flow and UI behavior of the 'Coverage Calculation' step in online travel insurance")
    public void verifySpecialCoverages() throws Exception {
        try {
            flow.navigateToSpecialCoveragesStep();
            flow.verifyTitleTexts();
            flow.showFinalPrice();
            flow.verifyNavigationButtonsAreVisible();
            flow.verifyCurrentAndPreviousStepsByColor();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC048_Failure", false);
        }
    }
}
