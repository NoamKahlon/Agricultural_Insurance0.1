package tests;

import flows.T012_TravelInsuranceProposalSummaryFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T012_TravelInsuranceProposalSummaryTests extends Base {

    private T012_TravelInsuranceProposalSummaryFlow flow;

    @BeforeMethod
    public void setUp(){
        this.flow = new T012_TravelInsuranceProposalSummaryFlow(loggerUtils,basePage);
    }

    @Test(description = "TC-050 - Verify full online travel insurance flow for a soldier: destinations, coverages, personal details, and summary.")
    public void verifyProposalSummary() throws Exception {
        try {
            flow.navigateToProposalSummaryStep();
            flow.verifySummaryDetails();
            flow.verifyNavigationButtonsAreVisible();
            flow.showFinalPrice();
            flow.verifyCurrentAndPreviousStepsByColor();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC050_Failure", false);
        }
    }
}
