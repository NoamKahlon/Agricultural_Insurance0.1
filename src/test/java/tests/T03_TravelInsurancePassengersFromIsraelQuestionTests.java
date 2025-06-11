package tests;

import flows.T03_TravelInsurancePassengersFromIsraelQuestionFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T03_TravelInsurancePassengersFromIsraelQuestionTests extends Base {

    private T03_TravelInsurancePassengersFromIsraelQuestionFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T03_TravelInsurancePassengersFromIsraelQuestionFlow(basePage,loggerUtils);
    }


    @Test(description = "TC-040 - Verify passengers from Israel question appears")
    public void verifyPassengersFromIsraelQuestion() throws Exception {
        try {
            flow.navigateToPassengersFromIsraelQuestionStep();
            flow.verifyPassengersFromIsraelQuestion();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC040_Failure", false);
        }
    }
}
