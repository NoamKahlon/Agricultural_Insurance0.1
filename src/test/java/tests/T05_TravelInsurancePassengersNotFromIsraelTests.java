package tests;

import flows.T05_TravelInsurancePassengersNotFromIsraelFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T05_TravelInsurancePassengersNotFromIsraelTests extends Base {

    private T05_TravelInsurancePassengersNotFromIsraelFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T05_TravelInsurancePassengersNotFromIsraelFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-042 - UI - Block flow when not all passengers depart from Israel")
    public void passengersNotFromIsrael() throws Exception {
        try {
            flow.navigateToPassengersFromIsraelQuestionStep();
            flow.clickPassengersFromIsraelNoOption();
            flow.isStopProcessErrorDisplayed();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC042_Failure", false);
        }
    }
}
