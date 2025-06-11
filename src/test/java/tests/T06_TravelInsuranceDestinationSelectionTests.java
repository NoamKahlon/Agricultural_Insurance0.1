package tests;

import flows.T06_TravelInsuranceDestinationSelectionFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T06_TravelInsuranceDestinationSelectionTests extends Base{

    private T06_TravelInsuranceDestinationSelectionFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T06_TravelInsuranceDestinationSelectionFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-043 - Verify online travel insurance quote flow is accessible and displayed correctly")
    public void verifyDestinationSelection() {
        try {
            flow.navigateToDestinationSelectionStep();
            flow.verifyDestinationSelectionStep();
            flow.verifyCurrentAndPreviousStepsByColor();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false);
        }
    }
}
