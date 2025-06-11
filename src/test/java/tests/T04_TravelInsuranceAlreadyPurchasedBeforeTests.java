package tests;

import flows.T04_TravelInsuranceAlreadyPurchasedBeforeFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T04_TravelInsuranceAlreadyPurchasedBeforeTests extends Base {

    private T04_TravelInsuranceAlreadyPurchasedBeforeFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T04_TravelInsuranceAlreadyPurchasedBeforeFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-041 - UI - Verify quote suggestion flow with previous insurance selected")
    public void verifyAlreadyPurchasedBefore() throws Exception {
        try {
            flow.navigateToAlreadyPurchasedBeforeStep();
            flow.verifyAlreadyPurchasedInsuranceDetails();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC041_Failure", false);
            throw e;
        }
    }
}
