package tests;

import flows.T08_TravelInsurancePassengersDetailsFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class T08_TravelInsurancePassengersDetailsTests extends Base {

    private T08_TravelInsurancePassengersDetailsFlow flow;

    @BeforeMethod
    public void setup(){
        flow = new T08_TravelInsurancePassengersDetailsFlow(basePage,loggerUtils);
    }

    @Test(description = "TC-045 - Verify Passenger Details UI and Flow")
    public void verifyPassengersDetails() throws Exception {
       try {
           flow.navigateToPassengersDetailsStep();
           flow.verifyPassengerDetailsFieldsAreVisible();
           flow.verifyCurrentAndPreviousStepsByColor();
       }catch (Exception e){
           loggerUtils.log("❌ Test failed: " + e.getMessage(), "TC-045_Failure", false);
       }

    }
}
