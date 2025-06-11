package tests;

import flows.T07_TravelInsuranceDateSelectionFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T07_TravelInsuranceDateSelectionTests extends Base{

    private T07_TravelInsuranceDateSelectionFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T07_TravelInsuranceDateSelectionFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-044 - Verify date selection step in travel insurance quote flow")
    public void verifyDateSelection() {
        try {
            flow.navigateToDateSelectionStep();
            flow.verifyDateSelection();
            flow.verifyCurrentAndPreviousStepsByColor();
            } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false);
        }
    }
}
