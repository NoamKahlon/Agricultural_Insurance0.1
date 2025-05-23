package flows;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.CheckingTheExistenceOfCarInsurancePage;
import pages.SearchDocumentsAndFormsPage;
import utils.LoggerUtils;
import org.testng.asserts.SoftAssert;

public class CheckingTheExistenceOfCarInsuranceFlow {

    private final CheckingTheExistenceOfCarInsurancePage checkingTheExistenceOfCarInsurancePage;
    private final BasePage basePage;
    private final LoggerUtils loggerUtils;
//    private final SoftAssert softAssert;




    public CheckingTheExistenceOfCarInsuranceFlow(CheckingTheExistenceOfCarInsurancePage page,BasePage basePage, LoggerUtils logger) {
        this.checkingTheExistenceOfCarInsurancePage = page;
        this.basePage = basePage;
        this.loggerUtils = logger;
//        this.softAssert = softAssert;
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
