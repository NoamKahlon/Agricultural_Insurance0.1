package tests;

import flows.T011_TravelInsuranceHealthDeclarationFlow;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T011_TravelInsuranceHealthDeclarationTests extends Base{

    private T011_TravelInsuranceHealthDeclarationFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T011_TravelInsuranceHealthDeclarationFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-049 - Verify online health insurance flow with coverage and health declaration")
    public void verifyOnlineHealthInsurance() throws Exception {
       try {
           flow.navigateToHealthDeclarationStep();
           flow.verifyNavigationButtonsAreVisible();
           flow.showFinalPrice();
           flow.verifyCurrentAndPreviousStepsByColor();
       }
       catch (Exception e) {
           loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC049_Failure", false);
       }
    }
}
