package tests;

import flows.T01_TravelInsuranceIntroFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class T01_TravelInsuranceIntroTests extends Base {

    private T01_TravelInsuranceIntroFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T01_TravelInsuranceIntroFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-038 - Verify landing on online travel insurance flow")
    @Description("Open insurance flow, verify title and main intro block")
    public void introFlow() throws Exception {
        try {
            flow.navigateToIntroPage();
            flow.verifyTitle();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC038_Failure", false);
            throw e;
        }
    }
}
