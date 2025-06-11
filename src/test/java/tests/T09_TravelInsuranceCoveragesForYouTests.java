package tests;

import flows.T09_TravelInsuranceCoveragesForYouFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T09_TravelInsuranceCoveragesForYouTests extends Base {

    T09_TravelInsuranceCoveragesForYouFlow flow;

    @BeforeMethod
    public void setFlow(){
        this.flow = new T09_TravelInsuranceCoveragesForYouFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-046: Stage 4 - Completion in Your Path (UI)")
    public void verifyCoveragesForYou() throws Exception {
        try{
            flow.navigateToCoveragesForYouStep();
            flow.verifyCoverages();
            flow.verifyNavigationButtonsAreVisible();
            flow.verifyFinalPriceVisible();
            flow.verifyCurrentAndPreviousStepsByColor();
        }catch (Exception e){
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC046_Failure", false);
        }
    }

    @Test(description = "TC-047: Verify the full flow and UI behavior of the 'Coverage Calculation' step in online travel insurance")
    public void verifyCoverageCalculation() throws Exception {
        try{
            flow.navigateToCoveragesForYouStep();
            flow.verifyPriceUpdatedAfterAddingCoverage();
        }catch (Exception e){
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC047_Failure", false);
        }
    }
}
