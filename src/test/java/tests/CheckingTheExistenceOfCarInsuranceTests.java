package tests;

import flows.CheckingTheExistenceOfCarInsuranceFlow;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckingTheExistenceOfCarInsuranceTests extends Base {

    private CheckingTheExistenceOfCarInsuranceFlow flow;

    @BeforeMethod
    public void setUp() throws Exception {
        this.flow = new CheckingTheExistenceOfCarInsuranceFlow(basePage,loggerUtils);
        flow.navigateToCheckingTheExistenceOfCarInsurance();
    }

    @Test(description = "TC-054 - Verify error message is shown for uninsured vehicle")
    public void verifyInvalidCarInsurance() {
        try {
            flow.searchCarNumberAndDate(false);
            flow.verifyInsuranceNotFoundErrorMessage();
        } catch (Exception e) {
            loggerUtils.log("❌ Test TC-054 failed: " + e.getMessage(), "UninsuredVehicleCheck", false);
        }
    }

    @Test(description = "TC-055 - Verify valid car insurance exists")
    public void verifyValidCarInsurance() throws InterruptedException {
        try {
            flow.searchCarNumberAndDate(true);
            flow.verifyInsuranceFoundMessage();
        } catch (Exception e) {
            loggerUtils.log("❌ Test TC-055 failed: " + e.getMessage(), "InsuranceCheck", false);
            throw e;
        }
    }
}
