package tests;

import flows.T02_TravelInsurancePurchasedBeforeQuestionFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T02_TravelInsurancePurchasedBeforeQuestionTests extends Base {

    private T02_TravelInsurancePurchasedBeforeQuestionFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T02_TravelInsurancePurchasedBeforeQuestionFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-039 - Verify previous insurance question appears")
    public void previousInsuranceQuestion() throws Exception {
        try {
            flow.navigateToPurchasedInsuranceBeforeQuestionStep();
            flow.verifyInsurancePurchasedQuestion();
        } catch (Exception e) {
            loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC-039 Failed", false);
            throw e;
        }
    }
}
