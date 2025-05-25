package flows;

import pages.CheckingTheExistenceOfCarInsurancePage;
import utils.LoggerUtils;

public class CheckingTheExistenceOfCarInsuranceFlow {

    private final CheckingTheExistenceOfCarInsurancePage checkingTheExistenceOfCarInsurancePage;
    private final LoggerUtils loggerUtils;


    public CheckingTheExistenceOfCarInsuranceFlow(CheckingTheExistenceOfCarInsurancePage page, LoggerUtils logger) {
        this.checkingTheExistenceOfCarInsurancePage = page;
        this.loggerUtils = logger;
    }

    public void nevigateToCheckingTheExistenceOfCarInsurance() throws Exception {

        checkingTheExistenceOfCarInsurancePage.ScrollToCheckingTheExistenceOfCarInsurance();
        loggerUtils.log("✅ Scroll to insurance check section", "InsuranceSection", true, false);

        checkingTheExistenceOfCarInsurancePage.ClickCheckingTheExistenceOfCarInsurance();
        loggerUtils.log("✅ Navigated to insurance check section", "InsuranceSection", true, false);

    }

    public void searchCarNumberAndDate(boolean validCarNubmer) {

        if(validCarNubmer){
            checkingTheExistenceOfCarInsurancePage.enterLicenseNumber();
            loggerUtils.log("✅ Entered license number", "LicenseInput", true, false);
        }else{
            checkingTheExistenceOfCarInsurancePage.enterInvalidLicenseNumber(); // מספר רכב שאין לו ביטוח
            loggerUtils.log("✅ Entered uninsured license number", "LicenseInput", true, false);
        }

        checkingTheExistenceOfCarInsurancePage.enterDate();
        loggerUtils.log("✅ Entered Date", "DateInput", true, false);

        checkingTheExistenceOfCarInsurancePage.clickSearch();
        loggerUtils.log("✅ Clicked search", "SearchClick", true, false);

    }
}
